import React from "react"

class Header extends React.Component{
    render() {
        return (
            <div>
                <h1>COVID Tracker by Tejus Nataraju</h1>
                <p className="lead">
                    This application gives you the information related to the number of active COVID cases
                    reported in a country/state/region daily.
                </p>
            </div>
    )
    }
}

export default Header;