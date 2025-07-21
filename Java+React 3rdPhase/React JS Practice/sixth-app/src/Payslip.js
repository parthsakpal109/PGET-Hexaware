import { useState } from "react";

const Payslip = () => {
  const [name, setName] = useState("");
  const [empId, setEmpId] = useState("");
  const [basicSalary, setBasicSalary] = useState("");
  const [totalSalary, setTotalSalary] = useState(null);

  const calculateSalary = () => {
    const basic = parseFloat(basicSalary);
    if (isNaN(basic)) {
      alert("Please enter a valid Basic Salary");
      return;
    }
    const bonus = basic * 0.05;
    const total = basic + bonus;
    setTotalSalary(total.toFixed(2));
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h1>Payslip Calculator</h1>
      <input
        placeholder="Enter Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      /><br /><br />
      <input
        placeholder="Enter Employee ID"
        value={empId}
        onChange={(e) => setEmpId(e.target.value)}
      /><br /><br />
      <input
        placeholder="Enter Basic Salary"
        value={basicSalary}
        onChange={(e) => setBasicSalary(e.target.value)}
      /><br /><br />
      <button onClick={calculateSalary}>Calculate Payslip</button>

      {totalSalary && (
        <div style={{ marginTop: "20px" }}>
          <h2>Payslip Details</h2>
          <p><strong>Name:</strong> {name}</p>
          <p><strong>Employee ID:</strong> {empId}</p>
          <p><strong>Basic Salary:</strong> {basicSalary}</p>
          <p><strong>Bonus (5%):</strong> {(parseFloat(basicSalary) * 0.05).toFixed(2)}</p>
          <p><strong>Total Salary:</strong> {totalSalary}</p>
        </div>
      )}
    </div>
  );
};

export default Payslip;
