import React from "react";

export const ErrorDiv = ({ errorMessage }) => {
  return (
    <div>
      <h1> Error!</h1>
      <h2 style={{ color: "red" }}> {errorMessage} </h2>
    </div>
  );
};
