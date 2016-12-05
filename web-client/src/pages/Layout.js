import React, {Component} from "react";
import "./Layout.css";
import Header from "../components/header/Header";
import Footer from "../components/footer/Footer";

class Layout extends Component {
    render() {
        return (
            <div>
                <Header />
                {this.props.children}
                <Footer />
            </div>
        );
    }
}

export default Layout;
