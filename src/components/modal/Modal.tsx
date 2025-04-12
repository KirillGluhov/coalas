import { FC } from "react";
import { ModalType } from "./Types";
import styles from './Modal.module.scss';
import { useTheme } from "../../providers/theme/useTheme";
import { themes } from "../../providers/theme/ThemeProvider";
import Close from '../../assets/icons/close.svg';

export const Modal: FC<ModalType> = ({open, setOpen, children}) => {

    const {theme} = useTheme();

    if (!open) return null;

    return <div className={styles.overlay}>
        <div className={`${styles.content} ${theme === themes.light ? styles.light : styles.dark}`}>
            <div className={styles.wrapper}>
                <button onClick={() => setOpen(false)} className={styles.close}>
                    <img src={Close}/>
                </button>
            </div>
            {children}
        </div>
    </div>;
}