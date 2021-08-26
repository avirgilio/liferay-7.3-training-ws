import React, { useContext } from "react";

import ClayLayout from "@clayui/layout";

import LiferayActions from "../../liferay-actions/LiferayActions";
import AppContext from "../../AppContext";
import { openToast } from "frontend-js-web";

import {
  MY_DEMO_ACTION_COMMAND_ID,
  MY_DEMO_RENDER_COMMAND_ID,
  MY_DEMO_RESOURCE_COMMAND_ID,
} from "../../constants/LiferayMVCActionCommandConstants";

import Button from "../../components/Button";

const LiferayActionsView = () => {
  const { baseResourceURL, baseActionURL, baseRenderURL } = useContext(
    AppContext
  );

  const callResourceCommand = () => {
    const resourceParameters = {
      name: "MY NAME",
      surname: "MY SURNAME",
      age: 26,
    };

    LiferayActions.MVCResourceCommand(
      baseResourceURL,
      MY_DEMO_RESOURCE_COMMAND_ID,
      (data) => {
        console.log(data);
        openToast({
          message: "Called Resource Command!",
          type: "success",
        });
      },
      (err) => {
        console.error(err);
        openToast({
          message: "Error While Calling Resource Command!",
          type: "danger",
        });
      },
      resourceParameters
    );
  };

  const callActionCommand = () => {
    const actionParameters = {
      text: "MY ACTION COMMAND TEXT!",
    };

    LiferayActions.MVCActionCommand(
      baseActionURL,
      MY_DEMO_ACTION_COMMAND_ID,
      (data) => {
        openToast({
          message: "Called Action Command!",
          type: "success",
        });
      },
      (err) => {
        console.error(err);
        openToast({
          message: "Error While Calling Action Command!",
          type: "danger",
        });
      },
      actionParameters
    );
  };

  const callRenderCommand = () => {
    const renderParameters = {
      text: "MY RENDER COMMAND TEXT!",
    };

    LiferayActions.MVCRenderCommand(
      baseRenderURL,
      MY_DEMO_RENDER_COMMAND_ID,
      renderParameters
    );
  };

  return (
    <ClayLayout.ContainerFluid>
      <ClayLayout.Row justify="center">
        <ClayLayout.Col size={8}>
          <Button
            displayType="success"
            handleOnClick={callResourceCommand}
            text="Call Resource Command"
          />

          <Button
            displayType="secondary"
            handleOnClick={callActionCommand}
            text="Call Action Command"
          />

          <Button
            displayType="danger"
            handleOnClick={callRenderCommand}
            text="Call Render Command"
          />
        </ClayLayout.Col>
      </ClayLayout.Row>
    </ClayLayout.ContainerFluid>
  );
};

export default LiferayActionsView;
