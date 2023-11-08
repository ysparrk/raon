import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getMemberActive } from '../../../api/MemberApi.tsx';

const SocialLogin = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const accessToken = params.get('accessToken');
    if (accessToken !== null) {
      localStorage.setItem('accessToken', accessToken);
    }

    getMemberActive()
      .then((response) => {
        if (response.data.data.active) {
          localStorage.setItem('nickname', response.data.data.nickname);
          navigate('/main');
        } else {
          navigate('/information/submit');
        }
      })
      .catch((error) => {
        console.error('There was an error checking the member status:', error);
      });
  }, [navigate]);

  return <div>Redirecting...</div>;
};

export default SocialLogin;
