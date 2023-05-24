import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { createBrowserRouter, RouterProvider,   BrowserRouter } from 'react-router-dom'
import ProductPage from './Components/ProductPage';
import Navbar from './Components/Navbar';
const router = createBrowserRouter([
  {
    path: "/",
    element: <App/>
  },
  {
    path:"/ProductPage/:productId",
    element: <ProductPage/>
  },


])



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Navbar></Navbar>
    <RouterProvider router={router}/>
  </React.StrictMode>
);

reportWebVitals();