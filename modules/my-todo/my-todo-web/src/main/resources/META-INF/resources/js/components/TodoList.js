import React, { useState } from "react";

import { useQuery } from "@apollo/client";
import ClayList from "@clayui/list";
import ClayLoadingIndicator from "@clayui/loading-indicator";
import { GET_TODOS_QUERY } from "../graphql/queries/todoQueries";
import { TodoItem } from "./TodoItem";
import { ClayInput } from "@clayui/form";

const TodoList = () => {
  const [todoSearchTerm, setTodoSearchTerm] = useState("");
  const { loading, error, data } = useQuery(GET_TODOS_QUERY, {
    fetchPolicy: "network-only",
    nextFetchPolicy: "cache-first",
  });

  if (loading) return <ClayLoadingIndicator />;
  if (error) return <p>Error :(</p>;

  const todos = !todoSearchTerm
    ? data.todos.items
    : data.todos.items.filter((todo) =>
        todo.description.toLowerCase().includes(todoSearchTerm.toLowerCase())
      );

  return (
    <>
      <ClayInput
        placeholder="Search Todo"
        value={todoSearchTerm}
        onChange={(e) => setTodoSearchTerm(e.target.value)}
      />

      <ClayList showQuickActionsOnHover>
        {todos.map((todo) => (
          <TodoItem
            key={todo.id}
            id={todo.id}
            title={todo.description}
            completed={todo.completed}
          />
        ))}
      </ClayList>
    </>
  );
};

export default TodoList;
