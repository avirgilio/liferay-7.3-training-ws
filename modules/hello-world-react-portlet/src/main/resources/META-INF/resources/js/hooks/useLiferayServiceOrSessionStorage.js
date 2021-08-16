import { useState, useEffect } from "react";

export default function useLiferayServiceOrSessionStorage(
    endpoint,
    initialValue = {},
    serviceParams={}, 
    useCache = false, 
    sessionKey = "",
    getDataFromCacheFN = () => JSON.parse(sessionStorage.getItem(sessionKey))
) {
    
    const [serviceResponse, setServiceResponse] = useState({
        isLoading: true,
        error: false,
        data: initialValue
    });

    const fetchDataByAPI = () => {
        Liferay.Service(endpoint, serviceParams,
            (result) => {

                setServiceResponse({
                    isLoading: false,
                    error: false,
                    data: result
                });

                if (useCache) {
                    sessionStorage.setItem(sessionKey, JSON.stringify(result));
                }
           },
           (err) => {
               console.err(err);
               setServiceResponse(prevState => ({
                   ...prevState,
                   isLoading: false,
                   error: true
               }));
           }
       );
    };
    
    useEffect(() => {
        
        if (useCache && sessionStorage.getItem(sessionKey)) {
            console.log("GETTING DATA FROM CACHE");

            const dataFromCache = getDataFromCacheFN();
                        
            setServiceResponse({
                isLoading: false,
                error: false,
                data: dataFromCache
            });

        } else {
            console.log("GETTING DATA FROM API");
            fetchDataByAPI();
        }
    }, []);

    return serviceResponse;
}