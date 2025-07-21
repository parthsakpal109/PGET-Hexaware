import React from "react";
import { useSelector } from "react-redux";

const Billing = () => {
  const cartItems = useSelector(state => state.cart.cartItems);
  const total = cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);

  return (
    <div>
      <h2>Billing Summary</h2>
      {cartItems.length === 0 ? <p>No items in cart</p> : (
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>Name</th><th>Price</th><th>Quantity</th><th>Total</th>
            </tr>
          </thead>
          <tbody>
            {cartItems.map(item => (
              <tr key={item.id}>
                <td>{item.name}</td>
                <td>₹{item.price}</td>
                <td>{item.quantity}</td>
                <td>₹{item.price * item.quantity}</td>
              </tr>
            ))}
            <tr>
              <td colSpan="3"><strong>Grand Total</strong></td>
              <td><strong>₹{total}</strong></td>
            </tr>
          </tbody>
        </table>
      )}
    </div>
  );
};

export default Billing;
