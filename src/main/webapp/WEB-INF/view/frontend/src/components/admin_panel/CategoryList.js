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

    changePage = event => {
        this.setState({
            [event.target.name]: parseInt(event.target.value)
        })
    };

    firstPage = () => {
        if (this.state.currentPage > 1) {
            this.setState({
                currentPage: 1
            });
        }
    };

    prevPage = () => {
        if (this.state.currentPage > 1) {
            this.setState({
                currentPage: this.state.currentPage - 1
            });
        }
    };

    lastPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.categories.length / this.state.categoriesPerPage)) {
            this.setState({
                currentPage: Math.ceil(this.state.categories.length / this.state.categoriesPerPage)
            });
        }
    };

    nextPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.categories.length / this.state.categoriesPerPage)) {
            this.setState({
                currentPage: this.state.currentPage + 1
            });
        }
    };

    render() {
        const {categories, currentPage, categoriesPerPage} = this.state;
        const lastIndex = currentPage * categoriesPerPage;
        const firstIndex = lastIndex - categoriesPerPage;
        const currentCategories = categories.slice(firstIndex, lastIndex);
        const totalPages = categories.length / categoriesPerPage;

        const pageNumCss = {
            width: "45px",
            border: "1px solid #17A2B8",
            color: "#17A2B8",
            textAlign: "center",
            fontWeight: "bold"
        };

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
                                    currentCategories.map((category) => (
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
                                <FormControl style={pageNumCss} className={"bg-dark"} name="currentPage" value={currentPage}
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