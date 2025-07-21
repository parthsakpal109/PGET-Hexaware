import React from 'react';

const AddBook = () => {
  return (
    <div>
      <h2>➕ Add Book</h2>
      <form>
        <input type='text' placeholder='Book Title' /><br />
        <input type='text' placeholder='Author' /><br />
        <input type='number' placeholder='Quantity' /><br />
        <button type='submit'>Add Book</button>
      </form>
    </div>
  );
};

export default AddBook;