import React, {Component} from "react";
import {Link} from "react-router";
import "./Header.css";

class Header extends Component {
    render() {
        return (
            <header>
                <h1>Ticket Overlord</h1>
                <ul>
                    <li><Link to="/">Featured</Link></li>
                    <li><Link to="events">Events</Link></li>
                    <li>Login</li>
                </ul>
            </header>
        );
    }
}

export default Header;
