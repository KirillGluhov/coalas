import { colors } from "@/const/Colors";
import { SetStateAction, useState } from "react";
import { TouchableOpacity, View, Modal, FlatList, Text, Image, StyleSheet } from "react-native";
import DateTimePicker, { DateType, getDefaultStyles } from "react-native-ui-datepicker";

interface DatePickerType
{
  title: string,
  value: DateType,
  change: React.Dispatch<SetStateAction<DateType>>;
  placeholder?: string;
  endDate?: DateType;
  startDate?: DateType;
}

export const DatePicker: React.FC<DatePickerType> = ({title, value, change, placeholder, startDate, endDate}) => {
   const [isModalVisible, setModalVisible] = useState<boolean>(false);
  const [displayValue, setDisplayValue] = useState<DateType | null>(null);
  const defaultStyles = getDefaultStyles();

  const toggleModal = () => setModalVisible(!isModalVisible)

  const handleSelect = (date: DateType) => {
    

    if (typeof date === 'object' && date && 'toLocaleDateString' in date)
    {
        const formatedDate = date?.toLocaleDateString();
        setDisplayValue(formatedDate);
        change(date?.toISOString());
    }
    else
    {
        setDisplayValue(date);
        change(date)
    }
    
    

    toggleModal();
  };
    
    return (
        <View style={styles.container}>
            <Text style={styles.title}>{title}</Text>
          <TouchableOpacity style={styles.select} onPress={toggleModal}>
            <Text style={styles.selectText}>
              {displayValue ? `${displayValue}` : placeholder ? placeholder : 'Выберите опцию'}
            </Text>
            <Image source={require("../assets/images/arrowdown.svg")}/>
          </TouchableOpacity>
    
          <Modal visible={isModalVisible} onRequestClose={toggleModal} style={styles.modal}>
            <View style={styles.modalContent}>
              <DateTimePicker
                mode="single"
                date={value}
                onChange={({ date }) => handleSelect(date)}
                styles={defaultStyles}
                style={styles.calendar}
                startDate={startDate}
                endDate={endDate}
              />
            </View>
          </Modal>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        display: "flex",
        width: "100%",
        flexDirection: "column",
        gap: 8,
    },
    title: {
        fontSize: 12,
        fontFamily: "GothamPro",
        color: "#92949C"
    },

    select: {
        color: colors.dark.text,
        fontSize: 15,
        height: 42,
        borderWidth: 1,
        borderColor: "#92949C",
        borderRadius: 8,
        padding: 16,
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between"

    },

    selectText: {
      fontSize: 15,
      fontFamily: "GothamPro",
      color: colors.dark.text
    },
    modal: {
      backgroundColor: colors.dark.black,
      height: "100%",
      alignItems: "center",
    },
    modalContent: {
      height: "100%",
      backgroundColor: colors.dark.black,
      padding: 16,
      alignItems: "center",
    },
    calendar: {
        backgroundColor: colors.dark.text,
        color: colors.dark.text
    }
  });