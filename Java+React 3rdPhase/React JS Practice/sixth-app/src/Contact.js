import { useNavigate } from "react-router-dom";

const Contact = () => {
  const nav = useNavigate();

  const openSignIn = () => {
    nav("/login");
  };

  return (
    <>
      <h1>Contact</h1>
      <h2>Ajay@gmail.com</h2>
      <h2>98765433</h2>

      <button onClick={openSignIn}>Sign In</button>
    </>
  );
};

export default Contact;
