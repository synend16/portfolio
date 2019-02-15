import React from "react";
import {Route, Switch} from "react-router-dom";
import About from "./About";
import Projects from "./Projects";
import Home from "./Home";
import Contact from "./Contact";
import Resume from "./Resume";

const Main = () => (
		<Switch>
			<Route path="/about" exact component={About} />
			<Route path="/projects" exact component={Projects} />
			<Route path="/contact" exact component={Contact} />
			<Route path="/resume" exact component={Resume} />
			<Route exact path="/" component={Home} />
		</Switch>
	);

export default Main;

