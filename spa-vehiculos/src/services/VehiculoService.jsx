import axios from "axios"


const API_URL=  "http://localhost:8080"

class VehiculoService
{
    getVehiculos(){
        return axios.get(API_URL+'/vehiculos');
    }

    createVehiculo(vehiculo)
    {
        return axios.post(API_URL+'/vehiculos', vehiculo);
    }

    realizarMantenimiento(vehiculo)
    {
        return axios.post(API_URL+'/mantenimiento',vehiculo);
    }
}
export default new VehiculoService();