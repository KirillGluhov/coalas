import { colors } from "@/const/Colors";
import { getData } from "@/storage/Storage";
import { useState, useEffect } from "react"
import { StyleSheet } from "react-native";
import { Operation } from "./Operation";

export const Operations = () => {
    const [operations, setOperations] = useState<Operationype[]>([]);

    const getOperations = () => {
        const token = getData("access");
        if (token)
        {
            const data = await getAllOperations(token);

            setOperations(data);
        }
    }

    useEffect(() => {
        getOperations()
    })

    return <View style={styles.wrapper}>
        {operations.length > 0 ? operations.map(operation => <Operation operation={operation}/>) : <Text>Нет данных</Text>}
    </View>
}

const styles = StyleSheet.create({
    wrapper: {
        display: "flex",
        flex: 1,
        alignItems: "center",
        fontFamily: "GothamPro",
        color: colors.dark.text
    }
})