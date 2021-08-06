import React, {useEffect, useState, useContext} from 'react';
import PropTypes from 'prop-types';

import ClayLayout from '@clayui/layout';
import ClayAlert from '@clayui/alert';

import DisplayUserInfoContext from "../DisplayUserInfoContext";

const UserInfoPanelAlert = ({name, surname, status}) => {

	const {spritemap} = useContext(DisplayUserInfoContext);

	const displayType = status === 'ok' ? 'success' : 'danger';

	return (
	    <ClayLayout.ContainerFluid view>
			<ClayLayout.Row justify="start">
				<ClayAlert displayType={displayType} spritemap={spritemap}>
					<h3> Information send for user {name} {surname} </h3>
				</ClayAlert>
			</ClayLayout.Row>
		</ClayLayout.ContainerFluid>
	);
}

UserInfoPanelAlert.propTypes = {
    name: PropTypes.string.isRequired,
    surname: PropTypes.string.isRequired,
    status: PropTypes.oneOf(['ok', 'ko']).isRequired,
	spritemap: PropTypes.string.isRequired
}

export default UserInfoPanelAlert;
