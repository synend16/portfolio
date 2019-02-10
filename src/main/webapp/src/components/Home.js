import React, { Component } from "react";
import "./../index.scss";
import {connect} from "react-redux";

class Home extends Component {

	componentDidMount() {

	}

	render() {
		return (
			<div className="TestClass">
				<h1>Home page</h1>
			</div>
		);
	}
}

function mapStateToProps(state) {
	console.log(state);
	return { state }
}

export default connect(mapStateToProps)(Home);