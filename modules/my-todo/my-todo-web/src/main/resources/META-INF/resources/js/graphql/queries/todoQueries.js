import { gql } from "@apollo/client";

export const GET_TODOS_QUERY = gql`
  query getTodos {
    todos {
      items {
        description
        completed
        id
      }
      page
      pageSize
      totalCount
    }
  }
`;
