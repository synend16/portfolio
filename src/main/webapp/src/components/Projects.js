import React, { Component } from "react";
import "./../index.scss";
import {connect} from "react-redux";
import { getProjects } from "../actions/projects";
import { Grid, Row, Col } from 'react-flexbox-grid';


class Projects extends Component {

	componentDidMount() {
		this.props.getProjects();
	}

	render() {
		return (
			<div className="TestClass">
				<h1>My Projects</h1>
				<p>Here is a collection of my latest public github projects, as well as some projects that i have contributed to.</p>
				<div>{this.renderpaveView()}</div>
			</div>
		);
	}

	renderpaveView() {
		if (this.props.projects.data) {
			return (
				<Grid fluid>
					<Row>
						<Col xs={4}>
							<Row>
								{this.props.projects.data.list[0].title}
							</Row>
							<Row>
								{this.props.projects.data.list[1].title}
							</Row>
						</Col>
						<Col xs={8}>
							{this.props.projects.data.list[2].title}
						</Col>
					</Row>
					<Row>
						<Col xs={8}>
							{this.props.projects.data.list[2].title}
						</Col>
						<Col xs={4}>
							<Row>
								{this.props.projects.data.list[0].title}
							</Row>
							<Row>
								{this.props.projects.data.list[1].title}
							</Row>
						</Col>
					</Row>
				</Grid>

			)
		} else {
			return (<div>No content</div>)
		}
	}

	renderList() {
		if (this.props.projects.data) {
			return this.props.projects.data.list.map(post => {
				return (
					<div className="item" key={post.id}>
						<div className="content">
							<h2>{post.title}</h2>
							<div className="description">
								{post.description}
							</div>
						</div>
					</div>
				);
			});
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