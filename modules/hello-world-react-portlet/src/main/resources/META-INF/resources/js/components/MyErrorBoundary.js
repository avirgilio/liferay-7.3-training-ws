import React, { Component } from "react";
import ErrorPage from "../pages/error-page/ErrorPage";

class MyErrorBoundary extends Component {
  constructor(props) {
    super(props);
    this.state = { error: null, errorInfo: null };
  }

  componentDidCatch(error, errorInfo) {
    this.setState({
      error: error,
      errorInfo: errorInfo,
    });
  }

  render() {
    if (this.state.errorInfo) {
      return (
        <ErrorPage
          errorMessage={this.state.error.toString()}
          errorStackTrace={this.state.errorInfo.componentStack}
        />
      );
    }
    return this.props.children;
  }
}

export default MyErrorBoundary;
