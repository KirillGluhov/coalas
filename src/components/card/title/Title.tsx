import { FC } from "react";
import { TitleType } from "./Types";
import styles from './Title.module.scss';
import { useTheme } from "../../../providers/theme/useTheme";
import { themes } from "../../../providers/theme/ThemeProvider";

export const Title: FC<TitleType> = ({text}) => {
    const {theme} = useTheme();
    
    return <div className={`${styles.title} ${theme === themes.light ? styles.light : styles.dark}`}>
        <p className={`${styles.text} ${theme === themes.light ? styles.lightText : styles.darkText}`}>{text}</p>
    </div>
}