import { Route, Routes, NavLink } from 'react-router-dom';
import './App.css';
import Home from './Home';
import Faq from './Faq';
import Contact from './Contact';
import Login from './Login';
import Welcome from './Welcome';
import Payslip from './Payslip'; 

const App = () => {
  return (
    <div className="App">
      <nav className="navbar">
        <NavLink className="nav-link" to="/" end>Home</NavLink>
        <NavLink className="nav-link" to="/faq">FAQ</NavLink>
        <NavLink className="nav-link" to="/contact">Contact</NavLink>
        <NavLink className="nav-link" to="/login">Login</NavLink>
        <NavLink className="nav-link" to="/payslip">Payslip</NavLink> {/* ✅ new menu item */}
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/faq" element={<Faq />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/login" element={<Login />} />
        <Route path="/welcome" element={<Welcome />} />
        <Route path="/payslip" element={<Payslip />} /> {/* ✅ new route */}
      </Routes>
    </div>
  );
};

export default App;
