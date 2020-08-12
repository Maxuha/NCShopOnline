import React, {Component} from "react";

import {Navbar, Nav} from 'react-bootstrap'
import {Link} from "react-router-dom";
import ShoppingCart from "./shop/ShoppingCart";
import axios from "axios";

export default class NavigationBar extends Component {
    constructor() {
        super();
        this.state = {
            isLogin: false,
            isCustomer: false
        }
    }

    componentDidMount() {
        this.getUser();
    }

    getUser() {
        axios.get("http://localhost:7001/getUser")
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    isLogin: (!!data)
                })
                this.getCustomerOrShipper(data);
            });
    }

    getCustomerOrShipper(user) {
        axios.get("http://localhost:7001/api/customer/get/" + user.id)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    isCustomer: (!!data)
                })
            });
    }

    render() {
        const marginRight = {
            marginRight: "5px"
        }
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <p>NC Shop Online</p>
                </Link>
                <Nav className="mr-auto">
                    <Link to={"/category/0"} className="nav-link">Категории</Link>
                </Nav>
                {this.state.isLogin ? <a href="/logout" style={marginRight}>Выйти</a> : ''}
                {this.state.isLogin && !this.state.isCustomer ? <Link style={marginRight} variant={"primary"} to={"/admin"}>Админ панель</Link> : ''}
                {this.state.isCustomer ? <ShoppingCart /> : ''}
            </Navbar>
        );
    }
}