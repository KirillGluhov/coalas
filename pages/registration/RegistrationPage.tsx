import { Card } from "@/components/Card"
import { Header } from "@/components/Header"
import { colors } from "@/const/Colors"
import { ScrollView, View } from "react-native"
import { StyleSheet } from "react-native"
import { RegistrationForm } from "./RegistrationForm"

export const RegistrationPage = () => {
    return <ScrollView style={styles.fone}>
        <Header/>
        <View style={styles.cardWrapper}>
            <Card title={"Регистрация"}>
                <RegistrationForm/>
            </Card>
        </View>
    </ScrollView>
}

const styles = StyleSheet.create({
    cardWrapper: {
        display: "flex",
        alignItems: "center",
    },
    fone: {
        backgroundColor: colors.dark.background
    }
})