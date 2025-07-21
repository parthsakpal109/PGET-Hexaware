import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const signUpUser = (e) => {
        e.preventDefault();
        const signUpData = {
            username: username,
            password: password,
        };

        axios.post('http://localhost:8081/auth/signup', signUpData)
            .then((res) => {
                alert(res.data);
                navigate("/login");
            })
            .catch((e) => {
                console.log("Error while sign up :" + e);
                alert("Sign up failed");
            });
    };

    return (
        <div className="container">
            <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Register</h2>
            <form onSubmit={signUpUser}>
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
                <button type='submit'>Register</button>
            </form>
        </div>
    );
};

export default Register;
