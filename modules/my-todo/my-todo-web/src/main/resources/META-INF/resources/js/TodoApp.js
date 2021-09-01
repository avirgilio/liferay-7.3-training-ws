import React from "react";

import ClayLayout from "@clayui/layout";
import ClayList from "@clayui/list";
import { TodoItem } from "./components/TodoItem";
import TodoAppContext from "./TodoAppContext";

export default function App({ context }) {
  return (
    <TodoAppContext.Provider value={context}>
      <ClayLayout.ContainerFluid view>
        <ClayList showQuickActionsOnHover>
          <TodoItem
            title="My Title"
            description="Hover this item for quick action menu"
          />
        </ClayList>
      </ClayLayout.ContainerFluid>
    </TodoAppContext.Provider>
  );
}
