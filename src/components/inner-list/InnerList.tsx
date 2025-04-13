import { FC } from "react";
import { themes } from "../../providers/theme/ThemeProvider";
import { useTheme } from "../../providers/theme/useTheme";
import { Card } from "../card/Card";
import styles from './InnerList.module.scss';
import { InnerListType } from "./Types";

export const InnerList: FC<InnerListType> = ({title, createButton, isLoading, error, mapper}) => {
    const {theme} = useTheme();
    
    return (
        <div className={styles.entities}>
            <Card title={title} additionalElement={createButton}>
                <div className={`${styles.innerPart} ${theme === themes.light ? styles.light : styles.dark}`}>
                    {
                        isLoading ? <p>Загрузка</p> :
                        error ? <p>Ошибка</p> :
                        <div className={styles.entityList}>
                            {mapper}
                        </div>
                    }
                </div>
            </Card>
        </div>
    )
}