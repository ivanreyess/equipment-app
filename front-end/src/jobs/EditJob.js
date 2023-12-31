import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function EditJob() {

    let navigate = useNavigate();

    const {id} = useParams();


    const [job, setJob] = useState({
        name: ""
    });
 
   
    const { name } = job;
    
    useEffect(() => {
        loadJob();
    }, []);


    const loadJob = async () => {
        const result = await axios.get(`http://localhost:8081/api/v1/jobs/${id}`);
        setJob(result.data);
    }


    const onInputChange = (e) => {

        setJob({ ...job, [e.target.name]: e.target.value })
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`http://localhost:8081/api/v1/jobs/${id}`, job);
        navigate("/jobs")
    };

    return (

        <div className='container'>
            <br />
            <br />
            <br />
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
                            <Link className='btn btn-outline-danger me-1' to="/jobs">Cancel</Link >
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}
