import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const loginUser = (e) => {
        e.preventDefault();
        const loginData = {
            username: username,
            password: password,
        };

        axios.post('http://localhost:8081/auth/signin', loginData)
            .then((res) => {
                const token = res.data;
                localStorage.setItem('token', token);
                alert("Login done");
                navigate("/books");
            })
            .catch((e) => {
                console.log("Error while login :" + e);
                alert("Login failed");
            });
    };

    return (
        <div className="container">
            <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Login</h2>
            <form onSubmit={loginUser}>
                <input
                    type='text'
                    placeholder='Username'
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
                <input
                    type='password'
                    placeholder='Password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type='submit'>Login</button>
            </form>
            <p style={{ marginTop: '20px', textAlign: 'center' }}>
                Create Account? <Link to="/register" style={{ color: '#ccc' }}>Register</Link>
            </p>
        </div>
    );
};

export default Login;
