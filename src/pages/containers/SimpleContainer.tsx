import { pages } from "../../consts"
import { Login } from "./login/Login"
import { useCustomLocation } from "../../hooks/useCustomLocation";
import styles from './SimpleContainer.module.scss';

export const SimpleContainer = () => {
    const page = useCustomLocation();

    const cards = {
        [pages.login.link]: <Login/>
    }

    return <div className={styles.wrapper}>
        {cards[page]}
    </div>
}