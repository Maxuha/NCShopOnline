import React, {Component} from "react";
import Category from "./Category";
import {CardColumns, Button} from "react-bootstrap";
import axios from 'axios';

export default class CategoryPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: []
        };
    }

    componentDidMount() {
        this.loadData(this.props.match.params.id);
    }

    loadData(id) {
        axios.get(`http://localhost:7001/api/category/get?parentId=${id}`)
            .then(response => response.status === 200 ? this.setState({categories: response.data}) : this.loadData(id));
    }

    showProduct = (id) => {
        return this.props.history.push(`/category/${id}/products`);
    }

    selectCategory = (id) => {
        this.loadData(id);
        return this.props.history.push(`/category/${id}`);
    };

    render() {
        return (
            <CardColumns>
                {this.state.categories.map((category) => (<Button key={category.id} onClick={this.selectCategory.bind(this, category.id)}><Category category={category}/></Button>))}
            </CardColumns>
        )
    }
}