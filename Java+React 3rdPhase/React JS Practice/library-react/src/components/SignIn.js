import React from 'react';

const SignIn = () => {
  return (
    <div>
      <h2>🔐 Sign In</h2>
      <form>
        <input type='text' placeholder='Username' /><br />
        <input type='password' placeholder='Password' /><br />
        <button type='submit'>Sign In</button>
      </form>
    </div>
  );
};

export default SignIn;