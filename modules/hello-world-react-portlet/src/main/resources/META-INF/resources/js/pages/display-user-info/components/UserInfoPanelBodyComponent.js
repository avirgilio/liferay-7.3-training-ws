import React, {useEffect, useContext} from 'react';
import PropTypes from 'prop-types';

import {createResourceURL, fetch, openToast } from 'frontend-js-web';

import ClayButton from '@clayui/button';
import ClayLayout from '@clayui/layout';
import AppContext from '../../../AppContext';

const UserInfoPanelBodyComponent = ({name, surname, age}) => {

	const {baseResourceURL} = useContext(AppContext);

    useEffect(() => {
        console.log(`SHOW USER: ${name} ${surname}`)
    }, [name, surname, age]);

    const handleClick = (name, surname) => {

		// p_p_auth = Liferay.authToken
		const resourceParameters = {
			p_p_resource_id: "myResourceCommand",
			name: name,
			surname: surname,
			age: age
		};

       const portletURL = createResourceURL(baseResourceURL, resourceParameters);

       fetch (portletURL.toString(), {
            method: 'POST'
        })
        	.then((response) => {
        		return response.json();
        	})
            .then((data) => {
               console.log(data);
               openToast({
					message: Liferay.Language.get('user-info-ok'),
					type: 'success',
				});
            })
            .catch((err) => {
                console.error("EXCEPTION!");
                console.error(err);

                openToast({
					message: Liferay.Language.get('error'),
					type: 'danger',
				});
            });
    };

    return (
        <ClayLayout.ContainerFluid view>
			<ClayLayout.Row justify="start">
                <ClayLayout.Col size={4}>
					<h2> {name} {surname}, {age} </h2>
				</ClayLayout.Col>
				<ClayLayout.Col size={4}>
					<ClayButton
						displayType="primary"
						onClick={() => handleClick(name, surname, age)}
					>
						{Liferay.Language.get('info-panel-button-label')}
					</ClayButton>
				</ClayLayout.Col>
			</ClayLayout.Row>
        </ClayLayout.ContainerFluid>
    );
};

UserInfoPanelBodyComponent.defaultProps = {
    name: 'Default Name',
    surname: 'Default Surname',
    age: 18
}

UserInfoPanelBodyComponent.propTypes = {
    name: PropTypes.string.isRequired,
    surname: PropTypes.string.isRequired,
    age: PropTypes.number,
    baseResourceURL: PropTypes.string.isRequired
}

export default UserInfoPanelBodyComponent;