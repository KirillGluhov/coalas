import { ReactNode } from "react";

export interface ModalType {
    open: boolean,
    setOpen: React.Dispatch<React.SetStateAction<boolean>>,
    children: ReactNode
}