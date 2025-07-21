import React from 'react';

const RemoveBook = () => {
  return (
    <div>
      <h2>🗑️ Remove Book</h2>
      <form>
        <input type='text' placeholder='Book ID or Title' /><br />
        <button type='submit'>Remove Book</button>
      </form>
    </div>
  );
};

export default RemoveBook;