import React, { useContext } from "react";
import PropTypes from "prop-types";

import { openToast } from "frontend-js-web";

import ClayLayout from "@clayui/layout";
import AppContext from "../../../AppContext";
import LiferayActions from "../../../liferay-actions/LiferayActions";
import { MY_DEMO_RESOURCE_COMMAND_ID } from "../../../constants/LiferayMVCActionCommandConstants";
import Button from "../../../components/Button";

const UserInfoPanelBodyComponent = ({ name, surname, age }) => {
  const { baseResourceURL } = useContext(AppContext);

  const handleClick = (name, surname) => {
    // p_p_auth = Liferay.authToken
    const resourceParameters = {
      name: name,
      surname: surname,
      age: age,
    };

    LiferayActions.MVCResourceCommand(
      baseResourceURL,
      MY_DEMO_RESOURCE_COMMAND_ID,
      (data) => {
        console.log(data);
        openToast({
          message: Liferay.Language.get("user-info-ok"),
          type: "success",
        });
      },
      (err) => {
        console.error(err);

        openToast({
          message: Liferay.Language.get("error"),
          type: "danger",
        });
      },
      resourceParameters
    );
  };

  const getCurrentUserInfo = () => `${surname} ${name} of age: ${age}`;

  return (
    <ClayLayout.ContainerFluid view>
      <ClayLayout.Row justify="start">
        <ClayLayout.Col size={6}>
          <h2>{getCurrentUserInfo()}</h2>
        </ClayLayout.Col>
        <ClayLayout.Col size={6}>
          <Button
            displayType="primary"
            handleOnClick={() => handleClick(name, surname, age)}
            text={Liferay.Language.get("info-panel-button-label")}
          />
        </ClayLayout.Col>
      </ClayLayout.Row>
    </ClayLayout.ContainerFluid>
  );
};

UserInfoPanelBodyComponent.defaultProps = {
  name: "Default Name",
  surname: "Default Surname",
  age: 18,
};

UserInfoPanelBodyComponent.propTypes = {
  name: PropTypes.string.isRequired,
  surname: PropTypes.string.isRequired,
  age: PropTypes.number,
  baseResourceURL: PropTypes.string.isRequired,
};

export default UserInfoPanelBodyComponent;
