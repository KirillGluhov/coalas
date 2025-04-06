import { themes } from "../../providers/theme/ThemeProvider";
import { useTheme } from "../../providers/theme/useTheme"
import { Toggle } from "../toggle/Toggle";
import styles from './Header.module.scss';
import Koala from "../../assets/logos/koala.svg";

export const Header = () => {
    const {theme, setTheme} = useTheme();

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
        </header>
    )
}