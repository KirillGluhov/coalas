import { FC } from "react";
import { CreateEmployeeFormType } from "./Types";
import { userAPI } from "../../../../../store/services/userService";
import { Controller, useForm } from "react-hook-form";
import { EmployeeRegisterForm, genders } from "../../../../../store/services/types/dataTypes";
import { FormWrapper } from "../../../../../components/forms/wrapper/FormWrapper";
import styles from './CreateEmployeeForm.module.scss';
import { SimpleInput } from "../../../../../components/forms/elements/SimpleInput";
import externStyles from '../../../../../pages/containers/simple-container/login/form/LoginForm.module.scss';
import { themes } from "../../../../../providers/theme/ThemeProvider";
import { useTheme } from "../../../../../providers/theme/useTheme";
import Calendar from '../../../../../assets/icons/calendar.svg';
import { SimpleSelect } from "../../../../../components/forms/elements/SimpleSelect";
import { OptionType } from "../../../../../components/forms/elements/Types";

export const CreateEmployeeForm: FC<CreateEmployeeFormType> = ({close}) => {
    const {
            handleSubmit, 
            setError, 
            reset,
            clearErrors,
            formState: {errors}, 
            control
        } = useForm<EmployeeRegisterForm>({
            mode: 'onChange',
            defaultValues: {
                role: "EMPLOYEE",
                positionId: ""
            }
        });
        
    const [registerEmployee, _] = userAPI.useRegisterEmployeeMutation();
    const {theme} = useTheme();
    const {data: positions} = userAPI.useGetPositionsQuery();

    const positionsOptions: OptionType[] = positions ? positions.map((position) => ({
        id: position.id,
        value: position.name ?? "Отсутствует"
    })) : []

    const gendersOptions: OptionType[] = [
        {
            id: genders.male,
            value: "Мужской"
        },
        {
            id: genders.female,
            value: "Женский"
        }
    ]

    return <FormWrapper 
        title={"Сотрудник"} 
        close={close} 
        reset={reset} 
        handleSubmit={handleSubmit} 
        errors={errors} 
        createEntity={registerEmployee} 
        setError={setError} 
        clearErrors={clearErrors}
    >
        <div className={styles.row}>
            <SimpleInput control={control} title={'Имя'} name={'name'} isRequired={true}/>
            <SimpleInput control={control} title={'Фамилия'} name={'lastName'} isRequired={true}/>
            <SimpleInput control={control} title={'Отчество'} name={'secondName'}/>
        </div>
        <div className={styles.row}>
            <Controller
                name="birthDate"
                control={control}
                rules={{
                    required: "День рождения обязателен",
                    validate: (value) => value ? true : "День рождения не пустой"
                }}
                render={({field, fieldState}) => (
                    <div className={`${externStyles.inputWithError} ${styles.dateWrapper}`}>
                        <span className={styles.placeholder}>Дата рождения *</span>
                        <input 
                            {...field}
                            onChange={(e) => {
                                let value = e.target.value;
                                field.onChange(value);
                            }}

                                type={"date"} 
                                className={`${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                        />
                        <img src={Calendar} alt="calendar" className={styles.calendar} />
                        <span className={externStyles.error}>{fieldState.error?.message}</span>
                    </div>
                )}
            />
        </div>
        <div className={styles.row}>
            <SimpleSelect 
                control={control} 
                name={"gender"}
                title={"Пол"} 
                options={gendersOptions}
                isRequired={true}
            />
            <SimpleSelect
                control={control}
                name={"positionId"}
                title={"Позиция"}
                options={positionsOptions}
                isRequired={true}
            />
        </div>
        <div className={styles.row}>
            <Controller
                name="password"
                control={control}
                rules={{
                    required: "Пароль обязателен",
                    validate: (value) => value?.length === 0 || value?.length >= 6 ? true : "Длина пароля минимум 6 символов"
                }}
                render={({field, fieldState}) => (
                    <div className={externStyles.inputWithError}>
                        <input 
                            type={"text"} 
                            onChange={(e) => {
                                const value = e.target.value;
                                field.onChange(value);
                            }}
                            placeholder="* Пароль"
                            className={`${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                        />
                        <span className={externStyles.error}>{fieldState.error?.message}</span>
                    </div>
                )}
            />
            <Controller
                name="email"
                control={control}
                rules={{
                    validate: (value) => {
                        const emailRegex = /^((?:[A-Za-z0-9!#$%&'*+\-\/=?^_`{|}~]|(?<=^|\.)"|"(?=$|\.|@)|(?<=".*)[ .](?=.*")|(?<!\.)\.){1,64})(@)((?:[A-Za-z0-9.\-])*(?:[A-Za-z0-9])\.(?:[A-Za-z0-9]){2,})$/

                        if ((value && emailRegex.test(value)) || (value && value.trim().length === 0) || !value)
                        {
                            return true;
                        }
                        else
                        {
                            return "Вы ввели не email"
                        }
                    },
                    required: "Email обязателен"
                }}
                render={({field, fieldState}) => (
                    <div className={externStyles.inputWithError}>
                    <input 
                        {...field}
                        onChange={(e) => {
                            const value = e.target.value;
                            field.onChange(value);
                        }}
                        placeholder="Email"
                        type={"email"} 
                        inputMode={"email"} 
                        className={`${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                    />
                    <span className={externStyles.error}>{fieldState.error?.message}</span>
                </div>
                )}
            />
            <Controller
                name="phone"
                control={control}
                rules={{
                    pattern: {
                        value: /^\+\d{8,18}$/,
                        message: "Длина от 8 до 18"
                    },
                    required: "Телефон обязателен"
                }}
                render={({field, fieldState}) => (
                    <div className={externStyles.inputWithError}>
                        <input 
                            {...field}
                            onChange={(e) => {
                                let value = e.target.value.replace(/[^\d]/g, "");

                                if (value.length > 18) value = value.slice(0, 18);

                                const formatted = value.length > 0 ? "+" + value : "";
                                field.onChange(formatted);
                            }}

                            type={"tel"} 
                            inputMode={"tel"} 
                            placeholder="Телефон"
                            className={`${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                        />
                        <span className={externStyles.error}>{fieldState.error?.message}</span>
                    </div>
                )}
            />
        </div>
    </FormWrapper>;
}