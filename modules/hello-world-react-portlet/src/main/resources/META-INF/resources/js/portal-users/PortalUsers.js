import React, {useState, useEffect} from 'react';

import ClayTable from '@clayui/table';
import ClayLayout from '@clayui/layout';

import RowOptions from './components/RowOptions';

const UserRow = ({user}) => {
	return (
		<ClayTable.Row>
		  <ClayTable.Cell>{user.firstName}</ClayTable.Cell>
		  <ClayTable.Cell>{user.lastName}</ClayTable.Cell>
		  <ClayTable.Cell> <RowOptions user={user} /> </ClayTable.Cell>
		</ClayTable.Row>
	)
}

const PortalUsers = () => {
	const [portalUser, setPortalUsers] = useState([]);
	const KEY_STORAGE_NAME = `portal-users-${Liferay.ThemeDisplay.getScopeGroupId()}`;

	const fetchGroupUsers = () => {
		Liferay.Service('/user/get-group-users',
		  {
			groupId: Liferay.ThemeDisplay.getScopeGroupId()
		  },
		  function(users) {
			setPortalUsers(users);
			sessionStorage.setItem(KEY_STORAGE_NAME, JSON.stringify(users))
		  }
		);
	}

	useEffect(() => {
		if (sessionStorage.getItem(KEY_STORAGE_NAME)) {
			let users = JSON.parse(sessionStorage.getItem(KEY_STORAGE_NAME));
			setPortalUsers(users);
		}
		else {
			fetchGroupUsers();
		}
     }, []);

	return (
		<ClayLayout.ContainerFluid view>
			<ClayLayout.Row justify="center">
				<ClayTable>
					<ClayTable.Head>
						<ClayTable.Row>
						  <ClayTable.Cell headingCell>
							{"First Name"}
						  </ClayTable.Cell>
						  <ClayTable.Cell headingCell>{"Last Name"}</ClayTable.Cell>
						  <ClayTable.Cell headingCell>{"Actions"}</ClayTable.Cell>
						</ClayTable.Row>
					</ClayTable.Head>
					<ClayTable.Body>
						{portalUser.map((user) =>
							<UserRow user={user} key={user.userId} />
						)}
					</ClayTable.Body>
				</ClayTable>
			</ClayLayout.Row>
		</ClayLayout.ContainerFluid>
	)
}

export default PortalUsers;