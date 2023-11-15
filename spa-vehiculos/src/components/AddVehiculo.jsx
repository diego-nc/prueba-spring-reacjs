import { useState } from "react"
import VehiculoService from "../services/VehiculoService";
import { Link, useNavigate } from "react-router-dom";


const AddVehiculo =() => {
    const [placa, setPlaca] = useState('');
    const [modelo, setModelo] = useState('');
    const [año, setAño] = useState('');
    const [fechaDeCompra, setFechaDeCompra] = useState('');
    const [observaciones, setObservaciones] = useState('');
    const navigate = useNavigate();

    const saveVehiculo = (e) =>
    {
        e.preventDefault();
        const vehiculo = {placa,modelo,año,fechaDeCompra,observaciones}
        console.log(vehiculo);

       VehiculoService.createVehiculo(vehiculo).then(response=>{
            console.log(response.data);
            navigate('/vehiculos');
       }).catch(error => {
        console.log(error);
       })
    }

  return (
    <div>
        <div className="container">
            <div className="row">
                <div className="card col-md-6 offset-md-3">
                    <h2>Registro de Vehiculo</h2>
                    <div className="card-body">
                    <form >
                        <div className="mb-3 mt-3">
                            <label className="form-label">Placa:</label>
                            <input type="text" className="form-control"  name="placa" value={placa} onChange={(e)=> setPlaca(e.target.value)}/>
                        </div>
                        <div className="mb-3 mt-3">
                            <label className="form-label">Modelo:</label>
                            <select className="form-select" name="modelo" onChange={(e)=> setModelo(e.target.value)}>
                                <option selected>Seleccione Modelo</option>
                                <option value="Rio" >Rio</option>
                                <option value="Stonic" >Stonic</option>
                                <option value="Niro" >Niro</option>                               
                            </select>                            
                        </div>
                        <div className="mb-3 mt-3">
                            <label className="form-label">Año:</label>
                            <input type="text" className="form-control"  name="año" value={año} onChange={(e)=> setAño(e.target.value)}/>
                        </div>
                        <div className="mb-3 mt-3">
                            <label className="form-label">Fecha De Compra:</label>
                            <input type="date" className="form-control"  name="fechaDeCompra" value={fechaDeCompra} onChange={(e)=> setFechaDeCompra(e.target.value)}/>
                        </div>
                        <div className="mb-3 mt-3">
                            <label className="form-label">Observaciones:</label>
                            <input type="text" className="form-control"  name="observaciones" value={observaciones} onChange={(e)=> setObservaciones(e.target.value)}/>
                        </div>
                        
                        
                        
                        <button className="btn btn-success" onClick={(e)=>saveVehiculo(e)}>Guardar</button>
                        &nbsp;&nbsp;
                       <Link to='/vehiculos' className="btn btn-primary"> Cancelar </Link>
                    </form>
                    </div>
                </div>
             </div>
        </div>
    </div>
    
    
  )
}

export default AddVehiculo;