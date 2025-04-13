import { FC, useState } from "react";
import { DeleteFormType } from "./Types";
import styles from './DeleteForm.module.scss';
import { Button } from "../../button/Button";
import { ButtonStyleType } from "../../button/Types";

export const DeleteForm: FC<DeleteFormType> = ({id, close, deleteEntity, title}) => {

    const [error, setError] = useState<boolean>(false);

    const handleSubmit = async () => {
        try {
            const response = await deleteEntity(id).unwrap();
            console.log(response);
            close(false);
        } catch (error) {
            console.log(error);
            setError(true)
        }
    }

    return <div>
        <h2 className={styles.title}>{`Вы точно хотите удалить ${title}?`}</h2>
        <div className={styles.row}>
            <Button text={"Да"} type={ButtonStyleType.Create} onClick={() => handleSubmit()}/>
            <Button text={'Нет'} onClick={() => close(false)}/>
        </div>
        <span>{error ? "Произошла ошибка" : ""}</span>
    </div>
}