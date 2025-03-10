import React, { useState } from 'react';
import { loginUser, loginWithGoogle } from '../../services/authService';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [formData, setFormData] = useState({ username: '', password: '' });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await loginUser(formData);
            const token = response.data.token;
            localStorage.setItem('token', token);
            alert('Đăng nhập thành công!');
            navigate('/');
        } catch (err) {
            setError(err.response?.data?.error || 'Đăng nhập thất bại');
        }
    };

    return (
        <div>
            <h2>Đăng nhập</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="username" placeholder="Username hoặc Email" onChange={handleChange} required />
                <input type="password" name="password" placeholder="Password" onChange={handleChange} required />
                <button type="submit">Đăng nhập</button>
            </form>
            <button onClick={loginWithGoogle}>Đăng nhập bằng Google</button>
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </div>
    );
};

export default Login;
