import { getPositions, registerClient, registerEmployee } from "@/api/httpMethods";
import { PositionRequestType } from "@/api/Types";
import { CustomButton } from "@/components/CustomButton";
import { DatePicker } from "@/components/DatePicker";
import { InputWithError } from "@/components/InputWithError";
import { Select, SelectItem } from "@/components/Select";
import { colors } from "@/const/Colors";
import { formatDepartmentCode, formatEmail, formatPassportNumber, formatPassword, formatPhone, formatSeries } from "@/helpers/utils";
import { SetStateAction, useEffect, useState } from "react";
import { StyleSheet, View, Text, Alert } from "react-native"
import DateTimePicker, { DateType, getDefaultStyles } from 'react-native-ui-datepicker';
import { Gender } from "../Types";
import { getData, saveData } from "@/storage/Storage";
import { useRouter } from "expo-router";

export const RegistrationForm = () => {
    const router = useRouter();

    const [role, setRole] = useState<string | null>(null);
    const [gender, setGender] = useState<Gender | null>(null);
    const [position, setPosition] = useState<string | null>(null);

    const [name, onChangeName] = useState<string>('');
    const [surname, onChangeSurname] = useState<string>('');
    const [patronic, onChangePatronic] = useState<string>('');
    const [password, onChangePassword] = useState<string>('');
    const [phone, onChangePhone] = useState<string>('');
    const [email, onChangeEmail] = useState<string>('');

    const [isError, setError] = useState<boolean>(false);
    const [isNameError, setNameError] = useState<boolean>(false);
    const [isSurnameError, setSurnameError] = useState<boolean>(false);
    const [isPatronicError, setPatronicError] = useState<boolean>(false);
    const [isPhoneError, setPhoneError] = useState<boolean>(false);
    const [isEmailError, setEmailError] = useState<boolean>(false);
    const [isPasswordError, setPasswordError] = useState<boolean>(false);

    const [isSeriesError, setSeriesError] = useState<boolean>(false);
    const [isPassportNumberError, setPassportNumberError] = useState<boolean>(false);
    const [isDepartmentCode, setDepartmentCodeError] = useState<boolean>(false);
    const [isDepartmentName, setDepartmentNameError] = useState<boolean>(false);

    const [series, onChangeSeries] = useState<string>('');
    const [passportNumber, onChangeNumber] = useState<string>('');
    const [issueDate, onChangeIssueDate] = useState<DateType>();
    const [departmentCode, onChangeDepartmentCode] = useState<string>('');
    const [departmentName, onChangeDepartmentName] = useState<string>('');

    const [positions, setPositions] = useState<SelectItem[]>([]);

    
    const [birthDate, setBirthDate] = useState<DateType>();

    const getAllPositions = async () => {
        const data = await getPositions()

        if (Array.isArray(data))
        {
            setPositions(data.map((position) => ({
                label: position.name,
                value: position.id
            })))
        }
    }

    useEffect(() => {
        getAllPositions()
    },[])

    const handleEmployeeRegistration = async () => {
        if (isNameError || isSurnameError || isPatronicError || isPhoneError || isEmailError || isPasswordError 
            || password.length === 0 || !role || !name || !surname || !position)
        {
            Alert.alert("Error", "В данных есть ошибки")
            setError(true);
        }
        else
        {
            const registrationData = {
                name: name,
                lastName: surname,
                positionId: position,
                password: password,
                phone: phone,
                email: email,
                ...(gender ? {gender: gender} : {}),
                ...(birthDate ? {birthDate: birthDate.toLocaleString()} : {}),
                ...(patronic ? {secondName: patronic} : {})
            }

            const tokens = await registerEmployee(registrationData);

            if ('status' in tokens)
            {
                Alert.alert("Error", "Ошибка при входе. возможно неправильные данные")
                setError(true);
            }
            else
            {
                saveData("refresh", tokens.refreshToken);
                saveData("access", tokens.accessToken);
                Alert.alert("Success", "Успешно")
                setError(false);
                router.push("/main");
            }
        }
    }

    const handleClientRegistration = async () => {
        if (isNameError || isSurnameError || isPatronicError || isPhoneError || isEmailError || isPasswordError 
            || isDepartmentCode || isDepartmentName || isSeriesError || isPassportNumberError
            || password.length === 0 || !name || !surname
            || !series || !passportNumber || !issueDate || !departmentCode || !departmentName)
        {
            Alert.alert("Error", "В данных есть ошибки")
            setError(true);
        }
        else
        {
            const registrationData = {
                name: name,
                lastName: surname,
                password: password,
                phone: phone,
                email: email,
                passport: {
                    series: series,
                    number: passportNumber,
                    issueDate: issueDate.toLocaleString(),
                    departmentCode: departmentCode,
                    departmentName: departmentName
                },
                ...(gender ? {gender: gender} : {}),
                ...(birthDate ? {birthDate: birthDate.toLocaleString()} : {}),
                ...(patronic ? {secondName: patronic} : {})
            }

            const token = await getData("access");

            if (token)
            {
                const data = await registerClient(registrationData, token);

                if ('status' in data)
                {
                    Alert.alert("Error", "Ошибка при вводе. Возможно неправильные данные")
                    console.log("11")
                    setError(true);
                    
                }
                else
                {
                    Alert.alert("Success", "Успешно")
                    setError(false);
                    console.log("33")
                    router.push("/main");
                }
            }
            else
            {
                Alert.alert("Error", "Вы не можете создать клиента")
                console.log("22")
                setError(true);
            }
        }
    }
  
    return <View style={styles.form}>
            <View style={styles.row}>
                <Select 
                    title="* Роль"
                    placeholder="Выберите роль"
                    change={setRole}
                    items={[
                        { label: 'Сотрудник', value: 'employee' },
                        { label: 'Клиент', value: 'client' },
                    ]}
                    value={role}
                />
            </View>
            {
                role === "employee" ? 
                <View style={styles.form}>
                    <View style={styles.row}>
                        <InputWithError 
                            placeholder="* Имя"
                            onChange={onChangeName}
                            formater={formatPassword}
                            setSpecificError={setNameError}
                            value={name}
                            specificError={isNameError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                        <InputWithError 
                            placeholder="* Фамилия"
                            onChange={onChangeSurname}
                            formater={formatPassword}
                            setSpecificError={setSurnameError}
                            value={surname}
                            specificError={isSurnameError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                        <InputWithError 
                            placeholder="Отчество"
                            onChange={onChangePatronic}
                            formater={formatPassword}
                            setSpecificError={setPatronicError}
                            value={patronic}
                            specificError={isPatronicError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                    </View>
                    <View style={styles.row}>
                        <Select 
                            title="Пол"
                            placeholder="Выберите пол"
                            change={setGender}
                            items={[
                                { label: 'Мужской', value: 'male' },
                                { label: 'Женский', value: 'female' },
                            ]}
                            value={gender}
                        />
                        <Select 
                            title="* Должность"
                            placeholder="Выберите должность"
                            change={setPosition}
                            items={positions}
                            value={position}
                        />
                    </View>
                    <View style={styles.row}>
                        <DatePicker
                            title="Дата рождения"
                            value={birthDate}
                            change={setBirthDate}
                            placeholder="Выберите дату рождения"
                        />
                    </View>
                    <View style={styles.row}>
                        <InputWithError 
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
                        <InputWithError
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
                        <InputWithError
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
                            title="Зарегистрироваться" 
                            isAccent={true} 
                            onClick={handleEmployeeRegistration}
                            isError={isError}
                        ></CustomButton>
                    </View>
                </View>
                : 
                role === "client" ? 
                <View style={styles.form}>
                    <View style={styles.row}>
                    <InputWithError 
                            placeholder="* Имя"
                            onChange={onChangeName}
                            formater={formatPassword}
                            setSpecificError={setNameError}
                            value={name}
                            specificError={isNameError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                        <InputWithError 
                            placeholder="* Фамилия"
                            onChange={onChangeSurname}
                            formater={formatPassword}
                            setSpecificError={setSurnameError}
                            value={surname}
                            specificError={isSurnameError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                        <InputWithError 
                            placeholder="Отчество"
                            onChange={onChangePatronic}
                            formater={formatPassword}
                            setSpecificError={setPatronicError}
                            value={patronic}
                            specificError={isPatronicError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                    </View>
                    <View style={styles.row}>
                    <InputWithError
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
                        <InputWithError
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
                        <Select 
                            title="Пол"
                            placeholder="Выберите пол"
                            change={setGender}
                            items={[
                                { label: 'Мужской', value: 'male' },
                                { label: 'Женский', value: 'female' },
                            ]}
                            value={gender}
                        />
                        <DatePicker
                            title="Дата рождения"
                            value={birthDate}
                            change={setBirthDate}
                            placeholder="Выберите дату рождения"
                        />
                    </View>
                    <View style={styles.row}>
                        <InputWithError 
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
                        <Text style={styles.passport}>Паспорт</Text>
                    </View>
                    <View style={styles.row}>
                        <InputWithError 
                            placeholder="* Серия"
                            onChange={onChangeSeries}
                            formater={formatSeries}
                            setSpecificError={setSeriesError}
                            value={series}
                            specificError={isSeriesError}
                            inputMode="numeric"
                            keyboardType="numeric"
                            errorText="У серии формат цц цц"
                        />
                        <InputWithError 
                            placeholder="* Номер"
                            onChange={onChangeNumber}
                            formater={formatPassportNumber}
                            setSpecificError={setPassportNumberError}
                            value={passportNumber}
                            specificError={isPassportNumberError}
                            inputMode="numeric"
                            keyboardType="numeric"
                            errorText="У номера 6 цифр"
                        />
                    </View>
                    <View style={styles.row}>
                        <DatePicker
                            title="* Дата выдачи"
                            value={issueDate}
                            change={onChangeIssueDate}
                            placeholder="Выберите дату выдачи"
                        />
                    </View>
                    <View style={styles.row}>
                        <InputWithError 
                            placeholder="* Код выдачи"
                            onChange={onChangeDepartmentCode}
                            formater={formatDepartmentCode}
                            setSpecificError={setDepartmentCodeError}
                            value={departmentCode}
                            specificError={isDepartmentCode}
                            inputMode="numeric"
                            keyboardType="numeric"
                            errorText="У кода 6 цифр"
                        />
                        <InputWithError 
                            placeholder="* Кем выдан"
                            onChange={onChangeDepartmentName}
                            formater={formatPassword}
                            setSpecificError={setDepartmentNameError}
                            value={departmentName}
                            specificError={isDepartmentName}
                            inputMode="text"
                            keyboardType="default"
                            errorText="Хотя бы один символ"
                        />
                    </View>
                    <View style={styles.row}>
                        <CustomButton 
                            title="Зарегистрировать" 
                            isAccent={true} 
                            onClick={handleClientRegistration}
                            isError={isError}
                        ></CustomButton>
                    </View>
                </View> : 
                null
            }
        </View>
}

const styles = StyleSheet.create({
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
    select: {
        color: colors.dark.text,
        fontSize: 15,
        height: 42,
        borderWidth: 1,
        borderColor: "#92949C",
        borderRadius: 8,
        padding: 16
    },
    passport: {
        fontSize: 24,
        fontFamily: "GothamPro-Bold",
        color: colors.dark.accent
    }
})