const initialState = {
    venues: [],
    fetching: false,
    fetched: false,
    error: null
};

export default function reducer(state = initialState, action) {

    switch (action.type) {
        case "FETCH_VENUES": {
            return {...state, fetching: true};
        }
        case "FETCH_VENUES_REJECTED": {
            return {...state, fetching: false, error: action.payload};
        }
        case "FETCH_VENUES_FULFILLED": {
            return {...state, fetching: false, fetched: true, venues: action.payload};
        }
        case "SET_VENUE_NAME": {
            return {...state, venues: {...state}};
        }
    }
}