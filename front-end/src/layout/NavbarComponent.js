import React from 'react'
import { Link } from 'react-router-dom'
import { NavItem, NavLink, NavbarBrand, Navbar, DropdownItem, Collapse, NavbarToggler, Nav } from 'reactstrap';


export default function NavbarComponent() {
    return (
        <div>
            {

                <Navbar data-cy="navbar" dark expand="md" fixed="top" className="bg-primary">

                    <NavbarBrand href="/">Equipment App</NavbarBrand>
                    <NavbarToggler  />
                    <Collapse navbar>
                        <Nav className="me-auto" navbar>
                            <NavItem>
                                <NavLink href="/jobs/">Jobs</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="/equipments/">
                                    Equipments
                                </NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="/addEquipmentToJob/">Assign Equipments</NavLink>
                            </NavItem>
                        </Nav>
                    </Collapse>

                </Navbar>


                /* <nav className="navbar navbar-expand-lg bg-light">
                    <div className="container-fluid">
                        <a className="navbar-brand" href="#">Equipment App</a>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
    
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Dropdown
                            </a>
                           
                        </li>
    
                        <Link className='navbar-brand' to="/addJob">Add job</Link>
    
                        <Link className='btn btn-outline-dark' to="/addEquipment">Add Equipment</Link>
    
                        <Link className='btn btn-outline-dark' to="/addEquipmentToJob">Add Equipment to Job</Link>
    
                    </div>
                </nav> */


            }
        </div>
    )
}
