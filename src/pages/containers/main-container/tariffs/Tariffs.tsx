import { CreateWrapper } from "../../../../components/forms/create/CreateWrapper";
import { InnerList } from "../../../../components/inner-list/InnerList";
import { tariffAPI } from "../../../../store/services/tariffService";
import { CreateTariffForm } from "./create-tariff/CreateTariffForm";
import { Tariff } from "./tariff/Tariff";

export const Tariffs = () => {
    const {data: tariffs, isLoading, error} = tariffAPI.useGetTariffsQuery();

    return <InnerList 
        title={"Тарифы"} 
        createButton={<CreateWrapper Form={CreateTariffForm}/>} 
        isLoading={isLoading} 
        error={error}
        mapper={
            tariffs?.map((tariff) => <Tariff tariff={tariff} key={tariff.id}/>) ?? []
        }
    />
}