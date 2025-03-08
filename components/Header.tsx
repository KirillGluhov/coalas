import { colors } from "@/const/Colors"
import { StyleSheet, View, Image, Text } from "react-native"

export const Header = () => {
    return <View style={styles.header}>
        <View style={styles.logo}>
            <Image source={require("@/assets/images/koala.svg")}/>
            <Text style={styles.logoText}>Koala Bank</Text>
        </View>
        <View></View>
    </View>
}

const styles = StyleSheet.create({
    header:{
        height: 100,
        backgroundColor: colors.dark.black,
        fontFamily: "GothamPro",
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-between",
        paddingLeft: 16,
        paddingRight: 16,
        alignItems: "center"
    },
    logo:{
        display: "flex",
        gap: 8,
        flexDirection: "row",
        alignItems: "center"
    },
    logoText: {
        fontSize: 20,
        color: "#FFFFFF"
    }
})