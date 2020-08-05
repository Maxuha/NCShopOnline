import React, {Component} from "react";
import {Button, Modal, Container, Row, Col} from "react-bootstrap";
import axios from 'axios';

export default class ShoppingCart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orderWithProducts: [], isShow: false
        }
    }

    getUser() {
        axios.get("http://localhost:7001/getUser")
            .then(response => response.data)
            .then((data) => {
                this.getOrder(data)
            });
    }

    getOrder(user) {
        axios.get("http://localhost:7001/api/order/get/processed?customerId=" + user.id)
            .then(response => response.data)
            .then((data) => {
                if (data) {
                    this.getOrderWithProduct(data)
                } else {
                    this.state.orderWithProducts = [];
                }
            });
    }

    getOrderWithProduct(order) {
        axios.get("http://localhost:7001/api/product_has_order/get/by_order/" + order.id)
            .then(response => response.data)
            .then((data) => this.setState({orderWithProducts: data}));
    }

    show(isShow) {
        this.getUser();
        this.setState({isShow: isShow});
    }

    getPriceWithDiscount(price, discount) {
        return price - price * discount;
    }

    changeCount = (target, event) => {
        this.state.orderWithProducts.map(orderWithProduct =>
            orderWithProduct === target ? orderWithProduct.count = event.target.value : orderWithProduct.count = 1
        );
    }

    ordering = () => {
        this.state.orderWithProducts.map((orderWithProduct) => {
            const order = orderWithProduct.order;
            order.processed = true;
            axios.put("http://localhost:7001/api/order/update", order)
                .then();
            axios.put("http://localhost:7001/api/product_has_order/update", orderWithProduct)
                .then();
            this.show(false);
            return order;
        });
    }

    render() {
        return (
            <div>
                <Button variant="primary" onClick={this.show.bind(this, true)}>Корзина</Button>
                <div>
                    <Modal
                        show={this.state.isShow}
                        size="lg"
                        aria-labelledby="contained-modal-title-vcenter"
                        centered
                    >
                        <Modal.Header closeButton>
                            <Modal.Title id="contained-modal-title-vcenter">
                                Корзина
                            </Modal.Title>
                        </Modal.Header>
                        <Modal.Body className="show-grid">
                            <Container>
                                <Row>
                                    <Col xs={6} md={4}>
                                        <h3>Товар</h3>
                                    </Col>
                                    <Col xs={6} md={4}>
                                        <h3>Цена</h3>
                                    </Col>
                                    <Col xs={6} md={4}>
                                        <h3>Количество</h3>
                                    </Col>
                                </Row>
                                {this.state.orderWithProducts.map((orderWithProduct) => <Row key={orderWithProduct.product.id}>
                                    <Col xs={6} md={4}>
                                        <h3>{orderWithProduct.product.title}</h3>
                                    </Col>
                                    <Col xs={6} md={4}>
                                        <h3>{this.getPriceWithDiscount(orderWithProduct.product.price, orderWithProduct.product.discount)} <strong style={{fontSize: "28pt"}}>грн</strong></h3>
                                    </Col>
                                    <Col xs={6} md={4}>
                                        <input style={{width: "50px"}} type="text" placeholder={orderWithProduct.count} onChange={this.changeCount.bind(this, orderWithProduct)}/>
                                    </Col>
                                </Row>)}
                            </Container>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button onClick={this.ordering}>Оформить заказ</Button>
                            <Button onClick={this.show.bind(this, false)}>Закрыть</Button>
                        </Modal.Footer>
                    </Modal>
                </div>
            </div>
        );
    }
}