import React, {useState, useEffect} from 'react';

import ClayTable from '@clayui/table';
import ClayLayout from '@clayui/layout';

const UserRow = ({user}) => {
	return (
		<ClayTable.Row>
		  <ClayTable.Cell>{user.firstName}</ClayTable.Cell>
		  <ClayTable.Cell>{user.lastName}</ClayTable.Cell>
		</ClayTable.Row>
	)
}

const PortalUsers = () => {
	const [portalUser, setPortalUsers] = useState([]);

	 useEffect(() => {
		Liferay.Service('/user/get-group-users',
		  {
			groupId: Liferay.ThemeDisplay.getScopeGroupId()
		  },
		  function(users) {
			setPortalUsers(users);
		  }
		);
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