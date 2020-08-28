import React, {Component} from "react";

import {Card, Form, Button, Col, Image} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPlusSquare, faSave, faUndo, faList, faEdit, faTimes} from "@fortawesome/free-solid-svg-icons";
import NCSOToast from '../NCSOToast';
import axios from 'axios';

export default class Product extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.setState({
            categories: [],
            shippers: [],
            images: [],
            show: false
        })
        this.productChangeInput = this.productChangeInput.bind(this);
        this.productChangeCategory = this.productChangeCategory.bind(this);
        this.productChangeShipper = this.productChangeShipper.bind(this);
        this.productChangeImage = this.productChangeImage.bind(this);
        this.submitProduct = this.submitProduct.bind(this);
    }

    initialState = {
        id: '', title: '', description: '', price: 0, discount: 0, category:{}, shipper:{user: {}}, image: {}, images: [], products: [], categories: [], shippers: []
    };

    componentDidMount() {
        this.getAllCategories();
        this.getAllShippers();
        const productId = +this.props.match.params.id;
        if (productId) {
            this.findProductById(productId);
        }
    };

    findProductById = (productId) => {
        axios.get(`http://localhost:7001/api/product/get/${productId}`)
            .then(response => {
                if (response.data != null) {
                    this.setState({
                        id: response.data.id,
                        title: response.data.title,
                        description: response.data.description,
                        price: response.data.price,
                        discount: response.data.discount,
                        category: response.data.category,
                        shipper: response.data.shipper
                    });
                    this.findImagesByProductId(productId);
                }
            }).catch((error) => {
            console.error("Error - " + error);
        });
    }

    findImagesByProductId = (productId) => {
        axios.get( `http://localhost:7001/api/image_to_product/get?product_id=${productId}`)
            .then(response => {
                if (response.data != null) {
                    response.data.map((data) => this.findImageById(data.image.id));
                }
            }).catch((error) => {
            console.error("Error - " + error);
        });
    }

    findImageById = (imageId) => {
        axios.get(`http://localhost:7001/api/image/get/${imageId}`)
            .then(response => {
                if (response.data != null) {
                    this.setState({
                        images: [...this.state.images, response.data]
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
                /*const categories = [...data];
                let categoriesNew = [];
                categories.map((category1) => {
                    let isSuccess = true
                    categories.map((category2) => {
                        if (category1 === category2.parent) {
                            isSuccess = false;
                        }
                        return category2;
                    })
                    if (isSuccess) {
                        categoriesNew = [...categoriesNew, category1];
                    }
                    return category1;
                })*/
                this.setState({categories: data})
            });
    }

    getAllShippers() {
        axios.get("http://localhost:7001/api/shipper/get/all")
            .then(response => response.data)
            .then((data) => {
                this.setState({shippers: data})
            });
    }

    resetProduct = () => {
        this.setState(() => this.initialState)
    };

    submitProduct = event => {
        event.preventDefault();
        const product = {
            id: this.state.id,
            title: this.state.title,
            description: this.state.description,
            price: this.state.price,
            discount: this.state.discount,
            category: this.state.category === {} ? this.state.category : this.state.categories[0],
            shipper: this.state.shipper === {} ? this.state.shipper : this.state.shippers[0]
        };
        axios.post("http://localhost:7001/api/product/create", product)
            .then(response => {
                if (response.data != null) {
                    this.submitImages(response.data.id);
                }
                this.setState(this.initialState);
                this.getAllCategories();
                this.getAllShippers();
            });
    };

    submitImages(productId) {
        if (this.state.images.length === 0) {
            const image = {
                image: "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSBWCN9Acq8fXUM4G4e3c9l--1RWCkVX9folw&usqp=CAU"
            }
            this.state.images.push(image)
        }
        this.state.images.map((image) => {
            axios.post("http://localhost:7001/api/image/create", image)
                .then(response => {
                    if (response.data != null) {
                        this.submitImageToProduct(response.data.id, productId);
                    }
                })
            return image;
        })
    }

    submitImageToProduct(imageId, productId) {
        const imageToProduct = {
            image: {
                id: imageId
            },
            product: {
                id: productId
            }
        }
        axios.post("http://localhost:7001/api/image_to_product/create", imageToProduct)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true, "method":"post"});
                    setTimeout(() => this.setState({"show":false}), 3000);
                } else {
                    this.setState({"show":false});
                }
            })
    }

    updateProduct = event => {
        event.preventDefault();
        const product = {
            id: this.state.id,
            title: this.state.title,
            description: this.state.description,
            price: this.state.price,
            discount: this.state.discount,
            category: this.state.category,
            shipper: this.state.shipper
        };
        axios.put("http://localhost:7001/api/product/update", product)
            .then(response => {
                if (response.data != null) {
                    this.submitImages(response.data.id);
                }
                this.setState(this.initialState);
                this.getAllCategories();
                this.getAllShippers();
            });
    };

    productChangeInput = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    productChangeImage = event => {
        console.log("Uploading");
        var self = this;
        var reader = new FileReader();
        var file = event.target.files[0];

        reader.onload = function(upload) {
            self.setState({
                image: {
                    image: upload.target.result,
                }
            }, function() {
                self.setState({
                    images: [...self.state.images, self.state.image]
                })
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

    productChangeCategory = event => {
        console.log("idD: " +  event.target.childNodes[event.target.selectedIndex].id);
        const category = {
            id: event.target.childNodes[event.target.selectedIndex].id,
            title: event.target.childNodes[event.target.selectedIndex].value
        }
        this.setState({category: category})
    };

    productChangeShipper = event => {
        const user = {
            id: event.target.childNodes[event.target.selectedIndex].id
        }

        const shipper = {
            user,
            companyName: event.target.childNodes[event.target.selectedIndex].value
        }
        this.setState({shipper: shipper})
    };

    productChangeImages = event => {
        const image = {
            image: event.target.childNodes[event.target.selectedIndex].id
        }
        this.setState({image: image})
    };

    productRemoveImage = event => {
        const images = this.state.images.filter((image) => image.image !== this.state.image.image)
        this.setState({
            images: images,
            image: images[0]
        });
    };

    productList = () => {
        return this.props.history.push("/admin/product/list");
    };

    render() {
        const {title, description, price, discount, category, shipper, image} = this.state
        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <NCSOToast show = {this.state.show} message = {this.state.method === "put" ? "Товар обновлен" : "Товар сохранен."} type = {"success"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare}/> {this.state.id ? "Обновить" : "Добавить новый товар"}</Card.Header>
                    <Form onReset={this.resetProduct} onSubmit={this.state.id ? this.updateProduct : this.submitProduct} id="bookFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridTitle">
                                    <Form.Label>Название товара</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="title"
                                                  value={title} onChange={this.productChangeInput}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите название товара" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridDescription">
                                    <Form.Label>Описание товара</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="description"
                                                  value={description} onChange={this.productChangeInput}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите описание товара" />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridPrice">
                                    <Form.Label>Цена товара</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="price"
                                                  value={price} onChange={this.productChangeInput}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите цену товара" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridDiscount">
                                    <Form.Label>Скидка товара</Form.Label>
                                    <Form.Control required autoComplete="off"
                                                  type="text" name="discount"
                                                  value={discount} onChange={this.productChangeInput}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите скидку на цену товара" />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridCategory">
                                    <Form.Label>Выберите категорию</Form.Label>
                                    <Form.Control as="select" type="text" name="category"
                                                  value={category.title} onChange={this.productChangeCategory}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите название категории">
                                        {
                                            this.state.categories.map((category) => (
                                                <option id={category.id} key={category.id}>
                                                    {category.title}
                                                </option>
                                            ))
                                        }
                                    </Form.Control>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridShipper">
                                    <Form.Label>Выберите поставщика</Form.Label>
                                    <Form.Control as="select" type="text" name="shipper"
                                                  value={shipper.companyName} onChange={this.productChangeShipper}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Введите название компании">
                                        {
                                            this.state.shippers.map((shipper) => (
                                                <option id={shipper.user.id} key={shipper.user.id}>
                                                    {shipper.companyName}
                                                </option>
                                            ))
                                        }
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridInputImage">
                                    <Form.Label>Изображение</Form.Label>
                                    <Form.File name="image" onChange={this.productChangeImage}
                                               placeholder="Выберите картинку"
                                               accept="image/*"
                                               src={image.image}
                                               feedbackTooltip/>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridImages">
                                    <Form.Label>Все изображения товара</Form.Label>
                                    <Form.Control as="select" type="text" name="images"
                                                  onChange={this.productChangeImages}
                                                  className={"bg-dark text-white"}>
                                        {
                                            this.state.images.map(function (image, id) {
                                                return <option id={image.image} key={id}>
                                                            {id}
                                                       </option>
                                            })
                                        }
                                    </Form.Control>
                                </Form.Group>
                                <Form.Group controlId="formGridImage">
                                    <Form.File>
                                        <Image src={image.image} roundedCircle width="100" height="100"/>
                                    </Form.File>
                                </Form.Group>
                                <Form.Group controlId="formGridImageRemove">
                                    <Button style={{marginTop: "35px", marginLeft: "5px"}} size="sm" variant="info" type="button" onClick={this.productRemoveImage}>
                                        <FontAwesomeIcon icon={faTimes} />
                                    </Button>
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
                            <Button size="sm" variant="info" type="button" onClick={this.productList.bind(this)}>
                                <FontAwesomeIcon icon={faList} /> Список товаров
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}