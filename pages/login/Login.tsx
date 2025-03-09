import { Card } from "@/components/Card"
import { Header } from "@/components/Header"
import { colors } from "@/const/Colors"
import { ScrollView, StyleSheet, TextInput, View } from "react-native"
import { LoginForm } from "./LoginForm"

export const Login = () => {
    return <ScrollView style={styles.fone}>
        <Header/>
        <View style={styles.cardWrapper}>
            <Card title={"Вход"}>
                <LoginForm/>
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