import React from "react";

const ProductCard = ({ name, price, image, onAdd }) => {
  return (
    <div style={{ border: "1px solid #ddd", padding: "10px", margin: "10px", width: "200px" }}>
      <img src={image} alt={name} width="100%" />
      <h3>{name}</h3>
      <p>Price: ₹{price}</p>
      <button onClick={onAdd}>Add to Cart</button>
    </div>
  );
};

export default ProductCard;
