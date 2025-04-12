import { FC } from "react"
import { ButtonStyleType, ButtonType } from "./Types"
import styles from './Button.module.scss';
import { useTheme } from "../../providers/theme/useTheme";
import { themes } from "../../providers/theme/ThemeProvider";

export const Button: FC<ButtonType> = ({text, onClick, type}) => {
    const {theme} = useTheme();

    return <button 
        onClick={onClick} 
        className={`
            ${styles.button} 
            ${
                theme === themes.light ? 
                styles.light : 
                styles.dark
            } 
            ${
                type === ButtonStyleType.Create ? 
                styles.create : 
                type === ButtonStyleType.Delete ? 
                styles.delete : 
                ""
            }
        `}
    >{text}</button>
}