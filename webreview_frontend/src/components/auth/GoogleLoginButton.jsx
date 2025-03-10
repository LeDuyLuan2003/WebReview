import React from 'react';
import { GoogleLogin } from 'react-google-login';
import { loginWithGoogle } from '../../services/authService';
import { useNavigate } from 'react-router-dom';

function GoogleLoginButton() {
  const navigate = useNavigate();

  const handleSuccess = async (response) => {
    try {
      const token = await loginWithGoogle(response.tokenId);
      localStorage.setItem('token', token); // Lưu token
      alert('Login with Google successful!');
      navigate('/'); // Chuyển hướng về trang chủ
    } catch (error) {
      alert(error.message);
    }
  };

  const handleFailure = (error) => {
    console.error('Google login failed:', error);
    alert('Google login failed');
  };

  return (
    <GoogleLogin
      clientId="YOUR_GOOGLE_CLIENT_ID"
      buttonText="Login with Google"
      onSuccess={handleSuccess}
      onFailure={handleFailure}
      cookiePolicy={'single_host_origin'}
    />
  );
}

export default GoogleLoginButton;