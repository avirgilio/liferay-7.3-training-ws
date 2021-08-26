import {
  createResourceURL,
  createActionURL,
  createRenderURL,
  fetch,
} from "frontend-js-web";

function fetchMVCCommandsAction(portletURL, onSuccessFN, onErrorFN) {
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
}

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

    fetchMVCCommandsAction(portletURL, onSuccessFN, onErrorFN);
  },
  MVCActionCommand: function (
    baseActionURL,
    actionId,
    onSuccessFN,
    onErrorFN,
    actionParams = {}
  ) {
    finalActionParams = {
      ...actionParams,
      "javax.portlet.action": actionId,
    };

    const portletURL = createActionURL(baseActionURL, finalActionParams);

    fetch(portletURL.toString(), {
      method: "POST",
    })
      .then((data) => {
        onSuccessFN(data);
      })
      .catch((err) => {
        onErrorFN(err);
      });
  },
  MVCRenderCommand: function (
    baseRenderURL,
    renderCommandId,
    renderParams = {}
  ) {
    finalRenderURLParams = {
      ...renderParams,
      mvcRenderCommandName: renderCommandId,
    };

    const portletURL = createRenderURL(baseRenderURL, finalRenderURLParams);

    Liferay.Util.navigate(portletURL);
  },
};
