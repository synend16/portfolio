import React, { Component } from "react";
import "./../index.scss";
import {connect} from "react-redux";
import { getProjects } from "../actions/projects";
import { Tabs, Tab, Grid, Cell, Card, CardTitle, CardText, CardActions, Button, CardMenu, IconButton } from 'react-mdl';
import Project from "./sub/Project";
const imagesPath = process.env.PUBLIC_URL + "/assets/";



class Projects extends Component {

	constructor(props) {
		super(props);
		this.state = { activeTab: 0 };

		this.topics = ["latest","kotlin", "java", "react"];
	}

	componentDidMount() {
		this.props.getProjects();
	}

	render() {
		return (
			<div>
				<Tabs activeTab={this.state.activeTab} onChange={(tabId) => this.setState({ activeTab: tabId })} ripple>
					<Tab>Latest</Tab>
					<Tab>Kotlin</Tab>
					<Tab>Java</Tab>
					<Tab>React</Tab>
				</Tabs>
				<Grid>
					<Cell col={12}>
						<div className="content">{this.toggleCategories()}</div>
					</Cell>
				</Grid>
			</div>
		);
	}


	renderList(list) {
		if (this.props.projects.data) {
			return list.map(post => {
				return (
					<div key={post.id}>
						<Project props={post}/>
					</div>
				);
			});
		} else {
			return (<div>No content</div>)
		}
	}

	toggleCategories() {
		if (this.props.projects.data && this.props.projects.data.list && this.props.projects.data.list.length > 0) {

			let list = [];

			if (this.state.activeTab === 0) {
				list = this.props.projects.data.list
			} else {
				list = this.props.projects.data.list
					.filter(post => post.topics.some( p => p === this.topics[this.state.activeTab]));
			}
			return (
				<div className="projects-grid">{this.renderList(list)}</div>

			)
		} else {
			return (<div>No content</div>)
		}
	}

}

const mapStateToProps = (state) => {
	console.log(state);
	return { projects: state.projects };
};

export default connect(mapStateToProps, { getProjects })(Projects);