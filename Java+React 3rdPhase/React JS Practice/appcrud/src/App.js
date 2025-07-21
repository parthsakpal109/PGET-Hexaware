import axios from 'axios';
import './App.css';
import { useEffect, useState } from 'react';

function App() {
  let [msg, setMsg] = useState("");
  let [actno, setAct] = useState();
  let [name, setName] = useState();
  let [balance, setBalance] = useState();
  let [type, setType] = useState();
  let [amt, setAmt] = useState();
  let [users, setUsers] = useState([]);
  let [flag, setFlag] = useState(true); // used to trigger re-fetch

  useEffect(() => {
    showAll();
  }, [flag]); // re-run showAll whenever flag changes

  const saveData = async () => {
    let data = { actno, name, balance, type };
    try {
      const res = await axios.post("http://localhost:8085/saveData", data);
      console.log(res);
      setMsg("✅ Data saved successfully!");
    } catch (err) {
      console.error(err);
      setMsg("❌ Failed to save data.");
    }
    setFlag(!flag); // trigger re-fetch
  };

  const RemoveData = async () => {
    try {
      let res = await axios.delete(`http://localhost:8085/deleteAc/${actno}`);
      setMsg(res.data);
    } catch (err) {
      console.error(err);
      setMsg("❌ Failed to delete.");
    }
    setFlag(!flag); // trigger re-fetch
  };

  const Deposit = async () => {
    try {
      let res = await axios.put(`http://localhost:8085/deposit/${actno}/${amt}`);
      setMsg(res.data);
    } catch (err) {
      console.error(err);
      setMsg("❌ Deposit failed.");
    }
    setFlag(!flag); // trigger re-fetch
  };

  const searchByAct = async () => {
    try {
      let res = await axios.get(`http://localhost:8085/getdataAc/${actno}`);
      console.log(res.data);
      setMsg(`Found: ${res.data.name} - Balance: ${res.data.balance}`);
    } catch (err) {
      console.error(err);
      setMsg("❌ Account not found.");
    }
    setFlag(!flag); // trigger re-fetch
  };

  const showAll = async () => {
    try {
      let res = await axios.get("http://localhost:8085/getUsers");
      console.log(res.data);
      setUsers(res.data);
    } catch (err) {
      console.error(err);
      setMsg("❌ Failed to fetch accounts.");
      setUsers([]); // show no accounts if fetch fails
    }
  };

  return (
    <div className="cont">
      <h2>Account Management</h2>
      <input type="text" placeholder='Enter Account No' onChange={(e) => setAct(e.target.value)} />
      <input type="text" placeholder='Enter Name' onChange={(e) => setName(e.target.value)} />
      <input type="number" placeholder='Enter Balance' onChange={(e) => setBalance(e.target.value)} />
      <input type="text" placeholder='Enter Type' onChange={(e) => setType(e.target.value)} />
      <input type="number" placeholder='Enter Amount' onChange={(e) => setAmt(e.target.value)} />

      <h3 style={{ color: msg.startsWith("❌") ? "red" : "green" }}>{msg}</h3>

      <button onClick={saveData}>Save Data</button>
      <button onClick={RemoveData}>Remove Data</button>
      <button onClick={Deposit}>Deposit</button>
      <button onClick={searchByAct}>Search by Account No</button>

      <h4>Accounts:</h4>
      {users.length > 0 ? (
        users.map((temp) => (
          <div key={temp.actno} className="account-card">
            <p>
              <strong>Acct No:</strong> {temp.actno} | <strong>Name:</strong> {temp.name} | <strong>Balance:</strong> ₹{temp.balance}
            </p>
          </div>
        ))
      ) : (
        <p>No accounts found.</p>
      )}
    </div>
  );
}

export default App;
