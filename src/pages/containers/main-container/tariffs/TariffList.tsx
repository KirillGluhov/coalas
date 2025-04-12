import { FC } from "react";
import { Tariff } from "./tariff/Tariff";
import { TariffListType } from "./Types";
import styles from './Tariffs.module.scss';

export const TariffList: FC<TariffListType> = ({tariffs}) => {

    return <div className={styles.tariffList}>
        {
            tariffs.map((tariff) => 
                <Tariff tariff={tariff} key={tariff.id}/>
            )
        }
    </div>
}