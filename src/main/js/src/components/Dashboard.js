import React from "react";

class Dashboard extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="jumbotron row">
                <div className="col-md-6">
                    <h1 className="display-4">
                        {this.props.totalReportedCases}
                    </h1>
                    <p className="lead">Total Cases reported as of today</p>
                </div>
                <div className="col-md-6">
                    <h1 className="display-4">
                        {this.props.totalReportedCasesToday}
                    </h1>
                    <p className="lead">Total Cases reported just today</p>
                </div>
                    <p>This data is updated daily. Checkout this website hourly to get latest updates.</p>
            </div>
        )
    }
}

export default Dashboard;