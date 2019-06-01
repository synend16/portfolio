import axios from "axios";
import moment from 'moment';
import {DELETE_EXPERIENCES, FETCH_EXPERIENCES} from "./types";


export const getExperiences = (type, experienceType) => async dispatch =>{
  const response = await axios.get(`/api/experiences?type=${experienceType}`);
  dispatch({ type: type, payload: response.data });
};

export const getAllExperiences = () => async dispatch =>{
  const response = await axios.get(`/api/experiences`);
  console.log("called");
  dispatch({ type: FETCH_EXPERIENCES, payload: response.data.data.list });
};

export const createExperience = (experienceType, title, description, startDate, endDate) => {
  return axios.post(`/api/experiences`, {
    experienceType,
    title,
    description,
    startYear: moment(startDate).format('DD-MM-YYYY'),
    endYear: moment(endDate).format('DD-MM-YYYY')
  });
};

export const deleteExperience= (id) => async dispatch => {
  await axios.delete(`/api/experiences/${id}`);

  dispatch({ type: DELETE_EXPERIENCES, payload: id });


};