import { ApolloClient, HttpLink, InMemoryCache } from "@apollo/client";

const HEADERS = {
  Accept: "application/json",
  "Accept-Language": Liferay.ThemeDisplay.getBCP47LanguageId(),
  "Content-Type": "text/plain; charset=utf-8",
};

export const client = new ApolloClient({
  cache: new InMemoryCache(),
  defaultOptions: {
    query: {
      errorPolicy: "all",
    },
  },
  link: new HttpLink({
    credentials: "include",
    fetch,
    headers: HEADERS,
    uri: "/o/graphql",
  }),
});
