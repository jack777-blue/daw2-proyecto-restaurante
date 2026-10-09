package pe.edu.cibertec.apprestaurantejwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apprestaurantejwt.model.Plato;
import pe.edu.cibertec.apprestaurantejwt.repository.PlatoRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlatoService {

    private final PlatoRepository platoRepository;

    public List<Plato> listar() {
        return platoRepository.findAll();
    }

    public Optional<Plato> buscar(Integer id) {
        return platoRepository.findById(id);
    }

    public Plato guardar(Plato plato) {
        plato.setIdplato(null);
        return platoRepository.save(plato);
    }

    public Optional<Plato> actualizar(Integer id, Plato datos) {
        return platoRepository.findById(id).map(p -> {
            p.setNombre(datos.getNombre());
            p.setDescripcion(datos.getDescripcion());
            p.setPrecio(datos.getPrecio());
            p.setCategoria(datos.getCategoria());
            p.setDisponible(datos.isDisponible());
            return platoRepository.save(p);
        });
    }

    public boolean eliminar(Integer id) {
        if (!platoRepository.existsById(id)) {
            return false;
        }
        platoRepository.deleteById(id);
        return true;
    }
}
