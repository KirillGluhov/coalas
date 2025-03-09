import { colors } from "@/const/Colors";
import { StyleSheet, Text } from "react-native";

interface ErrorTextType
{
    title: string;
    isVisible: boolean
}

export const ErrorText: React.FC<ErrorTextType> = ({title, isVisible}) => {
    return <Text style={[styles.errorStyle, !isVisible ? styles.invisible : ""]}>{title}</Text>
}

const styles = StyleSheet.create({
    errorStyle: {
        color: colors.dark.red,
    },
    invisible: {
        opacity: 0
    }
})