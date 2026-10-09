package pe.edu.cibertec.apprestaurantejwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.apprestaurantejwt.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNomusuario(String nomusuario);
    boolean existsByNomusuario(String nomusuario);
    boolean existsByEmail(String email);
}
