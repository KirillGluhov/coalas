import { BrowserRouter, Route, Routes } from 'react-router'
import { Header } from './components/header/Header'
import { MainPage } from './pages/MainPage'
import { ThemeProvider } from './providers/theme/ThemeProvider'
import { pages } from './consts'
import { SimpleContainer } from './pages/containers/simple-container/SimpleContainer'
import { MainContainer } from './pages/containers/main-container/MainContainer'

function App() {

  return (
    <ThemeProvider>
      <BrowserRouter>
        <Header/>
        <Routes>
          <Route path={"/"} element={<MainPage/>}/>
          <Route path={pages.login.link} element={<SimpleContainer/>}/>
          <Route path={pages.tariffs.link} element={<MainContainer/>}/>
          <Route path={pages.employees.link} element={<MainContainer/>}/>
          <Route path={pages.clients.link} element={<MainContainer/>}/>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  )
}

export default App
