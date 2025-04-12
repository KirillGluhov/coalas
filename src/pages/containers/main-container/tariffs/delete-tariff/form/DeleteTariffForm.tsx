import { FC, useState } from "react";
import { DeleteTariffFormType } from "./DeleteTariffFormType";
import { Button } from "../../../../../../components/button/Button";
import { tariffAPI } from "../../../../../../store/services/tariffService";
import styles from './DeleteTariffForm.module.scss';
import { ButtonStyleType } from "../../../../../../components/button/Types";

export const DeleteTariffForm: FC<DeleteTariffFormType> = ({id, close}) => {
    
    const [deleteTariff, _] = tariffAPI.useDeleteTariffMutation();
    const [error, setError] = useState<boolean>(false);

    //const {theme} = useTheme();

    const handleSubmit = async () => {
        try {
            const response = await deleteTariff(id).unwrap();
            console.log(response);
            close(false);
        } catch (error) {
            console.log(error);
            setError(true)
        }
    }

    return <div>
        <h2 className={styles.title}>Вы точно хотите удалить тариф?</h2>
        <div className={styles.row}>
            <Button text={"Да"} type={ButtonStyleType.Create} onClick={() => handleSubmit()}/>
            <Button text={'Нет'} onClick={() => close(false)}/>
        </div>
        <span>{error ? "Произошла ошибка" : ""}</span>
    </div>
}