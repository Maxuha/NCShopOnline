import React, {Component} from "react";

import {Card, Table, Image, ButtonGroup, Button} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList, faEdit, faTrash} from "@fortawesome/free-solid-svg-icons";
import {Link} from 'react-router-dom';
import axios from "axios";
import NCSOToast from "../NCSOToast";

export default class ProductListAdmin extends Component {

    constructor(props) {
        super(props);
        this.state = {
            products: [], arr: new Map()
        };
    }

    componentDidMount() {
        axios.get("http://localhost:7001/api/product/get/all")
            .then(response => response.data)
            .then((data) => {
                this.setState({products: data})
                this.state.products.map((product) => axios.get("http://localhost:7001/api/image_to_product/get?product_id=" + product.id)
                    .then(response => response.data)
                    .then((data) => {
                        this.setState({arr: this.state.arr.set(product.id, data)});
                    }));
            });
    }

    deleteProduct = (product) => {
        axios.delete("http://localhost:7001/api/product/remove", {data: product})
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}), 3000);
                    this.setState({
                        products: this.state.products.filter(temp => temp.id !== product.id)
                    });
                }
            })
    };

    render() {
        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <NCSOToast show = {this.state.show} message = {"Товар удален."} type = {"danger"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><FontAwesomeIcon icon={faList}/> Все товары</Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                            <tr>
                                <th>Название</th>
                                <th>Описание</th>
                                <th>Цена</th>
                                <th>Скидка</th>
                                <th>Категория</th>
                                <th>Поставщик</th>
                                <th>Действия</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.products.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">{this.state.products.length} Список пуст</td>
                                    </tr> :
                                    this.state.products.map((product) => (
                                        <tr key={product.id}>
                                            <td>
                                                <Image width="36" height="36" roundedCircle src={this.state.arr.get(product.id) ? this.state.arr.get(product.id)[0].image.image : ''}/> {product.title}
                                            </td>
                                            <td>
                                                {product.description}
                                            </td>
                                            <td>
                                                {product.price} <strong style={{fontSize: "18px"}}>грн</strong>
                                            </td>
                                            <td>
                                                {product.discount}
                                            </td>
                                            <td>
                                                {product.category.title}
                                            </td>
                                            <td>
                                                {product.shipper.companyName}
                                            </td>
                                            <td>
                                                <ButtonGroup>
                                                    <Link to={"edit/" + product.id} className="btn btn-sm btn-outline-primary"><FontAwesomeIcon icon={faEdit}/></Link>{' '}
                                                    <Button size="sm" variant="outline-danger" onClick={this.deleteProduct.bind(this, product)}><FontAwesomeIcon icon={faTrash}/>
                                                    </Button>
                                                </ButtonGroup>
                                            </td>
                                        </tr>
                                    ))
                            }
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
            </div>
        )
    }
}