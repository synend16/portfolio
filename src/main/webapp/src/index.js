import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, HashRouter, Route} from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import reduxThunk from 'redux-thunk';

import reducers from './reducers';
import App from './components/App';
import Home from "./components/Home";
import About from "./components/About";
import SignUp from "./components/auth/SignUp";

const store = createStore(
	reducers,
	{
		auth: { authenticated: localStorage.getItem('username') }
	},
	applyMiddleware(reduxThunk)
);

ReactDOM.render(
	<Provider store={store}>
		<BrowserRouter>
			<App>
				<Route path="/about" exact component={About} />
				<Route path="/" exact component={Home} />
				<Route path="/sign-up" exact component={SignUp} />
			</App>
		</BrowserRouter>
	</Provider>,
	document.querySelector('#root')
);