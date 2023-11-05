import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function EditEquipment() {

    let navigate = useNavigate();

    const {id} = useParams();

    const [equipment, setEquipment] = useState({
        name: "",
        equipmentStatus: "AVAILABLE"
    });

    const loadEquipment = async () => {
        const result = await axios.get(`http://localhost:8081/api/v1/equipments/${id}`);
        setEquipment(result.data);
    }

    useEffect(() => {
        loadEquipment();
    }, []);

    const { name } = equipment;
    const { equipmentStatus } = equipment;

    const onInputChange = (e) => {
        setEquipment({ ...equipment, [e.target.name]: e.target.value })
    };

    const selectOptions = [
        { value: "AVAILABLE", label: "AVAILABLE" },
        { value: "IN_USE", label: "IN_USE" }
    ];

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`http://localhost:8081/api/v1/equipments/${id}`, equipment);
        navigate("/equipments")
    };

    return (

        <div className='container'>
            <br />
            <br />
            <br />
            <div className='row'>
                <div className='col-md-6 offset-md3 border rounded p-4 mt-2 shadow'>
                    <h2>Edit Equipment</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor='Name' className='form-label'>
                                Name
                            </label>
                            <input type={'text'} className='form-control' placeholder='Enter equipment name' name='name' value={name} onChange={(e) => onInputChange(e)}></input>

                            <br />
                            <select class="form-select" aria-label="Status" name='equipmentStatus' value={equipmentStatus} onChange={(e) => onInputChange(e)}>
                                {
                                    selectOptions.map(option => (
                                        <option value={option.value}>{option.label}</option>
                                    ))

                                }
                            </select>

                            <br />
                            <button type='submit' className='btn btn-outline-primary me-1'>Submit</button>
                            <Link className='btn btn-outline-danger me-1' to="/equipments">Cancel</Link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}
