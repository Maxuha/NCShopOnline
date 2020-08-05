import React, {Component} from "react";
import {Button, Form} from "react-bootstrap";

export default class LogIn extends Component {
    render() {
        return (
            <div>
                <Form action={"http://localhost:7001/j_spring_security_check"} method="post">
                    <Form.Group controlId="formBasicLogin">
                        <Form.Label style={{"color":"white"}}>Имя пользователя</Form.Label>
                        <Form.Control type="text" placeholder="Введите имя пользователя" name="username" onChange={this.changeUser}/>
                    </Form.Group>
                    <Form.Group controlId="formBasicPassword">
                        <Form.Label style={{"color":"white"}}>Пароль</Form.Label>
                        <Form.Control type="password" placeholder="Пароль" name="password" onChange={this.changeUser}/>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Войти
                    </Button>
                </Form>
            </div>
        )
    }
}