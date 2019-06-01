import {
  FETCH_EXPERIENCES_EDUCATION,
  FETCH_EXPERIENCES_WORK,
  FETCH_EXPERIENCES,
  DELETE_EXPERIENCES
} from "../actions/types";

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
      return {all: action.payload};

    case DELETE_EXPERIENCES:{
      let st = state.all ?
        state.all.filter(e => {console.log(e.id); return e.id !== action.payload})
        : state;

      return {all: st};
    }


    default:
      return state;
  }
}