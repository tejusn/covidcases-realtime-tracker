import LocationStatRow from "./LocationStatRow";

const React = require('react');

class Table extends React.Component{

    constructor(props) {
        super(props);
    }


    render() {
        const locationRows = this.props.locationRowData.map((locationRowData, index) =>
            <LocationStatRow key={index} locationRow={locationRowData}/>
        );
        return (
            <table class="table-responsive table-striped table-bordered table-sm table-hover row">
                <thead>
                <tr>
                    <th>SL/No</th>
                    <th>State/Province</th>
                    <th>Country</th>
                    <th>Total Cases Reported</th>
                    <th>Difference from Prev day</th>
                </tr>
                </thead>
                <tbody>
                    {locationRows}
                </tbody>

            </table>
        )
    }
}

export default Table;