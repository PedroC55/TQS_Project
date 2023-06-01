import './Main.css';
import React, { useState } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Box, Button, Modal, TextField, Typography , Menu,  MenuItem, Select, Tabs, Tab} from '@mui/material';

import axios from 'axios';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';


const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: "#feb236",
      color: theme.palette.common.black,
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


export default function Main() {


      const [acps, setAcps] = useState([]);
      const [orders, setOrders] = useState([]);
      const { id } = useParams();
      console.log(id);


      useEffect(() => {
        const fetchData = async () => {
          try {
            const response1 = await axios.get(`http://localhost:8080/v1/orders/byAcp/${id}`); 
            /*console.log(response.data);*/
              setOrders(response1.data);
            } catch (error) {
            console.error(error);
          }
        };

        fetchData();
      }, []);



      if(!acps) {
        return (
          <div>No data</div>
    )
  }





    return (

        <div className="main">
            <div className="table">
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
                          <StyledTableRow key={row.id}>
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
        </div>



    )

    



}