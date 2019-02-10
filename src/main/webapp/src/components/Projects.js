import React, { Component } from "react";
import "./../index.scss";
import {connect} from "react-redux";
import { getProjects } from "../actions/projects";

class Projects extends Component {

	componentDidMount() {
		this.props.getProjects();
	}

	render() {
		return (
			<div className="TestClass">
				<h1>My Projects</h1>
				<p>Here is a collection of my latest public github projects, as well as some projects that i have contributed to.</p>
				<div>{this.renderList()}</div>
			</div>
		);
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