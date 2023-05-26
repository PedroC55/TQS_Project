import { Button, Card, CardContent, TextField, Typography } from "@mui/material";
import { Link } from "react-router-dom";




export default function Login() {
    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission logic here
      };


    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Card style={{backgroundColor: 'white' ,width: '40%' , minHeight: '500px' , display: 'flex', flexDirection: 'column', justifyContent: 'space-evenly' }}>
                <CardContent>
                    <form onSubmit={handleSubmit}>
                    <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', marginBottom: '90px'}}>
                    <Typography variant="h3" gutterBottom>
                        LOG IN
                    </Typography>
                    </div>
                    <TextField sx={{backgroundColor:'white'}} label="Username" variant="outlined" margin="normal" fullWidth />
                    <TextField sx={{backgroundColor:'white'}} label="Password" variant="outlined" margin="normal" type="password" fullWidth />
                    <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', marginTop: '50px'}}>
                        <Link to="/Main">
                            <Button sx={{color: 'white' , backgroundColor: '#feb236'}} size="large" variant="contained" type="submit" >
                                Login
                            </Button>
                        </Link>
                    </div>
                    </form>
                </CardContent>
            </Card>
        </div>
    );
  }

    