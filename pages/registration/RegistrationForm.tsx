import { getPositions } from "@/api/httpMethods";
import { PositionRequestType } from "@/api/Types";
import { InputWithError } from "@/components/InputWithError";
import { Select, SelectItem } from "@/components/Select";
import { colors } from "@/const/Colors";
import { formatPassword } from "@/helpers/utils";
import { SetStateAction, useEffect, useState } from "react";
import { StyleSheet, View, Text } from "react-native"

export const RegistrationForm = () => {

    const [role, setRole] = useState<string | null>(null);
    const [gender, setGender] = useState<string | null>(null);
    const [position, setPosition] = useState<string | null>(null);

    const [name, onChangeName] = useState<string>('');
    const [surname, onChangeSurname] = useState<string>('');
    const [patronic, onChangePatronic] = useState<string>('');

    const [isError, setError] = useState<boolean>(false);
    const [isNameError, setNameError] = useState<boolean>(false);
    const [isSurnameError, setSurnameError] = useState<boolean>(false);
    const [isPatronicError, setPatronicError] = useState<boolean>(false);

    const [positions, setPositions] = useState<SelectItem[]>([]);

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
    })
  
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
                <View>
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
                            placeholder="* Отчество"
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
                </View>
                : 
                role === "client" ? 
                <Text>Client</Text> : 
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
    }
})