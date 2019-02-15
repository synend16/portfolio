import { combineReducers } from 'redux';
import projects from './projects';
import experiences from "./experiences";

export default combineReducers({
  projects : projects,
  experiences : experiences
});