import React, { useState } from 'react';

// Calculator component for calculating the price of cheese
function Calculator({ cheeses }) {
    // State variables for selected cheese, weight, and total price
    const [selectedCheese, setSelectedCheese] = useState('');
    const [weight, setWeight] = useState('');
    const [totalPrice, setTotalPrice] = useState(0);

    // Function to calculate the total price
    const handleCalculatePrice = () => {
        const selectedCheeseObj = cheeses.find(cheese => cheese.name === selectedCheese);
        if (selectedCheeseObj && weight) {
            const price = selectedCheeseObj.pricePerKilo * parseFloat(weight);
            setTotalPrice(price);
        }
    };

    return (
        <div className="calculator">
            <h2>Calculator</h2>
            <select value={selectedCheese} onChange={(e) => setSelectedCheese(e.target.value)}>
                <option value="">Select Cheese</option>
                {cheeses.map((cheese) => (
                    <option key={cheese.id} value={cheese.name}>{cheese.name}</option>
                ))}
            </select>
            <input type="number" placeholder="Weight (kg)" value={weight} onChange={(e) => setWeight(e.target.value)} />
            <button onClick={handleCalculatePrice}>Calculate Price</button>
            {totalPrice > 0 && (
                <p className="price">Total Price: ${totalPrice.toFixed(2)}</p>
            )}
        </div>
    );
}

export default Calculator;