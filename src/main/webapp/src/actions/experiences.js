import axios from "axios";


export const getExperiences = (type, experienceType) => async dispatch =>{
  const response = await axios.get(`/api/experiences?type=${experienceType}`);

  dispatch({ type: type, payload: response.data });

};