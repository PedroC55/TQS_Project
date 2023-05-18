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

    const [rows, setRows] = useState([
        {trackingNumber: '123', user: 'User 1', state: 'Na Loja' },
        {trackingNumber: '132', user: 'User 2', state: 'Com estafeta' },
        {trackingNumber: '456', user: 'User 3', state: 'Com estafeta' },
        {trackingNumber: '445', user: 'User 4', state: 'No ponto' },
        {trackingNumber: '743', user: 'User 5', state: 'Entregue' }
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


    return (

        <div className="main">
            <div className="table">
            <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 800}} aria-label="customized table">
                      <TableHead>
                        <TableRow>
                          <StyledTableCell align="right">Tracking Number</StyledTableCell>
                          <StyledTableCell align="right">User</StyledTableCell>
                          <StyledTableCell align="right">State</StyledTableCell>
                          <StyledTableCell align="right">Change State</StyledTableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {rows.map((row) => (
                          <StyledTableRow key={row.trackingNumber}>

                            <StyledTableCell align="right">{row.trackingNumber}</StyledTableCell>
                            <StyledTableCell align="right">{row.user}</StyledTableCell>
                            <StyledTableCell align="right">{row.state}</StyledTableCell>
                            <StyledTableCell align="right">
                              <Select
                                value={row.state}
                                onChange={(event) => handleStateChange(event.target.value, row.trackingNumber)}
                              >
                                <MenuItem value="Na Loja">Na Loja</MenuItem>
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