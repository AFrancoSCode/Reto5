
package Controller;

import View.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Control implements ActionListener {
    
    private VentanaPrincipal vista;

    public Control() throws SQLException {
        vista = new VentanaPrincipal();
        asignarOyentes();
    }

    public void asignarOyentes() {
        
        /* Aqui se declara un listener por cada boton de navegación */
        
        vista.getMainPanel().getBotonProducto().addActionListener(this);
        vista.getMainPanel().getBotonUnidad().addActionListener(this);
        vista.getMainPanel().getBotonTipoUnidad().addActionListener(this);
        vista.getMainPanel().getBotonCategoria().addActionListener(this);
        vista.getMainPanel().getBotonConfig().addActionListener(this);
        vista.getProductosPanel().getBotonVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        /* Este metodo se usa para los botones de navegación */
        
        String comando = e.getActionCommand();
        if (comando.equals("PRODUCTO")) {
            vista.getMainPanel().setVisible(false);
            vista.getProductosPanel().setVisible(true);
        }else if(comando.equals("UNIDAD")){
            vista.getMainPanel().setVisible(false);
            vista.getPanelUnidades().setVisible(true);
        }else if(comando.equals("TIPOUNIDAD")){
            vista.getMainPanel().setVisible(false);
            vista.getPanelTipoUnidad().setVisible(true);
        }else if(comando.equals("CATEGORIA")){
            vista.getMainPanel().setVisible(false);
            vista.getPanelCategoria().setVisible(true);
        }else if(comando.equals("CONFIG")){
            vista.getMainPanel().setVisible(false);
            vista.getPanelConfig().setVisible(true);
        }else if(comando.equals("VOLVERPRODUCTOS")){
            vista.getMainPanel().setVisible(true);
            vista.getProductosPanel().setVisible(false);
        }
    }
}
