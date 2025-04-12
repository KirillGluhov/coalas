import { Card } from "../../../../components/card/Card"
import { themes } from "../../../../providers/theme/ThemeProvider";
import { useTheme } from "../../../../providers/theme/useTheme";
import { tariffAPI } from "../../../../store/services/tariffService";
import { CreateTariff } from "./create-tariff/CreateTariff";
import { TariffList } from "./TariffList";
import styles from './Tariffs.module.scss';

export const Tariffs = () => {
    const {theme} = useTheme();
    const {data: tariffs, isLoading, error} = tariffAPI.useGetTariffsQuery();
    
    return (
        <div className={styles.tariffs}>
            <Card title={"Тарифы"} additionalElement={<CreateTariff/>}>
                <div className={`${styles.innerPart} ${theme === themes.light ? styles.light : styles.dark}`}>
                    {
                        isLoading ? <p>Загрузка</p> :
                        error ? <p>Ошибка</p> :
                        tariffs ? <TariffList tariffs={tariffs}/>
                        : null
                    }
                </div>
            </Card>
        </div>
    )
}