// App.js
import { useState } from "react";
import { MyContext } from "./MyContext";
import BCard from "./BCard";

const App = () => {
  const [age, setAge] = useState(21);
  const [name, setName] = useState("Ajay");

  return (
    <MyContext.Provider value={{ age, name }}>
      <BCard />
    </MyContext.Provider>
  );
};

export default App;
