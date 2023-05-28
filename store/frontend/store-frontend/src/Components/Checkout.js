import React from "react";
import '../App.css';
import { useContext } from 'react';
import { useLocation, Link } from 'react-router-dom';
import BannerBackground from "../Assets/home-banner-background.png"
import {FaStar} from 'react-icons/fa';


import Form from "../classes/Form";


export default function Checkout(props) {
  


  
  const location = useLocation();
  console.log(location.state.data.state.data.name);




  return (
    <Form></Form>
  )
}