import { useState } from "react"
import VehiculoService from "../services/VehiculoService";
import { Link, useNavigate } from "react-router-dom";

const MantenimientoVehiculo = () => {
    const [fechaDeCompra, setFechaDeCompra] = useState('');
    const [vehiculosMantenimiento, setvehiculosMantenimiento] = useState([]);
    //const navigate = useNavigate();
    
    const mantenimientoVehiculo = (e) =>
    {
        e.preventDefault();
        const vehiculo = {fechaDeCompra}
        console.log(vehiculo);

       VehiculoService.realizarMantenimiento(vehiculo).then(response=>{
        setvehiculosMantenimiento(response.data);
            console.log(response.data);
           // navigate('/vehiculos');
       }).catch(error => {
        console.log(error);
       })

       
    }
    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3">
                        <h2>Mantenimiento Vehiculo</h2>
                        <div className="card-body">
                        <form >
                            
                            <div className="mb-3 mt-3">
                                <label className="form-label">Fecha De Mantenimiento:</label>
                                <input type="date" className="form-control"  name="fechaDeCompra" value={fechaDeCompra} onChange={(e)=> setFechaDeCompra(e.target.value)}/>
                            </div>
                           
                            <button className="btn btn-success" onClick={(e)=>mantenimientoVehiculo(e)}>Buscar</button>
                            &nbsp;&nbsp;
                           <Link to='/vehiculos' className="btn btn-primary"> Cancelar </Link>
                        </form>
                        </div>
                        
                        <div className='container'>
                            <h2 className='text-center'>Lista de vehiculos para mantenimiento</h2>
                            
                            <table className="table">
                            <thead>
                            <tr>
                            
                                <th>Placa</th>
                                <th>Fecha de compra</th>
                                
                            </tr>
                            </thead>
                            <tbody>
                            {
                                vehiculosMantenimiento.map(
                                vehiculoM => 
                                <tr key={vehiculoM.placa}>
                                    
                                    <td >{vehiculoM.placa}</td>
                                    <td >{vehiculoM.fechaDeCompra}</td>
                                    
                                </tr>
                                
                                )
                            }
                            </tbody>
                        </table>
                            </div>
                    </div>
                 </div>
            </div>
        </div>
        
        
      )
}

export default MantenimientoVehiculo