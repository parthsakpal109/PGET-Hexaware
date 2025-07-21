import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

function BookEdit() {
  const { isbn } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState({
    title: '',
    author: '',
    publicationyear: '',
  });

  const token = localStorage.getItem('token');

  // Fetch existing book data
  useEffect(() => {
    axios.get(`http://localhost:8086/api/books/getbookbyisbn/${isbn}`, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then(response => {
      setBook(response.data); // assuming response.data is a single book object
    })
    .catch(error => {
      console.error('Error fetching book details:', error);
    });
  }, [isbn]);

  // Handle input changes
  const handleChange = (e) => {
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    axios.put(`http://localhost:8086/api/books/updatebook/${isbn}`, book, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then(() => {
      alert('Book updated successfully');
      navigate('/books'); // Go back to list
    })
    .catch(error => {
      console.error('Error updating book:', error);
    });
  };

  return (
    <div>
      <h2>Edit Book</h2>
      <form onSubmit={handleSubmit}>
        <label>Title:</label><br />
        <input
          type="text"
          name="title"
          value={book.title}
          onChange={handleChange}
          required
        /><br />

        <label>Author:</label><br />
        <input
          type="text"
          name="author"
          value={book.author}
          onChange={handleChange}
          required
        /><br />

        <label>Publication Year:</label><br />
        <input
          type="text"
          name="publicationyear"
          value={book.publicationyear}
          onChange={handleChange}
          required
        /><br /><br />

        <button type="submit">Update Book</button>
      </form>
    </div>
  );
}

export default BookEdit;
