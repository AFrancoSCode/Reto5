
package View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TipoUnidadPanel extends JPanel{
    
    private JLabel titulo;
    private JButton botonVolver;
    
    public TipoUnidadPanel(){
        setLayout(null);
        setVisible(false);
        inicializarComponentes();
    }
    
    public void inicializarComponentes(){
        titulo = new JLabel("Tipo de Unidades");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.decode("#006BFF"));
        titulo.setBounds(235,20,400,80);
        add(titulo);
        
        botonVolver = new JButton("Volver");
        botonVolver.setActionCommand("VOLVER");
        botonVolver.setFont(new Font("Arial", Font.BOLD, 13));
        botonVolver.setForeground(Color.white);
        botonVolver.setBackground(Color.BLUE);
        botonVolver.setBounds(20, 20, 75, 20);
        add(botonVolver);
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public void setBotonVolver(JButton botonVolver) {
        this.botonVolver = botonVolver;
    }
}