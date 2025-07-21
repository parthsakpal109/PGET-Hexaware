import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

const BookEdit = () => {
    const data = useLocation();
    const { bookId } = data.state;
    const nav = useNavigate();

    const [title, setTitle] = useState('');
    const [isbn, setIsbn] = useState('');
    const [publicationYear, setPublicationYear] = useState('');

    const token = localStorage.getItem('token');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const book = {title, isbn, publicationYear: parseInt(publicationYear, 10) };

        try {
            await axios.put(`http://localhost:8081/books/id/${bookId}`, book, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });
            alert("Updated Book");
            nav('/books');
        } catch (e) {
            console.error('Error updating book:', e);
            alert('Failed to update book');
        }
    };

    return (
        <div>
            <h1>Edit Book</h1>
            <form onSubmit={handleSubmit}>
                <input type='text' placeholder='Title' value={title} onChange={(e) => setTitle(e.target.value)} />
                <input type='text' placeholder='Isbn' value={isbn} onChange={(e) => setIsbn(e.target.value)} />
                <input type='number' placeholder='Publication Year' value={publicationYear} onChange={(e) => setPublicationYear(e.target.value)} />
                <button type='submit'>Update</button>
            </form>
        </div>
    )
}

export default BookEdit