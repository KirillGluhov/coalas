import { StyleSheet, TextInput, View } from "react-native"
import { Title } from "./Title"
import { ReactElement } from "react";
import { colors } from "@/const/Colors";

interface CardType
{
    title: string;
    children: React.ReactNode;
}

export const Card: React.FC<CardType> = ({title, children}) => {
    return <View style={styles.card}>
        <Title text={title}/>
        <View style={styles.innerCard}>
            {children}
        </View>
    </View>
}

const styles = StyleSheet.create({
    card: {
        marginTop: 16,
        marginBottom: 16,
        maxWidth: 810,
        minWidth: 300,
        width: "75%",
    },
    innerCard: {
        backgroundColor: colors.dark.black,
        padding: 24,
        borderRadius: 16,
        marginTop: 43
    }
})

