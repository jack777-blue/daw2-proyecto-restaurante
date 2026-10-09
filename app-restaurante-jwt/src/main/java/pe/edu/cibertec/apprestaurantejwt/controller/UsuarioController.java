package pe.edu.cibertec.apprestaurantejwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.apprestaurantejwt.dto.UsuarioRegistroDto;
import pe.edu.cibertec.apprestaurantejwt.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRegistroDto dto) {
        try {
            usuarioService.crearUsuarioConRoles(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario " + dto.getNomusuario() + " creado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
