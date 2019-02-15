import { FETCH_EXPERIENCES_EDUCATION, FETCH_EXPERIENCES_WORK } from "../actions/types";

const EXPERIENCE = {
  education: null,
  work: null
};

export default function(state = EXPERIENCE, action) {

  console.log(action);

  switch (action.type) {

    case FETCH_EXPERIENCES_EDUCATION:
      return {...state, education: action.payload};

    case FETCH_EXPERIENCES_WORK:
      return {...state, work: action.payload};

    default:
      return state;
  }
}