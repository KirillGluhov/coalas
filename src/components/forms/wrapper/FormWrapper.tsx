import { FormWrapperType } from "./Types";
import styles from './FormWrapper.module.scss';
import { useTheme } from "../../../providers/theme/useTheme";
import { themes } from "../../../providers/theme/ThemeProvider";
import { Button } from "../../button/Button";
import { FieldValues, SubmitHandler } from "react-hook-form";

export const FormWrapper = <T extends FieldValues>({title, children, reset, close, handleSubmit, errors, createEntity, setError, check, clearErrors}: FormWrapperType<T>) => {

    const {theme} = useTheme();

    const onSubmit: SubmitHandler<T> = async (data) => {
        if (!check || (check && check()))
        {
            clearErrors();

            try {
                const response = await createEntity(data).unwrap();
                console.log(response);
                close(false);
            } catch (error) {
                console.log(error);
                setError('root', {message: 'Есть ошибки в данных'})
            }
        }
        else
        {
            return;
        }
        
    }

    return <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
        <h2 className={styles.title}>{title}</h2>
        {children}
        <div className={styles.row}>
            <div className={`${styles.inputWithError} ${styles.right}`}>
                <input 
                    type="submit" 
                    value={"Создать"}
                    className={`${styles.enter} ${styles.input} ${theme === themes.light ? styles.light : styles.dark}`}
                />
                <span className={styles.error}>{errors?.root?.message}</span>
            </div>
            <div className={styles.inputWithError}>
                <Button text={'Отменить'} onClick={() => {close(false);() => reset()}}/>
                <span className={styles.error}></span>
            </div>
        </div>
    </form>
}