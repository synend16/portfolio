import React, { Component } from "react";
import { Tabs, Tab, Grid, Cell} from 'react-mdl';
import ExperienceForm from "./ExperienceForm";

class ControlPanel extends Component {

	render() {
		return (
			<div className="page-content">
				<ExperienceForm/>
			</div>
		);
	}
}

export default ControlPanel
