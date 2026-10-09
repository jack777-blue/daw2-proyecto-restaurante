package pe.edu.cibertec.apprestaurantejwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.apprestaurantejwt.model.Plato;
import pe.edu.cibertec.apprestaurantejwt.model.Rol;
import pe.edu.cibertec.apprestaurantejwt.model.Usuario;
import pe.edu.cibertec.apprestaurantejwt.repository.PlatoRepository;
import pe.edu.cibertec.apprestaurantejwt.repository.RolRepository;
import pe.edu.cibertec.apprestaurantejwt.repository.UsuarioRepository;

import java.math.BigDecimal;
import java.util.Set;

/** Crea roles, usuarios de prueba (password con BCrypt) y platos si no existen. */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlatoRepository platoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Rol admin = obtenerRol("ADMIN");
        Rol mesero = obtenerRol("MESERO");

        crearUsuario("admin", "admin@restaurante.com", "admin123", "Administrador", "Sistema", admin);
        crearUsuario("mesero", "mesero@restaurante.com", "mesero123", "Mesero", "Demo", mesero);

        if (platoRepository.count() == 0) {
            guardarPlato("Lomo saltado", "Lomo fino con cebolla, tomate y papas fritas", "32.50", "Fondos");
            guardarPlato("Ceviche clásico", "Pescado fresco, limón, ají y camote", "35.00", "Entradas");
            guardarPlato("Chicha morada", "Jarra de 1 litro", "12.00", "Bebidas");
        }
    }

    private Rol obtenerRol(String nombre) {
        Rol rol = rolRepository.findByNomrol(nombre);
        if (rol == null) {
            rol = new Rol();
            rol.setNomrol(nombre);
            rol = rolRepository.save(rol);
        }
        return rol;
    }

    private void crearUsuario(String user, String email, String pass,
                              String nombres, String apellidos, Rol rol) {
        if (usuarioRepository.existsByNomusuario(user)) return;
        Usuario u = new Usuario();
        u.setNomusuario(user);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(pass));
        u.setNombres(nombres);
        u.setApellidos(apellidos);
        u.setActivo(true);
        u.setRoles(Set.of(rol));
        usuarioRepository.save(u);
    }

    private void guardarPlato(String nombre, String desc, String precio, String categoria) {
        Plato p = new Plato();
        p.setNombre(nombre);
        p.setDescripcion(desc);
        p.setPrecio(new BigDecimal(precio));
        p.setCategoria(categoria);
        p.setDisponible(true);
        platoRepository.save(p);
    }
}
