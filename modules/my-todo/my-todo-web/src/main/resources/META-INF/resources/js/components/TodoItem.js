import React from "react";
import ClayList from "@clayui/list";
import TodoQuickAction from "./TodoQuickAction";

export const TodoItem = ({ title, description, completed = false }) => {
  return (
    <ClayList.Item flex>
      <ClayList.ItemField expand>
        <ClayList.ItemTitle> {title}</ClayList.ItemTitle>
        <ClayList.ItemText>{description}</ClayList.ItemText>
      </ClayList.ItemField>
      <ClayList.ItemField>
        <ClayList.QuickActionMenu>
          <TodoQuickAction symbol="check" handleClick={() => alert("Check!")} />
        </ClayList.QuickActionMenu>
      </ClayList.ItemField>
    </ClayList.Item>
  );
};
