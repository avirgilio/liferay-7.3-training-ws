import React from "react";

import ClayLayout from "@clayui/layout";

export default function App() {
  return (
    <ClayLayout.ContainerFluid view>
      <ClayLayout.Row justify="center">
        <h1 style={{ color: "blue" }}>Hello From React!</h1>
      </ClayLayout.Row>
    </ClayLayout.ContainerFluid>
  );
}
