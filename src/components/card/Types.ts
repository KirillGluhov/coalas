import { ReactNode } from "react";

export interface CardType {
    title: string;
    children: ReactNode,
    additionalElement?: ReactNode
}