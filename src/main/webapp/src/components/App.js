import React, { Component } from 'react';
import { Layout, Header, Navigation, Drawer, Content } from 'react-mdl';
import {Link} from "react-router-dom";
import Main from "./Main";
import './App.css';


class App extends Component {

  close = () => {
    console.log("called")
      var d = document.querySelector('.mdl-layout');
      d.MaterialLayout.toggleDrawer();
  }

  render() {
    return (
      <div className="demo-big-content">
        <Layout>
          <Header className="header-color"
                  title={<Link style={{textDecoration: 'none', color: 'white'}} to="/">Endre Mikal Synnes</Link>} scroll>
            <Navigation>
              <Link to="/resume">Resume</Link>
              <Link to="/projects">Projects</Link>
              <Link to="/github-projects">GitHub Projects</Link>
              <Link to="/contact">Contact</Link>
            </Navigation>
          </Header>
          <Drawer title={<Link onClick={() => this.close()} style={{textDecoration: 'none', color: 'black'}} to="/">Endre Mikal Synnes</Link>}>
            <Navigation>
              <Link onClick={() => this.close()} to="/resume">Resume</Link>
              <Link onClick={() => this.close()} to="/projects">Projects</Link>
              <Link onClick={() => this.close()} to="/github-projects">GitHub Projects</Link>
              <Link onClick={() => this.close()} to="/contact">Contact</Link>
            </Navigation>
          </Drawer>
          <Content>
              <Main/>
          </Content>
        </Layout>
      </div>
    );
  }
}

export default App