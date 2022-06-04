
package View;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
    
    private MainPanel mainPanel;
    private ProductosPanel panelProductos;
    private UnidadesPanel panelUnidades;
    private TipoUnidadPanel panelTipoUnidad;
    private CategoriaPanel panelCategoria;
    private ConfigPanel panelConfig;
    
    public VentanaPrincipal() throws SQLException{
        setTitle("Ferreteria");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#CADCF5"));
        getContentPane().setLayout(null);
        
        inicializar();
        
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void inicializar() throws SQLException{
        mainPanel = new MainPanel();
        mainPanel.setBounds(25,25,735,510);
        getContentPane().add(mainPanel);
        
        panelProductos = new ProductosPanel();
        panelProductos.setBounds(25,25,735,510);
        getContentPane().add(panelProductos);
        
        panelUnidades = new UnidadesPanel();
        panelUnidades.setBounds(25,25,735,510);
        getContentPane().add(panelUnidades);
        
        panelTipoUnidad = new TipoUnidadPanel();
        panelTipoUnidad.setBounds(25,25,735,510);
        getContentPane().add(panelTipoUnidad);
        
        panelCategoria = new CategoriaPanel();
        panelCategoria.setBounds(25,25,735,510);
        getContentPane().add(panelCategoria);
        
        panelConfig = new ConfigPanel();
        panelConfig.setBounds(25,25,735,510);
        getContentPane().add(panelConfig);
    }

    public ConfigPanel getPanelConfig() {
        return panelConfig;
    }

    public void setPanelConfig(ConfigPanel panelConfig) {
        this.panelConfig = panelConfig;
    }

    public CategoriaPanel getPanelCategoria() {
        return panelCategoria;
    }

    public void setPanelCategoria(CategoriaPanel panelCategoria) {
        this.panelCategoria = panelCategoria;
    }
    
    public TipoUnidadPanel getPanelTipoUnidad() {
        return panelTipoUnidad;
    }

    public void setPanelTipoUnidad(TipoUnidadPanel panelTipoUnidad) {
        this.panelTipoUnidad = panelTipoUnidad;
    }

    public UnidadesPanel getPanelUnidades() {
        return panelUnidades;
    }

    public void setPanelUnidades(UnidadesPanel panelUnidades) {
        this.panelUnidades = panelUnidades;
    }
    
    public ProductosPanel getProductosPanel() {
        return panelProductos;
    }

    public void setProductosPanel(ProductosPanel panelProductos) {
        this.panelProductos = panelProductos;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
    
}
