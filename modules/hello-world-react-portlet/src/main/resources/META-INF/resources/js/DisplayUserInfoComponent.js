import React from 'react';
import ClayPanel from '@clayui/panel';
import UserInfoPanelBodyComponent from "./components/UserInfoPanelBodyComponent";

export default ({name, surname, age, spritemap}) => {
    return (
        <ClayPanel
            collapsable
            displayTitle={name}
            displayType="secondary"
            showCollapseIcon={true}
            spritemap={spritemap}
        >
            <ClayPanel.Body>
                <UserInfoPanelBodyComponent name={name} surname={surname} age={age} />
            </ClayPanel.Body>
        </ClayPanel>
    );
};