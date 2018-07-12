package edu.uade.seminario.tpo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "medicamentos")
public class MedicamentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicamento_id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "stock")
    private int stock;
    @Column(name = "stock_compra")
    private int stockCompra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockCompra() {
        return stockCompra;
    }

    public void setStockCompra(int stockCompra) {
        this.stockCompra = stockCompra;
    }
}
