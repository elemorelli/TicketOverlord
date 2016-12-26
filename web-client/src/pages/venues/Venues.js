import React, {Component} from "react";
import VenuesStore from "../../stores/VenuesStore";
import * as VenuesActions from "../../actions/VenuesActions";
import VenueSnippet from "../../components/VenueSnippet";



export default class Venues extends Component {

    constructor() {
        super();
        this.getVenues = this.getVenues.bind(this);
        this.state = {
            venues: VenuesStore.getAll()
        };
    };

    componentWillMount() {
        VenuesStore.on("change", this.getVenues);
    };

    componentWillUnmount() {
        VenuesStore.removeListener("change", this.getVenues);
    };

    getVenues() {
        this.setState({
            venues: VenuesStore.getAll()
        });
    };

    createVenue() {
        VenuesActions.createVenue("pepe");
    };

    reloadVenues() {
        VenuesActions.reloadVenues();
    };

    render() {

        const {venues} = this.state;

        const VenuesComponent = venues.map((venue) => {
            return <VenueSnippet key={venue.id} {...venue}/>;
        });

        return (
            <div>
                <h2>Todos los recintos</h2>
                <ul>{VenuesComponent}</ul>
                <div>
                    <button onClick={this.reloadVenues.bind(this)}>Reload!</button>
                </div>
            </div>
        );
    };
};
