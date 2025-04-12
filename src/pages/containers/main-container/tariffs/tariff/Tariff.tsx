import { FC } from "react";
import { TariffPropsType } from "./Types";
import styles from './Tariff.module.scss'
import { useTheme } from "../../../../../providers/theme/useTheme";
import { themes } from "../../../../../providers/theme/ThemeProvider";
import { Button } from "../../../../../components/button/Button";
import { ButtonStyleType } from "../../../../../components/button/Types";
import { userAPI } from "../../../../../store/services/userService";
import { getDisplayName } from "../../../../../components/header/utils";
import { DeleteTariff } from "../delete-tariff/DeleteTariff";

export const Tariff: FC<TariffPropsType> = ({tariff}) => {

    const {theme} = useTheme();

    const {data: user} = userAPI.useGetUserQuery(tariff?.employeeId ?? "", {skip: !tariff?.employeeId});

    return <div className={`${styles.tariff} ${theme === themes.light ? styles.light : styles.dark}`}>
        <div className={styles.row}>
            <h2>{tariff.name}</h2>
            <DeleteTariff id={tariff.id}/>
        </div>
        <div className={styles.row}>
            <p className={styles.procent}>{tariff.procent}%</p>
            {
                user ? <div>
                    <h2>{getDisplayName(user)}</h2>
                </div> : null
            }
        </div>
    </div>
}