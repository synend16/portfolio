import React, { Component } from "react";
import {Tabs, Tab, Grid, Cell, RadioGroup, Radio, Checkbox, Textfield, Button} from 'react-mdl';
import DatePicker from "react-datepicker";

import { createExperience } from "../../actions/experiences";

import "react-datepicker/dist/react-datepicker.css";

class ExperienceForm extends Component {

	constructor(props) {
		super(props);

		this.state = {
			startDate: new Date(),
			endDate: new Date(),
			title: null,
			description: null,
			work: true,
		};
	}

	render() {
		return (
			<div>
				<Cell col={6}>
					<h3>Create new Experience</h3>
					<Textfield
						onChange={(text) => this.setState({title: text.target.value})}
						label="Title"
						floatingLabel
						style={{width: '100%'}}
					/>
					<Textfield
						onChange={(text) => this.setState({description: text.target.value})}
						label="Description"
						floatingLabel
						rows={3}
						style={{width: '100%'}}
					/>

						<h4>Start Date</h4>
						<DatePicker
							dateFormat="dd-MM-yyyy"
							selected={this.state.startDate}
							onChange={(startDate) =>this.setState({startDate})}
						/>
						<h4>End Date</h4>
						<DatePicker
							dateFormat="dd-MM-yyyy"
							selected={this.state.endDate}
							onChange={(endDate) =>this.setState({endDate})}
						/>

						<Checkbox label="WORK" onChange={this.setType} checked={this.state.work}/>
						<Button raised colored onClick={() => this.create()}>Create</Button>

				</Cell>
			</div>
		);
	}

	setType = () => {
		this.setState(prevState => ({
			work: !prevState.work
		}));
	};



	create = () => {

		console.log("rrrr");

		createExperience(
			this.state.experienceType,
			this.state.title,
			this.state.description,
			this.state.startDate,
			this.state.endDate)
	}

}

export default
(ExperienceForm)