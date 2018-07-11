package edu.uade.seminario.tpo.model;

public class Medicamento {

    private Long id;
    private String nombre;
    private int stock;
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
