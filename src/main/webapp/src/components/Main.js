import React from "react";
import {Route, Switch} from "react-router-dom";
import About from "./About";
import GitHubProjects from "./GitHubProjects";
import Home from "./Home";
import Contact from "./Contact";
import Resume from "./Resume";
import Projects from "./Projects";
import ControlPanel from "./admin/ControlPanel";

const Main = () => (
		<Switch>
			<Route path="/about" exact component={About} />
			<Route path="/github-projects" exact component={GitHubProjects} />
			<Route path="/projects" exact component={Projects} />
			<Route path="/contact" exact component={Contact} />
			<Route path="/resume" exact component={Resume} />
			<Route exact path="/" component={Home} />
			<Route exact path="/admin" component={ControlPanel} />
		</Switch>
	);

export default Main;

