// src/AppRoutes.js
import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import CarList from './pages/CarList';
import CarDetails from './pages/CarDetails';
import Booking from './pages/Booking';
import Payment from './pages/Payment';
import DashboardUser from './pages/DashboardUser';
import DashboardAdmin from './pages/DashboardAdmin';
import PaymentSuccess from './pages/PaymentSuccess';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/cars" element={<CarList />} />
      <Route path="/car/:id" element={<CarDetails />} />
      <Route path="/book/:carId" element={<Booking />} />
      <Route path="/payment/:bookingId" element={<Payment />} />
      <Route path="/dashboard" element={<DashboardUser />} />
      <Route path="/admin" element={<DashboardAdmin />} />
      <Route path="/payment-success" element={<PaymentSuccess />} />
    </Routes>
  );
};

export default AppRoutes;
