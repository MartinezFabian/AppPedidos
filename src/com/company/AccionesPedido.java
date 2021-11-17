package com.company;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;


public class AccionesPedido {
    ArrayList<Pedido> pedidoCompleto = new ArrayList<>();

    /**METODO PARA ANADIR EL TEXTO INGRESADO EN LA INTERFAZ AL ARCHIVO .txt**/
    public void agregarPedido(String pedido, String cantidad, String nombreClientetxt) {
        Pedido objPedido = new Pedido(pedido,cantidad, nombreClientetxt); // Objeto para documentar en el .txt

        this.pedidoCompleto.add(objPedido);

        // Verificar que no ingrese en blanco
        if(!nombreClientetxt.equals("") && !pedido.equals("")) {
            try {

                //Flujo de datos
                FileWriter salida = new FileWriter("Pedido.txt", true);

                //Creo el buffer y le paso como argumento el flujo de datos
                BufferedWriter buffer = new BufferedWriter(salida);

                // Pide el texto del objeto a traves de un toString, para imprimirlo en el txt
                buffer.write(pedidoCompleto.toString().replace("[", "").replace("]", ""));


                // salta una linea
                buffer.newLine();

                //flush() vacía el flujo de salida y fuerza a que se escriban los bytes de salida almacenados en buffer.
                buffer.flush();

                salida.close(); //Cierro el flujo de datos


            } catch (IOException e) {
                System.out.println("Error E/S:" + e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.",
                    "Error en el ingreso de datos.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**METODO QUE "ELIMINA" EL ARCHIVO EXISTENTE Y LO SOBRE-ESCRIBE EN BLANCO **/
    public void eliminarPedido(){
        try{
            //Flujo de datos
            FileWriter salida = new FileWriter("Pedido.txt");

            //Creo el buffer y le paso como argumento el flujo de datos
            BufferedWriter buffer = new BufferedWriter(salida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
