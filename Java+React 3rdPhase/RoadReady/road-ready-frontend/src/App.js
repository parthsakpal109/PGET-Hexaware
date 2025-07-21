// src/App.js
import React from 'react';
import AppRoutes from './AppRoutes';
import NavBar from './components/NavBar';
import './App.css'; // Optional: if not using now, remove import

function App() {
  return (
    <div>
      <NavBar />
      <AppRoutes />
    </div>
  );
}

export default App;
