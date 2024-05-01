import React, { useState } from 'react';

// Form component for adding a new cheese
function AddCheeseForm({ onAddCheese, disabled }) {
    // State variable for the new cheese
    const [newCheese, setNewCheese] = useState({ name: '', pricePerKilo: '', color: '', imageUrl: '' });

    // Function to handle input change
    const handleChange = (e) => {
        const { name, value } = e.target;
        setNewCheese({ ...newCheese, [name]: value });
    };

    // Function to handle form submission
    const handleSubmit = () => {
        onAddCheese(newCheese);
        // Reset form fields
        setNewCheese({ name: '', pricePerKilo: '', color: '', imageUrl: '' });
    };

    return (
        <div className="add-cheese-form">
            <h2>Add New Cheese:</h2>
            <input type="text" name="name" placeholder="Name" value={newCheese.name} onChange={handleChange} />
            <input type="text" name="pricePerKilo" placeholder="Price per kilo" value={newCheese.pricePerKilo} onChange={handleChange} />
            <input type="text" name="color" placeholder="Color" value={newCheese.color} onChange={handleChange} />
            <input type="text" name="imageUrl" placeholder="Image URL" value={newCheese.imageUrl} onChange={handleChange} />
            <button onClick={handleSubmit} disabled={disabled}>Add Cheese</button>
        </div>
    );
}

export default AddCheeseForm;