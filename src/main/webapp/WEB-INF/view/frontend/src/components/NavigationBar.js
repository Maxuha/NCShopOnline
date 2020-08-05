import React, {Component} from "react";

import {Navbar, Nav} from 'react-bootstrap'
import {Link} from "react-router-dom";
import ShoppingCart from "./shop/ShoppingCart";

export default class NavigationBar extends Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <p>NC Shop Online</p>
                </Link>
                <Nav className="mr-auto">
                    <Link to={"/category/0"} className="nav-link">Категории</Link>
                </Nav>
                <Link style={{marginRight: "5px"}} variant={"primary"} to={"/admin"}>Админ панель</Link>
                <ShoppingCart />
            </Navbar>
        );
    }
}