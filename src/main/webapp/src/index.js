import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, HashRouter, Route} from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import promise from "redux-promise";


import reducers from './reducers';
import App from './components/App';
import Home from "./components/Home";
import About from "./components/About";
import Projects from "./components/Projects";
import thunk from "redux-thunk";

const store = createStore(reducers, applyMiddleware(thunk));


ReactDOM.render(
	<Provider store={store}>
		<BrowserRouter>
			<App>
				<Route path="/about" exact component={About} />
				<Route path="/projects" exact component={Projects} />
				<Route path="/" exact component={Home} />
			</App>
		</BrowserRouter>
	</Provider>,
	document.querySelector('#root')
);