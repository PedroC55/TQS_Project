import React, { Component } from 'react'
import AcpList from "./AcpsList";
import "../css/form.css"
import { Link } from 'react-router-dom';

class Form extends Component {
	constructor(props) {
		super(props)

		this.state = {
			username: '',
			address: '',
			topic: 're'
		}
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

	handleTopicChange = event => {
		this.setState({
			topic: event.target.value
		})
	}

	handleSubmit = event => {
		alert(`${this.state.username} ${this.state.address} ${this.state.topic}`)
		event.preventDefault()
	}

	render() {
		const { username, address, topic } = this.state
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
                        <AcpList></AcpList>
                    </div>
                    <Link to={"/app"}>
			    	    <button type="submit" className='submitBtn'>Submit</button>
                    </Link>
			    </form>
            </div>
		)
	}
}

export default Form