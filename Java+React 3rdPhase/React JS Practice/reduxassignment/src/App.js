import React from "react";
import FlowerList from "./components/FlowerList";
import WatchList from "./components/WatchList";
import Billing from "./components/Billing";

function App() {
  return (
    <div style={{ padding: "20px" }}>
      <h1>🛍️ Product Cart</h1>
      <FlowerList />
      <WatchList />
      <Billing />
    </div>
  );
}

export default App;
