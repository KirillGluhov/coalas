import { ReactNode } from "react";

export interface ThemeProviderType
{
    children: ReactNode
}

export interface ThemeContextType
{
    theme: string,
    setTheme: React.Dispatch<React.SetStateAction<string>>
}