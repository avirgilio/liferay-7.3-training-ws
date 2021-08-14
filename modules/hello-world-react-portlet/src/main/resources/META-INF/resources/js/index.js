import React from 'react';
import {HashRouter as Router, Route, Switch, Redirect} from 'react-router-dom';

import DisplayUserInfoComponent from "./display-user-info/DisplayUserInfoComponent";
import DisplayUserInfoContext from "./display-user-info/DisplayUserInfoContext";
import PortalUsers from "./portal-users/PortalUsers";

import NavBar from "./nav-bar/NavBar";
import NoAuthPage from "./no-auth-page/NoAuthPage";

const ProtectedRoute = ({component: Component, ...rest}) => (
	<Route
		{...rest}
		render={(props) =>
			Liferay.ThemeDisplay.isSignedIn()
				? (<Component {...props} />)
				: (<Redirect to={{pathname: '/no-auth'}} />
			)
		}
	/>
);

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

                    <ProtectedRoute
						component={PortalUsers}
						exact
						path="/portal-users"
					/>

					<Route
						component={NoAuthPage}
						exact
						path="/no-auth"
					/>

				</Switch>
			</Router>
		</DisplayUserInfoContext.Provider>
	);
}
