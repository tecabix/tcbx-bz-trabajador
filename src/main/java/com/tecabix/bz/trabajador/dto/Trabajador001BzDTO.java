package com.tecabix.bz.trabajador.dto;

import java.util.List;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.repository.BancoRepository;
import com.tecabix.db.repository.CatalogoRepository;
import com.tecabix.db.repository.ContactoRepository;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.DireccionRepository;
import com.tecabix.db.repository.EstadoRepository;
import com.tecabix.db.repository.MunicipioRepository;
import com.tecabix.db.repository.PerfilRepository;
import com.tecabix.db.repository.PersonaRepository;
import com.tecabix.db.repository.PuestoRepository;
import com.tecabix.db.repository.SalarioRepository;
import com.tecabix.db.repository.SeguroSocialRepository;
import com.tecabix.db.repository.SucursalRepository;
import com.tecabix.db.repository.TrabajadorRepository;
import com.tecabix.db.repository.TurnoRepository;
import com.tecabix.db.repository.UsuarioPersonaRepository;
import com.tecabix.db.repository.UsuarioRepository;

public class Trabajador001BzDTO {

    /**
     * Lista de catálogos que define los tipos de contacto disponibles. Cada
     * elemento representa un tipo de contacto permitido en el sistema.
     */
    private List<Catalogo> tipoContacto;

    /**
     * Estado "activo" obtenido desde el catálogo.
     */
    private Catalogo activo;

