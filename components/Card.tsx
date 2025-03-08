import { StyleSheet, View } from "react-native"
import { Title } from "./Title"
import { ReactNode } from "react"

interface CardType
{
    title: string;
    inner: ReactNode | null | undefined;
}

export const Card: React.FC<CardType> = ({title, inner}) => {
    return <View style={styles.card}>
        <Title text={title}/>
        <View style={styles.cardInner}>
            {inner}
        </View>
    </View>
}

const styles = StyleSheet.create({
    card: {
        marginTop: 16,
        marginBottom: 16,
        maxWidth: 810,
        minWidth: 300
    },
    cardInner: {
    }
})

