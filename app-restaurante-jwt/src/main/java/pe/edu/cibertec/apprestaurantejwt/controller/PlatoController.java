package pe.edu.cibertec.apprestaurantejwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.apprestaurantejwt.model.Plato;
import pe.edu.cibertec.apprestaurantejwt.service.PlatoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/platos")
@RequiredArgsConstructor
public class PlatoController {

    private final PlatoService platoService;

    // GET /api/v1/platos
    @PreAuthorize("hasAnyRole('ADMIN','MESERO')")
    @GetMapping
    public List<Plato> listar() {
        return platoService.listar();
    }

    // GET /api/v1/platos/{id}
    @PreAuthorize("hasAnyRole('ADMIN','MESERO')")
    @GetMapping("/{id}")
    public ResponseEntity<Plato> buscar(@PathVariable Integer id) {
        return platoService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/platos
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Plato> crear(@RequestBody Plato plato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platoService.guardar(plato));
    }

    // PUT /api/v1/platos/{id}
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Plato> actualizar(@PathVariable Integer id, @RequestBody Plato plato) {
        return platoService.actualizar(id, plato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/platos/{id}
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return platoService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
