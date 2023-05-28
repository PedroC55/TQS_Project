import '../App.css';
import Footer from "./Footer"

import { Products } from './Product-cards';
import products from '../products'

import BannerBackground from "../Assets/home-banner-background.png"



export function ProductsPage(props) {
  return (
    
    <div className='App'>

        <div className='home-banner-container'>

            <div className='home-bannerImage-container'>
              <img src={BannerBackground} alt=""/>
            </div>
            
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

                        
                        
                        
        </div>
    </div>
    
  );
}

