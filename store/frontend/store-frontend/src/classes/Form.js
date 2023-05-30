import React, { Component } from 'react'
import AcpList from "./AcpsList";
import "../css/form.css"
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useState } from 'react';

class Form extends Component {
	constructor(props) {
		super(props)


		this.state = {
			username: '',
			address: '',
			acps: [],
			selectedACP: ''
		}
	}

	

	componentDidMount() {
		axios.get(`http://localhost:8080/v1/mailMover/all`)
		  .then(res => {
			const acps = res.data;
			console.log(acps);
			this.setState({ acps });
		  })
	  }

	handleUsernameChange = event => {
		this.setState({
			username: event.target.value
		})
	}

	handleAddressChange = event => {
		this.setState({
			address: event.target.value
		})
	}

	handleACPChange = event => {
		const selectedKey = event.target.value;
    	this.setState({ selectedACP: selectedKey });
	}

	handleSubmit = event => {
		event.preventDefault();
	
		const user = {
		  name: this.state.username
		  
		};
		console.log(user)
		const acp_id = {
			name: this.state.selectedACP
		  };
		console.log(acp_id)
		axios.post(`http://localhost:8080/v1/mailMover/new/${user}/${acp_id}`)
		  .then(res => {
			console.log(res);
			console.log(res.data);
		  })
		alert(`${this.state.username} ${this.state.address}`)


	  }

	

	render() {
		const { username, address, topic, acps, selectedACP } = this.state
		return (
            <div className = "form-box">
                <h3>Introduzir dados:</h3>
			    <form onSubmit={this.handleSubmit}>
			    	

                    <div className="field1">
                        <label> customer info </label>
                        <input placeholder="Name" type="text"  value={username} onChange={this.handleUsernameChange}/>
                        <input placeholder="Phone 123456789" type="number"/>
                        <input placeholder="E-mail" />
                        <textarea placeholder="Shipping Address" type="text"  value={address} onChange={this.handleAddressChange}/>
                        <input placeholder="Credit Card Number" type="number"/>
                        <select value={selectedACP} onChange={this.handleACPChange} >
        					{
        					  this.state.acps
        					    .map(acp => {
        					      return (<option key={acp.id} value={acp.id}>{acp.name}</option>);
        					    })
        					}
      					</select>
                    </div>

			    	<button type="submit" className='submitBtn'>Submit</button>

			    </form>
            </div>
		)
	}
}

export default Form