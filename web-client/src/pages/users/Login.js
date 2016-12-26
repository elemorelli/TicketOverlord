import React, {Component} from "react";

export default class Login extends Component {
    render() {
        return (
            <div>
                <h2>Login</h2>
                <form>
                    <label htmlFor="username">Username</label>
                    <input type="text" name="username" id="username"/>
                    <label htmlFor="password">Password</label>
                    <input type="password" name="password" id="password"/>
                    <input type="button" value={"Login"}/>
                </form>
            </div>
        );
    }
}
