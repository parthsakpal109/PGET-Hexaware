import React from 'react';
import { Navbar, Nav, Button, Container } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';

function NavBar() {
  const navigate = useNavigate();
  const isLoggedIn = !!localStorage.getItem("userId");

  const handleLogout = () => {
    localStorage.clear(); 
    navigate("/login");
  };

  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <Navbar.Brand as={Link} to="/" className="fw-bold text-white">ROAD READY</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav" className="justify-content-center">
          <Nav className="mx-auto d-flex gap-4 align-items-center">
            <Nav.Link as={Link} to="/" className="text-white">Home</Nav.Link>
            <Nav.Link as={Link} to="/cars" className="text-white">Browse Cars</Nav.Link>
            {isLoggedIn && (
              <Nav.Link as={Link} to="/dashboard" className="text-white">Dashboard</Nav.Link>
            )}
          </Nav>
          <div className="ms-auto">
            {isLoggedIn ? (
              <Button variant="danger" onClick={handleLogout}>Log Out</Button>
            ) : (
              <Button variant="danger" as={Link} to="/login">Login/Register</Button>
            )}
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavBar;