import { Controller, SubmitHandler, useForm } from "react-hook-form"
import { LoginFormType, OptionalLoginForm } from "./Types"
import styles from './LoginForm.module.scss';
import { useTheme } from "../../../../../providers/theme/useTheme";
import { themes } from "../../../../../providers/theme/ThemeProvider";
import { authentificationAPI } from "../../../../../store/services/authentificationService";
import { useAppDispatch } from "../../../../../hooks/redux";
import { setAuthData } from "../../../../../store/slices/userProfileViewSlice";
import { toOptionalLoginForm } from "./utils";
import { useNavigate } from "react-router";
import { pages } from "../../../../../consts";

export const LoginForm = () => {
    const {
        handleSubmit, 
        setError, 
        clearErrors, 
        formState: {errors}, 
        control
    } = useForm<LoginFormType>({
        mode: 'onChange',
        defaultValues: {
            email: "",
            phone: "",
            password: ""
        }
    });

    const {theme} = useTheme();
    const [loginIntoAccount, _] = authentificationAPI.useLoginIntoAccountMutation();
    const navigate = useNavigate();

    const dispatch = useAppDispatch();

    const onSubmit: SubmitHandler<LoginFormType> = (data) => {
        const hasPhone = (data.phone.length > 0) && (data.phone.trim() !== "");
        const hasEmail = (data.email.length > 0) && (data.email.trim() !== "");

        if (!hasPhone && !hasEmail)
        {
            setError("phone", {message: "Введите телефон или email"});
            setError("email", {message: "Введите телефон или email"});
            return;
        }

        if (data.password.length === 0)
        {
            setError('root', {message: "Не введены некоторые из обязательных полей"})
            return;
        }

        clearErrors("phone");
        clearErrors("email");
        clearErrors("password");
        clearErrors("root");

        sendLoginData(data);
    }

    const sendLoginData = async (data: LoginFormType) => {
        try {
            const filteredData = toOptionalLoginForm(data);
            const response = await loginIntoAccount(filteredData).unwrap();

            dispatch(setAuthData(response));
            sessionStorage.setItem("auth", JSON.stringify(response));
            navigate(`/${pages.tariffs.link}`)

        } catch (error) {
            console.log(error);
            setError('root', {message: 'Есть ошибки в данных'})
        }
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)} className={styles.form}>
            <div className={styles.row}>
                <Controller
                    name="password"
                    control={control}
                    rules={{
                        required: "Пароль обязателен",
                        validate: (value) => value?.length === 0 || value?.length >= 6 ? true : "Длина пароля минимум 6 символов"
                    }}
                    render={({field, fieldState}) => (
                        <div className={styles.inputWithError}>
                            <input 
                                type={"password"} 
                                onChange={(e) => {
                                    const value = e.target.value;
                                    field.onChange(value);
                                }}
                                placeholder="* Пароль"
                                className={`${styles.input} ${theme === themes.light ? styles.light : styles.dark}`}
                            />
                            <span className={styles.error}>{fieldState.error?.message}</span>
                        </div>
                    )}

                />
            </div>
            <div className={styles.row}>
                <Controller
                    name="email"
                    control={control}
                    rules={{
                        validate: (value) => {
                            const emailRegex = /^((?:[A-Za-z0-9!#$%&'*+\-\/=?^_`{|}~]|(?<=^|\.)"|"(?=$|\.|@)|(?<=".*)[ .](?=.*")|(?<!\.)\.){1,64})(@)((?:[A-Za-z0-9.\-])*(?:[A-Za-z0-9])\.(?:[A-Za-z0-9]){2,})$/

                            if (emailRegex.test(value) || value.trim().length === 0)
                            {
                                return true;
                            }
                            else
                            {
                                return "Вы ввели не email"
                            }
                        }
                    }}
                    render={({field, fieldState}) => (
                        <div className={styles.inputWithError}>
                        <input 
                            {...field}
                            onChange={(e) => {
                                const value = e.target.value;
                                field.onChange(value);
                            }}
                            placeholder="Email"
                            type={"email"} 
                            inputMode={"email"} 
                            className={`${styles.input} ${theme === themes.light ? styles.light : styles.dark}`}
                        />
                        <span className={styles.error}>{fieldState.error?.message}</span>
                    </div>
                    )}
                />
                <p className={styles.or}>Или</p>
                <Controller
                    name="phone"
                    control={control}
                    rules={{
                        pattern: {
                            value: /^\+\d{8,18}$/,
                            message: "Длина от 8 до 18"
                        }
                    }}
                    render={({field, fieldState}) => (
                        <div className={styles.inputWithError}>
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
                                className={`${styles.input} ${theme === themes.light ? styles.light : styles.dark}`}
                            />
                            <span className={styles.error}>{fieldState.error?.message}</span>
                        </div>
                    )}

                />
            </div>
            <div className={styles.row}>
                <div className={styles.inputWithError}>
                    <input 
                        type="submit" 
                        value={"Войти"}
                        className={`${styles.enter} ${styles.maxWidth} ${styles.input} ${theme === themes.light ? styles.light : styles.dark}`}
                    />
                    <span className={styles.error}>{errors?.root?.message}</span>
                </div>
            </div>
        </form>
    )
}