import React, {Component} from "react";

import {Navbar, Nav} from 'react-bootstrap'
import {Link} from "react-router-dom";
import ShoppingCart from "./shop/ShoppingCart";
import axios from "axios";

export default class NavigationBar extends Component {
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
                <a href="/logout" style={marginRight}>Выйти</a>
                <Link style={marginRight} variant={"primary"} to={"/admin"}>Админ панель</Link>
                <ShoppingCart />
            </Navbar>
        );
    }
}