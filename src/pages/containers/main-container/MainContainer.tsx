import { Tabs } from "../../../components/tabs/Tabs";
import { pages } from "../../../consts";
import { useCustomLocation } from "../../../hooks/useCustomLocation";
import { Employees } from "./employees/Employees";
import { Tariffs } from "./tariffs/Tariffs";

export const MainContainer = () => {
    const page = useCustomLocation();

    const cards = {
        [pages.tariffs.link]: <Tariffs/>,
        [pages.employees.link]: <Employees/>,
        [pages.clients.link]: <Clients/>
    }

    return <div>
        <Tabs/>
        {cards[page]}
    </div>
}