import React from 'react'
import Navbar from "./Navbar"
import BannerImage from "../Assets/paris.png"
import { FiArrowRight} from "react-icons/fi"
import BannerBackground from "../Assets/home-banner-background.png"

export const Home = () => {
  return (
    <div className='home-container'>
        <Navbar />
        <div className='home-banner-container'>
          <div className='home-bannerImage-container'>
            <img src={BannerBackground} alt=""/>
          </div>
          <div className='home-text-section'>
            <h1 className='primary-heading'>
              The Dunk Low Paris!! Now Available!
            </h1>
            <button className='secondary-button'>
              Order Now <FiArrowRight/>
            </button>
          </div>
          <div className='home-image-container'>
            <img src={BannerImage} alt=""/>
          </div>
        </div>
    </div>
  )
}

export default Home;