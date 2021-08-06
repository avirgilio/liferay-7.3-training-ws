import React from 'react';

import DisplayUserInfoComponent from "./DisplayUserInfoComponent";
import DisplayUserInfoContext from "./DisplayUserInfoContext";

export default function ({context, props}) {
	return (
		<DisplayUserInfoContext.Provider value={context}>
			<DisplayUserInfoComponent {...props} />
		</DisplayUserInfoContext.Provider>
	);
}
