import api from './axiosConfig';

// Đăng ký người dùng
export const registerUser = (userData) => api.post('api/auth/register', userData);

// Đăng nhập người dùng
export const loginUser = (userData) => api.post('api/auth/login', userData);

// Đăng nhập bằng Google
export const loginWithGoogle = () => {
  window.location.href = `${import.meta.env.VITE_API_BASE_URL}/api/auth/oauth2/success`;
};
