import { FC } from "react";
import { themes } from "../../providers/theme/ThemeProvider";
import { useTheme } from "../../providers/theme/useTheme";
import styles from './InnerCard.module.scss';
import { InnerCardType } from "./Types";

export const InnerCard: FC<InnerCardType> = ({children, id}) => {
    const {theme} = useTheme();
    
    return <div className={`${styles.card} ${theme === themes.light ? styles.light : styles.dark}`} key={id} id={id}>
        {children}
    </div>
}