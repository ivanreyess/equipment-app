import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom';


export default function Job() {

    const [jobs, setJobs] = useState([])

    useEffect(() => {
        loadJobs();
    }, [])

    const loadJobs = async () => {
        const result = await axios.get("http://localhost:8080/api/v1/jobs")
        setJobs(result.data);
    }

    return (


        <div className='container'>

            <br />
            <br />
            <br />

            <div class="row justify-content-center">
                <div className='col-md-9'>
                    <h1>Job</h1>
                </div>
                <div className='col'>
                    <Link className='btn btn-success me-1' to="/addJob">Add job</Link >
                </div>
            </div>
            <div className='py-4'>
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Tools</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            jobs.map((job, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{job.name}</td>
                                    <td>{
                                        job.equipment.map((eq) => eq.name + ', ')}</td>
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
