import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const SocialLogin = () => {
  const navigate = useNavigate();
  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const accessToken = params.get('accessToken');
    if (accessToken !== null) {
      localStorage.setItem('accessToken', accessToken);
    }
    navigate('/main');
  }, []);

  return <div>Trans</div>;
};

export default SocialLogin;
