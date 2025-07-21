import React from 'react';

const ReturnBook = () => {
  return (
    <div>
      <h3>📥 Return Book</h3>
      <form>
        <input type='text' placeholder='Book ID' /><br />
        <input type='text' placeholder='User ID' /><br />
        <button type='submit'>Return</button>
      </form>
    </div>
  );
};

export default ReturnBook;