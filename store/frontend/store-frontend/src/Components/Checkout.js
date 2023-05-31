import React from "react";
import '../App.css';
import { useContext } from 'react';
import { useLocation, Link } from 'react-router-dom';
import BannerBackground from "../Assets/home-banner-background.png"
import {FaStar} from 'react-icons/fa';
import Navbar from './Navbar';

import Form from "../classes/Form";


export default function Checkout(props) {
  


  
  const location = useLocation();
  




  return (
    <div className="App">
      <Navbar></Navbar>
      <Form message={location}></Form>
    </div>
  )
}