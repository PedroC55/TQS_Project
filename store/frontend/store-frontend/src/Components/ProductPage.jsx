import React from "react";
import '../App.css';
import { useLocation } from 'react-router-dom';
import BannerBackground from "../Assets/home-banner-background.png"


export default function ProductPage(props) {
  
  const location = useLocation();

  

  return (
    <div className='home-container'>
        
        <div className='home-banner-container'>
          <div className='home-bannerImage-container'>
            <img src={BannerBackground} alt=""/>
          </div>
          <div className="product-container">
            <div className="product-image-container">
              <img src={location.state.data.image} alt={location.state.data.name} className="product-image" />
            </div>
            <div className="product-details-container">
              <h1 className="product-name">{location.state.data.name}</h1>
              <div className="product-details">
                <p><b>Price:</b> {location.state.data.price}€</p>         
                  <button className="addToCartBttn" >
                    Add To Cart 
                  </button>
              </div>
            </div>
          </div>
        </div>
      </div>
  )
}
