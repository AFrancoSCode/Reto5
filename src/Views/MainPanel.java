
package Views;

import Model.BDcontext;
import Model.ProductoClass;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainPanel extends JPanel{
        
    private JLabel titulo;
    private BDcontext context = new BDcontext();
    private JTextField Codigo;
    private JTextField Nombre;
    private JTextField ValorCompra;
    private JTextField ValorVenta;
    private JTextField Cantidad;
    private JComboBox Unidad;
    private JComboBox Categoria;
    private VentanaPrincipal vista;
    private Object[] listaUnidad;
    private Object[] listaCategoria;
    private Tabla tablaNueva = new Tabla(context.RefreshProducto());
    
    public MainPanel() throws SQLException{
        setLayout(null);
        setBackground(Color.WHITE);
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
        
        listaUnidad = context.LlenarUnidad();
        Unidad = new JComboBox(listaUnidad);
        Unidad.setBounds(445, 115, 100, 20);
        add(Unidad);
        
        /* Campos de Detalle de producto */
        
        JLabel labelDetalle = new JLabel("Categoria:");
        labelDetalle.setFont(new Font("Arial", Font.PLAIN, 15));
        labelDetalle.setForeground(Color.decode("#006BFF"));
        labelDetalle.setBounds(20,120,120,50);
        add(labelDetalle);
        
        listaCategoria = context.LlenarCategoria();
        Categoria = new JComboBox(listaCategoria);
        Categoria.setBounds(100, 145, 100, 20);
        add(Categoria);
        
        /* Label de Mensajes */
        
        JLabel mensajes = new JLabel();
        mensajes.setFont(new Font("Arial", Font.BOLD, 15));
        mensajes.setForeground(Color.decode("#FFBD00"));
        mensajes.setBounds(20,210,500,50);
        add(mensajes);        
                
        /* Declaración y Evento de boton Crear */
        
        JTable tabla = new JTable();
        tabla.setModel(context.RefreshProducto());
        JScrollPane tableContainer = new JScrollPane(tabla);
        
        JButton botonCrear = new JButton("Crear");
        botonCrear.setBounds(20, 200, 100, 20);
        botonCrear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    if(validacion()){
                        ProductoClass producto = new ProductoClass();
                        producto.Codigo = Codigo.getText();
                        producto.Nombre = Nombre.getText();
                        producto.ValorCompra = ValorCompra.getText();
                        producto.ValorVenta = ValorVenta.getText();
                        producto.Cantidad = Cantidad.getText();
                        producto.Unidades = (String) Unidad.getSelectedItem();
                        producto.Categoria = (String) Categoria.getSelectedItem();
                        mensajes.setText(context.CreateProducto(producto));
                        tablaNueva.setModel(context.RefreshProducto());
                        tablaNueva.settingModel();
                        limpiar();
                    }else{
                        mensajes.setText("Por favor llena todos los campos");
                    }
                }catch(Exception ex){
                    System.out.println(ex);
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
                try{
                    if(!Codigo.getText().equals("")){
                        int resp = JOptionPane.showConfirmDialog(null, "Se va a eliminar un registro \n"+"¿Esta seguro?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if(resp == 0){
                            mensajes.setText(context.DeleteProducto(Codigo.getText()));
                            tablaNueva.setModel(context.RefreshProducto());
                            tablaNueva.settingModel();
                            limpiar();
                        }
                    }else{
                        mensajes.setText("Por favor llene el campo CODIGO");
                    }
                }catch(Exception ex){
                    System.out.println(ex);
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
                try{
                    ProductoClass producto = new ProductoClass();
                    producto = context.QueryProducto(Codigo.getText(), producto);
                    Codigo.setText(producto.Codigo);
                    Nombre.setText(producto.Nombre);
                    ValorCompra.setText(producto.ValorCompra);
                    ValorVenta.setText(producto.ValorVenta);
                    Cantidad.setText(producto.Cantidad);
                    Unidad.setSelectedIndex(Arrays.asList(listaUnidad).indexOf(producto.Unidades));
                    Categoria.setSelectedIndex(Arrays.asList(listaCategoria).indexOf(producto.Categoria));
                    mensajes.setText("Consulta exitosa");
                }catch(Exception ex){
                    System.out.println(ex);
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
                try{
                    if(validacion()){
                        ProductoClass producto = new ProductoClass();
                        producto.Codigo = Codigo.getText();
                        producto.Nombre = Nombre.getText();
                        producto.ValorCompra = ValorCompra.getText();
                        producto.ValorVenta = ValorVenta.getText();
                        producto.Cantidad = Cantidad.getText();
                        producto.Unidades = (String) Unidad.getSelectedItem();
                        producto.Categoria = (String) Categoria.getSelectedItem();
                        mensajes.setText(context.UpdateProducto(producto));
                        tablaNueva.setModel(context.RefreshProducto());
                        tablaNueva.settingModel();
                        limpiar();
                    }else{
                        mensajes.setText("Por favor llena todos los campos");
                    }
                }catch(Exception ex){
                    System.out.println(ex);
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
                try{
                    tablaNueva.setModel(context.RefreshProducto());
                    tablaNueva.settingModel();
                    limpiar();
                    mensajes.setText("");
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });
        add(botonRefrescar);
        
        JButton botonListar = new JButton("Listar");
        botonListar.setBounds(520, 200, 100, 20);
        botonListar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    tablaNueva.setVisible(true);
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });
        add(botonListar);
    }
    
    public boolean validacion(){
        /* Este metodo sirve para saber si todos los campos estan llenos */
        String unidad = (String) Unidad.getSelectedItem();
        String categoria = (String) Categoria.getSelectedItem();        
        if(Codigo.getText().equals("")||Nombre.getText().equals("")||ValorCompra.getText().equals("")||ValorVenta.getText().equals("")||Cantidad.getText().equals("")||unidad.equals("Seleccionar")||categoria.equals("Seleccionar")){
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
        Unidad.setSelectedIndex(0);
        Categoria.setSelectedIndex(0);
    }

    public void setCodigo(String codigo){
        this.Codigo.setText(codigo);
    }
}

class Tabla extends JFrame{
    
    private MainPanel mainPanel;
    private DefaultTableModel model;
    private JTable tabla;
    
    public Tabla(DefaultTableModel modelo) throws SQLException{
        setTitle("Ferreteria");
        setSize(700,510);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#CADCF5"));
        getContentPane().setLayout(null);        
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(false);
        
        this.model = modelo;
        tabla = new JTable();
        this.settingModel();
        JPanel panel = new JPanel();
        panel.setBounds(20,20,640,430);
        panel.setBackground(Color.WHITE);
        JScrollPane tableContainer = new JScrollPane(tabla);
        panel.add(tableContainer);
        add(panel);
    }
    
    public void settingModel(){
        tabla.setModel(model);
    }
    
    public void setModel(DefaultTableModel modelo){
        this.model = modelo;
    }
    
    public JTable getTable(){
        return this.tabla;
    }
}