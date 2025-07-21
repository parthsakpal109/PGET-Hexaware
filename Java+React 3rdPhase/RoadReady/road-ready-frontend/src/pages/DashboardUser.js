import React, { useEffect, useState, useCallback } from 'react'; 
import axios from '../services/Api';
import { Container, Row, Col, Nav, Tab, Form, Button, Alert, Table } from 'react-bootstrap';

const DashboardUser = () => {
  const [userId, setUserId] = useState(null);
  const [profile, setProfile] = useState({});
  const [bookings, setBookings] = useState([]);
  const [payments, setPayments] = useState([]); 
  const [reviews, setReviews] = useState([]);
  const [editMode, setEditMode] = useState(false);
  const [error, setError] = useState('');
  const [successMsg, setSuccessMsg] = useState('');


  const fetchData = useCallback((uid) => { 
    axios.get(`/api/users/${uid}`)
      .then(res => setProfile(res.data))
      .catch((err) => {
        console.error("Error loading profile:", err);
        setError("Error loading profile. " + (err.response?.data?.message || err.message));
      });

    axios.get(`/api/bookings/user/${uid}`)
      .then(res => {
        console.log("Bookings loaded:", res.data);
        setBookings(res.data);
      })
      .catch((err) => {
        console.error("Error loading bookings:", err);
      });

    axios.get(`/api/payments/user/${uid}`) 
        .then(res => {
            console.log("Payments loaded:", res.data); 
            setPayments(res.data);
        })
        .catch((err) => {
            console.error("Error loading payments:", err);
        });

    // Fetch Reviews
    axios.get(`/api/reviews/user/${uid}`)
      .then(res => setReviews(res.data))
      .catch(() => console.log("Reviews not loaded"));
  }, [setProfile, setBookings, setPayments, setReviews, setError]); 

  useEffect(() => {
    const uid = localStorage.getItem("userId");
    if (uid) {
      setUserId(uid);
      fetchData(uid);
    } else {
      setError("User ID not found. Please log in.");
    }
  }, [fetchData]); 

  const handleProfileUpdate = (e) => {
    e.preventDefault();
    axios.put(`/api/users/profile/${userId}`, profile)
      .then(() => {
        setSuccessMsg("Profile updated successfully!");
        setEditMode(false);
        fetchData(userId);
      })
      .catch((err) => {
        console.error("Failed to update profile:", err);
        setError("Failed to update profile. " + (err.response?.data?.message || err.message));
      });
  };

  const handleChange = (e) => {
    setProfile({ ...profile, [e.target.name]: e.target.value });
  };

  const formatDateTime = (dateTimeString) => {
    if (!dateTimeString) return 'N/A';
    try {
      const date = new Date(dateTimeString);
      return date.toLocaleString();
    } catch (e) {
      console.error("Error formatting date:", dateTimeString, e);
      return dateTimeString;
    }
  };

  return (
    <Container className="my-5">
      <h3>User Dashboard</h3>

      <Tab.Container defaultActiveKey="profile">
        <Row>
          <Col sm={3}>
            <Nav variant="pills" className="flex-column mt-4">
              <Nav.Item><Nav.Link eventKey="profile">Profile</Nav.Link></Nav.Item>
              <Nav.Item><Nav.Link eventKey="bookings">Bookings</Nav.Link></Nav.Item>
              <Nav.Item><Nav.Link eventKey="payments">Payments</Nav.Link></Nav.Item>
              <Nav.Item><Nav.Link eventKey="reviews">Reviews</Nav.Link></Nav.Item>
            </Nav>
          </Col>

          <Col sm={9}>
            <Tab.Content>
              <Tab.Pane eventKey="profile">
                {successMsg && <Alert variant="success">{successMsg}</Alert>}
                {error && <Alert variant="danger">{error}</Alert>}
                <Form onSubmit={handleProfileUpdate}>
                  {['firstName', 'lastName', 'email', 'phoneNumber'].map(field => (
                    <Form.Group className="mb-3" key={field}>
                      <Form.Label>{field.replace(/^\w/, l => l.toUpperCase()).replace('number', ' Number')}</Form.Label>
                      {editMode ? (
                        <Form.Control
                          type={field === 'email' ? 'email' : 'text'}
                          name={field}
                          value={profile[field] || ''}
                          onChange={handleChange}
                          readOnly={field === 'email'}
                        />
                      ) : (
                        <Form.Control plaintext readOnly defaultValue={profile[field] || ''} />
                      )}
                    </Form.Group>
                  ))}

                  {editMode ? (
                    <>
                      <Button variant="success" type="submit">Save</Button>{' '}
                      <Button variant="secondary" onClick={() => setEditMode(false)}>Cancel</Button>
                    </>
                  ) : (
                    <Button variant="primary" onClick={() => setEditMode(true)}>Edit Profile</Button>
                  )}
                </Form>
              </Tab.Pane>

              <Tab.Pane eventKey="bookings">
                <h5>Your Bookings</h5>
                {bookings.length === 0 ? (
                    <Alert variant="info">No bookings found.</Alert>
                ) : (
                    <Table striped bordered hover>
                    <thead>
                        <tr><th>ID</th><th>Pickup Date/Time</th><th>Dropoff Date/Time</th><th>Total Amount</th><th>Status</th></tr>
                    </thead>
                    <tbody>
                        {bookings.map(b => (
                        <tr key={b.id}>
                            <td>{b.id}</td>
                            <td>{formatDateTime(b.pickupDateTime)}</td>
                            <td>{formatDateTime(b.dropoffDateTime)}</td>
                            <td>₹{b.totalAmount ? b.totalAmount.toFixed(2) : 'N/A'}</td>
                            <td>{b.status}</td>
                        </tr>
                        ))}
                    </tbody>
                    </Table>
                )}
              </Tab.Pane>

              <Tab.Pane eventKey="payments">
                <h5>Your Payments</h5>
                {payments.length === 0 ? (
                    <Alert variant="info">No payments found.</Alert>
                ) : (
                    <Table striped bordered hover>
                    <thead>
                        <tr><th>ID</th><th>Booking</th><th>Amount</th><th>Date</th><th>Method</th><th>Status</th></tr>
                    </thead>
                    <tbody>
                        {payments.map(p => (
                        <tr key={p.id}>
                            <td>{p.id}</td>
                            <td>{p.bookingId}</td>
                            <td>₹{p.amount ? p.amount.toFixed(2) : 'N/A'}</td>
                            <td>{formatDateTime(p.paymentDate)}</td>
                            <td>{p.paymentMethod}</td>
                            <td>{p.status}</td>
                        </tr>
                        ))}
                    </tbody>
                    </Table>
                )}
              </Tab.Pane>

              <Tab.Pane eventKey="reviews">
                <h5>Your Reviews</h5>
                {reviews.length === 0 ? (
                    <Alert variant="info">No reviews found.</Alert>
                ) : (
                    <ul className="list-group">
                    {reviews.map(r => (
                        <li className="list-group-item" key={r.id}>
                        <strong>Car:</strong> #{r.carId} |  {r.rating}/5 <br />
                        <em>{r.comment}</em> <br />
                        <small>{formatDateTime(r.reviewDate)}</small>
                        </li>
                    ))}
                    </ul>
                )}
              </Tab.Pane>
            </Tab.Content>
          </Col>
        </Row>
      </Tab.Container>
    </Container>
  );
};

export default DashboardUser;