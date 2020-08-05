import React, {Component} from "react";
import {Jumbotron, Button} from "react-bootstrap";
import LogInOrSignIn from "./LogInOrSignIn";
import axios from 'axios';

export default class Welcome extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showLogInOrSignIn: false,
            show: true
        };
    }

    componentDidMount() {
        this.isAuthentication();
    }

    onSelect = event => {
        this.setState({showLogInOrSignIn: !this.state.showLogInOrSignIn, type: event.target.value})
    }

    isAuthentication() {
        axios.get("http://localhost:7001/getUser")
            .then(response => response.data)
            .then(data => data ? this.setState({show: false}) : this.setState({show: true}))
    }

    render() {
        return (
            this.state.show === true ? (
                <div>
                    <LogInOrSignIn show={this.state.showLogInOrSignIn} type={this.state.type}/>
                    <Jumbotron className="bg-dark text-white">
                        <h1>Добро пожаловать! Зайдите как </h1>
                        <Button className="btn btn-lg" variant="primary" onClick={this.onSelect} value="customer">Покупатель</Button>
                        <h3>или</h3>
                        <Button className="btn btn-lg" variant="primary" onClick={this.onSelect} value="shipper">Продавец</Button>
                    </Jumbotron>
                </div>
            ) : (
                <div>{this.props.history.push("/category/0")}</div>
            )
        )
    }
}