import React, { Component } from "react";
import { Grid, Cell } from 'react-mdl';


const QuizGame = () => {

	return(
		<Grid>
			<Cell col={12}>
				<h3>Quiz Game <a href="https://github.com/endre-synnes/WebApiExam" rel="noopener noreferrer" target="_blank">
					<i className="github-icon-black fa fa-github-square" aria-hidden="true" />
				</a></h3>
			</Cell>
			<Cell col={8}>
				<img
					src={`https://i.imgur.com/qrypyzv.png`}
					alt="diagram"
					className="project-img"
				/>
			</Cell>
			<Cell col={4}>
				<p>This project was given as an exam in the course Web & API design.
					The task was to create an online quiz game, where people could sign up and play against each other.
				</p>

				<br/>

				<p>To make this application work we had to implement Passport to handle the authentication,
					and tokens stored in a cookie. Websocket is used to send questions to the user and guesses back to the server.
				</p>

				<h5>Technologies</h5>
				<ul>
					<li>NodeJS</li>
					<li>React</li>
					<li>Webpack</li>
					<li>Passport</li>
					<li>Websocket</li>
					<li>Docker</li>
				</ul>
				<br/>
				<p>
					To make deployment easier I used Docker. By using docker I could easily create docker images of the application
					and the database. In this application I'm using MongoDB to store all the data.
				</p>
			</Cell>
			<Cell col={12}>
				<p>In this application the first player to join the queue can choose when to start the game,
					there is no limit for how many people can join a single game. When a game is started no one can join this game.
					A new queue will be created for any new players logging in.</p>
			</Cell>
			<Cell col={4}>
				<p>
					At the right you can see an example question. Every question is multiple choice, with four alternatives.
					Players have 10 seconds to answer each question.
					The winner is the player who used the least time to answer the questions.
				</p>
			</Cell>
			<Cell col={8}>
				<img
					src={`https://i.imgur.com/QPuoURu.png`}
					alt="diagram"
					className="project-img"
				/>
			</Cell>


			
		</Grid>
	)

};

export default QuizGame
