import React, { useState } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import './Main.css';
import { Box, Button, Modal, TextField, Typography , Menu,  MenuItem, Select, Tabs, Tab} from '@mui/material';
import axios from 'axios';
import { useEffect } from 'react';






    const StyledTableCell = styled(TableCell)(({ theme }) => ({
      [`&.${tableCellClasses.head}`]: {
        backgroundColor: "#152238",
        color: theme.palette.common.white,
      },
      [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
      },
    }));

    const StyledTableRow = styled(TableRow)(({ theme }) => ({
      '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
      },
      // hide last border
      '&:last-child td, &:last-child th': { 
        border: 0,
      },
    }));

    function createEntry(ACP, trackingNumber , user, state) {
      return { ACP, trackingNumber, user, state};
    }
    
    const rows = [
      createEntry('ACP1', 14231,"José Lopes","No ponto"),
      createEntry('ACP2', 23212,"Ana Ferreira","Na Loja"),
      createEntry('ACP2', 83731,"Luís Monteiro","Com estafeta"),
      createEntry('ACP3', 42132,"Joana Antunes","Com estafeta"),
      createEntry('ACP4', 12313,"Filipa Ribeiro","Entregue"),
      
    ];

    

    const style = {
      position: 'absolute',
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)',
      width: 400,
      bgcolor: 'background.paper',
      border: '2px solid #000',
      boxShadow: 24,
      p: 4,
    };

export default function Main () {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  
  const [data, setData] = useState(null);
  const [acps, setAcps] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://192.168.1.71:8080/v1/acp/all');
        setData(response.data);
        /*console.log(response.data);*/
        setAcps(response.data);
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);


 

  
  
  
  const [rows, setRows] = useState([
    { ACP: 'ACP 1', trackingNumber: '123', user: 'User 1', state: 'Na Loja' },
    { ACP: 'ACP 2', trackingNumber: '132', user: 'User 2', state: 'Com estafeta' },
    { ACP: 'ACP 2', trackingNumber: '456', user: 'User 3', state: 'Com estafeta' },
    { ACP: 'ACP 3', trackingNumber: '445', user: 'User 4', state: 'No ponto' },
    { ACP: 'ACP 4', trackingNumber: '743', user: 'User 5', state: 'Entregue' }
  ]);
  const handleStateChange = (newState, trackingNumber) => {
    setRows((prevRows) => {
      return prevRows.map((row) => {
        if (row.trackingNumber === trackingNumber) {
          return { ...row, state: newState };
        }
        return row;
      });
    });
  };

  const [activeTab, setActiveTab] = useState(0);

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };


  
 
  







  const [acpInput, setAcpInput] = useState('');
  const [descriptionInput, setDescriptionInput] = useState('');

  
  const handleAcpInputChange = (event) => {
    setAcpInput(event.target.value);
  };

  const handleDescriptionInputChange = (event) => {
    setDescriptionInput(event.target.value);
  };





  if(!data) {
    return (
      <div>No data</div>
    )
  }


  




    return (
      <div className='main'>
       
        <div className='tabs'>
          <Tabs value={activeTab} onChange={handleTabChange}>
            <Tab label="Parcels"/>
            <Tab label="ACP Points"/>
          </Tabs>
        </div>
        {activeTab === 0 && (
                  <div  style={{ marginBottom: '100px' }} className='table'>
                  <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 800}} aria-label="customized table">
                      <TableHead>
                        <TableRow>
                          
                          <StyledTableCell align="right">ACP</StyledTableCell>
                          <StyledTableCell align="right">Tracking Number</StyledTableCell>
                          <StyledTableCell align="right">User</StyledTableCell>
                          <StyledTableCell align="right">State</StyledTableCell>
                          <StyledTableCell align="right">Change State</StyledTableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {rows.map((row) => (
                          <StyledTableRow key={row.trackingNumber}>
                            <StyledTableCell align="right">{row.ACP}</StyledTableCell>
                            <StyledTableCell align="right">{row.trackingNumber}</StyledTableCell>
                            <StyledTableCell align="right">{row.user}</StyledTableCell>
                            <StyledTableCell align="right">{row.state}</StyledTableCell>
                            <StyledTableCell align="right">
                              <Select
                                value={row.state}
                                onChange={(event) => handleStateChange(event.target.value, row.trackingNumber)}
                              >
                                <MenuItem value="Na Loja">Na Loja</MenuItem>
                                <MenuItem value="Com estafeta">Com estafeta</MenuItem>
                                <MenuItem value="No ponto">No ponto</MenuItem>
                                <MenuItem value="Entregue">Entregue</MenuItem>
                              </Select>
                            </StyledTableCell>
                          </StyledTableRow>
                        ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </div>
        )}
         {activeTab === 1 && (
        <div className='acp-table'>
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 800 }} aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell align="right">ACP</StyledTableCell>
                  <StyledTableCell align="right">Address</StyledTableCell>
                  <StyledTableCell align="right">Email</StyledTableCell>
                  <StyledTableCell align="right">Actions</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {acps.map((row, index) => (
                  <StyledTableRow key={row.id}>
                    <StyledTableCell align="right">{row.name}</StyledTableCell>
                    <StyledTableCell align="right">{row.address}</StyledTableCell>
                    <StyledTableCell align="right">{row.email}</StyledTableCell>
                    <StyledTableCell align="right">
                      <Button
                        sx={{ backgroundColor: '#8B0000' }}
                        variant="contained"
                      >
                        Remove ACP
                      </Button>
                    </StyledTableCell>
                  </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
                    
          
          
          
          
          
          <div className='button'>
            <Button onClick={handleOpen} variant="contained">Add ACP</Button>
            <Modal
              open={open}
              onClose={handleClose}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"      >
              <Box sx={style}>
              <Box
                  component="form"
                  sx={{
                    '& > :not(style)': { m: 1, width: '25ch' },
                  }}
                  noValidate
                  autoComplete="off"
                >
                  <Typography>ACP</Typography>
                  <TextField id="standard-basic" label="ACP" variant="filled" 
                    value={acpInput}
                    onChange={handleAcpInputChange} />
                  <Typography>Description</Typography>
                  <TextField id="standard-basic" label="Description" variant="filled" 
                    value={descriptionInput}
                    onChange={handleDescriptionInputChange} />
                  <Button sx={{ backgroundColor: "#152238" }} variant="contained">Add ACP</Button>
                </Box>
              </Box>
            </Modal>
        </div>
        </div>
      )}     
       
      </div> 
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      

      
    )
}