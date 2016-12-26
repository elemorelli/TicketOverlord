import React, {Component} from "react";
import {Link} from "react-router";

export default class Events extends Component {
    render() {
        return (
            <div>
                <h2>Todos los eventos</h2>
                <ul>
                    <li><Link to="/events/a">a</Link></li>
                    <li>b</li>
                    <li>c</li>
                </ul>
            </div>
        );
    }
}
