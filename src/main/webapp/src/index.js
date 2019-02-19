import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, HashRouter} from 'react-router-dom';
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
		<HashRouter>
			<App/>
		</HashRouter>
	</Provider>,
	document.querySelector('#root')
);