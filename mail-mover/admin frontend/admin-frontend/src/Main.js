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
import { Box, Button, Modal, TextField, Typography , Menu,  MenuItem, Select, Tabs, Tab, IconButton} from '@mui/material';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';

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

  const [ACP_data, setAcp_Data] = useState(null);
  const [Order_data , setOrder_data] = useState(null);
  const [acps, setAcps] = useState([]);
  const [orders, setOrders] = useState([]);
  const [acpNameInput, setAcpNameInput] = useState('');
  const [addressInput, setAddressInput] = useState('');
  const [emailInput, setEmailInput] = useState('');
  const [passwordInput, setPasswordInput] = useState('');
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response1 = await axios.get('http://localhost:8080/v1/acp/all');
        const response2 = await axios.get('http://localhost:8080/v1/orders/all');
        setOrder_data(response2.data);
        setAcp_Data(response1.data);
        /*console.log(response.data);*/
        setAcps(response1.data);
        setOrders(response2.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

  const handleAcpInputChange = (event) => {
    setAcpNameInput(event.target.value);
  };

  const handleAddressInputChange = (event) => {
    setAddressInput(event.target.value);
  };

  const handleEmailInputChange = (event) => {
    setEmailInput(event.target.value);
  };

  const handlePasswordInputChange = (event) => {
    setPasswordInput(event.target.value);
  };

  const handleAddAcp = async () => {
    try {
      const response = await axios.post('http://localhost:8080/v1/acp/new', {
        address: addressInput,
        email: emailInput,
        name: acpNameInput,
        password: passwordInput
      });

      console.log('ACP added successfully:', response.data);

      handleClose();
    } catch (error) {
      console.error('Error adding ACP:', error);
    }
  };
 

  
  
  
 
  

  const [activeTab, setActiveTab] = useState(0);

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };



  const handleFilterByAcp = async () => {
    try {
      const acpId = document.getElementById('acpId').value; 

      const response = await axios.get(`http://localhost:8080/v1/orders/byAcp/${acpId}`);
      console.log(response.data);
      const filteredOrders = response.data;
      setOrders(filteredOrders); 
    } catch (error) {
      console.log('Error:', error);
    }
  };

  const handleFilterById = async () => {
    try {
      const orderId = document.getElementById('orderId').value; 

      const response = await axios.get(`http://localhost:8080/v1/orders/byId/${orderId}`);
      const order = response.data; 
      console.log(response.data);
      if (order && order.id) {
        setOrders([order]); 
      } else {
        setOrders([]); 
      }
    } catch (error) {
      console.log('Error:', error);
    }
  };

  const handleFilterReset = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/v1/orders/all`);
      console.log(response.data);
      setOrders(response.data); 
    } catch (error) {
      console.log('Error:', error);
    }
  };
  if(!ACP_data || !Order_data) {
    return (
      <div>No data</div>
    )
  }


  




    return (
      <div className='main'>
       
        <div className='tabs'>
          <Tabs value={activeTab} onChange={handleTabChange}>
            <Tab id="here" label="Parcels"/>
            <Tab label="ACP Points"/>
          </Tabs>
        </div>
        {activeTab === 0 && (
                <>
                  <div className='filers'>
                    <TextField
                        className='filteracp'
                        id="acpId"
                        label="Fitler by ACP"
                        variant="standard"
                        style={{marginLeft:'200px'}}
                        
                      />
                    <IconButton className='btn-flt-acp' aria-label="filter"  style={{ marginLeft: '10px', marginTop: '15px' ,backgroundColor: '#152238', borderRadius: 0 , color: 'white', width: '32px',height: '32px'}} onClick={handleFilterByAcp}>
                      <ArrowForwardIcon />
                    </IconButton>   
                    <TextField
                        className='filterid'
                        id="orderId"
                        label="Fitler by Id"
                        variant="standard"
                        style={{marginLeft:'30px'}}
                        
                      />
                    <IconButton className='btn-flt-id' aria-label="filter"  style={{ marginLeft: '10px', marginTop: '15px' ,backgroundColor: '#152238', borderRadius: 0 , color: 'white', width: '32px',height: '32px'}} onClick={handleFilterById}>
                      <ArrowForwardIcon />
                    </IconButton>  
                    
                    <Button style={{ marginLeft: '300px', marginTop: '15px' ,backgroundColor: '#152238', borderRadius: 0 , color: 'white'}} onClick={handleFilterReset}>Reset Filters</Button>
                                 
                  </div>
                  
                  <div  style={{ marginBottom: '100px' }} className='table'>
                  <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 800}} aria-label="customized table">
                      <TableHead>
                        <TableRow>        
                          <StyledTableCell align="right">Order Id</StyledTableCell>
                          <StyledTableCell align="right">ACP Delivery Date</StyledTableCell>
                          <StyledTableCell align="right">Client Name</StyledTableCell>
                          <StyledTableCell align="right">Client Pick Up Date </StyledTableCell>
                          <StyledTableCell align="right">ACP Id</StyledTableCell>
                          <StyledTableCell align="right">Store Pick Up Date</StyledTableCell>
                          <StyledTableCell align="right">Status</StyledTableCell>  
                          <StyledTableCell align="right">Change Status</StyledTableCell> 
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {orders.map((row , index) => (
                          <StyledTableRow className='orderrow' key={row.id}>
                            <StyledTableCell align="right">{row.id}</StyledTableCell>
                            <StyledTableCell align="right">{row.acpDeliveryDate ?  new Date(row.acpDeliveryDate).toLocaleDateString('en-GB') : ''}</StyledTableCell>
                            <StyledTableCell align="right">{row.clientName}</StyledTableCell>
                            <StyledTableCell align="right">{row.clientPickUpDate ?  new Date(row.clientPickUpDate).toLocaleDateString('en-GB') : 'N/A' }</StyledTableCell>
                            <StyledTableCell align="right">{row.acp.id}</StyledTableCell>
                            <StyledTableCell align="right">{row.storePickUpDate ?  new Date(row.storePickUpDate).toLocaleDateString('en-GB') : 'N/A'}</StyledTableCell>
                            <StyledTableCell align="right">{row.status}</StyledTableCell>
                            <StyledTableCell align="right">
                              <Select
                                value={row.status}
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
              </>
        )}
         {activeTab === 1 && (
        <div className='acp-table'>
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 800 }} aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell align="right">Id</StyledTableCell>
                  <StyledTableCell align="right">ACP</StyledTableCell>
                  <StyledTableCell align="right">Address</StyledTableCell>
                  <StyledTableCell align="right">Email</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {acps.map((row, index) => (
                  <StyledTableRow className='acprow' key={row.id}>
                    <StyledTableCell  align="right">{row.id}</StyledTableCell>
                    <StyledTableCell  align="right">{row.name}</StyledTableCell>
                    <StyledTableCell align="right">{row.address}</StyledTableCell>
                    <StyledTableCell  align="right">{row.email}</StyledTableCell>
                    {/*<StyledTableCell align="right">
                      <Button
                        sx={{ backgroundColor: '#8B0000' }}
                        variant="contained"
                      >
                        Remove ACP
                      </Button>
                    </StyledTableCell>*/}
                  </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
                    
          
          
          
          
          
          <div className="button">
        <Button onClick={handleOpen} variant="contained">Add ACP</Button>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <Box
              component="form"
              sx={{
                '& > :not(style)': { m: 1, width: '25ch' },
              }}
              noValidate
              autoComplete="off"
            >
              <Typography>Name</Typography>
              <TextField
                className='acpname'
                id="standard-basic"
                label="ACP"
                variant="filled"
                value={acpNameInput}
                onChange={handleAcpInputChange}
              />
              <Typography>Address</Typography>
              <TextField
                className='address'
                id="standard-basic"
                label="Description"
                variant="filled"
                value={addressInput}
                onChange={handleAddressInputChange}
              />
              <Typography>Email</Typography>
              <TextField
                className='email'
                id="standard-basic"
                label="Email"
                variant="filled"
                value={emailInput}
                onChange={handleEmailInputChange}
              />
              <Typography>Password</Typography>
              <TextField
                className='password'
                id="standard-basic"
                label="Password"
                variant="filled"
                value={passwordInput}
                onChange={handlePasswordInputChange}
              />
              <Button sx={{ backgroundColor: "#152238" }} variant="contained" disabled={!acpNameInput || !addressInput || !emailInput || !passwordInput} onClick={handleAddAcp}>
                Add ACP
              </Button>
            </Box>
          </Box>
        </Modal>
      </div>
        </div>
      )}     
       
      </div> 
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      

      
    )
}