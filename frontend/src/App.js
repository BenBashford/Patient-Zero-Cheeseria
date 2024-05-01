import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [cheeses, setCheeses] = useState([]);
  const [newCheese, setNewCheese] = useState({ name: '', pricePerKilo: '', color: '', imageUrl: '' });
  const [errorMessage, setErrorMessage] = useState('');

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

  const handleCreateCheese = async () => {
    try {
      if (cheeses.length >= 5) {
        throw new Error('Maximum number of cheeses reached');
      }
      const response = await fetch('http://localhost:8080/cheeses', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newCheese),
      });
      if (!response.ok) {
        throw new Error('Failed to create cheese: Ensure Name, Price, and Color are Provided');
      }
      fetchCheeses(); // Refresh the cheese list after creation
      setNewCheese({ name: '', pricePerKilo: '', color: '', imageUrl: '' }); // Clear the form including imageUrl
    } catch (error) {
      console.error('Error creating cheese:', error.message);
      setErrorMessage(error.message);
      setTimeout(() => {
        setErrorMessage('');
      }, 2000);
    }
  };

  const handleUpdateCheese = async (id, updatedCheese) => {
    try {
      const response = await fetch(`http://localhost:8080/cheeses/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedCheese),
      });
      if (!response.ok) {
        throw new Error('Failed to update cheese: Ensure Valid Input is Provided');
      }
      fetchCheeses(); // Refresh the cheese list after update
    } catch (error) {
      console.error('Error updating cheese:', error.message);
      setErrorMessage(error.message);
      setTimeout(() => {
        setErrorMessage('');
      }, 2000);
    }
  };

  const handleDeleteCheese = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/cheeses/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete cheese');
      }
      fetchCheeses(); // Refresh the cheese list after deletion
    } catch (error) {
      console.error('Error deleting cheese:', error);
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Cheese Selection</h1>
        {errorMessage ? (
          <h2 style={{ color: 'red' }}>{errorMessage}</h2>
        ) : (
          <>
            <h2>Add New Cheese:</h2>
          </>
        )}
        <input type="text" placeholder="Name" value={newCheese.name} onChange={(e) => setNewCheese({ ...newCheese, name: e.target.value })} />
        <input type="text" placeholder="Price per kilo" value={newCheese.pricePerKilo} onChange={(e) => setNewCheese({ ...newCheese, pricePerKilo: e.target.value })} />
        <input type="text" placeholder="Color" value={newCheese.color} onChange={(e) => setNewCheese({ ...newCheese, color: e.target.value })} />
        <input type="text" placeholder="Image URL" value={newCheese.imageUrl} onChange={(e) => setNewCheese({ ...newCheese, imageUrl: e.target.value })} />
        <button onClick={handleCreateCheese} disabled={cheeses.length >= 5}>Add Cheese</button>
        <div className="cheese-container">
          {cheeses.map((cheese) => (
            <div key={cheese.id} className="cheese-card">
              <img src={cheese.imageUrl} alt={cheese.name} />
              <h2>{cheese.name}</h2>
              <p>Price per kilo: ${cheese.pricePerKilo}</p>
              <p>Color: {cheese.color}</p>
              <button onClick={() => handleDeleteCheese(cheese.id)}>Delete</button>
              <button onClick={() => {
                const updatedName = prompt('Enter updated name:', cheese.name);
                if (updatedName !== null) {
                  handleUpdateCheese(cheese.id, { ...cheese, name: updatedName });
                }
              }}>Update Name</button>
              <button onClick={() => {
                const updatedPrice = prompt('Enter updated price:', cheese.pricePerKilo);
                if (updatedPrice !== null) {
                  handleUpdateCheese(cheese.id, { ...cheese, pricePerKilo: parseFloat(updatedPrice) });
                }
              }}>Update Price</button>
              <button onClick={() => {
                const updatedColor = prompt('Enter updated color:', cheese.color);
                if (updatedColor !== null) {
                  handleUpdateCheese(cheese.id, { ...cheese, color: updatedColor });
                }
              }}>Update Color</button>
              <button onClick={() => {
                const updatedImageUrl = prompt('Enter updated image URL:', cheese.imageUrl);
                if (updatedImageUrl !== null) {
                  handleUpdateCheese(cheese.id, { ...cheese, imageUrl: updatedImageUrl });
                }
              }}>Update Image URL</button>
            </div>
          ))}
        </div>
      </header>
    </div>
  );
}

export default App;