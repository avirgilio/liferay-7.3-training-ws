import React, {useEffect} from 'react';

const UserInfoPanelBodyComponent = ({name, surname, age}) => {

    useEffect(() => {
        console.log(`SHOW USER: ${name} ${surname}`)
    }, [name, surname, age]);

    return (
        <div>
            <h2> Full Name: {name} {surname} </h2>
            <h3> Age: {age} </h3>
        </div>
    );
};

export default UserInfoPanelBodyComponent;