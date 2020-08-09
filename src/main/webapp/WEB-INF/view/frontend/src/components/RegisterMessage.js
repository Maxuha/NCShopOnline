import React, {Component} from 'react';
import {Modal, Button} from "react-bootstrap";

export default class RegisterMessage extends Component {
    constructor(props) {
        super(props);
    }

    OnLogin = () => {
        return this.props.history.push("/login/" + this.props.type + "?username=" + this.props.userName);
    }

    render() {
        const status = this.props.status;
        console.log(status)
        return (
            <Modal
                show={!!status}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        {status === 200 ? 'Успех' : 'Не удача'}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>
                        {status === 200 ? 'Вы успешно зарегистрированы ' : 'Данный пользователь уже зарегистрирован'}
                    </p>
                </Modal.Body>
                <Modal.Footer>
                    {status === 200 ? <Button onClick={this.OnLogin.bind(this)}>Войти</Button> : <Button onClick={this.props.onHide}>Закрыть</Button>}
                </Modal.Footer>
            </Modal>
        );
    }
}