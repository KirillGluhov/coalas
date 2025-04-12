export enum ButtonStyleType 
{
    Delete, Create
}

export interface ButtonType {
    text: string,
    onClick?: () => void,
    type?: ButtonStyleType
}