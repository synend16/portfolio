import React, { Component } from "react";
import "./../index.scss";
import {connect} from "react-redux";
import { getProjects } from "../actions/projects";
import { Tabs, Tab, Grid, Cell} from 'react-mdl';
import Enterprise2Exam from "./projects/Enterprise2Exam";

class Projects extends Component {


	render() {
		return (
			<div className="projects-content">
				<Enterprise2Exam/>
			</div>
		);
	}

}

export default Projects