import { SubmitHandler, useForm } from "react-hook-form"
import { LoginFormType } from "./Types"
import { ChangeEvent } from "react";

export const LoginForm = () => {
    const {
        register, 
        handleSubmit, 
        setValue, 
        setError, 
        clearErrors, 
        watch, 
        formState: {errors, isValid}, 
        getValues
    } = useForm<LoginFormType>();

    const allValues = getValues();

    const onSubmit: SubmitHandler<LoginFormType> = (data) => {
        const hasPhone = (data.phone.length > 0) && (data.phone.trim() !== "");
        const hasEmail = (data.email.length > 0) && (data.email.trim() !== "");

        if (!hasPhone && !hasEmail)
        {
            setError("phone", {message: "Введите телефон или email"});
            setError("email", {message: "Введите телефон или email"});
            return;
        }

        clearErrors("phone");
        clearErrors("email");

        console.log(data, errors, isValid);
    }
    
    const phone = watch("phone", "");
    const email = watch("email", "");
    const password = watch("password", "");

    const handlePhoneChange = (e: ChangeEvent<HTMLInputElement>) => {
        let value = e.target.value.replace(/[^\d]/g, "");

        if (value.length < 8 && value.length > 0)
        {
            setError("phone", {message: "Длина от 8 до 18"})
        }

        if (value.length >= 8 && value.length <= 18)
        {
            clearErrors("phone");
        }

        if (value.length > 18)
        {
            value = value.slice(0,18);
        }

        const formatted = value.length > 0 ? "+" + value : "";

        setValue("phone", formatted);
    }

    const handleEmailChange = (e: ChangeEvent<HTMLInputElement>) => {
        let value = e.target.value;
        const emailRegex = /^((?:[A-Za-z0-9!#$%&'*+\-\/=?^_`{|}~]|(?<=^|\.)"|"(?=$|\.|@)|(?<=".*)[ .](?=.*")|(?<!\.)\.){1,64})(@)((?:[A-Za-z0-9.\-])*(?:[A-Za-z0-9])\.(?:[A-Za-z0-9]){2,})$/

        if (emailRegex.test(value) || value.trim().length === 0)
        {
            clearErrors("email");
        }
        else
        {
            setError("email", {message: "Вы ввели не email"})
        }

        setValue("email", value);

    }

    const handlePasswordChange = (e: ChangeEvent<HTMLInputElement>) => {
        let value = e.target.value;

        if (value.length > 0 && value.length < 6)
        {
            setError("email", {message: "Длина пароля минимум 6 символов"})
        }
        else
        {
            clearErrors("email");
        }

        setValue("password", value);
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <input 
                        type={"password"} 
                        {...register("password", {required: true})}
                        value={password}
                        onChange={handlePasswordChange}
                        placeholder="Пароль"
                    />
                    {errors.password && <span>{errors.password.message}</span>}
                </div>
                <div>
                    <input 
                        type={"email"} 
                        inputMode={"email"} 
                        {...register("email")}
                        value={email}
                        onChange={handleEmailChange}
                        placeholder="Email"
                    />
                    {errors.email && <span>{errors.email.message}</span>}
                </div>
                <div>
                    <input 
                        type={"tel"} 
                        inputMode={"tel"} 
                        {...register("phone", {
                            maxLength: 19,
                            pattern: {
                                value: /^\+\d{8,18}$/,
                                message: "Длина от 8 до 18"
                            }
                        })}
                        value={phone}
                        onChange={handlePhoneChange}
                        placeholder="Телефон"
                    />
                    {errors.phone && <span>{errors.phone.message}</span>}
                </div>
                <div>
                    <input type="submit" onClick={() => console.log(allValues, errors, isValid)}/>
                    {!isValid && <span>Есть ошибки в данных</span>}
                </div>
        </form>
    )
}