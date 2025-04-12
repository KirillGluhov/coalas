import { pages } from "../../consts"
import { themes } from "../../providers/theme/ThemeProvider";
import { useTheme } from "../../providers/theme/useTheme";
import { Tab } from "./tab/Tab"
import styles from './Tabs.module.scss';

export const Tabs = () => {
    const {theme} = useTheme();

    return <div className={`${styles.tabs} ${theme === themes.light ? styles.light : styles.dark}`}>
        <Tab title="Счета" path={pages.accounts.link}/>
        <Tab title="Кредиты" path={pages.loans.link}/>
        <Tab title="Сотрудники" path={pages.employees.link}/>
        <Tab title="Клиенты" path={pages.clients.link}/>
        <Tab title="Тарифы" path={pages.tariffs.link}/>
    </div>
}