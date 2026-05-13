package cl.duocuc.EvaRetail.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table(name = "producto")


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
//Tabla producto
    public class Producto {
        //Coumna ID
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_producto", precision = 10)
        private Long id;


        //columna nombre
        @Column(name = "nombre", nullable = false, length = 100)
        private String nombre;

        //columna precio

        @Column(nullable = false)
        private Double precio;

        //columna stock

        @Column(nullable = false)
        private Integer stock;

    }


