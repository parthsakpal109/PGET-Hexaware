import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from '../services/Api';
import { Container, Form, Button, Alert, Spinner } from 'react-bootstrap';

const Payment = () => {
  const { bookingId } = useParams();
  const [booking, setBooking] = useState(null);
  const [paymentMethod, setPaymentMethod] = useState('CREDIT_CARD');
  const [loading, setLoading] = useState(true);
  const [processing, setProcessing] = useState(false);
  const [error, setError] = useState('');
  const [successMsg, setSuccessMsg] = useState('');

  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`/api/bookings/${bookingId}`)
      .then(res => {
        setBooking(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error loading booking details:", err);
        setError("Failed to load booking details.");
        setLoading(false);
      });
  }, [bookingId]);

  const handlePayment = async (e) => {
    e.preventDefault();
    setError('');
    setProcessing(true);

    if (!booking) {
      setError("Booking details not loaded. Cannot process payment.");
      setProcessing(false);
      return;
    }

    try {
      const paymentRequestPayload = {
        bookingId: Number(bookingId),
        amount: booking.totalAmount,
        paymentMethod: paymentMethod
      };

      await axios.post(`/api/payments/process`, paymentRequestPayload);

      setSuccessMsg("Payment successful!");
      setTimeout(() => navigate('/payment-success'), 1500);

    } catch (err) {
      console.error("Payment failed:", err.response?.data || err.message);
      setError(err.response?.data?.message || "Payment failed. Please try again.");
    } finally {
      setProcessing(false);
    }
  };

  if (loading) {
    return (
      <Container className="text-center my-5">
        <Spinner animation="border" variant="primary" />
        <p>Loading booking details...</p>
      </Container>
    );
  }

  if (error && !booking) {
    return (
      <Container className="text-center my-5">
        <Alert variant="danger">{error}</Alert>
        <Button onClick={() => navigate('/dashboard')}>Back to Dashboard</Button>
      </Container>
    );
  }

  return (
    <Container className="my-5">
      <h3>Process Payment</h3>
      <p><strong>Booking ID:</strong> {booking?.id}</p>
      <p><strong>Total Cost:</strong> ₹{booking?.totalAmount ? booking.totalAmount.toFixed(2) : 'N/A'}</p>

      {error && <Alert variant="danger">{error}</Alert>}
      {successMsg && <Alert variant="success">{successMsg}</Alert>}

      <Form onSubmit={handlePayment}>
        <Form.Group className="mb-3">
          <Form.Label>Select Payment Method</Form.Label>
          <Form.Select
            value={paymentMethod}
            onChange={(e) => setPaymentMethod(e.target.value)}
          >
            <option value="CREDIT_CARD">Credit Card</option>
            <option value="DEBIT_CARD">Debit Card</option>
            <option value="UPI">UPI</option>
            <option value="NET_BANKING">Net Banking</option>
          </Form.Select>
        </Form.Group>

        <Button type="submit" disabled={processing}>
          {processing ? 'Processing...' : 'Pay Now'}
        </Button>
      </Form>
    </Container>
  );
};

export default Payment;