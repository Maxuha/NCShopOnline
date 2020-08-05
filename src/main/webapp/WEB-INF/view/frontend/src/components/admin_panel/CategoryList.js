import React, {Component} from "react";

import {Card, Table, Image, ButtonGroup, Button} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList, faEdit, faTrash} from "@fortawesome/free-solid-svg-icons";
import {Link} from 'react-router-dom';
import axios from "axios";
import NCSOToast from "../NCSOToast";

export default class CategoryList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: []
        };
    }

    componentDidMount() {
        axios.get("http://localhost:7001/api/category/get/all")
            .then(response => response.data)
            .then((data) => {
                this.setState({categories: data})
            });
    }

    deleteCategory = (category) => {
        axios.delete("http://localhost:7001/api/category/remove", {data: category})
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}), 3000);
                    this.setState({
                       categories: this.state.categories.filter(temp => temp.id !== category.id)
                    });
                }
            })
    };

    render() {
        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <NCSOToast show = {this.state.show} message = {"Категория удаленна."} type = {"danger"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><FontAwesomeIcon icon={faList}/> Все категории</Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                            <tr>
                                <th>Название</th>
                                <th>Родительская категория</th>
                                <th>Действия</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.categories.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">{this.state.categories.length} Список пуст</td>
                                    </tr> :
                                    this.state.categories.map((category) => (
                                        <tr key={category.id}>
                                            <td>
                                                <Image src={category.image !== null ? category.image.image : ""} roundedCircle width="25" height="25"/> {category.title}
                                            </td>
                                            <td>
                                                {category.parent !== null ? category.parent.title : 'Нету'}
                                            </td>
                                            <td>
                                                <ButtonGroup>
                                                    <Link to={"edit/" + category.id} className="btn btn-sm btn-outline-primary"><FontAwesomeIcon icon={faEdit}/></Link>{' '}
                                                    <Button size="sm" variant="outline-danger" onClick={this.deleteCategory.bind(this, category)}><FontAwesomeIcon icon={faTrash}/>
                                                    </Button>
                                                </ButtonGroup>
                                            </td>
                                        </tr>
                                    ))
                            }
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
            </div>
        )
    }
}