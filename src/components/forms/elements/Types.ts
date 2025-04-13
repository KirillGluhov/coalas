import { Control, FieldValues, Path } from "react-hook-form";

export interface SimpleInputType<T extends FieldValues> {
    control: Control<T, any, T>,
    title: string,
    isRequired?: boolean,
    name: Path<T>
}

export interface OptionType {
    id: string,
    value: string
}

export interface SimpleSelectType<T extends FieldValues> {
    name: Path<T>,
    control: Control<T, any, T>,
    title: string,
    options: OptionType[],
    isRequired?: boolean
}