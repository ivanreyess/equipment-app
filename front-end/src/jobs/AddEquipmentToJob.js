import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';

export default function AddEquipmentToJob() {

    let navigate = useNavigate();

    const [job, setJob] = useState({
        jobId: ""
    });

    const [equipment, setEquipment] = useState({
        equipmentId: ""
    });

    const [equipments, setEquipments] = useState([])

    const [jobs, setJobs] = useState([])

    useEffect(() => {
        loadEquipments();
        loadJobs();
    }, [])

    const { jobId } = job;
    const { equipmentId } = equipment;

    const onInputChange = (e) => {
        setJob({ ...job, [e.target.name]: e.target.value })
    };

    const loadEquipments = async () => {
        const result = await axios.get("http://localhost:8080/api/v1/equipments?equipmentStatus=AVAILABLE")
        setEquipments(result.data)
    }

    const loadJobs = async () => {
        const result = await axios.get("http://localhost:8080/api/v1/jobs")
        setJobs(result.data);
    }



    const onSubmit = async (e) => {
        e.preventDefault();
        // await axios.post("http://localhost:8080/api/v1/jobs", job);
        // navigate("/jobs")
        // console.log(job);
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md3 border rounded p-4 mt-2 shadow'>
                    <h2>Add Equipment to Job</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor='jobId' className='form-label'>
                                Job
                            </label>
                            <select class="form-select" aria-label="Status" name='jobId' value={job} onChange={(e) => onInputChange(e)} >
                            <option value="">Select job</option>
                            {
                                    jobs.map(option => (
                                        <option value={option.id}>{option.name}</option>
                                    ))

                               }
                            </select>
                            <br />
                            <label htmlFor='equipmentId' className='form-label'>
                                Equipment
                            </label>
                            <select class="form-select" aria-label="Status" name='equipmentId'>
                            <option value="">Select equipment</option>
                            {
                                    equipments.map(option => (
                                        <option value={option.id}>{option.name}</option>
                                    ))

                               }
                            </select>
                            <br/>
                            <button type='submit' className='btn btn-outline-primary me-1'>Submit</button>
                            <Link className='btn btn-outline-danger me-1' to="/addEquipmentToJob">Cancel</Link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}
