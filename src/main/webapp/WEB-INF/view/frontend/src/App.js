import React from 'react';
import './App.css';
import NavigationBar from "./components/NavigationBar";
import {Container, Row, Col} from "react-bootstrap";
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import Welcome from "./components/Welcome";
import Footer from "./components/Footer";
import Category from "./components/admin_panel/Category";
import CategoryList from "./components/admin_panel/CategoryList";
import CategoryPage from "./components/shop/CategoryPage";
import Register from "./components/Register";
import LogIn from "./components/LogIn";
import ProductList from "./components/shop/ProductList";
import ProductListAdmin from "./components/admin_panel/ProductListAdmin";
import AdminPanel from "./components/admin_panel/AdminPanel";
import Product from "./components/admin_panel/Product";

function App() {
  return (
    <Router>
      <NavigationBar/>
      <Container>
          <Row>
              <Col lg={12} className={"margin-top"}>
                  <Switch>
                      <Route path="/" exact component={Welcome}/>
                      <Route path="/admin" exact component={AdminPanel}/>
                      <Route path="/admin/category/add" exact component={Category}/>
                      <Route path="/admin/category/edit/:id" exact component={Category}/>
                      <Route path="/admin/category/list" exact component={CategoryList}/>
                      <Route path="/admin/product/list" exact component={ProductListAdmin}/>
                      <Route path="/admin/product/add" exact component={Product}/>
                      <Route path="/admin/product/edit/:id" exact component={Product}/>
                      <Route path="/register/:type" exact component={Register}/>
                      <Route path="/login/:type" exact component={LogIn}/>
                      <Route path="/category/:id" exact component={CategoryPage}/>
                      <Route path="/category/:id/products" exact component={ProductList}/>
                      <Route path="/category/:categoryId/product/:productId" exact component={ProductList}/>
                  </Switch>
              </Col>
          </Row>
      </Container>
        <Footer/>
    </Router>
  );
}

export default App;
