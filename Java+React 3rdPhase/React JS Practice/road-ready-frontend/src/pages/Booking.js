import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from '../services/Api';
import { Container, Form, Button, Alert, Row, Col, Card, Spinner } from 'react-bootstrap';

const Booking = () => {
  const { carId } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    pickupDate: '',
    dropoffDate: '',
    pickupTime: '',
    dropoffTime: '',
    pickupAddress: '',
    dropoffAddress: '',
    optionalExtras: '',
  });

  const [paymentMethod, setPaymentMethod] = useState('CREDIT_CARD');
  const [bookingDetails, setBookingDetails] = useState(null);

  const [error, setError] = useState('');
  const [successMsg, setSuccessMsg] = useState('');
  const [loading, setLoading] = useState(false);
  const [showPayment, setShowPayment] = useState(false);

  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleBookingCreation = async (e) => {
    e.preventDefault();
    setError('');
    setSuccessMsg('');
    setLoading(true);

    const userId = localStorage.getItem("userId");
    if (!userId) {
      navigate('/login');
      setLoading(false);
      return;
    }

    const pickupDateTime = `${formData.pickupDate}T${formData.pickupTime}:00`;
    const dropoffDateTime = `${formData.dropoffDate}T${formData.dropoffTime}:00`;

    const combinedOptionalExtras = `Pickup Address: ${formData.pickupAddress}, Dropoff Address: ${formData.dropoffAddress}, Duration (hrs): ${formData.duration || 'N/A'}, Other Extras: ${formData.optionalExtras || ''}`;

    try {
      const bookingPayload = {
        carId: Number(carId),
        pickupDateTime: pickupDateTime,
        dropoffDateTime: dropoffDateTime,
        optionalExtras: combinedOptionalExtras
      };

      const res = await axios.post(`/api/bookings/make/${userId}`, bookingPayload);
      const newBooking = res.data;
      setBookingDetails(newBooking);
      setShowPayment(true);
      setSuccessMsg('Booking created! Proceed to payment...');

    } catch (err) {
      console.error("Booking creation error:", err.response?.data || err.message);
      setError(err.response?.data?.message || 'Booking failed. Please check input and car availability.');
    } finally {
      setLoading(false);
    }
  };

  const handlePaymentProcessing = async () => {
    setError('');
    setLoading(true);

    if (!bookingDetails) {
      setError("Booking details missing. Please try booking again.");
      setLoading(false);
      return;
    }

    try {
      const paymentRequestPayload = {
        bookingId: bookingDetails.id,
        amount: bookingDetails.totalAmount,
        paymentMethod: paymentMethod
      };

      await axios.post(`/api/payments/process`, paymentRequestPayload);

      setSuccessMsg("Payment successful!");
      setTimeout(() => navigate('/payment-success'), 1500);

    } catch (err) {
      console.error("Payment failed:", err.response?.data || err.message);
      setError(err.response?.data?.message || "Payment failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="my-5">
      <h3 className="text-center mb-4">Book Now (Car ID: {carId})</h3>
      {error && <Alert variant="danger">{error}</Alert>}
      {successMsg && <Alert variant="success">{successMsg}</Alert>}

      {!showPayment ? (
        <Form onSubmit={handleBookingCreation}>
          <Row>
            <Col md={6}>
              <Form.Group className="mb-3">
                <Form.Label>Pickup Date</Form.Label>
                <Form.Control
                  type="date"
                  name="pickupDate"
                  value={formData.pickupDate}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Col>

            <Col md={6}>
              <Form.Group className="mb-3">
                <Form.Label>Dropoff Date</Form.Label>
                <Form.Control
                  type="date"
                  name="dropoffDate"
                  value={formData.dropoffDate}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Col>

            <Col md={6}>
              <Form.Group className="mb-3">
                <Form.Label>Pickup Time</Form.Label>
                <Form.Control
                  type="time"
                  name="pickupTime"
                  value={formData.pickupTime}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Col>

            <Col md={6}>
              <Form.Group className="mb-3">
                <Form.Label>Dropoff Time</Form.Label>
                <Form.Control
                  type="time"
                  name="dropoffTime"
                  value={formData.dropoffTime}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Col>

            <Col md={6}>
              <Form.Group className="mb-3">
                <Form.Label>Pickup Address</Form.Label>
                <Form.Control
                  type="text"
                  name="pickupAddress"
                  value={formData.pickupAddress}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Col>

            <Col md={6}>
              <Form.Group className="mb-3">
                <Form.Label>Dropoff Address</Form.Label>
                <Form.Control
                  type="text"
                  name="dropoffAddress"
                  value={formData.dropoffAddress}
                  onChange={handleChange}
                  required
                />
              </Form.Group>
            </Col>

            <Col md={6}>
              <Form.Group className="mb-4">
                <Form.Label>Optional Extras (e.g., duration, car seat)</Form.Label>
                <Form.Control
                  as="textarea"
                  rows={3}
                  name="optionalExtras"
                  value={formData.optionalExtras}
                  onChange={handleChange}
                />
              </Form.Group>
            </Col>
          </Row>

          <div className="text-center">
            <Button variant="danger" type="submit" className="px-4 py-2" disabled={loading}>
              {loading ? <Spinner as="span" animation="border" size="sm" role="status" aria-hidden="true" /> : 'Book Now'}
            </Button>
          </div>
        </Form>
      ) : (
        <Card className="p-4 mt-4 shadow-sm">
          <Card.Body>
            <Card.Title>Booking Confirmed! Proceed to Payment</Card.Title>
            <p><strong>Booking ID:</strong> {bookingDetails?.id}</p>
            <p><strong>Total Amount:</strong> ₹{bookingDetails?.totalAmount ? bookingDetails.totalAmount.toFixed(2) : 'N/A'}</p>

            <Form>
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

              <Button variant="success" onClick={handlePaymentProcessing} disabled={loading}>
                {loading ? <Spinner as="span" animation="border" size="sm" role="status" aria-hidden="true" /> : 'Pay Now'}
              </Button>
            </Form>
          </Card.Body>
        </Card>
      )}
    </Container>
  );
};

export default Booking;