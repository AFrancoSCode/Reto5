
package View;

import Model.BDcontext;
import Model.ConfigClass;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel{
    
    private JButton botonCategoria;
    private JButton botonConfig;
    private JButton botonProducto;
    private JButton botonTipoUnidad;
    private JButton botonUnidad;
    private JLabel titulo;
    private JLabel nombreTitulo;
    private JLabel propietarioTitulo;
    private JLabel direccionTitulo;
    private JLabel telefonoTitulo;
    private JLabel nombre;
    private JLabel propietario;
    private JLabel direccion;
    private JLabel telefono;
    
    public MainPanel() throws SQLException{
        setLayout(null);
        setVisible(true);
        inicializarComponentes();
    }
    
    public void inicializarComponentes() throws SQLException{
        
        BDcontext context = new BDcontext();
        ConfigClass config = context.SelectConfig();
        
        titulo = new JLabel("APLICACION FERRETERIA");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.decode("#006BFF"));
        titulo.setBounds(235,10,400,80);
        add(titulo);
        
        nombreTitulo = new JLabel("Nombre ferreteria:");
        nombreTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        nombreTitulo.setForeground(Color.decode("#000000"));
        nombreTitulo.setBounds(345,100,400,80);
        add(nombreTitulo);
        nombre = new JLabel(config.NombreFerreteria);
        nombre.setFont(new Font("Arial", Font.PLAIN, 15));
        nombre.setForeground(Color.decode("#000000"));
        nombre.setBounds(490,100,400,80);
        add(nombre);
        
        propietarioTitulo = new JLabel("Propietario:");
        propietarioTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        propietarioTitulo.setForeground(Color.decode("#000000"));
        propietarioTitulo.setBounds(345,187,400,80);
        add(propietarioTitulo);
        propietario = new JLabel(config.Propietario);
        propietario.setFont(new Font("Arial", Font.PLAIN, 15));
        propietario.setForeground(Color.decode("#000000"));
        propietario.setBounds(445,187,400,80);
        add(propietario);
        
        direccionTitulo = new JLabel("Dirección:");
        direccionTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        direccionTitulo.setForeground(Color.decode("#000000"));
        direccionTitulo.setBounds(345,274,400,80);
        add(direccionTitulo);
        direccion = new JLabel(config.Direccion);
        direccion.setFont(new Font("Arial", Font.PLAIN, 15));
        direccion.setForeground(Color.decode("#000000"));
        direccion.setBounds(435,274,400,80);
        add(direccion);
        
        telefonoTitulo = new JLabel("Telefono:");
        telefonoTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        telefonoTitulo.setForeground(Color.decode("#000000"));
        telefonoTitulo.setBounds(345,361,400,80);
        add(telefonoTitulo);
        
        telefono = new JLabel(config.Telefono);
        telefono.setFont(new Font("Arial", Font.PLAIN, 15));
        telefono.setForeground(Color.decode("#000000"));
        telefono.setBounds(434,361,400,80);
        add(telefono);

        botonProducto = new JButton("Productos");
        botonProducto.setActionCommand("PRODUCTO");
        botonProducto.setBounds(60, 100, 130, 40);
        botonProducto.setBackground(Color.decode("#006BFF"));
        botonProducto.setForeground(Color.WHITE);
        add(botonProducto);
        
        botonUnidad = new JButton("Unidades");
        botonUnidad.setActionCommand("UNIDAD");
        botonUnidad.setBounds(60, 180, 130, 40);
        botonUnidad.setBackground(Color.decode("#006BFF"));
        botonUnidad.setForeground(Color.WHITE);
        add(botonUnidad);
        
        botonTipoUnidad = new JButton("Tipo de unidades");
        botonTipoUnidad.setActionCommand("TIPOUNIDAD");
        botonTipoUnidad.setBounds(60, 260, 130, 40);
        botonTipoUnidad.setBackground(Color.decode("#006BFF"));
        botonTipoUnidad.setForeground(Color.WHITE);
        add(botonTipoUnidad);
        
        botonCategoria = new JButton("Categorias");
        botonCategoria.setActionCommand("CATEGORIA");
        botonCategoria.setBounds(60, 340, 130, 40);
        botonCategoria.setBackground(Color.decode("#006BFF"));
        botonCategoria.setForeground(Color.WHITE);
        add(botonCategoria);
        
        botonConfig = new JButton("Configuración");
        botonConfig.setActionCommand("CONFIG");
        botonConfig.setBounds(60, 420, 130, 40);
        botonConfig.setBackground(Color.decode("#006BFF"));
        botonConfig.setForeground(Color.WHITE);
        add(botonConfig);
    }
    
//    public void paint(Graphics g){
//        g.drawLine(60, 110, 675, 110);
//    }

    public JButton getBotonCategoria() {
        return botonCategoria;
    }

    public void setBotonCategoria(JButton botonCategoria) {
        this.botonCategoria = botonCategoria;
    }

    public JButton getBotonConfig() {
        return botonConfig;
    }

    public void setBotonConfig(JButton botonConfig) {
        this.botonConfig = botonConfig;
    }

    public JButton getBotonProducto() {
        return botonProducto;
    }

    public void setBotonProducto(JButton botonProducto) {
        this.botonProducto = botonProducto;
    }

    public JButton getBotonTipoUnidad() {
        return botonTipoUnidad;
    }

    public void setBotonTipoUnidad(JButton botonTipoUnidad) {
        this.botonTipoUnidad = botonTipoUnidad;
    }

    public JButton getBotonUnidad() {
        return botonUnidad;
    }

    public void setBotonUnidad(JButton botonUnidad) {
        this.botonUnidad = botonUnidad;
    }
    
    
}
