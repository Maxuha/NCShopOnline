import React, {Component} from "react";
import {Modal} from "react-bootstrap";
import {Link} from "react-router-dom";

export default class LogInOrSignIn extends Component {
    render() {
        return (
            <Modal
                show={this.props.show}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered>
                <Modal.Body>
                    <h4>Вы зарегистрированы у нас?</h4>
                </Modal.Body>
                <Modal.Footer>
                    <Link to={"login/" + this.props.type} className="btn btn-sm btn-outline-primary" variant="primary">Да</Link>
                    <Link to={"register/" + this.props.type} className="btn btn-sm btn-outline-primary" variant="primary">Нет</Link>
                </Modal.Footer>
            </Modal>
        )
    }
}