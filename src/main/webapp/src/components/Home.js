import React, { Component } from "react";
import "./../index.scss";
import {connect} from "react-redux";

class Home extends Component {

	render() {
		return (
			<div className="TestClass">
				<h1>Home page</h1>
			</div>
		);
	}
}

function mapStateToProps(state) {
	return { errorMessage: state.auth.errorMessage };
}

export default connect(mapStateToProps)(Home);