import { colors } from "@/const/Colors";
import { TouchableOpacity, Text, GestureResponderEvent, StyleSheet } from "react-native"

interface CustomButtonType
{
    title: string;
    onClick?: (event: GestureResponderEvent) => void,
    isAccent?: boolean,
    isError?: boolean
}

export const CustomButton: React.FC<CustomButtonType> = ({title, onClick, isAccent, isError}) => {
    return <TouchableOpacity 
        onPress={onClick} 
        style={[
            styles.button, 
            isAccent ? styles.accent : styles.primary, 
            isError ? styles.errorStyle : ""
        ]}
    >
        <Text style={styles.text}>{title}</Text>
    </TouchableOpacity>
}

const styles = StyleSheet.create({
    button: {
        flex: 1,
        padding: 12,
        borderRadius: 8,
        display: "flex",
        alignItems: "center"
    },
    accent: {
        backgroundColor: colors.dark.accent
    },
    primary: {
        backgroundColor: colors.dark.primary
    },
    text: {
        color: colors.dark.text,
        fontSize: 15,
    },
    errorStyle: {
        backgroundColor: colors.dark.red
    }
})