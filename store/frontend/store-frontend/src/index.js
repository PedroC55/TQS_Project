import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { createBrowserRouter, RouterProvider,   BrowserRouter } from 'react-router-dom'
import ProductPage from './Components/ProductPage';
import Navbar from './Components/Navbar';
import {Cart} from './Components/cart.jsx';
import Checkout from './Components/Checkout.js';
import {ProductsPage} from './Components/ProductsPage';
import { ShopContextProvider } from "./context/shop-context";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";





const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <ShopContextProvider>
      <Router>
        <Navbar></Navbar>

        <Routes>
              <Route path="/" element={<App/>} />
              <Route path="/ProductPage/:productId" element={<ProductPage/>} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/Checkout/:productName" element={<Checkout />} />
              <Route path="/AllProducts" element={<ProductsPage />} />
        </Routes>
      </Router>
    </ShopContextProvider>
  </React.StrictMode>
);

reportWebVitals();