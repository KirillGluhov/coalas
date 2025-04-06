import { FC } from "react"
import { CardType } from "./Types"
import styles from './Card.module.scss';
import { Title } from "./title/Title";
import { useTheme } from "../../providers/theme/useTheme";
import { themes } from "../../providers/theme/ThemeProvider";

export const Card: FC<CardType> = ({title, children}) => {
    const {theme} = useTheme();

    return <div className={styles.card}>
        <Title text={title}/>
        <div className={`${styles.innerCard} ${theme === themes.light ? styles.light : styles.dark}`}>
            {children}
        </div>
    </div>
}