import { render, screen , fireEvent} from '@testing-library/react';
import Login from '../Login';
import { MemoryRouter } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { act } from 'react-dom/test-utils';
import userEvent from '@testing-library/user-event';
import Main from '../Main';


test('renders Main component without errors', () => {
    render(<Main></Main>);
    screen.debug();
  });
