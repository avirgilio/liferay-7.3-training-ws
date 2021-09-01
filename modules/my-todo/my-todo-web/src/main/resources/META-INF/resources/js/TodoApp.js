import React from "react";

import ClayLayout from "@clayui/layout";
import TodoAppContext from "./TodoAppContext";
import { ApolloProvider } from "@apollo/client";
import { client } from "./graphql/client";
import TodoList from "./components/TodoList";

export default function App({ context }) {
  return (
    <TodoAppContext.Provider value={context}>
      <ApolloProvider client={client}>
        <ClayLayout.ContainerFluid view>
          <TodoList />
        </ClayLayout.ContainerFluid>
      </ApolloProvider>
    </TodoAppContext.Provider>
  );
}
