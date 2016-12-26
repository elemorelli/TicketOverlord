import React, {Component} from "react";
import {Link} from "react-router";

export default class VenueSnippet extends Component {
    constructor(props) {
        super();
    }

    render() {
        const {id, name} = this.props;
        return (
            <li>
                <b><Link to={"events/" + id}>{name}</Link></b>
            </li>
        );
    }
}
