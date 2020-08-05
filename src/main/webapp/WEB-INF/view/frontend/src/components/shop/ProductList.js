import React, {Component} from "react";
import axios from 'axios';
import {CardColumns} from "react-bootstrap";
import Product from "./Product";
import Button from "react-bootstrap/Button";
import ProductPage from "./ProductPage";

export default class ProductList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [], arr: new Map(), isPage: false, selectedProductId: -1
        };
    }

    componentDidMount() {
        axios.get("http://localhost:7001/api/product/get?categoryId=" + this.props.match.params.id)
            .then(response => response.data)
            .then((data) => {
                this.setState({products: data})
                this.state.products.map(product => axios.get("http://localhost:7001/api/image_to_product/get?product_id=" + product.id)
                    .then(response => response.data)
                    .then((data) => {
                        this.setState({arr: this.state.arr.set(product.id, data)});
                        //Переделать
                        this.props.match.params.productId ? this.selectProduct(this.props.match.params.categoryId, this.props.match.params.productId) : this.selectProduct.bind(this);
                    }));
            });
    }

    selectProduct = (categoryId, productId) => {
        this.setState({
            isPage: true,
            selectedProductId: productId
        });
        return this.props.history.push("/category/" + categoryId + "/product/" + productId);
    }

    render() {
        return (
            !this.state.isPage ? (<CardColumns>
                {this.state.products.map(product => <Button key={product.id}
                                                            onClick={this.selectProduct.bind(this, this.props.match.params.id, product.id)}><Product
                                                            productWithImages={this.state.arr.get(product.id)}/></Button>)}
            </CardColumns>) : <ProductPage productWithImages={this.state.arr.get(this.state.selectedProductId)}/>
        )
    }
}