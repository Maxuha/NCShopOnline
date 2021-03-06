import React, {Component} from "react";

import {Card, Table, Image, ButtonGroup, Button, InputGroup, FormControl} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList, faEdit, faTrash, faStepBackward, faFastBackward, faStepForward, faFastForward} from "@fortawesome/free-solid-svg-icons";
import {Link} from 'react-router-dom';
import axios from "axios";
import NCSOToast from "../NCSOToast";

export default class CategoryList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            currentPage: 1,
            categoriesPerPage: 5
        };
    }

    componentDidMount() {
        this.findAllCategory(this.state.currentPage);
    }

    findAllCategory(currentPage) {
        currentPage -= 1;
        axios.get(`http://localhost:7001/api/category/get/all/list?page=${currentPage}&size=${this.state.categoriesPerPage}`)
            .then(response => response.data)
            .then((data) => {
                this.setState({categories: data.categories, totalPages: data.totalPages, totalElements: data.totalElements, currentPage: data.number + 1})
            });
    }

    deleteCategory = (category) => {
        axios.delete(`http://localhost:7001/api/category/remove/${category.id}`)
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

    changePage = event => {
        let targetPage = parseInt(event.target.value)
        this.findAllCategory(targetPage);
        this.setState({
            [event.target.name]: targetPage
        })
    };

    firstPage = () => {
        let firstPage = 1;
        if (this.state.currentPage > firstPage) {
            this.findAllCategory(firstPage);
        }
    };

    prevPage = () => {
        let prevPage = 1;
        if (this.state.currentPage > prevPage) {
            this.findAllCategory(this.state.currentPage - prevPage);
        }
    };

    lastPage = () => {
        let condition = Math.ceil(this.state.totalElements / this.state.categoriesPerPage);
        if (this.state.currentPage < condition) {
            this.findAllCategory(condition);
        }
    };

    nextPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.totalElements / this.state.categoriesPerPage)) {
            this.findAllCategory(this.state.currentPage + 1);
        }
    };

    render() {
        const {categories, currentPage, totalPages} = this.state;

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
                                categories.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">{categories.length} Список пуст</td>
                                    </tr> :
                                    categories.map((category) => (
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
                    <Card.Footer>
                        <div style={{"float": "left"}}>
                            ShowingPage {currentPage} of {totalPages}
                        </div>
                        <div style={{"float": "right"}}>
                            <InputGroup size="sm">
                                <InputGroup.Prepend>
                                    <Button type="button" variant="outline-info" disabled={currentPage === 1}
                                        onClick={this.firstPage}>
                                        <FontAwesomeIcon icon={faFastBackward} /> Первая
                                    </Button>
                                    <Button type="button" variant="outline-info" disabled={currentPage === 1}
                                            onClick={this.prevPage}>
                                        <FontAwesomeIcon icon={faStepBackward} /> Предыдущая
                                    </Button>
                                </InputGroup.Prepend>
                                <FormControl className={"page-num bg-dark"} name="currentPage" value={currentPage}
                                        onChange={this.changePage}/>
                                <InputGroup.Append>
                                    <Button type="button" variant="outline-info" disabled={currentPage === totalPages}
                                            onClick={this.nextPage}>
                                        <FontAwesomeIcon icon={faStepForward} /> Следущая
                                    </Button>
                                    <Button type="button" variant="outline-info" disabled={currentPage === totalPages}
                                            onClick={this.lastPage}>
                                        <FontAwesomeIcon icon={faFastForward} /> Последняя
                                    </Button>
                                </InputGroup.Append>
                            </InputGroup>
                        </div>
                    </Card.Footer>
                </Card>
            </div>
        )
    }
}