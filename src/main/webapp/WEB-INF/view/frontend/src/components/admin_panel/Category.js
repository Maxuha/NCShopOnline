import React, {Component} from "react";

import {Card, Form, Button, Col, Image} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPlusSquare, faSave, faUndo, faList, faEdit} from "@fortawesome/free-solid-svg-icons";
import NCSOToast from '../NCSOToast';
import axios from 'axios';

export default class Category extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.categoryChangeTitle = this.categoryChangeTitle.bind(this);
        this.categoryChangeParent = this.categoryChangeParent.bind(this);
        this.categoryChangeImage = this.categoryChangeImage.bind(this);
        this.submitCategory = this.submitCategory.bind(this);
    }

    initialState = {
        id:'', title:'', parent:{}, image:{}, categories: []
    };

    componentDidMount() {
        this.getAllCategories()
        const categoryId = +this.props.match.params.id;
        if (categoryId) {
            this.findCategoryById(categoryId);
        }
    };

    findCategoryById = (categoryId) => {
        axios.get("http://localhost:7001/api/category/get/" + categoryId)
            .then(response => {
                if (response.data != null) {
                    this.setState({
                        id: response.data.id,
                        title: response.data.title,
                        parent: response.data.parent === null ? {} : response.data.parent,
                        image: response.data.image
                    });
                }
            }).catch((error) => {
            console.error("Error - " + error);
        });
    }

    getAllCategories() {
        axios.get("http://localhost:7001/api/category/get/all")
            .then(response => response.data)
            .then((data) => {
                this.setState({categories: data})
            });
    };

    resetCategory = () => {
        this.setState(() => this.initialState)
    };

    submitCategory = event => {
        event.preventDefault();
        const category = {
            title: this.state.title,
            parent: this.state.parent.id ? this.state.parent : null,
            image: this.state.image.image ? this.state.image : null
        };
        axios.post("http://localhost:7001/api/category/create", category)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true, "method":"post"});
                    setTimeout(() => this.setState({"show":false}), 3000);
                } else {
                    this.setState({"show":false});
                }
                this.setState(this.initialState);
                this.getAllCategories();
            });
    };

    updateCategory = event => {
        event.preventDefault();
        const category = {
            id: this.state.id,
            title: this.state.title,
            parent: this.state.parent.id ? this.state.parent : null,
            image: this.state.image.image ? this.state.image : null
        };
        axios.put("http://localhost:7001/api/category/update", category)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true, "method":"put"});
                    setTimeout(() => this.setState({"show":false}), 3000);
                    setTimeout(() => this.categoryList(), 3000);
                } else {
                    this.setState({"show":false});
                }
            });

        this.setState(this.initialState);
        this.getAllCategories();
    };

    categoryChangeTitle = event => {
        this.setState({
            title: event.target.value
        });
    };

    categoryChangeImage = event => {
        console.log("Uploading");
        var self = this;
        var reader = new FileReader();
        var file = event.target.files[0];

        reader.onload = function(upload) {
            self.setState({
                image: { image: upload.target.result }
            }, function() {
                console.log(self.state.image);
            });
        };
        if (file != null && file !== '') {
            reader.readAsDataURL(file);
            console.log("Uploaded");
        } else {
            self.setState({
                image: null
            });
            console.log("File not found")
        }
    };

    categoryChangeParent = event => {
        const parent = {
            id: event.target.childNodes[event.target.selectedIndex].id,
            title: event.target.childNodes[event.target.selectedIndex].value
        }
        this.setState({parent})
    };

    categoryList = () => {
        return this.props.history.push("/admin/category/list");
    };

    render() {
        const {title, parent, image} = this.state
        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <NCSOToast show = {this.state.show} message = {this.state.method === "put" ? "Категория обновлена" : "Категория сохранена."} type = {"success"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare}/> {this.state.id ? "Обновить" : "Добавить новую категорию"}</Card.Header>
                    <Form onReset={this.resetCategory} onSubmit={this.state.id ? this.updateCategory : this.submitCategory} id="bookFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridTitle">
                                    <Form.Label>Название категории</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="title"
                                                  value={title} onChange={this.categoryChangeTitle}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите название категории" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridParent">
                                    <Form.Label>Название родительской категории</Form.Label>
                                    <Form.Control as="select" type="text" name="parent"
                                                  value={parent.title} onChange={this.categoryChangeParent}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите название родительской категории">
                                        <option id="0">
                                            Нету
                                        </option>
                                        {
                                            this.state.categories.map((category) => (
                                                <option id={category.id} key={category.id}>
                                                    {category.title}
                                                </option>
                                            ))
                                        }
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridInputImage">
                                    <Form.Label>Изображение</Form.Label>
                                    <Form.File name="image" onChange={this.categoryChangeImage}
                                               placeholder="Выберите картинку"
                                               accept="image/*"
                                               src={image.image}
                                               feedbackTooltip/>
                                </Form.Group>
                                <Form.Group controlId="formGridImage">
                                    <Form.File>
                                        <Image src={image.image} roundedCircle width="100" height="100"/>
                                    </Form.File>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"right"}}>
                            <Button size="sm" variant="success" type="submit">
                                <FontAwesomeIcon icon={faSave}/> {this.state.id ? "Обновить" : "Добавить "}
                            </Button>{' '}
                            <Button size="sm" variant="info" type="reset">
                                <FontAwesomeIcon icon={faUndo} /> Сбросить
                            </Button>{' '}
                            <Button size="sm" variant="info" type="button" onClick={this.categoryList.bind(this)}>
                                <FontAwesomeIcon icon={faList} /> Список категорий
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}