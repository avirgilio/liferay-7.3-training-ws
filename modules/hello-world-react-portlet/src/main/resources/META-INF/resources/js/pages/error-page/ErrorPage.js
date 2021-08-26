import ClayLayout from "@clayui/layout";
import React from "react";
import ClayAlert from "@clayui/alert";
import { useContext } from "react";
import AppContext from "../../AppContext";
import Button from "../../components/Button";

const ErrorPage = ({ errorMessage, errorStackTrace }) => {
  const { spritemap } = useContext(AppContext);

  return (
    <ClayLayout.ContainerFluid view>
      <ClayLayout.Row justify="center">
        <h2>Error while loading app...</h2>
      </ClayLayout.Row>

      <ClayLayout.Row justify="center">
        <ClayLayout.Col size={8}>
          <ClayAlert displayType="danger" spritemap={spritemap} title="Error:">
            {errorMessage}
          </ClayAlert>
          <details style={{ whiteSpace: "pre-wrap" }}>
            <summary>Show Stack Trace</summary>
            {errorStackTrace}
          </details>
        </ClayLayout.Col>
      </ClayLayout.Row>

      <ClayLayout.Row justify="center">
        <Button
          displayType="secondary"
          handleOnClick={() => window.location.reload()}
          text="Reload Page"
        />
      </ClayLayout.Row>
    </ClayLayout.ContainerFluid>
  );
};

export default ErrorPage;
