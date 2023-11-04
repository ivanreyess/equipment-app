import React from 'react'
import { NavItem, NavLink, NavbarBrand, Navbar, Collapse, NavbarToggler, Nav } from 'reactstrap';


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

            }
        </div>
    )
}
