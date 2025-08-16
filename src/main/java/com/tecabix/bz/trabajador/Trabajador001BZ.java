package com.tecabix.bz.trabajador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;

import com.tecabix.bz.trabajador.dto.Trabajador001BzDTO;
import com.tecabix.db.entity.Banco;
import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.Contacto;
import com.tecabix.db.entity.Cuenta;
import com.tecabix.db.entity.Direccion;
import com.tecabix.db.entity.Estado;
import com.tecabix.db.entity.Municipio;
import com.tecabix.db.entity.Perfil;
import com.tecabix.db.entity.Persona;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Puesto;
import com.tecabix.db.entity.Salario;
import com.tecabix.db.entity.SeguroSocial;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Sucursal;
import com.tecabix.db.entity.Trabajador;
import com.tecabix.db.entity.Turno;
import com.tecabix.db.entity.Usuario;
import com.tecabix.db.entity.UsuarioPersona;
import com.tecabix.db.repository.BancoRepository;
import com.tecabix.db.repository.CatalogoRepository;
import com.tecabix.db.repository.ContactoRepository;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.DireccionRepository;
import com.tecabix.db.repository.EstadoRepository;
import com.tecabix.db.repository.MunicipioRepository;
import com.tecabix.db.repository.PerfilRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.PersonaRepository;
import com.tecabix.db.repository.PuestoRepository;
import com.tecabix.db.repository.SalarioRepository;
import com.tecabix.db.repository.SeguroSocialRepository;
import com.tecabix.db.repository.SucursalRepository;
import com.tecabix.db.repository.TrabajadorRepository;
import com.tecabix.db.repository.TurnoRepository;
import com.tecabix.db.repository.UsuarioPersonaRepository;
import com.tecabix.db.repository.UsuarioRepository;
import com.tecabix.res.b.RSB029;
import com.tecabix.sv.rq.RQSV024;
import com.tecabix.sv.rq.RQSV037;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public abstract class Trabajador001BZ {

    /**
     * Repositorio para acceder a la entidad Catalogo.
     */
    private final CatalogoRepository catalogoRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Municipio}.
     */
    private final MunicipioRepository municipioRepository;

    /**
     * Repositorio para acceder a los datos de los trabajadores.
     */
    private final TrabajadorRepository trabajadorRepository;

    /**
     * Repositorio para acceder a los datos de los puestos de trabajo.
     */
    private final PuestoRepository puestoRepository;

    /**
     * Repositorio para acceder a los datos de los turnos laborales.
     */
    private final TurnoRepository turnoRepository;

    /**
     * Repositorio para acceder a los datos de los bancos asociados.
     */
    private final BancoRepository bancoRepository;

    /**
     * Repositorio para acceder a los estados del sistema o del trabajador.
     */
    private final EstadoRepository estadoRepository;

    /**
     * Repositorio para acceder a los datos de los salarios.
     */
    private final SalarioRepository salarioRepository;

    /**
     * Repositorio para acceder a los datos del seguro social.
     */
    private final SeguroSocialRepository seguroSocialRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Perfil}.
     */
    private final PerfilRepository perfilRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Direccion}.
     */
    private final DireccionRepository direccionRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Persona}.
     */
    private final PersonaRepository personaRepository;
    
    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link PersonaFisica}.
     */
    private final PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private final CuentaRepository cuentaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link UsuarioPersona}.
     */
    private final UsuarioPersonaRepository usuarioPersonaRepository;

    /**
     * Repositorio para acceder a la entidad Usuario.
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Contacto}.
     */
    private final ContactoRepository contactoRepository;

    /**
     * Repositorio para manejar operaciones de la entidad Sucursal.
     */
    private final SucursalRepository sucursalRepository;

    /**
     * Catálogo que representa el estado "Activo" en el sistema.
     */
    private final Catalogo activo;

    /**
     * Lista de catálogos que define los tipos de contacto disponibles. Cada
     * elemento representa un tipo de contacto permitido en el sistema.
     */
    private final List<Catalogo> tipoContacto;

    /**
     * Expresión para correo electrónico.
     */
    private static final String CORREO;

    /**
     * Expresión para URLs.
     */
    private static final String URL;

    /**
     * Expresión para teléfono.
     */
    private static final String TELEFONO;

    /**
     * Constante que representa el identificador del tipo de persona.
     */
    private static final String PERSONA;

    /**
     * Constante que representa el tipo de persona "Física".
     */
    private static final String FISICA;

    /**
     * Mensaje cuando no hay coincidencias con el tipo de contacto.
     */
    private static final String SIN_COINCIDENCIAS_TIPO_DE_CONTACTO;

    /**
     * Mensaje cuando no hay coincidencias con la clave de sexo.
     */
    private static final String SIN_COINCIDENCIAS_CLAVE_SEXO;
    
    /**
     * Mensaje cuando no hay coincidencias con el nombre de sexo.
     */
    private static final String SIN_COINCIDENCIAS_NOMBRE_SEXO;
    
    /**
     * Persona fisica no encontrada.
     */
    private static final String NO_SE_ENCONTRO_PERSONA_FISICA;
    
    /**
     * Municipio no encontrado.
     */
    private static final String NO_SE_ENCONTRO_EL_MUNICIPIO;

    /**
     * Municipio no encontrado.
     */
    private static final String NO_SE_ENCONTRO_EL_ESTADO;
    
    /**
     * Banco no encontrado.
     */
    private static final String NO_SE_ENCONTRO_EL_BANCO;

    /**
     * Sucursal no encontrada.
     */
    private static final String NO_SE_ENCONTRO_LA_SUCURSAL;
    
    /**
     * Jefe no encontrado.
     */
    private static final String NO_SE_ENCONTRO_JEFE;
    
    /**
     * Puesto no encontrado.
     */
    private static final String NO_SE_ENCONTRO_PUESTO;
    
    /**
     * Estatus de puesto es inactivo.
     */
    private static final String PUESTO_ESTATUS_INACTIVO;
    
    /**
     * Turno no encontrado.
     */
    private static final String NO_SE_ENCONTRO_TURNO;

    /**
     * Tipo pago no encontrado.
     */
    private static final String NO_SE_ENCONTRO_TIPO_PAGO;
    
    /**
     * Tipo periodo no encontrado.
     */
    private static final String NO_SE_ENCONTRO_TIPO_PERIODO;
    
    /**
     * Estatus de turno es inactivo.
     */
    private static final String TURNO_ESTATUS_INACTIVO;
    
    /**
     * Perfil no encontrado.
     */
    private static final String NO_SE_ENCONTRO_PERFIL;
    
    /**
     * Estatus de perfil es inactivo.
     */
    private static final String PERFIL_ESTATUS_INACTIVO;

    /**
     * Mensaje cuando el username ya existe.
     */
    private static final String USERNAME_YA_EXISTE;

    /**
     * Mensaje cuando el CURP ya existe.
     */
    private static final String CURP_YA_EXISTE;
    
    /**
     * Constructor que inicializa una instancia de {@code Trabajador001BZ}
     * utilizando los datos proporcionados en el objeto
     * {@code Trabajador001BzDTO}.
     *
     * @param dto Objeto de transferencia de datos que contiene las
     *            dependencias necesarias para inicializar los repositorios
     *            y atributos del trabajador.
     */
    public Trabajador001BZ(final Trabajador001BzDTO dto) {
        this.tipoContacto = dto.getTipoContacto();
        this.activo = dto.getActivo();
        this.catalogoRepository = dto.getCatalogoRepository();
        this.municipioRepository = dto.getMunicipioRepository();
        this.trabajadorRepository = dto.getTrabajadorRepository();
        this.puestoRepository = dto.getPuestoRepository();
        this.turnoRepository = dto.getTurnoRepository();
        this.bancoRepository = dto.getBancoRepository();
        this.estadoRepository = dto.getEstadoRepository();
        this.perfilRepository = dto.getPerfilRepository();
        this.salarioRepository = dto.getSalarioRepository();
        this.seguroSocialRepository = dto.getSeguroSocialRepository();
        this.direccionRepository = dto.getDireccionRepository();
        this.personaRepository = dto.getPersonaRepository();
        this.cuentaRepository = dto.getCuentaRepository();
        this.usuarioPersonaRepository = dto.getUsuarioPersonaRepository();
        this.usuarioRepository = dto.getUsuarioRepository();
        this.contactoRepository = dto.getContactoRepository();
        this.sucursalRepository = dto.getSucursalRepository();
        this.personaFisicaRepository = dto.getPersonaFisicaRepository();
    }

    static {
        SIN_COINCIDENCIAS_TIPO_DE_CONTACTO = "Sin coincidencias con el tipo de contacto.";
        SIN_COINCIDENCIAS_CLAVE_SEXO = "No se encontró la clave sexo";
        SIN_COINCIDENCIAS_NOMBRE_SEXO = "No se encontró el nombre sexo.";
        NO_SE_ENCONTRO_PERSONA_FISICA = "No se encontro a la persona fisica.";
        NO_SE_ENCONTRO_EL_MUNICIPIO = "No se encontro el municipio.";
        NO_SE_ENCONTRO_EL_ESTADO = "No se encontro el estado.";
        NO_SE_ENCONTRO_JEFE = "No se encontró jefe.";
        NO_SE_ENCONTRO_PUESTO = "No se encontro puesto.";
        PUESTO_ESTATUS_INACTIVO = "El estatus de puesto es inactivo.";
        NO_SE_ENCONTRO_TURNO = "No se encontró turno.";
        TURNO_ESTATUS_INACTIVO = "El estatus de turno es inactivo";
        NO_SE_ENCONTRO_PERFIL = "No se encontró perfil.";
        PERFIL_ESTATUS_INACTIVO = "El estatus de perfil es inactivo";
        USERNAME_YA_EXISTE = "El username ya existe.";
        NO_SE_ENCONTRO_EL_BANCO = "No se encontró el banco.";
        NO_SE_ENCONTRO_LA_SUCURSAL = "No se encontró la sucursal.";
        NO_SE_ENCONTRO_TIPO_PAGO = "No se encontró el tipo pago.";
        NO_SE_ENCONTRO_TIPO_PERIODO = "No se encontró el tipo periodo.";
        CURP_YA_EXISTE = "El CURP ya existe.";
        CORREO = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        URL = "^https?:\\\\/\\\\/(?:www\\\\.)?[a-zA-Z0-9-]+\\\\.[a-zA-Z]{2,6}(?:\\\\/[^\\\\s]*)?$";
        TELEFONO = "\\d{10}";
        PERSONA = "TIPO_DE_PERSONA";
        FISICA = "FISICA";
    }

    /**
     * Crea y persiste un nuevo trabajador junto con sus entidades relacionadas
     * (persona, dirección, salario, seguro social, cuenta, usuario, etc.)
     * a partir de los datos proporcionados en {@link RQSV037}.
     *
     * Este método no realiza validaciones profundas y asume que los datos son
     * correctos.
     *
     * @param rqsv037 Datos de entrada para construir el trabajador y sus
     *                relaciones.
     * @return {@link ResponseEntity} con {@link RSB028} si se crea
     *         correctamente.
     */
    public ResponseEntity<RSB029> crear(final RQSV037 rqsv037) {

        RSB029 rsb029 = rqsv037.getRsb029();
        Sesion sesion = rqsv037.getSesion();
        List<Contacto> contactosC = procesarContactos(rqsv037.getContactos());
        if (contactosC == null) {
            return rsb029.badRequest(SIN_COINCIDENCIAS_TIPO_DE_CONTACTO);
        }
        
        if (usuarioRepository.findByNameRegardlessOfStatus(rqsv037.getUsuario()).isPresent()) {
            return rsb029.badRequest(USERNAME_YA_EXISTE);
        }

        if (personaFisicaRepository.findByCurp(rqsv037.getCurp()).isPresent()) {
            return rsb029.badRequest(CURP_YA_EXISTE);
        }

        String nombre = rqsv037.getNombre();
        PersonaFisica personaFisica = new PersonaFisica();
        personaFisica.setNombre(nombre);

        Persona persona = new Persona();
        persona.setContactos(contactosC);
        personaFisica.setPersona(persona);


        String calle = rqsv037.getCalle();
        String asentamiento = rqsv037.getAsentamiento();
        String numExt = rqsv037.getNumExt();
        Optional<String> numIntOpt = rqsv037.getNumInt();

        Direccion direccion = new Direccion();
        direccion.setCalle(calle);
        direccion.setAsentamiento(asentamiento);
        direccion.setNumExt(numExt);

        if (numIntOpt.isPresent()) {
            direccion.setNumInt(numIntOpt.get());
        }

        UUID sexo = rqsv037.getSexo();
        Optional<Catalogo> sexoOpt;
        sexoOpt = catalogoRepository.findByClave(sexo);
        if (sexoOpt.isEmpty()) {
            return rsb029.badRequest(SIN_COINCIDENCIAS_CLAVE_SEXO);
        }

        Catalogo tipoS = sexoOpt.get();
        String sexoNombre = tipoS.getNombre();
        String tipoNombre = tipoS.getCatalogoTipo().getNombre();
        Optional<Catalogo> optCatalogoS;
        optCatalogoS = catalogoRepository.findByTipoAndNombre(tipoNombre, sexoNombre);
        if (optCatalogoS.isEmpty()) {
            return rsb029.badRequest(SIN_COINCIDENCIAS_NOMBRE_SEXO);
        }

        Optional<Catalogo> personaOpt;
        personaOpt = catalogoRepository.findByTipoAndNombre(PERSONA, FISICA);
        if (personaOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_PERSONA_FISICA);
        }

        UUID claveMunicipio = rqsv037.getMunicipio();
        Optional<Municipio> municipioOpt;
        municipioOpt = municipioRepository.findByClave(claveMunicipio);
        if (municipioOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_EL_MUNICIPIO);
        }
        direccion.setMunicipio(municipioOpt.get());

        UUID claveEntidadFederativa = rqsv037.getEntidadFederativa();
        Optional<Estado> entidadFederativaOpt;
        entidadFederativaOpt = estadoRepository.findByClave(claveEntidadFederativa);
        if (entidadFederativaOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_EL_ESTADO);
        }

        UUID claveJefe = rqsv037.getJefe();
        Optional<Trabajador> trabajadorOpt;
        trabajadorOpt = trabajadorRepository.findByClave(claveJefe);
        if (trabajadorOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_JEFE);
        }

        UUID claveBanco = rqsv037.getBanco();
        Optional<Banco> bancoOpt;
        bancoOpt = bancoRepository.findByClave(claveBanco);
        if (bancoOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_EL_BANCO);
        }

        Trabajador trabajador = new Trabajador();
        trabajador.setJefe(trabajadorOpt.get());

        UUID claveSucursal = rqsv037.getSucursal();
        Optional<Sucursal> sucursalOpt;
        sucursalOpt = sucursalRepository.findByClave(claveSucursal);
        if (sucursalOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_LA_SUCURSAL);
        }

        Sucursal sucursal = new Sucursal();
        sucursal.setClave(UUID.randomUUID());
        sucursal.setGerente(trabajador.getJefe());
        sucursal.setDireccion(direccion);
        sucursal.setFechaDeModificacion(LocalDateTime.now());
        sucursal.setEstatus(activo);
        sucursal.setIdUsuarioModificado(sesion.getIdUsuarioModificado());

        UUID clavePuesto = rqsv037.getPuesto();
        Optional<Puesto> puestoOpt = puestoRepository.findByClave(clavePuesto);
        if (puestoOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_PUESTO);
        }
        trabajador.setPuesto(puestoOpt.get());
        if (!trabajador.getPuesto().getEstatus().equals(activo)) {
            return rsb029.badRequest(PUESTO_ESTATUS_INACTIVO);
        }

        UUID claveTurno = rqsv037.getTurno();
        Optional<Turno> turnoOpt = turnoRepository.findByClave(claveTurno);
        if (turnoOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_TURNO);
        }
        
        UUID claveTipoPago = rqsv037.getTipoPago();
        Optional<Catalogo> tipoPagoOpt;
        tipoPagoOpt = catalogoRepository.findByClave(claveTipoPago);
        if (tipoPagoOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_TIPO_PAGO);
        }
        
        UUID claveTipoPeriodo = rqsv037.getTipoPeriodo();
        Optional<Catalogo> tipoPeriodoOpt;
        tipoPeriodoOpt = catalogoRepository.findByClave(claveTipoPeriodo);
        if (tipoPeriodoOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_TIPO_PERIODO);
        }

        trabajador.setTurno(turnoOpt.get());
        if (!trabajador.getTurno().getEstatus().equals(activo)) {
            return rsb029.badRequest(TURNO_ESTATUS_INACTIVO);
        }

        UUID clavePerfil = rqsv037.getPerfil();
        Optional<Perfil> perfilOpt = perfilRepository.findByClave(clavePerfil);
        if (perfilOpt.isEmpty()) {
            return rsb029.badRequest(NO_SE_ENCONTRO_PERFIL);
        }

        Catalogo estatusPerfil = perfilOpt.get().getEstatus();

        Perfil perfil = new Perfil();

        if (!estatusPerfil.equals(activo)) {
            return rsb029.badRequest(PERFIL_ESTATUS_INACTIVO);
        }
        
        perfil.setAutorizaciones(perfilOpt.get().getAutorizaciones());
        perfil.setClave(perfilOpt.get().getClave());
        perfil.setDescripcion(perfilOpt.get().getDescripcion());
        perfil.setEstatus(perfilOpt.get().getEstatus());
        perfil.setFechaDeModificacion(perfilOpt.get().getFechaDeModificacion());
        perfil.setId(perfilOpt.get().getId());
        perfil.setIdUsuarioModificado(perfilOpt.get().getIdUsuarioModificado());
        perfil.setNombre(perfilOpt.get().getNombre());
        perfil.setTipo(perfilOpt.get().getTipo());
        perfil = perfilRepository.save(perfil);

        LocalDateTime now =  LocalDateTime.now();
        Usuario usuario = crearUsuario(rqsv037, sesion, now, perfil);
        Salario salario = crearSalario(rqsv037, sesion, now, perfil);
        SeguroSocial seguroSocial = crearSeguroSocial(rqsv037, sesion, now);

        direccion.setClave(UUID.randomUUID());
        direccion.setEstatus(activo);
        direccion.setEntreCalle(rqsv037.getEntreCalle());
        direccion.setCodigoPostal(rqsv037.getCodigoPostal());
        direccion.setFechaDeModificacion(LocalDateTime.now());
        direccion.setIdUsuarioModificado(sesion.getUsuario().getId());
        direccion.setLatitud(rqsv037.getLatitud());
        direccion.setLongitud(rqsv037.getLongitud());
        direccion = direccionRepository.save(direccion);

        persona.setClave(UUID.randomUUID());
        persona.setIdUsuarioModificado(sesion.getUsuario().getId());
        persona.setFechaDeModificacion(LocalDateTime.now());
        persona.setEstatus(activo);
        persona.setTipo(personaOpt.get());
        List<Contacto> contactos = persona.getContactos();
        persona = personaRepository.save(persona);

        crearCuenta(usuario, sesion, now);
        
        sucursal = sucursalRepository.save(sucursal);
        if(rqsv037.getApellidoPaterno().isPresent()) {
             personaFisica.setApellidoPaterno(rqsv037.getApellidoPaterno().get());
        }
        
        if(rqsv037.getApellidoMaterno().isPresent()) {
            personaFisica.setApellidoMaterno(rqsv037.getApellidoMaterno().get());
       }
        
        personaFisica.setCurp(rqsv037.getCurp());
        personaFisica.setIdUsuarioModificado(usuario.getIdUsuarioModificado());
        personaFisica.setFechaDeModificacion(LocalDateTime.now());
        personaFisica.setEstatus(activo);
        personaFisica.setClave(UUID.randomUUID());
        personaFisica = personaFisicaRepository.save(personaFisica);
        
        trabajador.setClave(UUID.randomUUID());
        
        trabajador.setEstatus(activo);
        trabajador.setFechaDeModificacion(LocalDateTime.now());
        trabajador.setIdUsuarioModificado(sesion.getUsuario().getId());
        trabajador.setSucursal(sucursal);
        trabajador.setSalario(salario);
        trabajador.setSeguroSocial(seguroSocial);
        trabajador.setPersonaFisica(personaFisica);
        trabajador = trabajadorRepository.save(trabajador);
        
        UsuarioPersona usuarioPersona;
        usuarioPersona = crearUsuarioPersona(usuario, persona, sesion, now);
        persona.setUsuarioPersona(usuarioPersona);

        for (Contacto contacto : contactos) {
            contacto.setPersona(persona);
            contactoRepository.save(contacto);
        }

        return rsb029.ok(trabajador);
    }

    private List<Contacto> procesarContactos(
        final List<RQSV024> listaContactos) {

        List<Contacto> contactosC = new ArrayList<>();

        for (RQSV024 contactoRq : listaContactos) {

            Contacto contactoObj = new Contacto();
            contactoObj.setValor(contactoRq.getValor());
            
            Optional<Catalogo> contactoOpt;
            contactoOpt = catalogoRepository.findByClave(contactoRq.getTipo());
            if (contactoOpt.isEmpty()) {
                return null;
            }

            Catalogo tipo = contactoOpt.get();
            contactoObj.setTipo(tipo);

            if (tipo == null || !tipoContacto.contains(tipo)) {
                return null;
            }
            if (!tipo.getEstatus().equals(activo)) {
                return null;
            }

            String tipoNombre = tipo.getNombre();
            String valor = contactoObj.getValor();

            boolean invalido = switch (tipoNombre) {
                case "E-MAIL" -> isNotValid(CORREO, valor);
                case "SITIO-WEB" -> isNotValid(URL, valor);
                default -> tipoNombre.contains("TEL")
                    && isNotValid(TELEFONO, valor);
            };
            

            if (invalido) {
                return null;
            }
            contactosC.add(contactoObj);
        }

        return contactosC;
    }

    private Usuario crearUsuario(
        final RQSV037 rqsv037, final Sesion sesion, final LocalDateTime now,
        final Perfil perfil) {

        Usuario usuario = new Usuario();
        usuario.setClave(UUID.randomUUID());
        usuario.setPerfil(perfil);
        usuario.setNombre(rqsv037.getUsuario());
        usuario.setPassword(encoder(rqsv037.getPassword()));
        usuario.setFechaDeModificacion(now);
        usuario.setIdUsuarioModificado(sesion.getUsuario().getId());
        usuario.setEstatus(activo);
        usuarioRepository.save(usuario);
        return usuario;
    }

    private Cuenta crearCuenta(final Usuario usuario, final Sesion sesion,
        final LocalDateTime now) {

        Cuenta cuenta = new Cuenta();
        cuenta.setClave(UUID.randomUUID());
        cuenta.setSaldo(0);
        cuenta.setFechaDeModificacion(LocalDateTime.now());
        cuenta.setIdUsuarioModificado(sesion.getUsuario().getId());
        cuenta.setEstatus(activo);
        cuenta.setUsuario(usuario);
        return cuentaRepository.save(cuenta);
    }

    private Salario crearSalario(final RQSV037 rqsv037, final Sesion sesion,
        final LocalDateTime now, final Perfil perfil) {

        Salario salario = new Salario();
        UUID claveBanco = rqsv037.getBanco();
        UUID claveSucursal = rqsv037.getSucursal();
        Optional<Banco> bancoOpt = bancoRepository.findByClave(claveBanco);
        if (bancoOpt.isEmpty()) {
            return null;
        }
        salario.setBanco(bancoOpt.get());
        if (!salario.getBanco().getEstatus().equals(activo)) {
            return null;
        }

        Optional<Sucursal> sucursalOpt;
        sucursalOpt = sucursalRepository.findByClave(claveSucursal);
        if (sucursalOpt.isEmpty()) {
            return null;
        }

        String claveInterbancaria = rqsv037.getClaveInterBancaria();
        salario.setSucursal(sucursalOpt.get().getNombre());
        salario.setClaveInterBancaria(claveInterbancaria);

        UUID tipoPago =  rqsv037.getTipoPago();
        Optional<Catalogo> catalogoPagoOpt;
        catalogoPagoOpt = catalogoRepository.findByClave(tipoPago);
        if (catalogoPagoOpt.isEmpty()) {
            return null;
        }
        salario.setTipoPago(catalogoPagoOpt.get());

        Optional<Catalogo> periodoOpt;
        UUID tipoPeriodo =  rqsv037.getTipoPeriodo();
        periodoOpt = catalogoRepository.findByClave(tipoPeriodo);
        if (periodoOpt.isEmpty()) {
            return null;
        }
        salario.setTipoPeriodo(periodoOpt.get());
        salario.setClave(UUID.randomUUID());
        salario.setEstatus(activo);
        salario.setFechaDeModificacion(LocalDateTime.now());
        salario.setIdUsuarioModificado(sesion.getUsuario().getId());
        return salarioRepository.save(salario);
    }

    private UsuarioPersona crearUsuarioPersona(final Usuario usuario,
        final Persona persona, final Sesion sesion, final LocalDateTime now) {

        UsuarioPersona usuarioPersona = new UsuarioPersona();
        usuarioPersona.setUsuario(usuario);
        usuarioPersona.setPersona(persona);
        usuarioPersona.setFechaDeModificacion(now);
        usuarioPersona.setIdUsuarioModificado(sesion.getUsuario().getId());
        usuarioPersona.setEstatus(activo);
        usuarioPersona.setClave(UUID.randomUUID());
        return usuarioPersonaRepository.save(usuarioPersona);
    }
    private SeguroSocial crearSeguroSocial(final RQSV037 rqsv037,
        final Sesion sesion, final LocalDateTime now) {

        SeguroSocial seguroSocial = new SeguroSocial();
        seguroSocial.setCiudad(rqsv037.getCiudad());
        seguroSocial.setAlta(LocalDate.now());

        Optional<LocalDate> bajaOpt = rqsv037.getBaja();
        if (bajaOpt.isPresent()) {
            Optional<String> obsBaja = rqsv037.getObservacionesBaja();
            seguroSocial.setObservacionesBaja(obsBaja.get());
        }

        UUID claveEntidadSeguro = rqsv037.getEntidadFederativa();
        Optional<Estado> estadoOpt;
        estadoOpt = estadoRepository.findByClave(claveEntidadSeguro);
        if (estadoOpt.isEmpty()) {
            return null;
        }
        seguroSocial.setEntidadFederativa(estadoOpt.get());
        if (!seguroSocial.getEntidadFederativa().getEstatus().equals(activo)) {
            return null;
        }

        seguroSocial.setClave(UUID.randomUUID());
        seguroSocial.setEstatus(activo);
        seguroSocial.setFechaDeModificacion(now);
        seguroSocial.setIdUsuarioModificado(sesion.getUsuario().getId());
        seguroSocial.setNumero(rqsv037.getNss());

        return seguroSocialRepository.save(seguroSocial);
    }

    private boolean isNotValid(final String regex, final String valor) {
        return !Pattern.matches(regex, valor);
    }

    /**
     * Codifica el texto proporcionado utilizando una estrategia definida por la
     * implementación concreta de esta clase.
     *
     * @param texto El texto que se desea codificar.
     * @return Una cadena que representa el texto codificado.
     */
    public abstract String encoder(String texto);

}
