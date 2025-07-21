import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AddBook = () => {
    const [title, setTitle] = useState('');
    const [isbn, setIsbn] = useState('');
    const [publicationYear, setPublicationYear] = useState('');
    const nav = useNavigate();

    const newBook = (e) => {
        e.preventDefault();

        const book = { title, isbn, publicationYear: parseInt(publicationYear, 10) };
        const token = localStorage.getItem('token');

        axios.post("http://localhost:8081/books", book, {
            headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
        }).then(() => {
            alert("Book Added");
            nav("/books");
        }).catch((e) => {
            console.log("Error while adding book :" + e);
            alert("Failed to add book");
        });
    };

    return (
        <div className="container">
            <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Add New Book</h2>
            <form onSubmit={newBook}>
                <input
                    type='text'
                    placeholder='Title'
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
                <input
                    type='text'
                    placeholder='ISBN'
                    value={isbn}
                    onChange={(e) => setIsbn(e.target.value)}
                    required
                />
                <input
                    type='number'
                    placeholder='Publication Year'
                    value={publicationYear}
                    onChange={(e) => setPublicationYear(e.target.value)}
                    required
                />
                <button type='submit'>Add Book</button>
            </form>
        </div>
    );
};

export default AddBook;
