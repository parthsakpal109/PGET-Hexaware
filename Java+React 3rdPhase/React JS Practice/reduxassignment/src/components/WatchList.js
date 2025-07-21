import React from "react";
import ProductCard from "./ProductCard";
import { useDispatch } from "react-redux";
import { addToCart } from "../Redux/cartSlice";

const watches = [/* your watch array here */];

const WatchList = () => {
  const dispatch = useDispatch();

  return (
    <div>
      <h2>Watches</h2>
      <div style={{ display: "flex", flexWrap: "wrap" }}>
        {watches.map(watch => (
          <ProductCard
            key={watch.id}
            name={watch.name}
            price={watch.price}
            image={watch.image}
            onAdd={() => dispatch(addToCart(watch))}
          />
        ))}
      </div>
    </div>
  );
};

export default WatchList;
