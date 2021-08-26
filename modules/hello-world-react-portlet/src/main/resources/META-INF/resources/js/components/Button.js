import React from "react";
import ClayButton from "@clayui/button";

const Button = ({ displayType, handleOnClick, text, className = "" }) => {
  return (
    <ClayButton
      className={className}
      displayType={displayType}
      onClick={handleOnClick}
    >
      {text}
    </ClayButton>
  );
};

export default Button;
