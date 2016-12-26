import axios from "axios";

export function fetchVenues() {
    return function (dispatch) {
        axios.get("http://ticketoverlord.herokuapp.com/api/v1/venues")
            .then((response) => {
                dispatch({type: "FETCH_VENUES_FULLFILED", payload: response.data})
            })
            .catch((err) => {
                dispatch({type: "FETCH_VENUES_REJECTED", payload: err})
            })
    }
}

export function fetchVenueDetails() {
    return function (dispatch) {
        axios.get("http://ticketoverlord.herokuapp.com/api/v1/venues/1")
            .then((response) => {
                dispatch({type: "FETCH_VENUES_FULLFILED", payload: response.data})
            })
            .catch((err) => {
                dispatch({type: "FETCH_VENUES_REJECTED", payload: err})
            })
    }
}