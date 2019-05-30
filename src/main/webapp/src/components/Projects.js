import React, { Component } from "react";
import "./../index.scss";
import Enterprise2Exam from "./projects/Enterprise2Exam";
import QuizGame from "./projects/QuizGame";

class Projects extends Component {


	render() {
		return (
			<div className="page-content">
				<Enterprise2Exam/>
				<QuizGame/>
			</div>
		);
	}

}

export default Projects