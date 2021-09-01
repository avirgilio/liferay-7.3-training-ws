import { gql } from "@apollo/client";

export const GET_TODOS_QUERY = gql`
  {
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
