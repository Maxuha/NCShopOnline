import React, {Component} from "react";
import {Form, Button} from 'react-bootstrap'
import axios from "axios";

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            type: '', userName: '', info: '', password: ''
        }
    };

    componentDidMount() {
        const type = this.props.match.params.type;
        this.setState({type});
    };

    changeUser = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    sendRegister = event => {
        event.preventDefault();
        const user = {
            userName: this.state.userName,
            password: this.state.password
        }
        const headers = {
            "info": this.state.info
        }
        axios.post("http://localhost:7001/register/" + this.state.type, user, {headers: headers}).then(response => console.log(response));
    };

    render() {
        return(
            <div>
                <Form onSubmit={this.sendRegister} method="post">
                    <Form.Group controlId="formBasicLogin">
                        <Form.Label style={{"color":"white"}}>Имя пользователя</Form.Label>
                        <Form.Control type="text" placeholder="Введите имя пользователя" name="userName" onChange={this.changeUser}/>
                    </Form.Group>
                    <Form.Group controlId="formBasicInfo">
                        <Form.Label style={{"color":"white"}}>{this.state.type === "customer" ? "ФИО" : "Компания"}</Form.Label>
                        <Form.Control type="text" placeholder={this.state.type === "customer" ? "Введите полное имя" : "Введение название Вашей компании"} name="info" onChange={this.changeUser}/>
                    </Form.Group>
                    <Form.Group controlId="formBasicPassword">
                        <Form.Label style={{"color":"white"}}>Пароль</Form.Label>
                        <Form.Control type="password" placeholder="Пароль" name="password" onChange={this.changeUser}/>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Зарегистрироваться
                    </Button>
                </Form>
            </div>
        )
    }
}