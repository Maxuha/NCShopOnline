import React, {Component} from 'react';
import {Button} from "react-bootstrap";

export default class AdminPanel extends Component {
    openPanel = (url) => {
        return this.props.history.push(url);
    }

    render() {
        const LeftPanel = {
            float: "left",
            width: "200px"
        }

        const RightPanel = {
            float: "right",
            width: "200px"
        }

        const ButtonStyle = {
            margin: "5px"
        }

        const styles = {
            LeftPanel,
            RightPanel,
            ButtonStyle
        }

        return (
            <div>
                <div style={styles.LeftPanel}>
                    <h1 className={"text-white"}>КАТЕГОРИИ</h1>
                    <Button style={styles.ButtonStyle} onClick={this.openPanel.bind(this, "/admin/category/add")}>Добавить категорию</Button>
                    <Button style={styles.ButtonStyle} onClick={this.openPanel.bind(this, "/admin/category/list")}>Список категорий</Button>
                </div>
                <div style={styles.RightPanel}>
                    <h1 className={"text-white"}>ТОВАРЫ</h1>
                    <Button style={styles.ButtonStyle} onClick={this.openPanel.bind(this, "/admin/product/add")}>Добавить товар</Button>
                    <Button style={styles.ButtonStyle} onClick={this.openPanel.bind(this, "/admin/product/list")}>Список товаров</Button>
                </div>
            </div>
        );
    }
}