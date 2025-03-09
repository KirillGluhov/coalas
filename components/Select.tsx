import { colors } from "@/const/Colors";
import { SetStateAction, useState } from "react";
import { TouchableOpacity, Text, Modal, FlatList, View, StyleSheet, Image } from "react-native";

export type SelectItem = {
  label: string;
  value: string;
}

interface SelectType
{
  items: SelectItem[],
  title: string,
  value: string | null,
  change: React.Dispatch<SetStateAction<string | null>>;
  placeholder?: string;
}

export const Select: React.FC<SelectType> = ({items, title, value, change, placeholder}) => {
   const [isModalVisible, setModalVisible] = useState<boolean>(false);
  const [displayValue, setDisplayValue] = useState<string | null>(null);

  const toggleModal = () => setModalVisible(!isModalVisible)

  const handleSelect = (value: string, label: string) => {
    change(value);
    setDisplayValue(label);

    toggleModal();
  };

  const renderItem = ({ item }: { item: SelectItem }) => (
    <TouchableOpacity style={styles.option} onPress={() => handleSelect(item.value, item.label)}>
      <Text style={styles.optionText}>{item.label}</Text>
    </TouchableOpacity>
  );
    
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
              <FlatList
                data={items}
                renderItem={renderItem}
                keyExtractor={(item) => item.value}
                style={styles.optionsList}
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
    optionsList: {
      gap: 8
    },
    option: {
      
    },
    optionText: {
      color: colors.dark.text,
      fontSize: 27
    },
  });