import React from 'react';

const IssueBook = () => {
  return (
    <div>
      <h3>📤 Issue Book</h3>
      <form>
        <input type='text' placeholder='Book ID' /><br />
        <input type='text' placeholder='User ID' /><br />
        <button type='submit'>Issue</button>
      </form>
    </div>
  );
};

export default IssueBook;