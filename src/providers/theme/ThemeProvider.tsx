import React, { createContext, FC, useState } from "react"
import { ThemeContextType, ThemeProviderType } from "./Types"

export const themes = {
    dark: "dark",
    light: "light"
}

const getTheme = () => {
    const theme = `${window?.localStorage?.getItem('theme')}`
    if (Object.values(themes).includes(theme)) return theme
  
    const userMedia = window.matchMedia('(prefers-color-scheme: light)')
    if (userMedia.matches) return themes.light
  
    return themes.dark
}

export const ThemeContext = createContext<ThemeContextType | null>(null);

export const ThemeProvider: FC<ThemeProviderType> = ({ children }) => {
    const [ theme, setTheme ] = useState<string>(getTheme)
  
    React.useEffect(() => {
      document.documentElement.dataset.theme = theme
      localStorage.setItem('theme', theme)
    }, [ theme ])
  
    return (
      <ThemeContext.Provider value={{ theme, setTheme }}>
        {children}
      </ThemeContext.Provider>
    )
  }