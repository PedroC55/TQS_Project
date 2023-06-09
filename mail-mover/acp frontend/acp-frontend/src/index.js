import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Navbar from './Navbar';
import Main from './Main';
import Login from './Login';
import './global.css';
import { createBrowserRouter , RouterProvider } from 'react-router-dom';



const router = createBrowserRouter([
  {
    path: "/",
    element: <Login/>
  },
  {
    path: "Main/:id",
    element: <Main/>
  }
]
)



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Navbar></Navbar>
    <RouterProvider router={router}/>
  </React.StrictMode>
);
//Main></Main>
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
