import { useLocation } from "react-router"

export const useCustomLocation = () => {
    const {pathname} = useLocation();
    return pathname.slice(1, pathname.length);
}