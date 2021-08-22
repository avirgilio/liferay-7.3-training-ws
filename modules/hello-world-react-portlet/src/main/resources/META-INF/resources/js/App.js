import React from 'react';
import {HashRouter as Router, Route, Switch, Redirect} from 'react-router-dom';

import PortalUsers from "./pages/portal-users/PortalUsers";
import UserDetail from "./pages/portal-users/user-detail/UserDetail";
import DisplayUserInfoComponent from "./pages/display-user-info/DisplayUserInfoComponent";
import NoAuthPage from "./pages/no-auth-page/NoAuthPage";

import NavBar from "./components/NavBar";
import AppContext from './AppContext';

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

export default function ({context, displayUserInfoProps}) {
	return (
		<AppContext.Provider value={context}>
			<Router>
				<NavBar />

				<Switch>
					<Route
						exact
						path='/'
						render={() => (
							<DisplayUserInfoComponent {...displayUserInfoProps} />
						)}
                    />

                    <ProtectedRoute
						component={PortalUsers}
						exact
						path="/portal-users"
					/>

					<ProtectedRoute
						component={UserDetail}
						exact
						path="/portal-users/view/:userId"
					/>

					<Route
						component={NoAuthPage}
						exact
						path="/no-auth"
					/>
				</Switch>

			</Router>
		</AppContext.Provider>
	);
}
