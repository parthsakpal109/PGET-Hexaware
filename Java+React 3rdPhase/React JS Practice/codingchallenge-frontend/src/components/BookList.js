import { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Header, Message, Table, Icon } from 'semantic-ui-react';

const BookList = () => {
  const [books, setBooks] = useState([]);
  const [flag, setFlag] = useState(true);

  const nav = useNavigate();

  useEffect(() => {
    getBook();  
  }, [flag]);

  const getBook = async () => {
    const token = localStorage.getItem('token');
    
    try {
      const res = await axios.get('http://localhost:8081/books', {
        headers: { Authorization: `Bearer ${token}` },
      });
      setBooks(res.data);
    } catch (e) {
      console.log("Error to get all books :" + e);
    }
  };

  const deleteBook = async (id) => {
    const token = localStorage.getItem('token');

    try {
      const res = await axios.delete(`http://localhost:8081/books/id/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      alert(res.data + ". ID :" + id);
      setFlag(!flag);
    } catch (e) {
      console.log("Error while deleting :" + e);
    }
  };

  return (
    <Container style={{ marginTop: '2rem' }}>
      <Header as="h1" textAlign="center">Book Management</Header>

      <Button primary icon labelPosition="left" style={{ marginBottom: '1rem' }} onClick={() => nav('/books/add')}>
        <Icon name="add" />
        Add New Book
      </Button>

      {books.length > 0 ? (
        <Table celled striped compact>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>ID</Table.HeaderCell>
              <Table.HeaderCell>Title</Table.HeaderCell>
              <Table.HeaderCell>ISBN</Table.HeaderCell>
              <Table.HeaderCell>Publication Year</Table.HeaderCell>
              <Table.HeaderCell>Actions</Table.HeaderCell>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {books.map((b, i) => (
              <Table.Row key={i}>
                <Table.Cell>{b.bookId}</Table.Cell>
                <Table.Cell>{b.title}</Table.Cell>
                <Table.Cell>{b.isbn}</Table.Cell>
                <Table.Cell>{b.publicationYear}</Table.Cell>
                <Table.Cell>
                  <Button color="red" size="small" onClick={() => deleteBook(b.bookId)}>
                    <Icon name="trash" /> Delete
                  </Button>
                  <Button
                    color="blue"
                    size="small"
                    onClick={() => nav("/books/edit", { state: { bookId: b.bookId } })}
                  >
                    <Icon name="edit" /> Update
                  </Button>
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      ) : (
        <Message warning>
          <Message.Header>No Books Found</Message.Header>
          <p>Try adding some books to see them listed here.</p>
        </Message>
      )}
    </Container>
  );
};

export default BookList;