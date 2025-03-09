import { colors } from "@/const/Colors"
import { StyleSheet, View } from "react-native"

export const AccountsCard = () => {
    return <View style={styles.card}>
        <AccountHeader/>
        <AccountInnerCard/>
        <Operations/>
    </View>
}

const styles = StyleSheet.create({
    card: {
        backgroundColor: colors.dark.background,
        fontSize: 20,
        color: colors.dark.text
    }
})