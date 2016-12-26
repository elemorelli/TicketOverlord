import React from "react";
import ReactDOM from "react-dom";
import {Router, Route, IndexRoute, hashHistory} from "react-router";
import Layout from "./pages/Layout";
import Featured from "./pages/events/Featured";
import Events from "./pages/events/Events";
import EventDetails from "./pages/events/EventDetails";
import Venues from "./pages/venues/Venues";
import VenuesDetails from "./pages/venues/VenueDetails";
import Login from "./pages/users/Login";
import {Provider} from "react-redux";
import store from "./store";

const app = document.getElementById('app');
ReactDOM.render(
    <Provider store={store}>
        <Router history={hashHistory}>
            <Route path="/" component={Layout}>
                <IndexRoute component={Featured}/>
                <Route path="events" name="events" component={Events}/>
                <Route path="events/:event" name="eventsDetails" component={EventDetails}/>
                <Route path="venues" name="venues" component={Venues}/>
                <Route path="venues/:venue" name="venuesDetails" component={VenuesDetails}/>
                <Route path="login" name="login" component={Login}/>
            </Route>
        </Router>
    </Provider>,
    app);
