import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from '../services/Api';
import { Container, Row, Col, Card, Button, Spinner } from 'react-bootstrap';

const CarDetails = () => {
  const { id } = useParams();
  const [car, setCar] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`/api/cars/${id}`) 
      .then(res => setCar(res.data))
      .catch(err => console.error('Error fetching car:', err))
      .finally(() => setLoading(false));
  }, [id]);

  const handleBooking = () => {
    navigate(`/booking/${id}`);
  };

  if (loading) {
    return (
      <Container className="text-center my-5">
        <Spinner animation="border" variant="primary" />
      </Container>
    );
  }

  if (!car) {
    return (
      <Container className="text-center my-5">
        <h4>Car not found.</h4>
      </Container>
    );
  }

  return (
    <Container className="my-4">
      <Row>
        <Col md={6}>
          <Card.Img
            variant="top"
            src={car.imageUrl || 'https://via.placeholder.com/400x250'}
            className="rounded"
          />
        </Col>
        <Col md={6}>
          <h2>{car.make} {car.model}</h2>
          <p><strong>Year:</strong> {car.year}</p>
          <p><strong>Price per day:</strong> ₹{car.pricePerDay}</p>
          <p><strong>Location:</strong> {car.location}</p>
          <p><strong>Status:</strong> {car.available ? 'Available' : 'Not Available'}</p>
          <Button
            variant="success"
            disabled={!car.available}
            onClick={handleBooking}
          >
            {car.available ? 'Book Now' : 'Unavailable'}
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default CarDetails;
