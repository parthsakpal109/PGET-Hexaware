import React from 'react';
import { Button, Container, Row, Col, Card } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function Home() {
  const navigate = useNavigate();

  const handleBookNow = () => {
    navigate('/cars'); // ✅ Redirects to Browse Cars page
  };

  return (
    <div>
      {/* Hero Section */}
      <div className="hero-section text-white text-center d-flex align-items-center justify-content-center">
        <div>
          <h1>WANNA RENT OUT A CAR?</h1>
          <Button variant="danger" className="mt-3" onClick={handleBookNow}>
            BOOK NOW
          </Button>
        </div>
      </div>

      {/* Trusted Partner Section */}
      <Container className="my-5">
        <Row>
          <Col md={6}>
            <h3 className="text-danger">YOUR TRUSTED PARTNER IN CAR RENTAL</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque at ultricies ligula. Donec efficitur maximus lorem.</p>
          </Col>
          <Col md={6}>
            <img src="/assets/car-dashboard.jpg" alt="Dashboard" className="img-fluid" />
          </Col>
        </Row>
      </Container>

      {/* Current Offers */}
      <Container className="my-5">
        <h4 className="text-danger mb-4">CURRENT OFFERS</h4>
        <Row>
          {[1, 2, 3, 4].map((_, i) => (
            <Col md={3} key={i}>
              <Card className="mb-3">
                <Card.Img variant="top" src="/assets/car-interior.jpg" />
                <Card.Body>
                  <Card.Title>Offer {i + 1}</Card.Title>
                  <Card.Text>Special discounts on selected models.</Card.Text>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      </Container>

      {/* Reviews */}
      <div className="bg-dark text-white py-5">
        <Container>
          <h4 className="text-danger mb-4">REVIEWS</h4>
          <Row>
            {["ABC", "XYZ", "PQR"].map((name, i) => (
              <Col md={4} key={i}>
                <p><strong>{name}</strong></p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum vel.</p>
              </Col>
            ))}
          </Row>
        </Container>
      </div>

      {/* Footer */}
      <div className="text-center py-4 bg-black text-white">
        <p>Get in touch | roadready@cars.com</p>
      </div>
    </div>
  );
}

export default Home;
