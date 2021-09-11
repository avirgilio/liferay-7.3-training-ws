import React from "react";
import ClayList from "@clayui/list";
import TodoQuickAction from "./TodoQuickAction";
import { DELETE_TODO, UPDATE_TODO } from "../graphql/mutations/todoMutations";
import { GET_TODOS_QUERY } from "../graphql/queries/todoQueries";
import { useMutation } from "@apollo/client";

export const TodoItem = ({ id, title, completed }) => {
  const [deleteTodo] = useMutation(DELETE_TODO, {
    refetchQueries: [GET_TODOS_QUERY, "getTodos"],
  });

  const [updateTodo] = useMutation(UPDATE_TODO, {
    refetchQueries: [GET_TODOS_QUERY, "getTodos"],
  });

  const deleteTodoActionClick = () => {
    deleteTodo({
      variables: {
        todoId: id,
      },
    });
  };

  const updateTodoActionClick = () => {
    updateTodo({
      variables: {
        todoId: id,
        description: title,
        completed: !completed,
      },
    });
  };

  const completedSymbol = completed ? "times" : "check";

  return (
    <ClayList.Item flex>
      <ClayList.ItemField expand>
        <ClayList.ItemTitle className={completed && "completed"}>
          {title}
        </ClayList.ItemTitle>
      </ClayList.ItemField>
      <ClayList.ItemField>
        <ClayList.QuickActionMenu>
          <TodoQuickAction
            symbol={completedSymbol}
            handleClick={updateTodoActionClick}
          />
          <TodoQuickAction
            symbol="times-circle"
            handleClick={deleteTodoActionClick}
          />
          .
        </ClayList.QuickActionMenu>
      </ClayList.ItemField>
    </ClayList.Item>
  );
};
