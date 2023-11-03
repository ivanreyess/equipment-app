import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddJob from './jobs/AddJob';

function App() {
  return (
    <div className="App">
      <Router>
      <Navbar/>
      <Routes>
        <Route exact path='/jobs' element={<Home/>}/>
        <Route exact path='/addJob' element={<AddJob/>}/>
      </Routes>
      </Router>

    </div>
  );
}

export default App;
