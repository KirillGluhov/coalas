import { BrowserRouter, Route, Routes } from 'react-router'
import { Header } from './components/header/Header'
import { MainPage } from './pages/MainPage'
import { ThemeProvider } from './providers/theme/ThemeProvider'
import { pages } from './consts'
import { Login } from './pages/containers/login/Login'
import { SimpleContainer } from './pages/containers/SimpleContainer'

function App() {

  return (
    <ThemeProvider>
      <BrowserRouter>
        <Header/>
        <Routes>
          <Route path={"/"} element={<MainPage/>}/>
          <Route path={pages.login.link} element={<SimpleContainer/>}/>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  )
}

export default App
