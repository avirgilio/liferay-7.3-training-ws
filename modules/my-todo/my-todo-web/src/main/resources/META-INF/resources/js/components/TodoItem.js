import React from "react";
import ClayList from "@clayui/list";
import TodoQuickAction from "./TodoQuickAction";

export const TodoItem = ({ title, completed = false }) => {
  return (
    <ClayList.Item flex>
      <ClayList.ItemField expand>
        <ClayList.ItemTitle className={completed && "completed"}>
          {title}
        </ClayList.ItemTitle>
      </ClayList.ItemField>
      <ClayList.ItemField>
        <ClayList.QuickActionMenu>
          {completed ? (
            <TodoQuickAction
              symbol="times"
              handleClick={() => alert("Unchecked!")}
            />
          ) : (
            <TodoQuickAction
              symbol="check"
              handleClick={() => alert("Checked!")}
            />
          )}
          .
        </ClayList.QuickActionMenu>
      </ClayList.ItemField>
    </ClayList.Item>
  );
};
