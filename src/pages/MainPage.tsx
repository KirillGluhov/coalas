import { pages } from "../consts";
import { useAppSelector } from "../hooks/redux";
import { Navigate } from 'react-router'


export const MainPage = () => {
    const authdata = useAppSelector(state => state.userProfileView.authData);

    if (authdata?.accessToken && authdata?.userType === "EMPLOYEE")
    {
        return <Navigate to={pages.tariffs.link}/>
    }

    return <Navigate to={pages.login.link}/>;
}