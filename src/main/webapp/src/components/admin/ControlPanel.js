import React, { Component } from "react";
import { Tabs, Tab} from 'react-mdl';
import ExperienceForm from "./ExperienceForm";

class ControlPanel extends Component {

	constructor(props) {
		super(props);

		this.state = {
			activeTab: 0
		}
	}


	render() {
		return (
			<div className="page-content">
				<Tabs className="tool-bar" activeTab={this.state.activeTab} onChange={(tabId) => this.setState({ activeTab: tabId })} ripple>
					<Tab>Experience</Tab>
					<Tab>Projects</Tab>
					<Tab>Traffic</Tab>
				</Tabs>
				{this.toggleView()}
			</div>
		);
	}

	toggleView(){
		if (this.state.activeTab === 0) {
			return <ExperienceForm/>
		} else if (this.state.activeTab === 1){
			return <div>Projects</div>
		} else {
			return <div>Traffic</div>
		}
	}
}

export default ControlPanel
