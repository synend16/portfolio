import axios from "axios";
import moment from 'moment';


export const getExperiences = (type, experienceType) => async dispatch =>{
  const response = await axios.get(`/api/experiences?type=${experienceType}`);

  dispatch({ type: type, payload: response.data });

};

export const createExperience = (experienceType, title, description, startDate, endDate) =>{
  axios.post(`/api/experiences`, {
    experienceType,
    title,
    description,
    startYear: moment(startDate).format('DD-MM-YYYY'),
    endYear: moment(endDate).format('DD-MM-YYYY')
  }).then(response => {return response})
    .catch(err => {throw err});
};