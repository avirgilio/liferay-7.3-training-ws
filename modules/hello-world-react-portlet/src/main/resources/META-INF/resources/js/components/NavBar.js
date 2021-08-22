import React from 'react';

import ClayLink from '@clayui/link';
import ClayNavigationBar from '@clayui/navigation-bar';
import ClayLayout from '@clayui/layout';

import {withRouter, useHistory} from 'react-router-dom';

export default withRouter(({location}) => {

	let history = useHistory();

	const isActive = (value) => {
		return location.pathname === value;
	}

	const label = () => {
		if (location.pathname.includes('portal-users')) {
			return Liferay.Language.get('portal-users-component-label') ;
		}
		else {
			return Liferay.Language.get('display-user-info-component-label') ;
		}
	};

	return (
	 	<ClayLayout.ContainerFluid view>
			<ClayLayout.Row justify="center">
				<ClayNavigationBar
					triggerLabel={label()}
				>
					<ClayNavigationBar.Item
						active={isActive('/')}
						onClick={() => history.push('/')}
					>
						<ClayLink
							className="nav-link"
							displayType="unstyled"
						>
							{Liferay.Language.get('display-user-info-component-label')}
						</ClayLink>
					</ClayNavigationBar.Item>

					<ClayNavigationBar.Item
						active={isActive(`/portal-users`)}
						onClick={() => history.push('/portal-users')}
					>
						<ClayLink
							className="nav-link"
							displayType="unstyled"
						>
							{Liferay.Language.get('portal-users-component-label')}
						</ClayLink>
					</ClayNavigationBar.Item>
				</ClayNavigationBar>
			</ClayLayout.Row>
        </ClayLayout.ContainerFluid>
	);
});
