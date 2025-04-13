import { MutationActionCreatorResult } from "@reduxjs/toolkit/query/react";
import { ReactNode } from "react";
import { FieldErrors, FieldValues, UseFormClearErrors, UseFormHandleSubmit, UseFormReset, UseFormSetError } from "react-hook-form";

export interface FormWrapperType<T extends FieldValues> {
    title: string,
    children: ReactNode,
    close: (value: React.SetStateAction<boolean>) => void,
    reset: UseFormReset<T>,
    handleSubmit: UseFormHandleSubmit<T, T>,
    errors: FieldErrors<T>
    createEntity: (arg: T) => MutationActionCreatorResult<any>,
    setError: UseFormSetError<T>,
    clearErrors: UseFormClearErrors<T>,
    check?: () => boolean
}