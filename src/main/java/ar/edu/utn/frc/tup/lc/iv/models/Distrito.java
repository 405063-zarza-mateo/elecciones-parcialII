package ar.edu.utn.frc.tup.lc.iv.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "distritos")
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "secciones")
    @OneToMany(cascade = CascadeType.ALL)
    List<Seccion> secciones;

    @Column(name = "resultados")
    @OneToMany(cascade = CascadeType.ALL)
    List<Resultado> resultados;

    @Column(name = "cargos")
    @OneToMany(cascade = CascadeType.ALL)
    List<Cargo> cargos;
}
