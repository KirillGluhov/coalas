import { themes } from "../../providers/theme/ThemeProvider";
import { useTheme } from "../../providers/theme/useTheme"
import { Toggle } from "../toggle/Toggle";
import styles from './Header.module.scss';
import Koala from "../../assets/logos/koala.svg";
import Profile from '../../assets/logos/profile.svg';
import { authentificationAPI } from "../../store/services/authentificationService";
import { getDisplayName } from "./utils";
import { useAppDispatch, useAppSelector } from "../../hooks/redux";
import { skipToken } from "@reduxjs/toolkit/query";
import { useNavigate } from "react-router";
import { pages } from "../../consts";
import { Button } from "../button/Button";
import { clearAuthData, setAuthData } from "../../store/slices/userProfileViewSlice";
import { useEffect } from "react";

export const Header = () => {
    const {theme, setTheme} = useTheme();
    const navigate = useNavigate();
    const dispatch = useAppDispatch();

    const authData = useAppSelector(state => state.userProfileView.authData);

    const {data: profile, isLoading, error} = authentificationAPI.useGetProfileQuery(authData?.accessToken ? undefined : skipToken);
    const {data: tokens} = authentificationAPI.useGetRefreshTokenQuery({refreshToken: authData?.refreshToken}, {skip: !authData?.refreshToken, pollingInterval: 1000 * 60 * 8})

    useEffect(() => {
        if (!authData)
        {
            const authInStorage = sessionStorage.getItem("auth");

            if (authInStorage)
            {
                dispatch(setAuthData(JSON.parse(authInStorage)));
            }
            else
            {
                navigate(pages.login.link)
            }
        }
    },[authData])

    useEffect(() => {
        if (tokens)
        {
            dispatch(setAuthData(tokens));
        }
    },[tokens])

    return (
        <header className={`${styles.header} ${theme === themes.light ? styles.light : styles.dark}`}>
            <div className={styles.logo}>
                <img src={Koala}/>
                <p className={`${styles.logoText} ${theme === themes.light ? styles.lightText : styles.darkText}`}>Koala Bank</p>
                <Toggle
                    onChange={() => {
                        if (theme === themes.light) setTheme(themes.dark)
                        if (theme === themes.dark) setTheme(themes.light)
                    }}
                    value={theme === themes.dark}
                />
            </div>
            <div>
                {
                    isLoading ? <p>Загрузка</p> :
                    error ? <p>Ошибка</p> :
                    (profile && authData) ? <div className={styles.rightPart}>
                        <div className={styles.profile}>
                            <img src={Profile}></img>
                            <p className={styles.name}>{getDisplayName(profile)}</p>
                        </div>
                        <Button onClick={() => {
                            dispatch(clearAuthData()); 
                            sessionStorage.clear();
                            navigate(pages.login.link);
                        }} text={"Выйти"}></Button>
                    </div> : <Button onClick={() => navigate(pages.login.link)} text={"Войти"}></Button>
                }
            </div>
        </header>
    )
}