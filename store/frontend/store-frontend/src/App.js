import logo from './logo.svg';
import './App.css';
import Home from './Components/Home';
import About from './Components/About';
import { Products } from './Components/Product-cards';
import products from './products'

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
    </div>
  );
}

export default App;
