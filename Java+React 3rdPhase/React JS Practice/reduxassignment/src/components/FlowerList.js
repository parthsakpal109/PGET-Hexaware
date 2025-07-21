import React from "react";
import ProductCard from "./ProductCard";
import { useDispatch } from "react-redux";
import { addToCart } from "../Redux/cartSlice";

const flowers = [/* your flower array here */];

const FlowerList = () => {
  const dispatch = useDispatch();

  return (
    <div>
      <h2>Flowers</h2>
      <div style={{ display: "flex", flexWrap: "wrap" }}>
        {flowers.map(flower => (
          <ProductCard
            key={flower.id}
            name={flower.name}
            price={flower.price}
            image={flower.image}
            onAdd={() => dispatch(addToCart(flower))}
          />
        ))}
      </div>
    </div>
  );
};

export default FlowerList;
