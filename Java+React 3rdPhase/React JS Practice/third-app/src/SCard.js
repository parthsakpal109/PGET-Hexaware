import React from 'react';

const SCard = () => (
  <div className="ui link cards">
    <div className="card">
      <div className="image">
        <img src="100_5724.jpg" alt="Matthew" />
      </div>
      <div className="content">
        <div className="header">Parth Sakpal</div>
        <div className="meta">
          <a href="#!">Friends</a>
        </div>
        <div className="description">
          Parth is an interior designer living in New York.
        </div>
      </div>
      <div className="extra content">
        <span className="right floated">
          Joined in 2013
        </span>
        <span>
          <i className="user icon"></i>
          75 Friends
        </span>
      </div>
    </div>

    <div className="card">
      <div className="image">
        <img src="08022008898.jpg" alt="Siya" />
      </div>
      <div className="content">
        <div className="header">Siya</div>
        <div className="meta">
          <span className="date">Coworker</span>
        </div>
        <div className="description">
          Siya is a personal assistant living in Paris.
        </div>
      </div>
      <div className="extra content">
        <span className="right floated">
          Joined in 2011
        </span>
        <span>
          <i className="user icon"></i>
          35 Friends
        </span>
      </div>
    </div>

    <div className="card">
      <div className="image">
        <img src="000_0181.jpg" alt="Parth" />
      </div>
      <div className="content">
        <div className="header">Parth</div>
        <div className="meta">
          <a href="#!">Coworker</a>
        </div>
        <div className="description">
          Parth is a copywriter working in New York.
        </div>
      </div>
      <div className="extra content">
        <span className="right floated">
          Joined in 2014
        </span>
        <span>
          <i className="user icon"></i>
          151 Friends
        </span>
      </div>
    </div>
  </div>
);

export default SCard;
