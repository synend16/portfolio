import React, { Component } from "react";
import {Grid, Cell, Checkbox, Textfield, Button, DataTable, TableHeader} from 'react-mdl';
import DatePicker from "react-datepicker";
import { createExperience , getAllExperiences, deleteExperience} from "../../actions/experiences";
import {connect} from "react-redux";


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

	componentDidMount() {
		this.props.getAllExperiences();

	}

	render() {
		console.log(this.state);

		return (
			<div>
				<Grid>
					<Cell col={6}>
						<h3>New Experience</h3>
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

						<Grid>
							<Cell col={4}>
								<h4>Start Date</h4>
								<DatePicker
									dateFormat="dd-MM-yyyy"
									selected={this.state.startDate}
									onChange={(startDate) =>this.setState({startDate})}
								/>
							</Cell>
							<Cell col={4}>
								<h4>End Date</h4>
								<DatePicker
									dateFormat="dd-MM-yyyy"
									selected={this.state.endDate}
									onChange={(endDate) =>this.setState({endDate})}
								/>
							</Cell>

							<Cell col={4}>
								<Checkbox label="WORK" onChange={this.setType} checked={this.state.work}/>
								<Button raised colored onClick={() => this.create()}>Create</Button>
							</Cell>
						</Grid>

					</Cell>
					<Cell col={6}>
						<h3>Manage Experiences</h3>
						{this.props.allExperiences ?
							<DataTable
								className="admin-table"
								onSelectionChanged={(rows) => this.setSelectedRows(rows)}
								selectable
								shadow={0}
								rowKeyColumn="id"
								rows={this.props.allExperiences.data.list}
							>
								<TableHeader name="id" tooltip="The Id of the Experience">Id</TableHeader>
								<TableHeader name="title" tooltip="The Title of the Experience">Title</TableHeader>
								<TableHeader name="startYear" tooltip="Start Date of Experience">Start Year</TableHeader>
								<TableHeader name="endYear" tooltip="End Date of Experience">End Year</TableHeader>
							</DataTable>
						: <div><h4>No Experiences</h4></div>
						}

						<Button raised colored onClick={() => this.deleteSelected()}>Delete</Button>


					</Cell>
				</Grid>
			</div>
		);
	}

	setType = () => {
		this.setState(prevState => ({
			work: !prevState.work
		}));
	};

	setSelectedRows = (rows) => {
		this.setState({selectedRows: rows});
	};

	create = () => {
		createExperience(
			this.state.experienceType,
			this.state.title,
			this.state.description,
			this.state.startDate,
			this.state.endDate)
	};

	deleteSelected = () => {
		let ids = this.state.selectedRows;
		console.log(ids);
		if (ids && ids.length){
			ids.forEach(id => deleteExperience(id))
		}

		this.props.getAllExperiences();
	}

}

const mapStateToProps = (state) => {
	return {
		allExperiences: state.experiences.all,
	};
};

export default connect(mapStateToProps, { getAllExperiences })(ExperienceForm)