import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../services/Api';
import { Container, Form, Button, Alert, Card } from 'react-bootstrap';

const Login = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await axios.post('/api/auth/login', formData);

      localStorage.setItem("userId", response.data.userId); // Store userId
      localStorage.setItem("userEmail", response.data.email); // Store email
      localStorage.setItem("userRoles", JSON.stringify(Array.from(response.data.roles))); 

      navigate('/dashboard');
    } catch (err) {
      console.error("Login error:", err.response?.data || err.message);
      setError(err.response?.data?.message || 'Invalid email or password.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="d-flex justify-content-center align-items-center mt-5">
      <Card style={{ width: '400px' }} className="p-4 shadow">
        <h3 className="text-center mb-4">Login</h3>
        {error && <Alert variant="danger">{error}</Alert>}
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="email" className="mb-3">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="Enter email"
              required
            />
          </Form.Group>

          <Form.Group controlId="password" className="mb-4">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="Password"
              required
            />
          </Form.Group>

          <Button type="submit" variant="primary" className="w-100" disabled={loading}>
            {loading ? 'Logging in...' : 'Login'}
          </Button>
        </Form>

        <p className="text-center mt-3">
          Don't have an account? <a href="/register">Register</a>
        </p>
      </Card>
    </Container>
  );
};

export default Login;