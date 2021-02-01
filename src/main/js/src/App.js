'use strict';

import React from 'react';
import Table from "./components/Table";
import Header from "./components/Header";
import Dashboard from "./components/Dashboard";
const axios = require('axios');

class App extends React.Component { // <1>

    constructor(props) {
        super(props);
        this.state = {allLocationStats: []};
        console.log("In Constructor")
    }

    componentDidMount() { // <2>
        axios.get("http://localhost:8080/restapi/react").then(locationStats => {
            this.setState({
                                allLocationStats: locationStats.data.allLocationStats,
                                totalReportedCases:locationStats.data.totalReportedCases,
                                totalReportedCasesToday:locationStats.data.totalReportedCasesToday
            });
            }
        );

    }

    render() { // <3>
        return (
            <div>
                <Header/>
                <Dashboard
                    totalReportedCases = {this.state.totalReportedCases}
                    totalReportedCasesToday={this.state.totalReportedCasesToday}>
                </Dashboard>
                <Table locationRowData={this.state.allLocationStats}/>
            </div>
        )
    }
}

export default App;
