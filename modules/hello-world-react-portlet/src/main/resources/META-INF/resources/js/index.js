import React from 'react';

import DisplayUserInfoComponent from "./display-user-info/DisplayUserInfoComponent";
import DisplayUserInfoContext from "./display-user-info/DisplayUserInfoContext";

export default function ({context, props}) {
	return (
		<DisplayUserInfoContext.Provider value={context}>
			<DisplayUserInfoComponent {...props} />
		</DisplayUserInfoContext.Provider>
	);
}
