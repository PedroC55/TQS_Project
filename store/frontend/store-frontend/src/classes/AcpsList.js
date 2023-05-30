import React from 'react';
import axios from 'axios';

export default class AcpList extends React.Component {
  state = {
    acps: []
  }

  componentDidMount() {
    axios.get(`http://localhost:8080/v1/mailMover/all`)
      .then(res => {
        const acps = res.data;
        console.log(acps);
        this.setState({ acps });
      })
  }

  render() {
    return (
      <select value="111" >
        {
          this.state.acps
            .map(acp => {
              return (<option key={acp.id} value={acp.name}>{acp.name}</option>);
            })
        }
      </select>
    )
  }
}