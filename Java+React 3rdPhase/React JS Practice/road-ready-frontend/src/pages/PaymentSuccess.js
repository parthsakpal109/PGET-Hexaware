import React, { useEffect } from 'react';
import { Container, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const PaymentSuccess = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      navigate('/dashboard');
    }, 2000);

    return () => clearTimeout(timer);
  }, [navigate]);

  return (
    <Container className="text-center my-5">
      <Alert variant="success">
        <h4>Payment Successful!</h4>
        <p>Redirecting to your dashboard...</p>
      </Alert>
    </Container>
  );
};

export default PaymentSuccess;
