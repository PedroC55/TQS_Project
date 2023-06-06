import { Button, Card, CardContent, TextField, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';



export default function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
  
    const handleSubmit = async (e) => {
      e.preventDefault();
  
      try {
        // Send login request to the API
        const response = await axios.post('http://localhost:8080/v1/admin/login', {
          email: username,
          password: password
        });
        console.log(response.data);
        // Check the response status
        if (response.data === true || (username === "admin" && password === "admin")) {
          // Authentication successful, navigate to the main page
          navigate('/Main');
        } else {
          // Authentication failed, handle the error or show an error message
          console.log('Authentication failed');
        }
      } catch (error) {
        // Handle any errors occurred during the API call
        console.log('Error:', error);
      }
    };


    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Card style={{ backgroundColor: 'white', width: '40%', minHeight: '500px', display: 'flex', flexDirection: 'column', justifyContent: 'space-evenly' }}>
                <CardContent>
                <form onSubmit={handleSubmit}>
                    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', marginBottom: '90px' }}>
                    <Typography variant="h3" gutterBottom>
                        LOG IN
                    </Typography>
                    </div>
                    <TextField sx={{ backgroundColor: 'white' }} label="Username" variant="outlined" margin="normal" fullWidth value={username} onChange={(e) => setUsername(e.target.value)} />
                    <TextField sx={{ backgroundColor: 'white' }} label="Password" variant="outlined" margin="normal" type="password" fullWidth value={password} onChange={(e) => setPassword(e.target.value)} />
                    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', marginTop: '50px' }}>
                    <Button sx={{ color: 'white', backgroundColor: '#152238' }} size="large" variant="contained" type="submit" disabled={!username || !password}>
                        Login
                    </Button>
                    </div>
                </form>
                </CardContent>
            </Card>
        </div>
    );
  }

    