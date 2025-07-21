import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import SCard from './SCard';
import reportWebVitals from './reportWebVitals';
import 'semantic-ui-css/semantic.min.css'


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <SCard/>
  </React.StrictMode>
);


reportWebVitals();
