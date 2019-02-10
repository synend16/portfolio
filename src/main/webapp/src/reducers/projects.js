import { FETCH_PROJECTS } from "../actions/types";

export default function(state = [], action) {

	console.log(action);

	switch (action.type) {

		case FETCH_PROJECTS:
			return action.payload;


		default:
			return state;
	}
}