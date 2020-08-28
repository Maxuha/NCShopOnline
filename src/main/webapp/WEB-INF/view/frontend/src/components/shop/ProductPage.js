import React, {Component} from 'react';
import {Image, Carousel, Button} from "react-bootstrap";
import axios from "axios";
import dateformat from 'dateformat';

export default class ProductPage extends Component {
    getPriceWithDiscount(price, discount) {
        return price - price * discount;
    }

    onBuy = event => {
        axios.get("http://localhost:7001/getUser")
            .then(response => response.data)
            .then((data) => {
                this.setState({user: data})
                this.executeOrder(this.state.user)
            });
    }

    executeOrder = user => {
        axios.get(`http://localhost:7001/api/order/get/processed?customerId=${user.id}`)
            .then(response => response.data)
            .then((data) => {
                data ? this.submitOrderWithProduct(data) : this.submitNewOrder(user)
            });
    }

    submitNewOrder(user) {
        const now = Date.now();
        const order = {
            date: dateformat(now, 'yyyy-mm-dd\'T\'HH:MM:ss'),
            isProcessed: false,
            customer: {
                user: user
            }
        }
        axios.post("http://localhost:7001/api/order/create", order)
            .then(response => response.data)
            .then(data => {console.log(data); this.submitOrderWithProduct(data)})
    }

    submitOrderWithProduct(order) {
        const orderWithProduct = {
            product: this.props.productWithImages[0].product,
            order: order,
            count: 1
        }
        axios.post("http://localhost:7001/api/product_has_order/create", orderWithProduct)
            .then(response => response.data)
            .then(data => console.log(data))
    }

    render() {
        const MainImage = {
            width: "350px",
            height: "350px",
            paddingBottom: "10px"
        }
        const SecondImages = {
            width: "250px",
            height: "250px",
        }
        const Description = {
            float: "right",
            minWidth: "500px",
            minHeight: "500px"
        }
        const Title = {
            width: "400px",
            marginBottom: "5px"
        }
        const Price = {
            paddingRight: "50px"
        }
        const PriceBlock = {
            marginTop: "200px",
            display: "block",
            float: "right"
        }
        const Shipper = {
            width: "300px",
            marginBottom: "5px"
        }
        const styles = {
            MainImage,
            SecondImages,
            Description,
            Title,
            Price,
            PriceBlock,
            Shipper
        }
        return (
            <div>
                <div style={styles.Title} className="border border-dark bg-dark text-white">
                    <h3>{this.props.productWithImages[0].product.title}</h3>
                </div>
                <div style={styles.Description} className="border border-dark bg-dark text-white">
                    <p>{this.props.productWithImages[0].product.description}</p>
                </div>
                <div style={styles.Shipper} className="border border-dark bg-dark text-white">
                    <p><strong>Поставщик: </strong>{this.props.productWithImages[0].product.shipper.companyName}</p>
                </div>
                <div style={styles.PriceBlock} className="text-white">
                    {this.props.productWithImages[0].product.discount === 0 ? "" : <p><strike>{this.props.productWithImages[0].product.price.toFixed(2)}</strike></p>}
                    <h4 style={styles.Price}>{this.getPriceWithDiscount(this.props.productWithImages[0].product.price, this.props.productWithImages[0].product.discount).toFixed(2)} <strong style={{fontSize: "30pt"}}>грн</strong></h4>
                    <Button size="lg" variant="danger" onClick={this.onBuy.bind(this)}>Купить</Button>
                </div>
                <Image style={styles.MainImage} src={this.props.productWithImages[0].image.image} rounded />
                <Carousel style={styles.SecondImages}>
                    {this.props.productWithImages.map(productWithImage =>
                        <Carousel.Item key={productWithImage.image.id}>
                            <img
                                style={styles.SecondImages}
                                src={productWithImage.image.image}
                                alt=""
                            />
                        </Carousel.Item>
                    )}
                </Carousel>
            </div>
        );
    }
}