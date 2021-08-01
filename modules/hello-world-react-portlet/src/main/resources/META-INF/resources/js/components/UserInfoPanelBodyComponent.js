import React, {useEffect} from 'react';

const UserInfoPanelBodyComponent = ({name, surname, age}) => {

    useEffect(() => {
        console.log(`SHOW USER: ${name} ${surname}`)
    }, [name, surname, age]);

    return (
        <div>
            <h2> {name} {surname} </h2>
            <h3> {age} </h3>
        </div>
    );
};

export default UserInfoPanelBodyComponent;