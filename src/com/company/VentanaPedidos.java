package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class VentanaPedidos extends JFrame {
    private JPanel panelPrincipal;
    private JLabel bienvenido;
    private JLabel orden;
    private JTextField barraPedido;
    private JButton agregar;
    private JTextPane textoPedido;
    private JButton pedidoNuevo;
    private JLabel cantidad;
    private JSpinner candidadUsuario;
    private JTextField txtNombreCliente;

    public VentanaPedidos(){

        setSize(640,720); //ancho y largodel lienzo
        setVisible(true); //es visible
        setTitle("Aplicación de pedidos."); //Nombre barra superior
        setContentPane(panelPrincipal); // declara el panel principal
        setResizable(false); //evita que el usuario cambie el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //la X de la esquina derecha finaliza el programa
        textoPedido.setText("Aquí podrás ver tus pedidos."); // para que aparezca por default este mensaje en la barra display
        SpinnerNumberModel snm = new SpinnerNumberModel(); // el spinner del contador de cantidad
        snm.setMaximum(10); // declaro un maximo al contador del spinner
        snm.setMinimum(1); // un minimo al contador del spinner
        snm.setValue(1); // y un valor por defecto del spinner
        candidadUsuario.setModel(snm); // aplico los 3 cambios de arriba al spinner de contador

        /**ESCUCHADOR DEL BOTON AGREGAR**/
        agregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //creo un objeto de tipo AccionesPedido para poder comunicarme con los metodos del mismo
                AccionesPedido pedidoInterfaz = new AccionesPedido();

                String pedidotxt = barraPedido.getText(); // pido el contenido de la barra de texto y se la asigno a un String
                String nombreClientetxt = txtNombreCliente.getText(); // pido el contenido de txtNombreCliente y se la asigno a un String
                String cantidad = candidadUsuario.getValue().toString();// pido el valor del Spinner


                pedidoInterfaz.agregarPedido(pedidotxt,cantidad, nombreClientetxt); // le asigno los datos pedidos al objeto de tipo AccionesPedido

                try{
                    FileReader entrada = new FileReader("Pedido.txt"); //Flujo de datos
                    BufferedReader buffer = new BufferedReader(entrada); //Creo el buffer y le paso como argumento el flujo de datos


                    String texto = ""; //por defecto esta en blanco y le quito el null por defecto
                    String linea = buffer.readLine(); //leo una linea del archivo

                    // Mientras que no llegue al final del texto contenido en el .txt
                    while (linea != null){
                        texto = texto + linea + "\n"; // le sumo la ultima linea leida por el txt

                        //Leo la siguiente linea del archivo
                        linea = buffer.readLine();
                    }

                    textoPedido.setText(texto);//Muestro el contenido del txt en la interfaz grafica
                    entrada.close(); //Cierro el flujo de datos


                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Error archivo no encontrado");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        /**SCUCHADOR DEL BOTON PEDIRNUEVO QUE REINICIA EL ARCHIVO TXT**/

        pedidoNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccionesPedido pedidoInterfaz = new AccionesPedido(); // objeto del tipo AccionesPedido para llamar a sus metodos

                pedidoInterfaz.eliminarPedido(); //llama a el metodo eliminar pedido y 'elimina' el txt
                JOptionPane.showMessageDialog(null, "Se inició un nuevo pedido.");
                textoPedido.setText("Aquí podrás ver tus pedidos."); // le da el valor por defecto, como cuando inicia el programa
            }
        });


    }

}
