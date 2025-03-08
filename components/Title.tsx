import { colors } from "@/const/Colors"
import { StyleSheet, View, Text } from "react-native"

export const Title = ({text}: {text: string}) => {
    return <View style={styles.title}>
        <Text style={styles.text}>{text}</Text>
    </View>
}

const styles = StyleSheet.create({
    title: {
        padding: 16,
        backgroundColor: colors.dark.black,
        display: "flex",
        alignSelf: "flex-start",
        borderRadius: 16
    },
    text: {
        color: "#FFFFFF",
        fontFamily: "GothamPro-Bold",
        fontSize: 27,
    }
})