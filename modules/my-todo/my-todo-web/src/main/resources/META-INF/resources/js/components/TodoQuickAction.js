import ClayList from "@clayui/list";
import React from "react";
import { useContext } from "react";
import TodoAppContext from "../TodoAppContext";

export const TodoQuickAction = ({ symbol, handleClick }) => {
  const { spritemap } = useContext(TodoAppContext);

  return (
    <ClayList.QuickActionMenu.Item
      onClick={handleClick}
      spritemap={spritemap}
      symbol={symbol}
    />
  );
};

export default TodoQuickAction;