    /**
     * Repositorio para acceder a la entidad Catalogo.
     */
    private CatalogoRepository catalogoRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Municipio}.
     */
    private MunicipioRepository municipioRepository;

    /**
     * Repositorio para acceder a los datos de los trabajadores.
     */
    private TrabajadorRepository trabajadorRepository;

    /**
     * Repositorio para acceder a los datos de los puestos de trabajo.
     */
    private PuestoRepository puestoRepository;

    /**
     * Repositorio para acceder a los datos de los turnos laborales.
     */
    private TurnoRepository turnoRepository;

    /**
     * Repositorio para acceder a los datos de los bancos asociados.
     */
    private BancoRepository bancoRepository;

    /**
     * Repositorio para acceder a los estados del sistema o del trabajador.
     */
    private EstadoRepository estadoRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Perfil}.
     */
    private PerfilRepository perfilRepository;

    /**
     * Repositorio para acceder a los datos de los salarios.
     */
    private SalarioRepository salarioRepository;

    /**
     * Repositorio para acceder a los datos del seguro social.
     */
    private SeguroSocialRepository seguroSocialRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Direccion}.
     */
    private DireccionRepository direccionRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Persona}.
     */
    private PersonaRepository personaRepository;

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private CuentaRepository cuentaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link UsuarioPersona}.
     */
    private UsuarioPersonaRepository usuarioPersonaRepository;

    /**
     * Repositorio para acceder a la entidad Usuario.
     */
    private UsuarioRepository usuarioRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Contacto}.
     */
    private ContactoRepository contactoRepository;

    /**
     * Repositorio para manejar operaciones de la entidad Sucursal.
     */
    private SucursalRepository sucursalRepository;

    /**
     * Obtiene el catálogo activo.
     *
     * @return el catálogo activo
     */
    public Catalogo getActivo() {
        return activo;
    }

    /**
     * Establece el catálogo activo.
     *
     * @param estatus el catálogo a establecer como activo
     */
    public void setActivo(final Catalogo estatus) {
        this.activo = estatus;
    }

    /**
     * Obtiene la lista de tipos de contacto.
     *
     * @return la lista de tipos de contacto
     */
    public List<Catalogo> getTipoContacto() {
        return tipoContacto;
    }

    /**
     * Establece la lista de tipos de contacto.
     *
     * @param contacto la lista de tipos de contacto a establecer
     */
    public void setTipoContacto(final List<Catalogo> contacto) {
        this.tipoContacto = contacto;
    }

    /**
     * Obtiene el repositorio de catálogos.
     *
     * @return el repositorio de catálogos
     */
    public CatalogoRepository getCatalogoRepository() {
        return catalogoRepository;
    }

    /**
     * Establece el repositorio de catálogos.
     *
     * @param repository el repositorio de catálogos a establecer
     */
    public void setCatalogoRepository(final CatalogoRepository repository) {
        this.catalogoRepository = repository;
    }

    /**
     * Obtiene el repositorio de municipios.
     *
     * @return el repositorio de municipios
     */
    public MunicipioRepository getMunicipioRepository() {
        return municipioRepository;
    }

    /**
     * Establece el repositorio de municipios.
     *
     * @param repository el repositorio de municipios a establecer
     */
    public void setMunicipioRepository(final MunicipioRepository repository) {
        this.municipioRepository = repository;
    }

    /**
     * Obtiene el repositorio de trabajadores.
     *
     * @return el repositorio de trabajadores
     */
    public TrabajadorRepository getTrabajadorRepository() {
        return trabajadorRepository;
    }

    /**
     * Establece el repositorio de trabajadores.
     *
     * @param repository el repositorio de trabajadores a establecer
     */
    public void setTrabajadorRepository(final TrabajadorRepository repository) {
        this.trabajadorRepository = repository;
    }

    /**
     * Obtiene el repositorio de puestos.
     *
     * @return el repositorio de puestos
     */
    public PuestoRepository getPuestoRepository() {
        return puestoRepository;
    }

    /**
     * Establece el repositorio de puestos.
     *
     * @param repository el repositorio de puestos a establecer
     */
    public void setPuestoRepository(final PuestoRepository repository) {
        this.puestoRepository = repository;
    }

    /**
     * Obtiene el repositorio de turnos.
     *
     * @return el repositorio de turnos
     */
    public TurnoRepository getTurnoRepository() {
        return turnoRepository;
    }

    /**
     * Establece el repositorio de turnos.
     *
     * @param repository el repositorio de turnos a establecer
     */
    public void setTurnoRepository(final TurnoRepository repository) {
        this.turnoRepository = repository;
    }

    /**
     * Obtiene el repositorio de bancos.
     *
     * @return el repositorio de bancos
     */
    public BancoRepository getBancoRepository() {
        return bancoRepository;
    }

    /**
     * Establece el repositorio de bancos.
     *
     * @param repository el repositorio de bancos a establecer
     */
    public void setBancoRepository(final BancoRepository repository) {
        this.bancoRepository = repository;
    }

    /**
     * Obtiene el repositorio de estados.
     *
     * @return el repositorio de estados
     */
    public EstadoRepository getEstadoRepository() {
        return estadoRepository;
    }

    /**
     * Establece el repositorio de estados.
     *
     * @param repository el repositorio de estados a establecer
     */
    public void setEstadoRepository(final EstadoRepository repository) {
        this.estadoRepository = repository;
    }

    /**
     * Obtiene el repositorio de perfiles.
     *
     * @return el repositorio de perfiles
     */
    public PerfilRepository getPerfilRepository() {
        return perfilRepository;
    }

    /**
     * Establece el repositorio de perfiles.
     *
     * @param repository el repositorio de perfiles a establecer
     */
    public void setPerfilRepository(final PerfilRepository repository) {
        this.perfilRepository = repository;
    }

    /**
     * Obtiene el repositorio de salarios.
     *
     * @return el repositorio de salarios
     */
    public SalarioRepository getSalarioRepository() {
        return salarioRepository;
    }

    /**
     * Establece el repositorio de salarios.
     *
     * @param repository el repositorio de salarios a establecer
     */
    public void setSalarioRepository(final SalarioRepository repository) {
        this.salarioRepository = repository;
    }

    /**
     * Obtiene el repositorio de seguro social.
     *
     * @return el repositorio de seguro social
     */
    public SeguroSocialRepository getSeguroSocialRepository() {
        return seguroSocialRepository;
    }

    /**
     * Establece el repositorio de seguro social.
     *
     * @param repository el repositorio de seguro social a establecer
     */
    public void setSeguroSocialRepository(
        final SeguroSocialRepository repository) {
        this.seguroSocialRepository = repository;
    }

    /**
     * Obtiene el repositorio de direcciones.
     *
     * @return el repositorio de direcciones
     */
    public DireccionRepository getDireccionRepository() {
        return direccionRepository;
    }

    /**
     * Establece el repositorio de direcciones.
     *
     * @param repository el repositorio de direcciones a establecer
     */
    public void setDireccionRepository(final DireccionRepository repository) {
        this.direccionRepository = repository;
    }

    /**
     * Obtiene el repositorio de personas.
     *
     * @return el repositorio de personas
     */
    public PersonaRepository getPersonaRepository() {
        return personaRepository;
    }

    /**
     * Establece el repositorio de personas.
     *
     * @param repository el repositorio de personas a establecer
     */
    public void setPersonaRepository(final PersonaRepository repository) {
        this.personaRepository = repository;
    }

    /**
     * Obtiene el repositorio de cuentas.
     *
     * @return el repositorio de cuentas
     */
    public CuentaRepository getCuentaRepository() {
        return cuentaRepository;
    }

    /**
     * Establece el repositorio de cuentas.
     *
     * @param repository el repositorio de cuentas a establecer
     */
    public void setCuentaRepository(final CuentaRepository repository) {
        this.cuentaRepository = repository;
    }

    /**
     * Obtiene el repositorio de usuario-persona.
     *
     * @return el repositorio de usuario-persona
     */
    public UsuarioPersonaRepository getUsuarioPersonaRepository() {
        return usuarioPersonaRepository;
    }

    /**
     * Establece el repositorio de usuario-persona.
     *
     * @param repository el repositorio de usuario-persona a establecer
     */
    public void setUsuarioPersonaRepository(
        final UsuarioPersonaRepository repository) {
        this.usuarioPersonaRepository = repository;
    }

    /**
     * Obtiene el repositorio de usuarios.
     *
     * @return el repositorio de usuarios
     */
    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    /**
     * Establece el repositorio de usuarios.
     *
     * @param repository el repositorio de usuarios a establecer
     */
    public void setUsuarioRepository(final UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    /**
     * Obtiene el repositorio de contactos.
     *
     * @return el repositorio de contactos
     */
    public ContactoRepository getContactoRepository() {
        return contactoRepository;
    }

    /**
     * Establece el repositorio de contactos.
     *
     * @param repository el repositorio de contactos a establecer
     */
    public void setContactoRepository(final ContactoRepository repository) {
        this.contactoRepository = repository;
    }

    /**
     * Obtiene el repositorio de sucursales.
     *
     * @return el repositorio de sucursales
     */
    public SucursalRepository getSucursalRepository() {
        return sucursalRepository;
    }

    /**
     * Establece el repositorio de sucursales.
     *
     * @param repository el repositorio de sucursales a establecer
     */
    public void setSucursalRepository(final SucursalRepository repository) {
        this.sucursalRepository = repository;
    }


}
