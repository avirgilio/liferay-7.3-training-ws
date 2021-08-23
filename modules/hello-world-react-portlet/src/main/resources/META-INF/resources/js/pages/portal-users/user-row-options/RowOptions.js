import React, { useContext } from "react";
import { useHistory } from "react-router-dom";

import ClayButton from "@clayui/button";
import { ClayDropDownWithItems } from "@clayui/drop-down";
import ClayIcon from "@clayui/icon";

import AppContext from "../../../AppContext";

function RowOptions({ user }) {
  const { spritemap } = useContext(AppContext);
  let history = useHistory();

  const OPTIONS = [
    {
      label: "View",
      onClick: () => history.push(`portal-users/view/${user.userId}`),
    },
  ];

  return (
    <ClayDropDownWithItems
      items={OPTIONS}
      spritemap={spritemap}
      trigger={
        <ClayButton
          aria-label="Open Dropdown"
          className="component-action"
          displayType="unstyled"
          monospaced
        >
          <ClayIcon spritemap={spritemap} symbol="ellipsis-v" />
        </ClayButton>
      }
    />
  );
}

export default RowOptions;
