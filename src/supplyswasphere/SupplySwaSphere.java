/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package supplyswasphere;
import com.swasphere.vista.InicioVista;

/**
 *
 * @author ljsanchez23
 */
public class SupplySwaSphere {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear y mostrar la ventana de inicio
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioVista().setVisible(true);
            }
        });
    }
    
}
