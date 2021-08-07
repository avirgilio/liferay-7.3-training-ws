import PropTypes from 'prop-types';
import React from 'react';

const DisplayUserInfoContext = React.createContext({
	spritemap: '',
	baseResourceURL: ''
});

DisplayUserInfoContext.displayName = 'MyDisplayUserInfoContext';

DisplayUserInfoContext.Provider.propTypes = {
	value: PropTypes.shape({
		spritemap: PropTypes.string,
		baseResourceURL: PropTypes.string
	})
};

export default DisplayUserInfoContext;
