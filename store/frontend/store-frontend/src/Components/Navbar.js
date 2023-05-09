import React, { useState } from 'react'
import Logo from "../Assets/Logo.png";
import Search from "../Assets/search.png"
import { BsCart2 } from "react-icons/bs";
import { HiOutlineBars3 } from "react-icons/hi2";

import {
  Box,
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,

}from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";
import CommentRoundedIcon from "@mui/icons-material/CommentRounded";
import PhoneRoundedIcon from "@mui/icons-material/PhoneRounded";
import ShoppingCartRoundedIcon from "@mui/icons-material/ShoppingCartCheckoutRounded";
import Info from '@mui/icons-material/Info';



const Navbar = () => {

  const [openMenu,setOpenMenu] = useState(false)
  const menuOptions = [
    {
      text:"Home",
      icon:<HomeIcon/>
    },
    {
      text:"About",
      icon:<InfoIcon/>
    },
    {
      text:"Testimonials",
      icon:<CommentRoundedIcon/>
    },
    {
      text:"Contact",
      icon:<PhoneRoundedIcon/>
    },
    
    {
      text:"Cart",
      icon:<ShoppingCartRoundedIcon/>
    },
  ];


  return (
    <nav>
      <div className='nav-logo-container'>
        <img src={Logo} alt="" />
      </div>
      <div className='search'>
        <input type="text" className='search_input' placeholder='Enter your shoe..'></input>
        <button className='search_button'><img src={Search}/></button>
      </div>
      <div className='navbar-links-container'>
        <a href=''>Home</a>
        <a href=''>About</a>
        <a href=''>Testimonials</a>
        <a href=''>Contact</a>
        <a href=''>
          <BsCart2 className='navbar-cart-icon'/>
        </a>
        <button className='primary-button'>Booking Now</button>
      </div>
      <div className='navbar-menu-container'>
        <HiOutlineBars3 onClick={() => setOpenMenu(true)}/>
      </div>
      <Drawer open={openMenu} onClose={() => setOpenMenu(false)}
      anchor="right">
        <Box sx={{ width: 250 }}
        role="presentation"
        onClick={() => setOpenMenu(false)}
        onKeyDown={() => setOpenMenu(false)}
        >
          
          <List>
            {menuOptions.map((item) => (
              <ListItem key={item.text} disablePadding>
                <ListItemButton>
                  <ListItemIcon>{item.icon}</ListItemIcon>
                  <ListItemText primary={item.text}></ListItemText>
                </ListItemButton>
              </ListItem>
            ))}
          </List>
        </Box>
      </Drawer>
    </nav>
  )
}

export default Navbar