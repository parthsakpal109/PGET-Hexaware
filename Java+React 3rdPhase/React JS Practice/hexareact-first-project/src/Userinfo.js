import React, { useState } from "react";

export default function MyComponent() {
  let [Name, setName] = useState();
  let [age, setAge] = useState();
  let [salary, setSalary] = useState();
  let [department, setDepartment] = useState();

  const handleName = (e) => {
    setName(e.target.value);
  };

  const handleAge = (e) => {
    setAge(e.target.value);
  };

  const handleSalary = (e) => {
    setSalary(e.target.value);
  };

  const handleDepartment = (e) => {
    setDepartment(e.target.value);
  };

  return (
    <div className="cont">
      <input type="text" placeholder="Enter your name" onChange={handleName} />

      <input type="text" placeholder="Enter your age" onChange={handleAge} />

      <input type="text" placeholder="Enter your salary" onChange={handleSalary} />

      <input type="text" placeholder="Enter your department" onChange={handleDepartment} />

      <h1>Name: {Name}</h1>
      <h2>Age: {age}</h2>
      <h2>Salary: {salary}</h2>
      <h2>Department: {department}</h2>
    </div>
  );
}
