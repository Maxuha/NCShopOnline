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
        this.setState((state) => {
            return {sortToggle: !state.sortToggle}
        });
        this.findAllProducts(this.state.currentPage);
    }

    componentDidMount() {
        this.findAllProducts(this.state.currentPage);
    }

    findAllProducts(currentPage) {
        let sortDir = this.state.sortToggle ? "asc" : "desc";
        currentPage -= 1;
        axios.get(`http://localhost:7001/api/product/get/all?page=${currentPage}&size=${this.state.productsPerPage}&sortBy=price&sortDir=${sortDir}`)
            .then(response => response.data)
            .then((data) => {
                this.setState({products: data.products, totalPages: data.totalPages, totalElements: data.totalElements, currentPage: data.number + 1})
                this.state.products.map((product) => axios.get(`http://localhost:7001/api/image_to_product/get?product_id=${product.id}`)
                    .then(response => response.data)
                    .then((data) => {
                        this.setState({arr: this.state.arr.set(product.id, data)});
                    }));
            });
    }

    deleteProduct = (product) => {
        axios.delete(`http://localhost:7001/api/product/remove/${product.id}`)
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
        let targetPage = parseInt(event.target.value)
        this.findAllProducts(targetPage);
        this.setState({
            [event.target.name]: targetPage
        })
    };

    firstPage = () => {
        let firstPage = 1;
        if (this.state.currentPage > firstPage) {
            this.findAllProducts(firstPage);
        }
    };

    prevPage = () => {
        let prevPage = 1;
        if (this.state.currentPage > prevPage) {
            this.findAllProducts(this.state.currentPage - prevPage);
        }
    };

    lastPage = () => {
        let condition = Math.ceil(this.state.totalElements / this.state.productsPerPage);
        if (this.state.currentPage < condition) {
            this.findAllProducts(condition);
        }
    };

    nextPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.totalElements / this.state.productsPerPage)) {
            this.findAllProducts(this.state.currentPage + 1);
        }
    };

    searchChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    cancelSearch = () => {
        this.setState({"search": ''});
        this.findAllProducts(this.state.currentPage);
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
        const {products, currentPage, totalPages, search} = this.state;
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
                                    products.map((product) => (
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