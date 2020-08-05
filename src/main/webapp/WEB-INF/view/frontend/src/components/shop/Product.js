import React, {Component} from "react";
import {Card} from "react-bootstrap";

export default class Product extends Component {
    getPWI() {
        if (typeof this.props.productWithImages === 'undefined') {
            return null;
        }
        return this.props.productWithImages;
    }

    render() {
        const style = {
            width: '11rem',
            cursor: "pointer"
        }
        return (
            <Card style={style} className="text-center bg-dark text-white">
                <Card.Img style={{height: '11rem'}} variant="top" src={this.getPWI() !== null ? this.getPWI()[0].image.image : ''}/>
                <Card.Body>
                    <Card.Title>{this.getPWI() !== null ? this.getPWI()[0].product.title : ''}</Card.Title>
                    <Card.Text>{this.getPWI() !== null ? this.getPWI()[0].product.price + ' грн' : ''}</Card.Text>
                </Card.Body>
            </Card>
        )
    }
}