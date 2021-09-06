import { useQuery } from "@apollo/client";
import ClayList from "@clayui/list";
import ClayLoadingIndicator from "@clayui/loading-indicator";
import React from "react";
import { GET_TODOS_QUERY } from "../graphql/queries/todoQueries";
import { TodoItem } from "./TodoItem";

const TodoList = () => {
  const { loading, error, data } = useQuery(GET_TODOS_QUERY, {
    fetchPolicy: "network-only",
    nextFetchPolicy: "cache-first",
  });

  if (loading) return <ClayLoadingIndicator />;
  if (error) return <p>Error :(</p>;

  return (
    <ClayList showQuickActionsOnHover>
      {data.todos.items.map((todo) => (
        <TodoItem
          key={todo.id}
          id={todo.id}
          title={todo.description}
          completed={todo.completed}
        />
      ))}
    </ClayList>
  );
};

export default TodoList;
