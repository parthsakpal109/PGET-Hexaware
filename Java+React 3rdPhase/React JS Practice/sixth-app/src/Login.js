import { useState } from "react";
import "./App.css";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [Id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [newPass, setNewPass] = useState("");
  const nav = useNavigate();

  const handleUserId = (e) => setId(e.target.value);
  const handleUserPassword = (e) => setPassword(e.target.value);
  const handleNewPassword = (e) => setNewPass(e.target.value);

  const Signup = () => {
    localStorage.setItem(Id, password);
    alert("User Registered Successfully");
  };

  const SignIn = () => {
    let p = localStorage.getItem(Id);
    if (p === password) {
      let data = { Id, password };
      localStorage.setItem("data", JSON.stringify(data)); // store as JSON string
      nav("/welcome"); 
    } else {
      alert("Incorrect Password. Try again.");
    }
  };

  const UpdatePassword = () => {
    let existing = localStorage.getItem(Id);
    if (existing) {
      localStorage.setItem(Id, newPass);
      alert("Password Updated Successfully");
      setNewPass("");
    } else {
      alert("User ID not found");
    }
  };

  return (
    <div className="cont">
      <input placeholder="Enter your User ID" onChange={handleUserId} />
      <input placeholder="Enter your Password" type="password" onChange={handleUserPassword} />

      <button onClick={SignIn}>Sign In</button>
      <button onClick={Signup}>Sign Up</button>

      <br />
      <input placeholder="Enter your New Password" type="password" onChange={handleNewPassword} />
      <button onClick={UpdatePassword}>Update Password</button>
    </div>
  );
};

export default Login;
