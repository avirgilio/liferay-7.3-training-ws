import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';

import ClayLayout from '@clayui/layout';
import ClayAlert from '@clayui/alert';

const UserInfoPanelAlert = ({name, surname, status, spritemap}) => {

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
