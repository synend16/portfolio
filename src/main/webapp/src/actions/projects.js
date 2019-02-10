import axios from "axios";
import {FETCH_PROJECTS} from "./types";

export const getProjects = () => async dispatch =>{
		const response = await axios.get('/projects');

		dispatch({ type: FETCH_PROJECTS, payload: response.data });

};