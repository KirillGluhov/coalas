import { Controller, useForm } from 'react-hook-form';
import { TariffFormType } from '../../../../../store/services/types/dataTypes';
import styles from './CreateTariffForm.module.scss'
import { useTheme } from '../../../../../providers/theme/useTheme';
import { themes } from '../../../../../providers/theme/ThemeProvider';
import externStyles from '../../../../../pages/containers/simple-container/login/form/LoginForm.module.scss';
import { tariffAPI } from '../../../../../store/services/tariffService';
import { CreateTariffFormType } from './Types';
import { FC } from 'react';
import { FormWrapper } from '../../../../../components/forms/wrapper/FormWrapper';
import { SimpleInput } from '../../../../../components/forms/elements/SimpleInput';

export const CreateTariffForm: FC<CreateTariffFormType> = ({close}) => {

    const {
        handleSubmit, 
        setError, 
        reset,
        clearErrors,
        formState: {errors}, 
        control
    } = useForm<TariffFormType>({
        mode: 'onChange'
    });

    const [createTariff, _] = tariffAPI.useCreateTariffMutation();
    const {theme} = useTheme();

    return <FormWrapper<TariffFormType> 
        title={'Тариф'} 
        close={close} 
        reset={reset} 
        handleSubmit={handleSubmit} 
        errors={errors} 
        createEntity={createTariff} 
        setError={setError} 
        clearErrors={clearErrors}
    >
        <div className={styles.row}>
            <SimpleInput control={control} title={'Название'} name={'name'} isRequired={true}/>
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
    </FormWrapper>
}