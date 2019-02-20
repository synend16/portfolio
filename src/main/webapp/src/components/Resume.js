import React, { Component } from 'react';
import { Grid, Cell } from 'react-mdl';
import Experience from "./sub/Experience";
import { getExperiences } from "../actions/experiences";
import { FETCH_EXPERIENCES_WORK, FETCH_EXPERIENCES_EDUCATION } from "../actions/types";
import {connect} from "react-redux";
import Skills from "./sub/Skills";
const imagesPath = process.env.PUBLIC_URL + "/assets/";

class Resume extends Component {


  componentDidMount() {
    this.props.getExperiences(FETCH_EXPERIENCES_WORK, "WORK");
    this.props.getExperiences(FETCH_EXPERIENCES_EDUCATION, "EDUCATION");
  }

  render() {
    console.log(this.props.education);
    console.log(this.props.work);

    return (
      <div>
        <Grid>
          <Cell col={4}>
            <div style={{textAlign: 'center'}}>
              <img
                src={`${imagesPath}avatar.jpg`}
                alt="avatar"
                style={{height: '250px'}}
              />
            </div>

            <h3 style={{paddingTop: '1em'}}>Endre Mikal Synnes</h3>
            <h4 style={{color: 'grey'}}>Software Developer</h4>
            <hr style={{borderTop: '3px solid #833fb2', width: '50%'}}/>
            <p>
              I am currently working as a Software developer at Aera Payment & Identification.
              At the same time I'm finishing my bachelors degree in software development.
              I will describe myself as a full stack developer, with the main focus on back-end development.
              At work I have been developing web applications using SpringBoot with Java and front-end development
              in React. At the same time I have learned other programming languages and frameworks at school such as
              Kotlin, Angular, Ionic, Android development (in Java), C#, Swift, NodeJs, etc.
            </p>
            <hr style={{borderTop: '3px solid #833fb2', width: '50%'}}/>
            <h5>Address</h5>
            <p>Løkkalia 12 B 0783 Oslo</p>
            <h5>Phone</h5>
            <p>+47 984 05 532</p>
            <h5>Email</h5>
            <p>endre.m.synnes@gmail.com</p>
            <h5>Github</h5>
            <p>https://github.com/synend16</p>
            <hr style={{borderTop: '3px solid #833fb2', width: '50%'}}/>
          </Cell>
          <Cell className="resume-right-col" col={8}>
            <h2>Education</h2>
            {this.props.education ?
              this.props.education.data.list.map( e =>
                <Experience
                startYear={e.startYear}
                endYear={e.endYear}
                title={e.title}
                description={e.description}/>
              )
              : <div>No Content</div>
            }
            <h2>Experience</h2>
            {this.props.work ?
              this.props.work.data.list.map( e =>
                <Experience
                startYear={e.startYear}
                endYear={e.endYear}
                title={e.title}
                description={e.description}/>
              )
              : <div>No Content</div>
            }
            <h2>Skills</h2>
            <Skills
              skill={"Java"}
              progress={"80"}
            />
            <Skills
              skill={"Kotlin"}
              progress={"80"}
            />
            <Skills
              skill={"React"}
              progress={"50"}
            />
            <Skills
              skill={"Swift"}
              progress={"30"}
            />
            <Skills
              skill={"C#"}
              progress={"40"}
            />
            <Skills
              skill={"Docker"}
              progress={"60"}
            />
            <Skills
              skill={"Spring Boot"}
              progress={"80"}
            />
          </Cell>
        </Grid>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  console.log(state);
  return {
    education: state.experiences.education,
    work: state.experiences.work
  };
};

export default connect(mapStateToProps, { getExperiences })(Resume);