import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [cheeses, setCheeses] = useState([]);

  useEffect(() => {
    fetchCheeses();
  }, []);

  const fetchCheeses = async () => {
    try {
      const response = await fetch('http://localhost:8080/cheeses');
      if (!response.ok) {
        throw new Error('Failed to fetch cheeses');
      }
      const data = await response.json();
      setCheeses(data);
    } catch (error) {
      console.error('Error fetching cheeses:', error);
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Cheese Selection</h1>
        <div className="cheese-container">
          {cheeses.map((cheese) => (
            <div key={cheese.id} className="cheese-card">
              <img src={"/public/favicon.ico"} alt={cheese.name} />
              <h2>{cheese.name}</h2>
              <p>Price per kilo: ${cheese.pricePerKilo}</p>
              <p>Color: {cheese.color}</p>
            </div>
          ))}
        </div>
      </header>
    </div>
  );
}

export default App;