import React, {useContext} from 'react';
import ClayPanel from '@clayui/panel';
import ClayLayout from '@clayui/layout';

import UserInfoPanelBodyComponent from "./components/UserInfoPanelBodyComponent";
import AppContext from '../../AppContext';

export default ({name, surname}) => {
	const {spritemap} = useContext(AppContext)

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
                            />
                        </ClayPanel.Body>
                    </ClayPanel>
                </ClayLayout.Col>
            </ClayLayout.Row>
        </ClayLayout.ContainerFluid>
    );
};