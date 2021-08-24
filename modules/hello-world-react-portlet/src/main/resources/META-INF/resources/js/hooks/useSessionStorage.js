import { useState, useEffect } from "react";

export default function useSessionStorage(
  sessionKey,
  retrieveValueFN = () => JSON.parse(sessionStorage.getItem(sessionKey))
) {
  const [valueFromCache, setValueFromCache] = useState({});
  const [error, setError] = useState({ hasError: false, errorMessage: "" });

  useEffect(() => {
    if (sessionStorage.getItem(sessionKey)) {
      const value = retrieveValueFN();
      setValueFromCache(value);
    } else {
      setError({
        hasError: true,
        errorMessage: "Cannot find value in cache!",
      });
    }
  }, []);

  return { valueFromCache, error };
}
