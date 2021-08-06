import PropTypes from 'prop-types';
import React from 'react';

const DisplayUserInfoContext = React.createContext({
	spritemap: '',
	baseResourceURL: ''
});

DisplayUserInfoContext.Provider.propTypes = {
	value: PropTypes.shape({
		spritemap: PropTypes.string,
		baseResourceURL: PropTypes.string
	})
};

export default DisplayUserInfoContext;
