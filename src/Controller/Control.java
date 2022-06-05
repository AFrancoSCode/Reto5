
package Controller;

import Views.VentanaPrincipal;
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
    }
}
