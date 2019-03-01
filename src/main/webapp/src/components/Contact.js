import React, { Component } from 'react';
import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';


class Contact extends Component {
	render() {
		return(
			<div className="contact-body">
				<Grid className="contact-grid">
					<Cell col={6}>
						<h2>Contact Me</h2>
						<hr/>
						<div className="contact-list">
							<List>
								<ListItem>
									<ListItemContent style={{fontSize: '16px', fontFamily: 'Anton'}}>
										<i className="fa fa-phone-square" aria-hidden="true"/>
									</ListItemContent>
									<ListItemContent>
										<a className="link-style" href={`tel:+47 984 05 532`}>+47 984 05 532</a>
									</ListItemContent>
								</ListItem>
								<ListItem>
									<ListItemContent style={{fontSize: '14px', fontFamily: 'Anton'}}>
										<i className="fa fa-envelope" aria-hidden="true"/>
									</ListItemContent>
									<ListItemContent>
										<a className="link-style" href={`mailto:endre.m.synnes@gmail.com`}>endre.m.synnes@gmail.com</a>
									</ListItemContent>
								</ListItem>
							</List>
						</div>
					</Cell>
				</Grid>
			</div>
		)
	}
}
export default Contact;