const React = require('react');

class LocationStatRow extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td>{this.props.index}</td>
                <td>{this.props.locationRow.state}</td>
                <td>{this.props.locationRow.country}</td>
                <td>{this.props.locationRow.latestTotalCases}</td>
                <td>{this.props.locationRow.latestTotalCases - this.props.locationRow.prevDayTotalCases}</td>
            </tr>
        )
    }
}

export default LocationStatRow;