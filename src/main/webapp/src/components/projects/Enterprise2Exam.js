import React from "react";
import { Grid, Cell } from 'react-mdl';


const Enterprise2Exam = () => {

	return(
		<Grid>
			<Cell col={12}>
				<h3>Cinema <a href="https://github.com/mudasar187/Enterprise_Exam2018" rel="noopener noreferrer" target="_blank">
					<i className="github-icon-black fa fa-github-square" aria-hidden="true" />
				</a></h3>
			</Cell>
			<Cell col={4}>
				<p>This project was given as an exam in the course Enterprise 2. It is developed by me and two other class mates.
					We where given the task of creating a complete solution for a cinema. This include displaying new movies,
					make it possible for customers to book tickets for a movie and choose seats themselves.</p>

				<br/>
				<p>
					The application is developed with a Micro Service architecture in mind. We use Redis to store the active sessions, 
					the session is stored as a http-only cookie.
				</p>
				<br/>
				<p>
					For communication between services we use normal http calls, but when a user is registered in the authentication service
					 we pass the user information to a user service using RabbitMQ.
				</p>
			</Cell>
			<Cell col={8}>
				<img
					src={`https://raw.githubusercontent.com/mudasar187/Enterprise_Exam2018/master/docs/imgs/Module%20diagram.png`}
					alt="diagram"
					className="project-img"
				/>
			</Cell>
			<Cell col={12}>
			<p>We also had to make sure the application could scale if there was to be a lot of traffic on our website.
					Therefore we made sure we could create docker images of all our services and then create replicas of each of the services for load balancing.
					For more information regarding the architecture view the architecture diagram.</p>
			</Cell>
			
		</Grid>
	)

};

export default Enterprise2Exam
