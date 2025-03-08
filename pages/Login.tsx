import { Card } from "@/components/Card"
import { Header } from "@/components/Header"
import { ScrollView, StyleSheet, View } from "react-native"

export const Login = () => {
    return <ScrollView>
        <Header/>
        <View style={styles.cardWrapper}>
            <Card title={"Вход"} inner={null}/>
        </View>
    </ScrollView>
}

const styles = StyleSheet.create({
    cardWrapper: {
        display: "flex",
        alignItems: "center"
    }
})