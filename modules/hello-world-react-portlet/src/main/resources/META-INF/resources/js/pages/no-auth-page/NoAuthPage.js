import React, { useContext } from "react";

import ClayLayout from "@clayui/layout";
import ClayAlert from "@clayui/alert";
import AppContext from "../../AppContext";

const NoAuthPage = () => {
  const { spritemap } = useContext(AppContext);

  return (
    <ClayLayout.ContainerFluid view>
      <ClayLayout.Row justify="center">
        <ClayLayout.Col size={8}>
          <ClayAlert displayType="info" spritemap={spritemap} title="Info">
            {Liferay.Language.get("you-have-to-be-logged")}
          </ClayAlert>
        </ClayLayout.Col>
      </ClayLayout.Row>
    </ClayLayout.ContainerFluid>
  );
};

export default NoAuthPage;
