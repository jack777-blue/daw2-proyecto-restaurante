package pe.edu.cibertec.apprestaurantejwt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "plato")
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idplato;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;
    private boolean disponible;
}
