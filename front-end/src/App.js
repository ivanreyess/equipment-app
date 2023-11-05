import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import NavbarComponent from './layout/NavbarComponent';
import Job from './pages/Job';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddJob from './jobs/AddJob';
import Equipment from './pages/Equipment';
import AddEquipment from './equipments/AddEquipment';
import AddEquipmentToJob from './jobs/AddEquipmentToJob';
import EditEquipment from './equipments/EditEquipment';

function App() {
  return (
    <div className="App">
      <Router>
      <NavbarComponent/>
      <Routes>
        <Route exact path='/jobs' element={<Job/>}/>
        <Route exact path='/addJob' element={<AddJob/>}/>
        <Route exact path='/addEquipment' element={<AddEquipment/>}/>
        <Route exact path='/equipments' element={<Equipment/>}/>
        <Route exact path='/addEquipmentToJob' element={<AddEquipmentToJob/>}/>
        <Route exact path='/editEquipment/:id' element={<EditEquipment/>}/>
      </Routes>
      </Router>

    </div>
  );
}

export default App;
