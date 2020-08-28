import React, {Component} from "react";

import './Style.css';
import {Card, Table, Image, ButtonGroup, Button, InputGroup, FormControl} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faList,
    faEdit,
    faTrash,
    faFastBackward,
    faStepBackward,
    faStepForward,
    faFastForward,
    faSearch,
    faTimes
} from "@fortawesome/free-solid-svg-icons";
import {Link} from 'react-router-dom';
import axios from "axios";
import NCSOToast from "../NCSOToast";

export default class ProductListAdmin extends Component {

    constructor(props) {
        super(props);
        this.state = {
            products: [], arr: new Map(), currentPage: 1, productsPerPage: 5, sortToggle: true, search: ''
        };
    }

    sortData = () => {
        this.setState(state => ({
            sortToggle: !state.sortToggle
        }));
        this.findAllProducts();
    }

    componentDidMount() {
        this.findAllProducts();
    }

    findAllProducts() {
        let sortDir = this.state.sortToggle ? "asc" : "desc";
        axios.get(`http://localhost:7001/api/product/get/all?sortBy=price&sortDir=${sortDir}`)
            .then(response => response.data)
            .then((data) => {
                this.setState({products: data})
                this.state.products.map((product) => axios.get(`http://localhost:7001/api/image_to_product/get?product_id=${product.id}`)
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

    changePage = event => {
        this.setState({
            [event.target.name]: parseInt(event.target.value)
        })
    };

    firstPage = () => {
        if (this.state.currentPage > 1) {
            this.setState({
                currentPage: 1
            });
        }
    };

    prevPage = () => {
        if (this.state.currentPage > 1) {
            this.setState({
                currentPage: this.state.currentPage - 1
            });
        }
    };

    lastPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.products.length / this.state.productsPerPage)) {
            this.setState({
                currentPage: Math.ceil(this.state.products.length / this.state.productsPerPage)
            });
        }
    };

    nextPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.products.length / this.state.productsPerPage)) {
            this.setState({
                currentPage: this.state.currentPage + 1
            });
        }
    };

    searchChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    cancelSearch = () => {
        this.setState({"search": ''});
        this.findAllProducts();
    };

    searchData = () => {
        axios.get(`http://localhost:7001/api/product/get/search?searchText=${this.state.search}`)
            .then(response => response.data)
            .then((data) => {
                this.setState({products: data})
                this.state.products.map((product) => axios.get(`http://localhost:7001/api/image_to_product/get?product_id=${product.id}`)
                    .then(response => response.data)
                    .then((data) => {
                        this.setState({arr: this.state.arr.set(product.id, data)});
                    }));
            });
    };

    render() {
        const {products, currentPage, productsPerPage, search} = this.state;
        const lastIndex = currentPage * productsPerPage;
        const firstIndex = lastIndex - productsPerPage;
        const currentProducts = products.slice(firstIndex, lastIndex);
        const totalPages = products.length / productsPerPage;

        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <NCSOToast show = {this.state.show} message = {"Товар удален."} type = {"danger"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                        <div style={{"float": "left"}}>
                            <FontAwesomeIcon icon={faList}/> Все товары
                        </div>
                        <div style={{"float": "right"}}>
                            <InputGroup size="sm">
                                <FormControl placeholder="Поиск" name="search" value={search}
                                             className={"bg-dark text-white info-border"}
                                             onChange={this.searchChange}/>
                                <InputGroup.Append>
                                    <Button size="sm" variant="outline-info" type="button" onClick={this.searchData}>
                                        <FontAwesomeIcon icon={faSearch}/>
                                    </Button>
                                    <Button size="sm" variant="outline-danger" type="button" onClick={this.cancelSearch}>
                                        <FontAwesomeIcon icon={faTimes}/>
                                    </Button>
                                </InputGroup.Append>
                            </InputGroup>
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                            <tr>
                                <th>Название</th>
                                <th>Описание</th>
                                <th onClick={this.sortData}>Цена <div className={this.state.sortToggle ? "arrow arrow-down" : "arrow arrow-up"}> </div></th>
                                <th>Скидка</th>
                                <th>Категория</th>
                                <th>Поставщик</th>
                                <th>Действия</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                products.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">{products.length} Список пуст</td>
                                    </tr> :
                                    currentProducts.map((product) => (
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
                    <Card.Footer>
                        <div style={{"float": "left"}}>
                            ShowingPage {currentPage} of {totalPages}
                        </div>
                        <div style={{"float": "right"}}>
                            <InputGroup size="sm">
                                <InputGroup.Prepend>
                                    <Button type="button" variant="outline-info" disabled={currentPage === 1}
                                            onClick={this.firstPage}>
                                        <FontAwesomeIcon icon={faFastBackward} /> Первая
                                    </Button>
                                    <Button type="button" variant="outline-info" disabled={currentPage === 1}
                                            onClick={this.prevPage}>
                                        <FontAwesomeIcon icon={faStepBackward} /> Предыдущая
                                    </Button>
                                </InputGroup.Prepend>
                                <FormControl className={"page-num bg-dark"} name="currentPage" value={currentPage}
                                             onChange={this.changePage}/>
                                <InputGroup.Append>
                                    <Button type="button" variant="outline-info" disabled={currentPage === totalPages}
                                            onClick={this.nextPage}>
                                        <FontAwesomeIcon icon={faStepForward} /> Следущая
                                    </Button>
                                    <Button type="button" variant="outline-info" disabled={currentPage === totalPages}
                                            onClick={this.lastPage}>
                                        <FontAwesomeIcon icon={faFastForward} /> Последняя
                                    </Button>
                                </InputGroup.Append>
                            </InputGroup>
                        </div>
                    </Card.Footer>
                </Card>
            </div>
        )
    }
}