import React from 'react';

import {useParams, useHistory} from 'react-router-dom';

import ClayLayout from '@clayui/layout';
import {ClayInput} from '@clayui/form';
import ClayButton from '@clayui/button';
import useLiferayServiceOrSessionStorage from '../../../hooks/useLiferayServiceOrSessionStorage';

const Input = ({id, defaultValue, label}) => {
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
	)
}

const UserDetail = () => {
	const KEY_STORAGE_NAME = `portal-users-${Liferay.ThemeDisplay.getScopeGroupId()}`;

	let { userId } = useParams();
	let history = useHistory();

	const {data: user} = useLiferayServiceOrSessionStorage('/user/get-user-by-id', {} ,{
		userId: userId
	  }, 
	  true,
	  KEY_STORAGE_NAME,
	  () => {
		  let users = JSON.parse(sessionStorage.getItem(KEY_STORAGE_NAME));
		  return users.find(user => user.userId == userId);
	  }
	);
	
	return (
		<ClayLayout.ContainerFluid view>
			<ClayLayout.Row justify="center">
				<ClayLayout.Col size={8}>
					<Input id="firstName" defaultValue={user.firstName} label="Name" />
					<Input id="screenName" defaultValue={user.screenName} label="Screen Name" />
					<Input id="jobTitle" defaultValue={user.jobTitle} label="Job Title" />
					<Input id="emailAddress" defaultValue={user.emailAddress} label="Mail Address" />
					<Input id="lastName" defaultValue={user.lastName} label="Surname" />
				</ClayLayout.Col>
			</ClayLayout.Row>
			<ClayLayout.Row justify="end">
				<ClayLayout.Col size={8}>
					<ClayButton
						className="mt-5"
						displayType="secondary"
						onClick={() => history.push('/portal-users')}
					>
						{Liferay.Language.get('go-back-to-portal-users')}
					</ClayButton>
				</ClayLayout.Col>
			</ClayLayout.Row>
		</ClayLayout.ContainerFluid>
	);
}

export default UserDetail;