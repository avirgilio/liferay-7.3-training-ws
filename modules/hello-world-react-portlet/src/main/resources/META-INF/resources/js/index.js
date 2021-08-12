import React from 'react';

import DisplayUserInfoComponent from "./display-user-info/DisplayUserInfoComponent";
import DisplayUserInfoContext from "./display-user-info/DisplayUserInfoContext";
import NavBar from "./nav-bar/NavBar";

import {HashRouter as Router, Route, Switch} from 'react-router-dom';

const PortalUsers = () => {
	return (<h1> PORTAL USERS COMPONENT</h1>)
}

export default function ({context, props}) {
	return (
		<DisplayUserInfoContext.Provider value={context}>
			<Router>
				<NavBar />
				<Switch>
					<Route
						exact
						path='/'
						render={() => (
							<DisplayUserInfoComponent {...props} />
						)}
                    />
                    <Route
						component={PortalUsers}
						exact
						path="/portal-users"
					/>
				</Switch>
			</Router>
		</DisplayUserInfoContext.Provider>
	);
}
