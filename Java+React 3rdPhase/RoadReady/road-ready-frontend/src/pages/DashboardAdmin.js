import React, { useEffect, useState } from 'react';
import axios from '../services/Api';
import { Container, Row, Col, Tab, Nav, Table, Button, Alert } from 'react-bootstrap';

const DashboardAdmin = () => {
  const [users, setUsers] = useState([]);
  const [cars, setCars] = useState([]);
  const [bookings, setBookings] = useState([]);
  const [payments, setPayments] = useState([]);
  const [reviews, setReviews] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchAllData();
  }, []);

  const fetchAllData = () => {
    axios.get('/api/users/all')
      .then(res => setUsers(res.data))
      .catch(() => setError("Failed to load users"));

    axios.get('/api/cars')
      .then(res => setCars(res.data))
      .catch(() => setError("Failed to load cars"));

    axios.get('/api/bookings')
      .then(res => setBookings(res.data))
      .catch(() => setError("Failed to load bookings"));

    axios.get('/api/payments')
      .then(res => setPayments(res.data))
      .catch(() => setError("Failed to load payments"));

    axios.get('/api/reviews')
      .then(res => setReviews(res.data))
      .catch(() => setError("Failed to load reviews"));
  };

  const deleteUser = (id) => {
    axios.delete(`/api/users/delete/${id}`)
      .then(() => fetchAllData())
      .catch(() => setError("Failed to delete user"));
  };

  const deleteReview = (id) => {
    axios.delete(`/api/reviews/delete/${id}`)
      .then(() => fetchAllData())
      .catch(() => setError("Failed to delete review"));
  };

  return (
    <Container className="my-5">
      <h3>Admin Dashboard</h3>
      {error && <Alert variant="danger">{error}</Alert>}

      <Tab.Container defaultActiveKey="users">
        <Row>
          <Col sm={3}>
            <Nav variant="pills" className="flex-column mt-4">
              <Nav.Item><Nav.Link eventKey="users">👤 Users</Nav.Link></Nav.Item>
              <Nav.Item><Nav.Link eventKey="cars">🚗 Cars</Nav.Link></Nav.Item>
              <Nav.Item><Nav.Link eventKey="bookings">📅 Bookings</Nav.Link></Nav.Item>
              <Nav.Item><Nav.Link eventKey="payments">💳 Payments</Nav.Link></Nav.Item>
              <Nav.Item><Nav.Link eventKey="reviews">⭐ Reviews</Nav.Link></Nav.Item>
            </Nav>
          </Col>

          <Col sm={9}>
            <Tab.Content>
              {/* Users */}
              <Tab.Pane eventKey="users">
                <h5>All Users</h5>
                <Table striped bordered>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Phone</th>
                      <th>Role</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {users.map(user => (
                      <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.firstName} {user.lastName}</td>
                        <td>{user.email}</td>
                        <td>{user.phone}</td>
                        <td>{user.role}</td>
                        <td>
                          <Button variant="danger" size="sm" onClick={() => deleteUser(user.id)}>Delete</Button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </Tab.Pane>

              {/* Cars */}
              <Tab.Pane eventKey="cars">
                <h5>All Cars</h5>
                <Table striped bordered>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Make</th>
                      <th>Model</th>
                      <th>Year</th>
                      <th>Price/Day</th>
                      <th>Available</th>
                    </tr>
                  </thead>
                  <tbody>
                    {cars.map(car => (
                      <tr key={car.id}>
                        <td>{car.id}</td>
                        <td>{car.make}</td>
                        <td>{car.model}</td>
                        <td>{car.year}</td>
                        <td>₹{car.pricePerDay}</td>
                        <td>{car.available ? "Yes" : "No"}</td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </Tab.Pane>

              {/* Bookings */}
              <Tab.Pane eventKey="bookings">
                <h5>All Bookings</h5>
                <Table striped bordered>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>User</th>
                      <th>Car</th>
                      <th>Pickup</th>
                      <th>Dropoff</th>
                      <th>Cost</th>
                    </tr>
                  </thead>
                  <tbody>
                    {bookings.map(b => (
                      <tr key={b.id}>
                        <td>{b.id}</td>
                        <td>{b.userId}</td>
                        <td>{b.carId}</td>
                        <td>{b.pickupDate}</td>
                        <td>{b.dropoffDate}</td>
                        <td>₹{b.totalCost}</td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </Tab.Pane>

              {/* Payments */}
              <Tab.Pane eventKey="payments">
                <h5>All Payments</h5>
                <Table striped bordered>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Booking ID</th>
                      <th>Method</th>
                      <th>Status</th>
                      <th>Amount</th>
                      <th>Date</th>
                    </tr>
                  </thead>
                  <tbody>
                    {payments.map(p => (
                      <tr key={p.id}>
                        <td>{p.id}</td>
                        <td>{p.bookingId}</td>
                        <td>{p.paymentMethod}</td>
                        <td>{p.status}</td>
                        <td>₹{p.amount}</td>
                        <td>{new Date(p.paymentDate).toLocaleString()}</td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </Tab.Pane>

              {/* Reviews */}
              <Tab.Pane eventKey="reviews">
                <h5>All Reviews</h5>
                <Table striped bordered>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>User</th>
                      <th>Car</th>
                      <th>Rating</th>
                      <th>Comment</th>
                      <th>Date</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {reviews.map(r => (
                      <tr key={r.id}>
                        <td>{r.id}</td>
                        <td>{r.userId}</td>
                        <td>{r.carId}</td>
                        <td>{r.rating}</td>
                        <td>{r.comment}</td>
                        <td>{new Date(r.postedAt).toLocaleString()}</td>
                        <td>
                          <Button variant="danger" size="sm" onClick={() => deleteReview(r.id)}>Delete</Button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </Tab.Pane>
            </Tab.Content>
          </Col>
        </Row>
      </Tab.Container>
    </Container>
  );
};

export default DashboardAdmin;
