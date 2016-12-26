import React, {Component} from "react";
import {connect} from "react-redux";
import Header from "../components/header/Header";
import Footer from "../components/footer/Footer";

function mapStateToProps(state) {
    return { todos: state.todos };
}

function mapDispatchToProps(dispatch) {
    return { actions: bindActionCreators(actionCreators, dispatch) };
}


class Layout extends Component {
    render() {
        return (
            <div>
                this.props.venues
                <Header />
                {this.props.children}
                <Footer />
            </div>
        );
    }
}


export default connect(mapStateToProps, mapDispatchToProps)(Layout);