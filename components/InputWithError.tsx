import { formatPhone } from "@/helpers/utils"
import { View, TextInput, StyleProp, TextStyle, InputModeOptions, KeyboardTypeOptions, StyleSheet } from "react-native"
import { ErrorText } from "./ErrorText"
import { colors } from "@/const/Colors"

interface InputWithErrorType
{
    style?: StyleProp<TextStyle>,
    placeholder?: string,
    value?: string,
    inputMode?: InputModeOptions,
    keyboardType?: KeyboardTypeOptions,
    specificError: boolean,
    setSpecificError: React.Dispatch<React.SetStateAction<boolean>>,
    onChange: (value: React.SetStateAction<string>) => void,
    errorText?: string,
    formater: (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) => string

}

export const InputWithError: React.FC<InputWithErrorType> = (
    {
        style, 
        placeholder, 
        value, 
        inputMode, 
        keyboardType, 
        specificError, 
        setSpecificError, 
        onChange, 
        errorText,
        formater
    }
) => {
    return <View style={[styles.column]}>
        <TextInput 
            style={[styles.inputStyle, specificError ? styles.errorStyle : "", style]} 
            placeholder={placeholder}
            onChangeText={(text) => onChange(formater(text, setSpecificError))}
            value={value}
            inputMode={inputMode}
            keyboardType={keyboardType}
        />
        <ErrorText title={errorText ?? ""} isVisible={specificError}/>
    </View>
}

const styles = StyleSheet.create({
    column: {
        display: "flex",
        flexDirection: "column",
        gap: 4,
        flex: 1
    },
    inputStyle: {
        color: colors.dark.text,
        fontSize: 15,
        height: 42,
        borderWidth: 1,
        borderColor: "#92949C",
        borderRadius: 8,
        padding: 16
    },
    errorStyle: {
        borderColor: colors.dark.red
    }
})