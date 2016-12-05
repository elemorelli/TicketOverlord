import React from "react";
import ReactDOM from "react-dom";
import {Router, Route, IndexRoute, hashHistory} from "react-router";
import Layout from "./pages/Layout";
import Featured from "./pages/Featured";
import Events from "./pages/Events";
import "./index.css";

const app = document.getElementById('app');
ReactDOM.render(
    <Router history={hashHistory}>
        <Route path="/" component={Layout}>
            <IndexRoute component={Featured}/>
            <Route path="events" component={Events}/>
        </Route>
    </Router>,
    app);
