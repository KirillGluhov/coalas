import { colors } from "@/const/Colors"
import { StyleSheet, View, Image, Text } from "react-native"
import { CustomButton } from "./CustomButton"
import { useEffect, useState } from "react"
import { useRouter, useSegments } from "expo-router"
import { getData, removeData } from "@/storage/Storage"
import { getProfile, logout } from "@/api/httpMethods"

export const Header = () => {
    const segments = useSegments();
    const [name, setName] = useState<string>('');
    const router = useRouter();

    const handleExit = async () => {
        const accessToken = await getData("access");

        if (accessToken)
        {
            const data = await logout(accessToken);

            removeData("access");
            removeData("refresh");
            setName('');

            router.push("/");
        }
        else
        {
            setName('');
            router.push("/");
        }
    }

    const getName = async () => {
        const accessToken = await getData("access");

        if (accessToken)
        {
            const data = await getProfile(accessToken);

            if ("name" in data)
            {
                setName(`${data.name} ${data.lastName}`);
            }
        }
    }

    useEffect(() => {
        getName()
    },[segments])

    return <View style={styles.header}>
        <View style={styles.logo}>
            <Image source={require("@/assets/images/koala.svg")}/>
            <Text style={styles.logoText}>Koala Bank</Text>
        </View>
        <View>
            {
                name ? 
                <View style={styles.rightPart}>
                    <View style={styles.logoAndName}>
                        <Image source={require("../assets/images/profile.svg")}/>
                        <Text style={styles.logoText}>{name}</Text>
                    </View>
                    <CustomButton title="Выйти" onClick={() => handleExit()}/>
                </View> : 
                <CustomButton title="Войти" onClick={() => router.push("/")}/>
            }
        </View>
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
        color: colors.dark.text
    },
    logoAndName: {
        display: "flex",
        flexDirection: "row",
        gap: 8,
        alignItems: "center"
    },
    rightPart: {
        display: "flex",
        flexDirection: "row",
        gap: 16,
        alignItems: "center"
    }
})