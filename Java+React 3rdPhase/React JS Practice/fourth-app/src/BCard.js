const BCard = ({ Bname, price, qty, city }) => {
  return (
    <div style={{
      border: "1px solid #ccc",
      margin: "10px",
      padding: "10px",
      borderRadius: "8px",
      width: "250px"
    }}>
      <h3>{Bname}</h3>
      <p>Price: ₹{price}</p>
      <p>Quantity: {qty}</p>
      <p>City: {city}</p>
    </div>
  );
};

export default BCard;
