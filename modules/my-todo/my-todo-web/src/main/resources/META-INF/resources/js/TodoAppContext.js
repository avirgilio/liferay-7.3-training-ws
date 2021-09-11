import React from "react";

const TodoAppContext = React.createContext({
  spritemap: "",
  baseResourceURL: "",
});

TodoAppContext.displayName = "TodoAppContext";

export default TodoAppContext;
