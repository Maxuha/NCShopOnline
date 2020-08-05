import React, {Component} from "react";
import {Card} from "react-bootstrap";

export default class Category extends Component {
    render() {
        const style = {
            width: '11rem',
            cursor: "pointer"
        }
        return (
            <Card style={style} className="text-center bg-dark text-white">
                <Card.Img style={{ height: '11rem' }} variant="top" src={this.props.category.image.image} />
                <Card.Body>
                    <Card.Title>{this.props.category.title}</Card.Title>
                </Card.Body>
            </Card>
        )
    }
}