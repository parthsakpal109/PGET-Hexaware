// CarList.js
import React, { useEffect, useState } from 'react';
import axios from '../services/Api';
import { Container, Row, Col, Card, Button, Spinner } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const CarList = () => {
  const [cars, setCars] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    // Ensure this endpoint is correct for fetching ALL cars
    // Your CarController's @GetMapping("/all") combined with @RequestMapping("/api/cars")
    // makes the full endpoint /api/cars/all
    axios.get('/api/cars/all')
      .then(res => {
        // Log the response data to the console to verify its structure and content
        console.log("Cars fetched successfully:", res.data);
        setCars(res.data);
      })
      .catch(err => {
        console.error('Error fetching cars:', err);
        // Optionally, set an error state here to display a message to the user
      })
      .finally(() => setLoading(false));
  }, []); // Empty dependency array ensures this runs once on component mount

  const handleBook = (carId) => {
    navigate(`/book/${carId}`);
  };

  return (
    <Container className="my-4">
      <h2 className="mb-4 text-center">Available Cars</h2>
      {loading ? (
        <div className="text-center">
          <Spinner animation="border" variant="primary" />
          <p>Loading cars...</p>
        </div>
      ) : (
        <Row>
          {/* Conditional rendering: Check if the cars array is empty */}
          {cars.length === 0 ? (
            <Col className="text-center">
              <h4>No cars available.</h4>
              <p>Please ensure cars are added to the system and the backend API is running correctly.</p>
            </Col>
          ) : (
            // Map over the cars array and render each car card
            cars.map(car => (
              <Col key={car.id} md={4} className="mb-4">
                <Card className="h-100 shadow-sm">
                  {/* Provide a fallback image if imageUrl is not available */}
                  <Card.Img 
                    variant="top" 
                    src={car.imageUrl || 'https://via.placeholder.com/300x180?text=No+Image'} 
                    alt={`${car.make} ${car.model}`}
                  />
                  <Card.Body>
                    <Card.Title>{car.make} {car.model}</Card.Title>
                    <Card.Text>
                      <strong>Year:</strong> {car.year}<br />
                      {/* CORRECTED PROPERTY NAMES to match CarResponse DTO */}
                      <strong>Daily Rate:</strong> ₹{car.dailyRate ? car.dailyRate.toFixed(2) : 'N/A'}<br /> 
                      <strong>Location:</strong> {car.currentLocation || 'N/A'}
                    </Card.Text>
                    <Button variant="primary" onClick={() => handleBook(car.id)}>
                      View / Book
                    </Button>
                  </Card.Body>
                </Card>
              </Col>
            ))
          )}
        </Row>
      )}
    </Container>
  );
};

export default CarList;