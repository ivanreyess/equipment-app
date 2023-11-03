import React from 'react'

export default function AddJob() {
  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md3 border rounded p-4 mt-2 shadow'>
                <h2>Add job</h2>
                <div className='mb-3'>
                    <label htmlFor='Name' className='form-label'>
                        Name
                    </label>
                    <input type={'text'} className='form-control' placeholder='Enter job name' name='name'></input>
                    <br/>
                    <button type='submit' className='btn btn-outline-primary'>Submit</button>
                </div>
            </div>
        </div>
    </div>
  )
}
