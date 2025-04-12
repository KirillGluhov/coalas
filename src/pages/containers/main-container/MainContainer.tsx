import { Tabs } from "../../../components/tabs/Tabs";
import { pages } from "../../../consts";
import { useCustomLocation } from "../../../hooks/useCustomLocation";
import { Tariffs } from "./tariffs/Tariffs";

export const MainContainer = () => {
    const page = useCustomLocation();

    const cards = {
        [pages.tariffs.link]: <Tariffs/>
    }

    return <div>
        <Tabs/>
        {cards[page]}
    </div>
}