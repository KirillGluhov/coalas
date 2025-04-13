import { FC } from "react";
import { TariffPropsType } from "./Types";
import styles from './Tariff.module.scss'
import { userAPI } from "../../../../../store/services/userService";
import { getDisplayName } from "../../../../../utils";
import { InnerCard } from "../../../../../components/inner-card/InnerCard";
import { tariffAPI } from "../../../../../store/services/tariffService";
import { DeleteWrapper } from "../../../../../components/forms/delete/DeleteWrapper";

export const Tariff: FC<TariffPropsType> = ({tariff}) => {

    const {data: user} = userAPI.useGetUserQuery(tariff?.employeeId ?? "", {skip: !tariff?.employeeId});
     const [deleteTariff, _] = tariffAPI.useDeleteTariffMutation();

    return <InnerCard id={tariff.id}>
        <>
            <div className={styles.row}>
                <h2>{tariff.name}</h2>
                <DeleteWrapper 
                    id={tariff.id} 
                    title={"тариф"} 
                    deleteEntity={deleteTariff}
                />
            </div>
            <div className={styles.row}>
                <p className={styles.procent}>{tariff.procent}%</p>
                {
                    user ? <div>
                        <h2>{getDisplayName(user)}</h2>
                    </div> : null
                }
            </div>
        </>
    </InnerCard>
}