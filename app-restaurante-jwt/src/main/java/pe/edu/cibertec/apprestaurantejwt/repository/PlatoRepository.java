package pe.edu.cibertec.apprestaurantejwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.apprestaurantejwt.model.Plato;

public interface PlatoRepository extends JpaRepository<Plato, Integer> {
}
