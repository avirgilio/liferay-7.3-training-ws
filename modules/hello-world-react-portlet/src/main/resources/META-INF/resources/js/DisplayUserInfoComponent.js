import React from 'react';
import ClayPanel from '@clayui/panel';
import ClayLayout from '@clayui/layout';

import UserInfoPanelBodyComponent from "./components/UserInfoPanelBodyComponent";

export default ({name, surname, age, spritemap}) => {
    return (
        <ClayLayout.ContainerFluid view>
            <ClayLayout.Row justify="center">
                <ClayLayout.Col size={8}>
                    <ClayPanel
                        collapsable
                        displayTitle={name}
                        displayType="secondary"
                        showCollapseIcon={true}
                        spritemap={spritemap}
                    >
                        <ClayPanel.Body>
                            <UserInfoPanelBodyComponent
                                name={name}
                                surname={surname}
                                age={age}
                            />
                        </ClayPanel.Body>
                    </ClayPanel>
                </ClayLayout.Col>
            </ClayLayout.Row>
        </ClayLayout.ContainerFluid>
    );
};