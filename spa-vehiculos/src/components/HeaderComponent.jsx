

function HeaderComponent() {
  return (
    <div>
        <header></header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <div className="container-fluid">
                <a href='/' className="navbar-brand">Vehículos</a>
                <a href='/mantenimiento' className="navbar-brand">Mantenimiento de vehículos</a>
            </div>
        </nav>
    </div>
  )
}

export default HeaderComponent