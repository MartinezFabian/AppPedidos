package com.company;

public class Pedido {
    String pedido;
    String cantidad;
    String nombreCliente;

    public Pedido(String pedido, String cantidad, String nombreCliente) {
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return "\nPedido: " + pedido + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "Para el cliente: " + nombreCliente + "\n";
    }
}
