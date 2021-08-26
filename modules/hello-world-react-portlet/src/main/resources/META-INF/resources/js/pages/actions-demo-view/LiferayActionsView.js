import React, { useContext } from "react";

import ClayLayout from "@clayui/layout";
import ClayButton from "@clayui/button";

import LiferayActions from "../../liferay-actions/LiferayActions";
import AppContext from "../../AppContext";
import { openToast } from "frontend-js-web";

import {
  MY_DEMO_ACTION_COMMAND_ID,
  MY_DEMO_RENDER_COMMAND_ID,
  MY_DEMO_RESOURCE_COMMAND_ID,
} from "../../constants/LiferayMVCActionCommandConstants";

export const LiferayActionsView = () => {
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
          <ClayButton
            displayType="success"
            onClick={() => callResourceCommand()}
          >
            {"Call Resource Command!"}
          </ClayButton>

          <ClayButton
            displayType="secondary"
            onClick={() => callActionCommand()}
          >
            {"Call Action Command!"}
          </ClayButton>

          <ClayButton displayType="danger" onClick={() => callRenderCommand()}>
            {"Call Render Command!"}
          </ClayButton>
        </ClayLayout.Col>
      </ClayLayout.Row>
    </ClayLayout.ContainerFluid>
  );
};
