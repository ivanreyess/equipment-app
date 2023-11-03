import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

export default function AddJob() {

    let navigate = useNavigate();

    const [job, setJob] = useState({
        name: ""
    });

    const { name } = job;

    const onInputChange = (e) => {

        setJob({ ...job, [e.target.name]: e.target.value })
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post("http://localhost:8080/api/v1/jobs", job);
        navigate("/jobs")
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md3 border rounded p-4 mt-2 shadow'>
                    <h2>Add job</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor='Name' className='form-label'>
                                Name
                            </label>
                            <input type={'text'} className='form-control' placeholder='Enter job name' name='name' value={name} onChange={(e) => onInputChange(e)}></input>
                            <br />
                            <button type='submit' className='btn btn-outline-primary me-1'>Submit</button>
                            <button type='submit' className='btn btn-outline-danger me-1'>Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}
