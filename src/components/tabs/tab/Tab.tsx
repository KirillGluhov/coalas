import { FC, useEffect } from "react"
import { TabType } from "../Types"
import styles from './Tab.module.scss';
import { useTheme } from "../../../providers/theme/useTheme";
import { themes } from "../../../providers/theme/ThemeProvider";
import { useCustomLocation } from "../../../hooks/useCustomLocation";
import { useNavigate } from "react-router";

export const Tab: FC<TabType> = ({title, path}) => {
    const {theme} = useTheme();
    const pathName = useCustomLocation();
    const navigate = useNavigate();

    return (
        <button
            className={`${styles.tab} ${theme === themes.light ? styles.light : styles.dark} ${pathName === path ? styles.active : ""}`}
            onClick={() => {
                if (path)
                {
                    navigate(`/${path}`)
                }
            }}
        >
            {title}
        </button>
    )
}