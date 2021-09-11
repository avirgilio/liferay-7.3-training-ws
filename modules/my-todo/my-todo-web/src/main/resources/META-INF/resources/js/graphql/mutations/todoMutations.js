import { gql } from "@apollo/client";

export const POST_TODO = gql`
  mutation CREATE_TODO($id: Int, $description: String, $completed: Boolean) {
    createTodo(
      todo: { id: $id, description: $description, completed: $completed }
    ) {
      id
      description
      completed
    }
  }
`;

export const DELETE_TODO = gql`
  mutation DELETE_TODO($todoId: Int!) {
    deleteTodo(todoId: $todoId)
  }
`;

export const UPDATE_TODO = gql`
  mutation UPDATE_TODO(
    $todoId: Int!
    $description: String
    $completed: Boolean
  ) {
    updateTodo(
      entityId: $todoId
      todo: { id: $todoId, description: $description, completed: $completed }
    ) {
      id
      description
      completed
    }
  }
`;
