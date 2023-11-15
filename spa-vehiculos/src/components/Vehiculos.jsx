import  {useEffect, useState} from 'react'
import VehiculoService from '../services/VehiculoService';
import { Link } from 'react-router-dom';
import DataTable from 'react-data-table-component';

const Vehiculos = () => {

  const column =[
    {name:"Id",selector:row=>row.id},
    {name:"Placa",selector:row=>row.placa},
    {name:"Modelo",selector:row=>row.modelo},
    {name:"Precio",selector:row=>row.precio}
    
  ]
  
  const [vehiculos, setVehiculos]= useState([]);
  useEffect(() =>{
    VehiculoService.getVehiculos().then(response => {
      setVehiculos(response.data);
      console.log(response.data);
    }).catch(error=>{
      console.log(error);
    })
  },[])

  return (
    <div className='container'>
      <h2 className='text-center'>Lista de vehiculos</h2>
      <Link to='/add-vehiculo' className='btn btn-primary'> Nuevo</Link>
    
      <DataTable
      columns={column}
      data={vehiculos}
      pagination
      >
      </DataTable>
    </div>
    
  )
}

export default Vehiculos