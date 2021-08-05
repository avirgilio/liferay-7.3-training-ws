import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import ClayButton from '@clayui/button';

const UserInfoPanelBodyComponent = ({name, surname, age, baseResourceURL}) => {

    useEffect(() => {
        console.log(`SHOW USER: ${name} ${surname}`)
    }, [name, surname, age]);

    const handleClick = (name, surname) => {

        let portletURL = new Liferay.PortletURL.createURL(baseResourceURL);

        portletURL.setResourceId('myResourceCommand');
        portletURL.setParameter('p_auth', Liferay.authToken);

        const data = {
            "name": name,
            "surname": surname,
            "age": age
        };

        Liferay.Util.fetch(portletURL.toString(), {
            body: JSON.stringify(data),
            method: 'POST',
        })
            .then((response) => {
                return response.json();
            })
            .then((data) => {
               console.log("Sending data ok!");
            })
            .catch((err) => {
                console.error("EXCEPTION!");
                console.error(err);
            });
    };

    return (
        <>
            <h2> Full Name: {name} {surname} </h2>
            <h3> Age: {age} </h3>

            <ClayButton
                displayType="primary"
                onClick={() => handleClick(name, surname, age)}
            >
                {Liferay.Language.get('send-user-notification-button-label')}
            </ClayButton>
        </>
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