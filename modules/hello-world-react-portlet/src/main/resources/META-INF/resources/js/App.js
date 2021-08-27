import React, { Suspense, lazy } from "react";
import {
  HashRouter as Router,
  Route,
  Switch,
  Redirect,
} from "react-router-dom";

import ClayLoadingIndicator from "@clayui/loading-indicator";

import NavBar from "./components/NavBar";
import AppContext from "./AppContext";
import MyErrorBoundary from "./components/MyErrorBoundary";

const ProtectedRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={(props) =>
      Liferay.ThemeDisplay.isSignedIn() ? (
        <Component {...props} />
      ) : (
        <Redirect to={{ pathname: "/no-auth" }} />
      )
    }
  />
);

export default function ({ context, displayUserInfoProps }) {
  const PortalUsers = lazy(
    () =>
      new Promise((resolve) => {
        console.log("Loading portal users component");

        import("./pages/portal-users/PortalUsers").then((module) =>
          resolve(module)
        );
      })
  );

  const UserDetail = lazy(
    () =>
      new Promise((resolve) => {
        console.log("Loading user detail component");

        import("./pages/portal-users/user-detail/UserDetail").then((module) =>
          resolve(module)
        );
      })
  );

  const DisplayUserInfoComponent = lazy(
    () =>
      new Promise((resolve) => {
        console.log("Loading display user info component");

        import(
          "./pages/display-user-info/DisplayUserInfoComponent"
        ).then((module) => resolve(module));
      })
  );

  const NoAuthPage = lazy(
    () =>
      new Promise((resolve) => {
        console.log("Loading no auth page component");

        import("./pages/no-auth-page/NoAuthPage").then((module) =>
          resolve(module)
        );
      })
  );

  const LiferayActionsView = lazy(
    () =>
      new Promise((resolve) => {
        console.log("Loading action view component");

        import("./pages/actions-demo-view/LiferayActionsView").then((module) =>
          resolve(module)
        );
      })
  );

  return (
    <AppContext.Provider value={context}>
      <MyErrorBoundary>
        <Suspense fallback={<ClayLoadingIndicator />}>
          <Router>
            <NavBar />

            <Switch>
              <Route
                exact
                path="/"
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

              <Route component={NoAuthPage} exact path="/no-auth" />
              <Route
                component={LiferayActionsView}
                exact
                path="/action-commands"
              />
            </Switch>
          </Router>
        </Suspense>
      </MyErrorBoundary>
    </AppContext.Provider>
  );
}
