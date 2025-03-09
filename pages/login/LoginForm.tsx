import { login } from "@/api/httpMethods"
import { CustomButton } from "@/components/CustomButton"
import { ErrorText } from "@/components/ErrorText"
import { InputWithError } from "@/components/InputWithError"
import { colors } from "@/const/Colors"
import { formatEmail, formatPassword, formatPhone } from "@/helpers/utils"
import { saveData } from "@/storage/Storage"
import { Redirect, useRouter } from "expo-router"
import { useState } from "react"
import { TextInput, View, Text, Button, StyleSheet, GestureResponderEvent, Alert } from "react-native"

export const LoginForm = () => {

    const router = useRouter();
    
    const [password, onChangePassword] = useState<string>('');
    const [phone, onChangePhone] = useState<string>('');
    const [email, onChangeEmail] = useState<string>('');

    const [isError, setError] = useState<boolean>(false);
    const [isPhoneError, setPhoneError] = useState<boolean>(false);
    const [isEmailError, setEmailError] = useState<boolean>(false);
    const [isPasswordError, setPasswordError] = useState<boolean>(false);

    const handleLogin = async (event: GestureResponderEvent) => {

        if (isPasswordError || isEmailError || isPhoneError || password.length === 0)
        {
            Alert.alert("Error", "В данных есть ошибки")
            setError(true);
        }
        else
        {
            const loginCredentials = {
                password: password,
                ...(email ? {email: email} : {phone: phone})
            }
            const tokens = await login(loginCredentials);
    
            if ('status' in tokens)
            {
                Alert.alert("Error", "Ошибка при входе. возможно неправильные данные")
                setError(true);
            }
            else if (tokens.userType === "client")
            {
                Alert.alert("Error", "Вы вошли под клиентом, а не сотрудником")
                setError(true);
            }
            else if (tokens.userType === "employee")
            {
                saveData("refresh", tokens.refreshToken);
                saveData("access", tokens.accessToken);
                Alert.alert("Success", "Успешно")
                setError(false);
                router.push("/main");
            }
        }
    }

    return <View style={styles.form}>
        <View style={styles.row}>
            <InputWithError
                style={[styles.inputStyle, isPasswordError ? styles.errorStyle : ""]} 
                placeholder="* Пароль"
                onChange={onChangePassword}
                formater={formatPassword}
                setSpecificError={setPasswordError}
                value={password}
                specificError={isPasswordError}
                inputMode="text"
                keyboardType="default"
                errorText="Длина не меньше 1"
            />
        </View>
        <View style={styles.row}>
            <InputWithError
                style={[styles.inputStyle, isPhoneError ? styles.errorStyle : ""]} 
                placeholder="Телефон"
                onChange={onChangePhone}
                formater={formatPhone}
                setSpecificError={setPhoneError}
                value={phone}
                specificError={isPhoneError}
                inputMode="tel"
                errorText="Длина от 8 до 18"
                keyboardType="phone-pad"
            />
            <Text style={styles.text}>Или</Text>
            <InputWithError
                style={[styles.inputStyle, isEmailError || isEmailError ? styles.errorStyle : ""]} 
                placeholder="Email"
                onChange={onChangeEmail}
                formater={formatEmail}
                setSpecificError={setEmailError}
                value={email}
                specificError={isEmailError}
                inputMode="email"
                errorText="Должен иметь формат как у email"
                keyboardType="email-address"
            />
        </View>
        <View style={styles.row}>
            <CustomButton 
                title="Войти" 
                isAccent={true} 
                onClick={handleLogin}
                isError={isError}
            ></CustomButton>
            <CustomButton title="Регистрация" onClick={() => router.push("/registration")}></CustomButton>
        </View>
    </View>
}

const styles = StyleSheet.create({
    inputStyle: {
        color: colors.dark.text,
        fontSize: 15,
        height: 42,
        borderWidth: 1,
        borderColor: "#92949C",
        borderRadius: 8,
        padding: 16
    },
    form: {
        display: "flex",
        gap: 8
    },
    row: {
        display: "flex",
        flexDirection: "row",
        gap: 8,
        alignItems: "center"
    },
    text: {
        color: colors.dark.text,
        fontSize: 15,
        marginBottom: 19
    },
    errorStyle: {
        borderColor: colors.dark.red
    }
})