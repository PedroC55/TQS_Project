import './App.css';
import Footer from "./Components/Footer"
import Home from './Components/Home';
import About from './Components/About';
import { Products } from './Components/Product-cards';
import products from './products'
import Navbar from './Components/Navbar';
import { useEffect } from 'react';
import {Cart} from './Components/cart.jsx';
import { BrowserRouter as Router, Route,Routes, Link } from 'react-router-dom';


import ProductPage from './Components/ProductPage';

function App() {
    useEffect(() => {
        document.title = "Store";
        
      }, []);
  return (
    
      <div className='App'>
          <Navbar></Navbar>
          <Home/>
          <div className='App'>
                    {products.map((products) => (
                        <Products 
                            id={products.id}
                            image={products.image}
                            name={products.name}
                            price={products.price}
                            totalSales={products.totalSales}
                            timeLeft={products.timeLeft}
                            rating={products.rating}
                        />
                    ))}
                </div>
          <About/>
          <Footer/>
        
        
        
      </div>
    
  );
}

export default App;
