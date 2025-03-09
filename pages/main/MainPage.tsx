import { Card } from "@/components/Card"
import { ScrollView, View, StyleSheet } from "react-native"
import { Header } from "react-native/Libraries/NewAppScreen"
import { LoginForm } from "../login/LoginForm"
import { colors } from "@/const/Colors"
import { MainForm } from "./MainForm"

export const MainPage = () => {
    return <ScrollView style={styles.fone}>
            <Header/>
            <View style={styles.cardWrapper}>
                <Card title={"Главная"}>
                    <MainForm/>
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