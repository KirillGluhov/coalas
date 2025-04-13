import { MutationActionCreatorResult } from "@reduxjs/toolkit/query/react";

export interface DeleteFormType {
    close: React.Dispatch<React.SetStateAction<boolean>>,
    id: string,
    title: string,
    deleteEntity: (arg: string) => MutationActionCreatorResult<any>
}

export interface DeleteWrapperType 
{
    id: string,
    title: string,
    deleteEntity: (arg: string) => MutationActionCreatorResult<any>
}