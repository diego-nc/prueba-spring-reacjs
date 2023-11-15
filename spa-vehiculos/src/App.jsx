import {BrowserRouter, Route, Routes} from "react-router-dom"
//import './App.css'
import HeaderComponent from "./components/HeaderComponent"
import Vehiculos from "./components/Vehiculos"
import AddVehiculo from "./components/AddVehiculo"
import MantenimientoVehiculo from "./components/MantenimientoVehiculo"

function App() {
  

  return (
  
      <div >
        <BrowserRouter>
        <HeaderComponent/>
        <div className="container">
          <Routes>
            <Route exact path='/' element={<Vehiculos/>}></Route>
            <Route path='/vehiculos' element={<Vehiculos/>}></Route>
            <Route path='/add-vehiculo' element={<AddVehiculo/>}></Route>
            <Route path='/mantenimiento' element={<MantenimientoVehiculo/>}></Route>
          </Routes>
        </div>
        
        </BrowserRouter>
        
      </div>
   
  )
}

export default App;
