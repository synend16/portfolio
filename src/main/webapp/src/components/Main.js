import React from "react";
import {Route, Switch} from "react-router-dom";
import About from "./About";
import Projects from "./Projects";
import Home from "./Home";
import Contact from "./Contact";

const Main = () => (
		<Switch>
			<Route path="/about" exact component={About} />
			<Route path="/projects" exact component={Projects} />
			<Route path="/contact" exact component={Contact} />
			<Route exact path="/" component={Home} />
		</Switch>
	);

export default Main;

