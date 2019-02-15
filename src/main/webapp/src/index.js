import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter} from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import 'react-mdl/extra/material.css';
import 'react-mdl/extra/material.js';


import reducers from './reducers';
import App from './components/App';
import thunk from "redux-thunk";

const store = createStore(reducers, applyMiddleware(thunk));


ReactDOM.render(
	<Provider store={store}>
		<BrowserRouter>
			<App/>
		</BrowserRouter>
	</Provider>,
	document.querySelector('#root')
);