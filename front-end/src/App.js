import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddJob from './jobs/AddJob';
import Equipment from './pages/Equipment';
import AddEquipment from './equipments/AddEquipment';
import AddEquipmentToJob from './jobs/AddEquipmentToJob';

function App() {
  return (
    <div className="App">
      <Router>
      <Navbar/>
      <Routes>
        <Route exact path='/jobs' element={<Home/>}/>
        <Route exact path='/addJob' element={<AddJob/>}/>
        <Route exact path='/addEquipment' element={<AddEquipment/>}/>
        <Route exact path='/equipments' element={<Equipment/>}/>
        <Route exact path='/addEquipmentToJob' element={<AddEquipmentToJob/>}/>
      </Routes>
      </Router>

    </div>
  );
}

export default App;
