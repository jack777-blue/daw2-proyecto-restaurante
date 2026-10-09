package pe.edu.cibertec.apprestaurantejwt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apprestaurantejwt.dto.UsuarioRegistroDto;
import pe.edu.cibertec.apprestaurantejwt.model.Rol;
import pe.edu.cibertec.apprestaurantejwt.model.Usuario;
import pe.edu.cibertec.apprestaurantejwt.repository.RolRepository;
import pe.edu.cibertec.apprestaurantejwt.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    public Usuario getUsuarioByNomusuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Transactional
    public Usuario crearUsuarioConRoles(UsuarioRegistroDto dto) {
        if (usuarioRepository.existsByNomusuario(dto.getNomusuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso.");
        }
        Set<Rol> rolesAsignados = new HashSet<>();
        if (dto.getRolesIds() != null && !dto.getRolesIds().isEmpty()) {
            List<Rol> encontrados = rolRepository.findAllById(dto.getRolesIds());
            if (encontrados.size() != dto.getRolesIds().size()) {
                throw new RuntimeException("Uno o más roles no existen en la base de datos.");
            }
            rolesAsignados.addAll(encontrados);
        }
        Usuario nuevo = new Usuario();
        nuevo.setNomusuario(dto.getNomusuario());
        nuevo.setEmail(dto.getEmail());
        nuevo.setPassword(passwordEncoder.encode(dto.getPassword())); // BCrypt
        nuevo.setNombres(dto.getNombres());
        nuevo.setApellidos(dto.getApellidos());
        nuevo.setActivo(true);
        nuevo.setRoles(rolesAsignados);
        return usuarioRepository.save(nuevo);
    }
}
