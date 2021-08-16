import PropTypes from 'prop-types';
import React from 'react';

const AppContext = React.createContext({
	spritemap: '',
	baseResourceURL: ''
});

AppContext.displayName = 'AppContext';

AppContext.Provider.propTypes = {
	value: PropTypes.shape({
		spritemap: PropTypes.string,
		baseResourceURL: PropTypes.string
	})
};

export default AppContext;
