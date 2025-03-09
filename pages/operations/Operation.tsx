import { StyleSheet } from "react-native";
import OperationType from "../api/Types";
import { colors } from "@/const/Colors";

interface OperationProps
{
    operation: OperationType
}

export const Operation = ({operation}) => {
    return <View>
        <View style={styles.row}>
            <View >
                <Text style={styles.text}>{operation.date}</Text>
            </View>
            <View>
                <Text style={styles.additionalText}>{operation.balance ? operation.balance : 'Не изменился'}</Text>
            </View>
        </View>
        <View>
            <View>
                <Text style={styles.text}>{operation.type}</Text>
            </View>
        </View>
        <View>
            <Text style={styles.text}>Баланс</Text>
        </View>
    </View>
}

const styles = StyleSheet.create({
    text: {
        color: colors.dark.accent
    },
    additionalText: {
        fontSize: 24,
        backgroundColor: colors.dark.background
    },
    row: {
        display: "flex",
        justifyContent: "space-between"
    }

})