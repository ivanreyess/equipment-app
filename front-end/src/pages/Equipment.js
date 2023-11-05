import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Row, Col } from 'reactstrap';
import { Link } from 'react-router-dom';


export default function Equipment() {

    const [equipment, setEquipment] = useState([])

    useEffect(() => {
        loadEquipment();
    }, [])

    const loadEquipment = async () => {
        const result = await axios.get("http://localhost:8081/api/v1/equipments")
        setEquipment(result.data);
    }

    return (

        <Row>
            <Col className="pad">
                <br />
                <br />
                <br />

                <div className='container'>
                    <div className="row justify-content-center">
                        <div className='col-md-9'>
                            <h1>Equipments</h1>
                        </div>
                        <div className='col'>
                            <Link className='btn btn-success me-1' to="/addEquipment">Add Equipment</Link >
                        </div>
                    </div>
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
                                                <Link className='btn btn-outline-primary mx-2' to={`/editEquipment/${equipment.id}`}>Edit</Link>
                                            </td>
                                        </tr>
                                    ))
                                }

                            </tbody>
                        </table>
                    </div>
                </div>
            </Col>

        </Row>


    )
}
