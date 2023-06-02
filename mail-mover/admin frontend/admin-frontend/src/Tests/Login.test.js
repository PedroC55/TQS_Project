import { render, screen , fireEvent} from '@testing-library/react';
import Login from '../Login';
import { MemoryRouter } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { act } from 'react-dom/test-utils';
import userEvent from '@testing-library/user-event';





test("Test the Login mechanism" , () => {
    render( <MemoryRouter><Login/></MemoryRouter>)
    

    const usernameInput = screen.getByLabelText('Username');
    const passwordInput = screen.getByLabelText('Password');

    expect(usernameInput.value).toBe('');
    expect(passwordInput.value).toBe('');
    expect(screen.getByRole("button", {name: /Login/i})).toBeDisabled();
    fireEvent.change(usernameInput, { target: { value: 'testuser' } });
    fireEvent.change(passwordInput, { target: { value: 'testpassword' } });

    expect(usernameInput.value).toBe('testuser');
    expect(passwordInput.value).toBe('testpassword');

    
    act(() => {
        userEvent.type(screen.getByLabelText(/Username/i) , "Username");
        userEvent.type(screen.getByLabelText(/Password/i) , "Password");
      });
    
    expect(screen.getByRole("button", {name: /Login/i})).toBeEnabled();

});