import React, { useEffect, useState } from "react";
import BCard from "./BCard";
import { Message, Dimmer, Loader, Segment, Button, Modal, Header } from 'semantic-ui-react';

const App = () => {
  let [flag, setFlag] = useState(0);
  let [marks, setMarks] = useState(0);

  const temp = [
    { bname: "JavaScript Basics", price: 299, qty: 10, city: "Delhi" },
    { bname: "Learning Python", price: 499, qty: 5, city: "Mumbai" },
    { bname: "Mastering Java", price: 399, qty: 8, city: "Chennai" },
    { bname: "Web Dev Guide", price: 199, qty: 15, city: "Kolkata" },
    { bname: "React Essentials", price: 349, qty: 12, city: "Bangalore" },
    { bname: "Node.js Handbook", price: 429, qty: 7, city: "Pune" },
    { bname: "HTML & CSS", price: 150, qty: 20, city: "Hyderabad" },
    { bname: "C++ for Beginners", price: 250, qty: 10, city: "Ahmedabad" },
    { bname: "Data Structures", price: 300, qty: 9, city: "Jaipur" },
    { bname: "Machine Learning", price: 550, qty: 4, city: "Chandigarh" },
    { bname: "AI Fundamentals", price: 600, qty: 3, city: "Bhopal" },
    { bname: "Django for All", price: 375, qty: 6, city: "Nagpur" },
    { bname: "Spring Boot Intro", price: 420, qty: 7, city: "Lucknow" },
    { bname: "Flutter Dev", price: 310, qty: 8, city: "Patna" },
    { bname: "SQL Mastery", price: 280, qty: 11, city: "Surat" },
    { bname: "MongoDB Guide", price: 270, qty: 13, city: "Indore" },
    { bname: "AWS for Developers", price: 650, qty: 2, city: "Noida" },
    { bname: "Cyber Security", price: 480, qty: 5, city: "Kanpur" },
    { bname: "Kotlin in Action", price: 360, qty: 6, city: "Thane" },
    { bname: "TypeScript Deep Dive", price: 330, qty: 9, city: "Amritsar" }
  ];

  const [books, setBooks] = useState([]);
  let [bname, setName] = useState();
  let [qty, setQty] = useState();
  let [price, setPrice] = useState();
  let [city, setCity] = useState();
  let [newFlag, setNewFlag] = useState(false);

  const [open, setOpen] = useState(false);

  // eslint-disable-next-line react-hooks/exhaustive-deps
  useEffect(() => {
    setTimeout(() => {
      setBooks(temp);
    }, 2000);
  }, []);

  const handleMarks = (e) => {
    setMarks(e.target.value);
  };

  const showR = () => {
    if (marks === 0) {
      setFlag(0);
    } else if (marks < 50) {
      setFlag(1);
    } else {
      setFlag(2);
    }
  };

  const addBook = () => {
    let newbook = { bname, qty, price, city };
    setBooks([newbook, ...books]);
    setNewFlag(true);
  };

  return (
    <>
      <Modal onClose={() => setOpen(false)} onOpen={() => setOpen(true)} open={open} trigger={<Button>Show Modal</Button>}>
        <Modal.Header>Sample Modal</Modal.Header>
        <Modal.Content>
          <Modal.Description>
            <Header>Modal Title</Header>
            <p>This is a sample modal for demonstration purposes.</p>
          </Modal.Description>
        </Modal.Content>
        <Modal.Actions>
          <Button color='black' onClick={() => setOpen(false)}>Close</Button>
        </Modal.Actions>
      </Modal>

      <div className="cont">
        <input type="text" placeholder="Enter book name" onChange={(e) => setName(e.target.value)} /><br />
        <input type="text" placeholder="Enter book qty" onChange={(e) => setQty(e.target.value)} /><br />
        <input type="text" placeholder="Enter book price" onChange={(e) => setPrice(e.target.value)} /><br />
        <input type="text" placeholder="Enter book city" onChange={(e) => setCity(e.target.value)} /><br />
        <button onClick={addBook}>Add Item</button>
      </div>

      {newFlag ? <Message color="green">Book Added Successfully!</Message> : null}

      <input type="text" placeholder="Enter your total marks" onChange={handleMarks} />

      {flag === 1 ? <Message color="black">Fail</Message> :
        flag === 2 ? <Message color="brown">Pass</Message> :
          <Message color="grey">Enter Marks to Check Result</Message>}

      <button onClick={showR}>Show Result</button>

      {books.length > 0 ? books.map((book, index) => (
        <BCard key={index} Bname={book.bname} price={book.price} qty={book.qty} city={book.city} />
      )) : (
        <Segment>
          <Dimmer active>
            <Loader>Loading...</Loader>
          </Dimmer>
        </Segment>
      )}
    </>
  );
};

export default App;
