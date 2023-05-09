import React from 'react'
import AboutBackground from "../Assets/about-background.png";
import AboutBackgroundImage from "../Assets/about.jpeg";


const About = () => {
    return (
        <div className="about-section-container">
          <div className="about-background-image-container">
            <img src={AboutBackground} alt="" />
          </div>
          <div className="about-section-image-container">
            <img src={AboutBackgroundImage} alt="" />
          </div>
          <div className="about-section-text-container">
            <p className="primary-subheading">About</p>
            <h1 className="primary-heading">
                The Best Marketplace
            </h1>
            <p className="primary-text">
            Our mission is to provide access to the world’s most coveted items in the smartest way possible. Buy the hottest sneakers you can find.
            </p>
            <div className="about-buttons-container">
              <button className="secondary-button">Learn More</button>
              
            </div>
          </div>
        </div>
    );
}

export default About