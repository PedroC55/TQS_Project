import React from "react";
import '../App.css';
import { useContext } from 'react';
import { useLocation } from 'react-router-dom';
import BannerBackground from "../Assets/home-banner-background.png"
import {FaStar} from 'react-icons/fa';
import { ShopContext } from "../context/shop-context";
import { BrowserRouter as Router, Route, Link, useNavigate } from 'react-router-dom';
import Navbar from './Navbar';
import { useEffect } from 'react';

export default function ProductPage(props) {
  
  const { addToCart, cartItems } = useContext(ShopContext);

  
  const location = useLocation();

  useEffect(() => {
    document.title = "Product";
    
  }, []);


  return (
    <div className='home-container'>
        <Navbar></Navbar>
        <div className='home-banner-container'>
          <div className='home-bannerImage-container'>
            <img src={BannerBackground} alt=""/>
          </div>
          <div className="product-container">
            <div className="product-image-container">
              <img src={location.state.data.image} alt={location.state.data.name} className="product-image" />
            </div>
            <div className="product-details-container">
              <h5 className="product-name">{location.state.data.name}</h5>
              <div className="product-details">
                <p><b>Price:</b> {location.state.data.price}€</p>    
                <p><b>Rating:</b> <FaStar/> {location.state.data.rating}</p>
                <p><b>Time left:</b> {location.state.data.timeLeft} days</p> 
                  {/*<button className="product-details-btn" onClick={() => addToCart(location.state.data.id)} >
                    Add To Cart 
                  </button>*/}
                  <Link to={`/Checkout/${location.state.data.name}`} state={{data: location}}>
                    <button name="buy_now" className="product-details-btn" onClick={() => addToCart(location.state.data.id)} >
                      Buy Now
                    </button>
                  </Link>
                  
                <p><b>Total sales:</b> {location.state.data.totalSales}</p> 
                
              </div>
            </div>
          </div>
          
        </div>
        
      </div>
  )
}
