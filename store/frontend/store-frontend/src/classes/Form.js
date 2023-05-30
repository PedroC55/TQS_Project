import React, { Component } from 'react'
import { Products } from '../Components/Product-cards';
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
			selectedACP: '12312'
		}
	}


	

	componentDidMount() {
		axios.get(`http://localhost:8080/v1/mailMover/all`)
		  .then(res => {
			const acps = res.data;
			console.log(acps);
			this.setState({ acps });
			console.log(this.props.message)
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
		console.log(this.state.selectedACP);
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
		console.log(acp_id.name)
		const path = `http://localhost:8080/v1/mailMover/new/${user.name}/${acp_id.name}`;
		console.log(path)
		axios.post(path)
		  .then(res => {
			console.log(res);
			console.log(res.data);
			
			alert(`Compra realizada com sucesso no nome de - ${this.state.username}`);
			window.location.href = '/app';
		  })
		


	  }

	

	render() {
		const { username, address, topic, acps, selectedACP } = this.state
		return (
			<div className='big-box'>
            	<div className = "form-box">
            	    <h3>Enter your data:</h3>
				    <form onSubmit={this.handleSubmit}>


            	        <div className="field1">
            	            <label> Consumer info</label>
            	            <input placeholder="Name" type="text"  value={username} onChange={this.handleUsernameChange}/>
            	            <input placeholder="Phone 123456789" type="number"/>
            	            <input placeholder="E-mail" />
            	            <textarea placeholder="Shipping Address" type="text"  value={address} onChange={this.handleAddressChange}/>
            	            <input placeholder="Credit Card Number" type="number"/>
            	            <select className="" value={selectedACP} onChange={this.handleACPChange}>
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
				<div className='card-box'>
	
                    <Products 
                        id={this.props.message.state.data.state.data.id}
                        image={this.props.message.state.data.state.data.image}
                        name={this.props.message.state.data.state.data.name}
                        price={this.props.message.state.data.state.data.price}
                        totalSales={this.props.message.state.data.state.data.totalSales}
                        timeLeft={this.props.message.state.data.state.data.timeLeft}
                        rating={this.props.message.state.data.state.data.rating}
                    />
 
                </div>
			</div>
		)
	}
}

export default Form