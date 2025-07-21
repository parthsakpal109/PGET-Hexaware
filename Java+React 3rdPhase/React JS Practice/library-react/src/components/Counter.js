import React, { Component } from "react";

class Counter extends Component {
    constructor() {
        super();
        this.state = {
            age: 21,
            name: "asha",
            flag: false
        };
        console.log("I am constructor");
    }

    changeName = () => {
        this.setState({ name: "ajay" });
        this.setState({ flag: true });
    }

    componentDidMount = () => {
        console.log("API calling");
    }

    componentDidUpdate = () => {
        console.log("Name changes");
    }

    componentWillUnmount = () => {
        console.log("Removing component");
    }

    render() {
        console.log("I am render method");
        return (
            <>
                {this.state.flag === true ? <h1>Hello</h1> : <h2>Bye</h2>}
                <h1>Welcome {this.state.name}</h1>
                <button onClick={this.changeName}>Change name</button>
            </>
        );
    }
}

export default Counter;
