import { ToastContainer } from 'react-toastify';
import styled from 'styled-components';

export const Container = styled(ToastContainer)`
  .Toastify__toast {
    font-size: 1.2rem;
    font-family: 'ONE-Mobile-POP';
    text-align: center;
    border-radius: 20px;
    padding: 0.5rem 0.875rem;
    color: black;
    background: lightyellow;
    border: 3px solid black;
  }

  .Toastify__toast-icon {
    width: 1.375rem;
    height: 1.375rem;
  }

  .Toastify__toast--info {
    background: ivory;
  }

  .Toastify__toast--success {
    background: rgba(48, 173, 120, 0.95);
  }

  .Toastify__toast--error {
    background: rgba(224, 72, 82, 0.95);
  }
  .Toastify__progress-bar {
    background: darkgray;
  }
`;
