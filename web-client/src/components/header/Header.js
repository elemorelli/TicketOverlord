import React, {Component} from "react";
import {Link} from "react-router";
import "./Header.css";

export default class Header extends Component {
    render() {
        return (
            <header>
                <div>
                    <h1> <Link to="/">Ticket Overlord</Link></h1>
                    <Link to="events" activeClassName="active">Events</Link>
                    <Link to="venues" activeClassName="active">Venues</Link>
                    <Link to="login" activeClassName="active"> Login</Link>
                </div>
            </header>
        );
    }
}
