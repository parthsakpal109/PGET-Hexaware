import { Link, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import Dashboard from './components/Dashboard';
import AddBook from './components/AddBook';
import RemoveBook from './components/RemoveBook';

const Routing = () => {
    return (
        <div>
            <Routes>
                <Route path = "/home" element = {<Home/>} />
                <Route path = "/signin" element = {<SignIn/>} />
                <Route path = "/signup" element = {<SignUp/>} />
                <Route path = "/dashboard" element = {<Dashboard/>} />
                <Route path = "/add" element = {<AddBook/>} />
                <Route path = "/remove" element = {<RemoveBook/>} />
            </Routes>
        </div>
    )
}

export default Routing


