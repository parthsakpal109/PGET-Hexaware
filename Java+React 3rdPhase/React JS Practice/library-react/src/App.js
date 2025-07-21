import React from 'react';
import { Link } from 'react-router-dom';
import Home from './components/Home';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import Dashboard from './components/Dashboard';
import AddBook from './components/AddBook';
import RemoveBook from './components/RemoveBook';
import Routing from './Routing';

const App = () => {
  return (
    <div>
      <h1>📖 Library Management System</h1>
      <table>
        <tr>
          <Link to = "/home"> <td>Home</td> </Link>
          <Link to = "/signin"> <td>Sign In</td> </Link>
          <Link to = "/signup"> <td>Sign Up</td> </Link>
          <Link to = "/dashboard"> <td>DashBoard</td> </Link>
          <Link to = "/add"> <td>Add Book</td> </Link>
          <Link to = "/remove"> <td>Remove Book</td> </Link>
        </tr>
      </table>

      <Routing />
    </div>
  );
};

export default App;
