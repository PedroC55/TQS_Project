import './App.css';
import Footer from "./Components/Footer"
import Home from './Components/Home';
import About from './Components/About';
import { Products } from './Components/Product-cards';
import products from './products'
import { BrowserRouter as Router, Route,Routes, Link } from 'react-router-dom';

import ProductPage from './Components/ProductPage';

function App() {
  return (
    
      <div className='App'>
        <Home/>
        <div className='App'>
                  {products.map(products => (
                      <Products 
                          key={products.id}
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
        <Routes>
          <Route path="/ProductPage" component={ProductPage} />
        </Routes>
        
      </div>
    
  );
}

export default App;
