import PropTypes from "prop-types";
import React from "react";

const AppContext = React.createContext({
  spritemap: "",
  baseResourceURL: "",
  baseActionURL: "",
  baseRenderURL: "",
});

AppContext.displayName = "AppContext";

AppContext.Provider.propTypes = {
  value: PropTypes.shape({
    spritemap: PropTypes.string,
    baseResourceURL: PropTypes.string,
    baseActionURL: PropTypes.string,
    baseRenderURL: PropTypes.string,
  }),
};

export default AppContext;
