import React, { Component } from "react";

const Enterprise2Exam = () => {

	return(
		<div>
			<h3>Enterprise 2 exam</h3>
			<p>This project was a group project developed by me and two other class mates.
				We where given the task of creating a complete solution for a cinema. This include displaying new movies,
				make it possible for customers to book tickets for a movie and choose seats themselves.</p>

			<p>We also had to make sure the application could scale if there was to be a lot of traffic on our website.
				Therefore we made sure we could create docker images of all our services and then create replicas of each of the services for load balancing.
				For more information regarding the architecture view the architecture diagram.</p>

			<img
				src={`https://raw.githubusercontent.com/mudasar187/Enterprise_Exam2018/master/docs/imgs/Module%20diagram.png`}
				alt="diagram"
				className="project-img"
			/>
		</div>
	)

};

export default Enterprise2Exam
