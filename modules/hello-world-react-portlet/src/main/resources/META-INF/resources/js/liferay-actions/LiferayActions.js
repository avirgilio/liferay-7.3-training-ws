import { createResourceURL, fetch, openToast } from "frontend-js-web";

export default {
  MVCResourceCommand: function (
    baseResourceURL,
    resourceId,
    onSuccessFN,
    onErrorFN,
    resourceParams = {}
  ) {
    finalResourceParams = {
      ...resourceParams,
      p_p_resource_id: resourceId,
    };

    const portletURL = createResourceURL(baseResourceURL, finalResourceParams);

    fetch(portletURL.toString(), {
      method: "POST",
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        onSuccessFN(data);
      })
      .catch((err) => {
        onErrorFN(err);
      });
  },
};
