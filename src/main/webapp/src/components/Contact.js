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
										+47 984 05 532
									</ListItemContent>
								</ListItem>
								<ListItem>
									<ListItemContent style={{fontSize: '14px', fontFamily: 'Anton'}}>
										<i className="fa fa-envelope" aria-hidden="true"/>
									</ListItemContent>
									<ListItemContent>
										endre.m.synnes@gmail.com
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