import { FETCH_EXPERIENCES_EDUCATION, FETCH_EXPERIENCES_WORK, FETCH_EXPERIENCES } from "../actions/types";

const EXPERIENCE = {
  education: null,
  work: null,
  all: null
};

export default function(state = EXPERIENCE, action) {

  switch (action.type) {

    case FETCH_EXPERIENCES_EDUCATION:
      return {...state, education: action.payload};

    case FETCH_EXPERIENCES_WORK:
      return {...state, work: action.payload};

    case FETCH_EXPERIENCES:
      return {...state, all: action.payload};

    default:
      return state;
  }
}