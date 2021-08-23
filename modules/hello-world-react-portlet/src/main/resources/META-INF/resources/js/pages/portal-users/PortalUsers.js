import React from "react";

import ClayTable from "@clayui/table";
import ClayLayout from "@clayui/layout";

import RowOptions from "./user-row-options/RowOptions";
import useLiferayServiceOrSessionStorage from "../../hooks/useLiferayServiceOrSessionStorage";

const UserRow = ({ user }) => {
  return (
    <ClayTable.Row>
      <ClayTable.Cell>{user.firstName}</ClayTable.Cell>
      <ClayTable.Cell>{user.lastName}</ClayTable.Cell>
      <ClayTable.Cell>
        {" "}
        <RowOptions user={user} />{" "}
      </ClayTable.Cell>
    </ClayTable.Row>
  );
};

const PortalUsers = () => {
  const KEY_STORAGE_NAME = `portal-users-${Liferay.ThemeDisplay.getScopeGroupId()}`;

  const { data: portalUsers } = useLiferayServiceOrSessionStorage(
    "/user/get-group-users",
    [],
    {
      groupId: Liferay.ThemeDisplay.getScopeGroupId(),
    },
    true,
    KEY_STORAGE_NAME
  );

  return (
    <ClayLayout.ContainerFluid view>
      <ClayLayout.Row justify="center">
        <ClayTable>
          <ClayTable.Head>
            <ClayTable.Row>
              <ClayTable.Cell headingCell>{"First Name"}</ClayTable.Cell>
              <ClayTable.Cell headingCell>{"Last Name"}</ClayTable.Cell>
              <ClayTable.Cell headingCell>{"Actions"}</ClayTable.Cell>
            </ClayTable.Row>
          </ClayTable.Head>
          <ClayTable.Body>
            {portalUsers.map((user) => (
              <UserRow user={user} key={user.userId} />
            ))}
          </ClayTable.Body>
        </ClayTable>
      </ClayLayout.Row>
    </ClayLayout.ContainerFluid>
  );
};

export default PortalUsers;
