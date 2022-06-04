
package View;

import Model.ProductoClass;
import Model.BDcontext;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ProductosPanel extends JPanel{
    
    private JLabel titulo;
    private JButton botonVolver;
    private BDcontext context = new BDcontext();
    private JTextField Codigo;
    private JTextField Nombre;
    private JTextField ValorCompra;
    private JTextField ValorVenta;
    private JTextField Cantidad;
    private JTextField Unidad;
    private JTextField Detalle;
    private VentanaPrincipal vista;
    
    public ProductosPanel() throws SQLException{
        setLayout(new BorderLayout());
        setVisible(false);
        inicializarComponentes();
    }
    
    public void inicializarComponentes() throws SQLException{
        
        /* Titulo */
        
        titulo = new JLabel("PANEL DE PRODUCTOS");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.decode("#006BFF"));
        titulo.setBounds(235,20,400,80);
        add(titulo);
        
        /* Boton de regresar */
        
        botonVolver = new JButton("Volver");
        botonVolver.setActionCommand("VOLVERPRODUCTOS");
        botonVolver.setFont(new Font("Arial", Font.BOLD, 13));
        botonVolver.setForeground(Color.white);
        botonVolver.setBackground(Color.BLUE);
        botonVolver.setBounds(20, 20, 75, 20);
        add(botonVolver);
        
        /* Campos de Codigo de producto */
        
        JLabel labelCodigo = new JLabel("Codigo:");
        labelCodigo.setFont(new Font("Arial", Font.PLAIN, 15));
        labelCodigo.setForeground(Color.decode("#006BFF"));
        labelCodigo.setBounds(20,75,100,50);
        add(labelCodigo);
        
        Codigo = new JTextField();
        Codigo.setBounds(75, 90, 100, 20);
        add(Codigo);
        
        /* Campos de Nombre de producto */
        
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        labelNombre.setForeground(Color.decode("#006BFF"));
        labelNombre.setBounds(180,75,100,50);
        add(labelNombre);
        
        Nombre = new JTextField();
        Nombre.setBounds(240, 90, 100, 20);
        add(Nombre);
        
        /* Campos de ValorCompra de producto*/
        
        JLabel labelValorCompra = new JLabel("Valor de compra:");
        labelValorCompra.setFont(new Font("Arial", Font.PLAIN, 15));
        labelValorCompra.setForeground(Color.decode("#006BFF"));
        labelValorCompra.setBounds(345,75,120,50);
        add(labelValorCompra);
        
        ValorCompra = new JTextField();
        ValorCompra.setBounds(460, 90, 100, 20);
        add(ValorCompra);
        
        /* Campos de ValorVenta de producto */
        
        JLabel labelValorVenta = new JLabel("Valor de venta:");
        labelValorVenta.setFont(new Font("Arial", Font.PLAIN, 15));
        labelValorVenta.setForeground(Color.decode("#006BFF"));
        labelValorVenta.setBounds(20,100,120,50);
        add(labelValorVenta);
        
        ValorVenta = new JTextField();
        ValorVenta.setBounds(120, 115, 100, 20);
        add(ValorVenta);
        
        /* Campos de Cantidad de producto*/
        
        JLabel labelCantidad = new JLabel("Cantidad:");
        labelCantidad.setFont(new Font("Arial", Font.PLAIN, 15));
        labelCantidad.setForeground(Color.decode("#006BFF"));
        labelCantidad.setBounds(225,100,120,50);
        add(labelCantidad);
        
        Cantidad = new JTextField();
        Cantidad.setBounds(290, 115, 100, 20);
        add(Cantidad);
        
        /* Campos de Unidad de producto */
        
        JLabel labelUnidad = new JLabel("Unidad:");
        labelUnidad.setFont(new Font("Arial", Font.PLAIN, 15));
        labelUnidad.setForeground(Color.decode("#006BFF"));
        labelUnidad.setBounds(393,100,120,50);
        add(labelUnidad);
        
        Unidad = new JTextField();
        Unidad.setBounds(445, 115, 100, 20);
        add(Unidad);
        
        /* Campos de Detalle de producto */
        
        JLabel labelDetalle = new JLabel("Detalle:");
        labelDetalle.setFont(new Font("Arial", Font.PLAIN, 15));
        labelDetalle.setForeground(Color.decode("#006BFF"));
        labelDetalle.setBounds(20,120,120,50);
        add(labelDetalle);
        
        Detalle = new JTextField();
        Detalle.setBounds(100, 145, 100, 20);
        add(Detalle);
        
        /* Label de Mensajes */
        
        JLabel mensajes = new JLabel();
        mensajes.setFont(new Font("Arial", Font.BOLD, 15));
        mensajes.setForeground(Color.decode("#FFBD00"));
        mensajes.setBounds(20,210,500,50);
        add(mensajes);
        
        /* Declaración de la tabla */

        JTable tabla = new JTable();
        tabla.setModel(context.RefreshProducto());
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int seleccionar = tabla.rowAtPoint(evt.getPoint());
                Codigo.setText(String.valueOf(tabla.getValueAt(seleccionar, 0)));
                Nombre.setText(String.valueOf(tabla.getValueAt(seleccionar, 1)));
                ValorCompra.setText(String.valueOf(tabla.getValueAt(seleccionar, 2)));
                ValorVenta.setText(String.valueOf(tabla.getValueAt(seleccionar, 3)));
                Cantidad.setText(String.valueOf(tabla.getValueAt(seleccionar, 4)));
                Unidad.setText(String.valueOf(tabla.getValueAt(seleccionar, 5)));
                Detalle.setText(String.valueOf(tabla.getValueAt(seleccionar, 6)));
            }
        });
        JScrollPane tableContainer = new JScrollPane(tabla);
        
        /* Declaración y Evento de boton Crear */
        
        JButton botonCrear = new JButton("Crear");
        botonCrear.setBounds(20, 200, 100, 20);
        botonCrear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(validacion()){
                        ProductoClass producto = new ProductoClass();
                        producto.Codigo = Codigo.getText();
                        producto.Nombre = Nombre.getText();
                        producto.ValorDeCompra = ValorCompra.getText();
                        producto.ValorDeVenta = ValorVenta.getText();
                        producto.Cantidad = Cantidad.getText();
                        producto.Unidades = Unidad.getText();
                        producto.Detalle = Detalle.getText();
                        mensajes.setText(context.CreateProducto(producto));
                        tabla.setModel(context.RefreshProducto());
                        limpiar();
                    }else{
                        mensajes.setText("Por favor llena todos los campos");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductosPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(botonCrear);
        
        /* Declaración y Evento de boton Eliminar */
        
        JButton botonBorrar = new JButton("Eliminar");
        botonBorrar.setBounds(120, 200, 100, 20);
        botonBorrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(validacion()){
                        mensajes.setText(context.DeleteProducto(Codigo.getText()));
                        tabla.setModel(context.RefreshProducto());
                        limpiar();
                    }else{
                        mensajes.setText("Por favor llena todos los campos");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductosPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(botonBorrar);
        
        /* Declaración y Evento de boton Buscar */
        
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(220, 200, 100, 20);
        botonBuscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    mensajes.setText(context.QueryProducto(Codigo.getText(),tabla));
                } catch (SQLException ex) {
                    Logger.getLogger(ProductosPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(botonBuscar);
        
        /* Declaración y Evento de boton Actualizar */
        
        JButton botonActualizar = new JButton("Actualizar");
        botonActualizar.setBounds(320, 200, 100, 20);
        botonActualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(validacion()){
                        ProductoClass producto = new ProductoClass();
                        producto.Codigo = Codigo.getText();
                        producto.Nombre = Nombre.getText();
                        producto.ValorDeCompra = ValorCompra.getText();
                        producto.ValorDeVenta = ValorVenta.getText();
                        producto.Cantidad = Cantidad.getText();
                        producto.Unidades = Unidad.getText();
                        producto.Detalle = Detalle.getText();
                        mensajes.setText(context.UpdateProducto(producto));
                        tabla.setModel(context.RefreshProducto());
                        limpiar();
                    }else{
                        mensajes.setText("Por favor llena todos los campos");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductosPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(botonActualizar);
        
        /* Declaración y Evento de boton Refrescar */
        
        JButton botonRefrescar = new JButton("Refrescar");
        botonRefrescar.setBounds(420, 200, 100, 20);
        botonRefrescar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    tabla.setModel(context.RefreshProducto());
                    limpiar();
                    mensajes.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(ProductosPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(botonRefrescar);
        
        /* Aquí recien se añade la tabla*/        
        add(tableContainer);        
    }
    
    public boolean validacion(){
        /* Este metodo sirve para saber si todos los campos estan llenos */
        if(Codigo.getText().equals("")||Nombre.getText().equals("")||ValorCompra.getText().equals("")||ValorVenta.getText().equals("")||Cantidad.getText().equals("")||Unidad.getText().equals("")||Detalle.getText().equals("")){
            return false;
        }
        return true;
    }
    
    public void limpiar(){
        /* Este metodo sirve para limpiar los campos de texto y dejarlos vacios*/
        Codigo.setText("");
        Nombre.setText("");
        ValorCompra.setText("");
        ValorVenta.setText("");
        Cantidad.setText("");
        Unidad.setText("");
        Detalle.setText("");
    }
    
    /* Metodos de acceso para el boton de Volver */
    
    public JButton getBotonVolver() {
        return botonVolver;
    }

    public void setBotonVolver(JButton botonVolver) {
        this.botonVolver = botonVolver;
    }
}