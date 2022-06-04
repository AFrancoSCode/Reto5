
package View;

import java.awt.BorderLayout;
import javax.swing.*;

public class Borrar extends JFrame{
    public Borrar(){
        setTitle("Los Planetas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300,300,400,200);
        JTable tablaPlanetas = new JTable(datosFila, nombresColumnas);
        add(new JScrollPane(tablaPlanetas), BorderLayout.CENTER);
        setVisible(true);
    }
    private String[] nombresColumnas = {"Nombre", "Radio", "Lunas", "Gaseoso"};
    
    private Object[][] datosFila = {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
    };
}