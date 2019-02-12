import React, { Component } from 'react';
import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
const imagesPath = process.env.PUBLIC_URL + "/assets/";


class Contact extends Component {
	render() {
		return(
			<div className="contact-body">
				<Grid className="contact-grid">
					<Cell col={6}>
						<h2>Endre M. Synnes</h2>
						<img
							src={`${imagesPath}avatar.jpg`}
							alt="avatar"
							style={{height: '200px'}}
						/>
						<p style={{ width: '75%', margin: 'auto', paddingTop: '1em'}}>
							I am currently working as a Software developer at Aera Payment & Identification.
							At the same time I'm finishing my bachelors degree in software development.
							I will describe my self as a full stack developer, with the main focus on back-end development.
							At work I have been developing web applications using SpringBoot with Java and front-end development
							in React. At the same time I have learned other programming languages and frameworks at school such as
							Kotlin, Angular, Ionic, Android development (in Java), C#, Swift, NodeJs, etc.
						</p>
					</Cell>
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