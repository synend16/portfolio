import React from "react";
import {Card, CardMenu, CardText, CardTitle} from "react-mdl";


const Project = (props) => {

	const post = props.props;

	if (!post.imgUrl) {
		post.imgUrl = "https://images.pexels.com/photos/546819/pexels-photo-546819.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
	}

	return (
		<Card shadow={5} style={{minWidth: '400', margin: 'auto'}}>
			<CardTitle
			style={{
				height: '176px',
				background: `url(${post.imgUrl}) center / cover`
			}} />
			<CardText className="card-header-text">
				{post.title}
			</CardText>
			<CardText>
				{post.description}
			</CardText>
			<CardMenu style={{color: '#fff'}}>
				<a href={post.url} rel="noopener noreferrer" target="_blank">
					<i className="github-icon fa fa-github-square" aria-hidden="true" />
				</a>
			</CardMenu>
		</Card>
	)
};

export default Project