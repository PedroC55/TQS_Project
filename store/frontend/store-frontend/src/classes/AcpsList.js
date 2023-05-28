import React from 'react';
import axios from 'axios';

export default class AcpList extends React.Component {
  state = {
    acps: []
  }

  componentDidMount() {
    axios.get(`https://localhost:8080/v1/all`)
      .then(res => {
        const acps = res.data;
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