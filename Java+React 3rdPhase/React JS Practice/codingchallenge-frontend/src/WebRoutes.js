import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom'
import Login from './components/Login'
import Register from './components/Register'
import BookList from './components/BookList'
import AddBook from './components/AddBook'
import BookEdit from './components/BookEdit'

const WebRoutes = () => {
  return (
    <>
        <Router>
            <Routes>
                <Route path = "/" element = {<Navigate to = "/login" />} />
                <Route path = "/login" element = {<Login />} />
                <Route path = "/register" element = {<Register />} />
                <Route path = "/books" element = {<BookList />} />
                <Route path = "/books/add" element = {<AddBook />} />
                <Route path = "/books/edit" element = {<BookEdit />} />
            </Routes>
        </Router>
    </>
  )
}

export default WebRoutes