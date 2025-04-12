import { Controller, SubmitHandler, useForm } from 'react-hook-form';
import { TariffFormType } from '../../../../../../store/services/types/dataTypes';
import styles from './CreateTariffForm.module.scss'
import { useTheme } from '../../../../../../providers/theme/useTheme';
import { themes } from '../../../../../../providers/theme/ThemeProvider';
import externStyles from '../../../../../../pages/containers/simple-container/login/form/LoginForm.module.scss';
import { Button } from '../../../../../../components/button/Button';
import { tariffAPI } from '../../../../../../store/services/tariffService';
import { CreateTariffFormType } from './Types';
import { FC } from 'react';

export const CreateTariffForm: FC<CreateTariffFormType> = ({close}) => {

    const {
        handleSubmit, 
        setError, 
        reset,
        formState: {errors}, 
        control
    } = useForm<TariffFormType>({
        mode: 'onChange'
    });

    const [createTariff, _] = tariffAPI.useCreateTariffMutation();

    const {theme} = useTheme();

    const onSubmit: SubmitHandler<TariffFormType> = async (data) => {
        try {
            const response = await createTariff(data).unwrap();
            console.log(response);
            close(false);
        } catch (error) {
            console.log(error);
            setError('root', {message: 'Есть ошибки в данных'})
        }
    }

    return <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
        <h2 className={styles.title}>Тариф</h2>
        <div className={styles.row}>
            <Controller
                name='name'
                control={control}
                rules={{
                    required: "Название обязательно",
                    validate: (value) => value?.length === 0 || value?.length >= 1 ? true : "Длина названия хотя бы один символ"
                }}
                render={({field, fieldState}) => (
                    <div className={externStyles.inputWithError}>
                        <input 
                            type={"text"} 
                            onChange={(e) => {
                                const value = e.target.value;
                                field.onChange(value);
                            }}
                            placeholder="* Название"
                            className={`${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                        />
                        <span className={externStyles.error}>{fieldState.error?.message}</span>
                    </div>
                )}
            />
            <Controller
                name="procent"
                control={control}
                rules={{
                    required: "Проценты обязательны",
                    validate: (value) => value ? true : "Проценты не нулевые"
                }}
                render={({field, fieldState}) => (
                    <div className={externStyles.inputWithError}>
                        <input 
                            {...field}
                            onChange={(e) => {
                                let value = e.target.value;
                                field.onChange(value === '' ? 0 : +value);
                            }}

                                type={"number"} 
                                inputMode={"numeric"} 
                                step="any"
                                placeholder="Проценты *"
                                className={`${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                        />
                        <span className={externStyles.error}>{fieldState.error?.message}</span>
                    </div>
                )}
            />
        </div>
        <div className={styles.row}>
            <div className={`${externStyles.inputWithError} ${styles.right}`}>
                <input 
                    type="submit" 
                    value={"Сохранить"}
                    className={`${externStyles.enter} ${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                />
                <span className={styles.error}>{errors?.root?.message}</span>
            </div>
            <div className={externStyles.inputWithError}>
                <Button text={'Отменить'} onClick={() => reset()}/>
                <span className={styles.error}></span>
            </div>
        </div>
    </form>
}