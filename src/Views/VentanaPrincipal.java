
package Views;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{

    private MainPanel mainPanel;
    
    public VentanaPrincipal() throws SQLException{
        setTitle("Ferreteria");
        setSize(900,700);
        setLocation(500,500);
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
        mainPanel.setBounds(25,25,830,610);
        getContentPane().add(mainPanel);
        mainPanel.setVisible(true);
    }
}
