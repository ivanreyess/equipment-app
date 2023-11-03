import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function Equipment() {

    const [equipment, setEquipment] = useState([])

    useEffect(() => {
        loadEquipment();
    }, [])

    const loadEquipment = async () => {
        const result = await axios.get("http://localhost:8080/api/v1/equipments")
        setEquipment(result.data);
    }

    return (
        <div className='container'>
            <div className='py-4'>
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Status</th> 
                            <th scope="col">Job</th>               
                            <th scope="col">Action</th>                 
                        </tr>
                    </thead>
                    <tbody>
                        {
                            equipment.map((equipment, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{equipment.name}</td>
                                    <td>{equipment.equipmentStatus}</td>
                                    <td>{equipment.job?.name}</td>                                     
                                    <td>
                                        <button className='btn btn-primary mx-2'>View</button>
                                        <button className='btn btn-outline-primary mx-2'>Edit</button>
                                    </td>
                                </tr>
                            ))
                        }

                    </tbody>
                </table>
            </div>
        </div>
    )
}
