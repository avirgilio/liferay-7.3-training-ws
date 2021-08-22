import React from 'react';
import { ClayInput } from '@clayui/form';

export const DisabledInputField = ({ id, defaultValue, label }) => {
	return (
		<div class="mt-3">
			<label htmlFor={id}> {label} </label>
			<ClayInput
				id={id}
				placeholder={`Insert ${label} here`}
				type="text"
				defaultValue={defaultValue}
				disabled 	
			/>
		</div>
	);
};
