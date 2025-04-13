import { SerializedError } from "@reduxjs/toolkit";
import { FetchBaseQueryError } from "@reduxjs/toolkit/query";
import { JSX, ReactNode } from "react";

export interface InnerListType {
    title: string;
    createButton: ReactNode;
    isLoading: boolean,
    error?: FetchBaseQueryError | SerializedError,
    mapper: JSX.Element[]
}