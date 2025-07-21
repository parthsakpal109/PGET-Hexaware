import React from 'react';

const Home = () => {
  return (
    <div>
      <h2>🏠 Welcome to the Library</h2>
      <h3>📋 Menu</h3>
      <ul style={{ listStyleType: 'none', padding: 0 }}>
        <li><button>Sign In</button></li>
        <li><button>Sign Up</button></li>
        <li><button>Dashboard</button></li>
        <li><button>Add Book</button></li>
        <li><button>Remove Book</button></li>
        <li><button>Issue Book</button></li>
        <li><button>Return Book</button></li>
      </ul>
    </div>
  );
};

export default Home;