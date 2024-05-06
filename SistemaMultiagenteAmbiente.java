import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaMultiagenteAmbiente extends JFrame {

    private static final int AMBIENTE_ANCHO = 10;
    private static final int AMBIENTE_ALTO = 10;

    private JButton[][] celdas;
    private JLabel estadoLabel;

    private int[][] ambiente;

    private int agenteX, agenteY;

    public SistemaMultiagenteAmbiente() {
        setTitle("Sistema Multiagente en Ambiente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        initComponents();
        inicializarAmbiente();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(AMBIENTE_ANCHO, AMBIENTE_ALTO));

        celdas = new JButton[AMBIENTE_ANCHO][AMBIENTE_ALTO];
        for (int i = 0; i < AMBIENTE_ANCHO; i++) {
            for (int j = 0; j < AMBIENTE_ALTO; j++) {
                celdas[i][j] = new JButton();
                panel.add(celdas[i][j]);
                final int x = i;
                final int y = j;
                celdas[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        celdaClicada(x, y);
                    }
                });
            }
        }

        add(panel, BorderLayout.CENTER);

        estadoLabel = new JLabel("Estado: ");
        add(estadoLabel, BorderLayout.SOUTH);
    }

    private void inicializarAmbiente() {
        ambiente = new int[AMBIENTE_ANCHO][AMBIENTE_ALTO];
        for (int i = 0; i < AMBIENTE_ANCHO; i++) {
            for (int j = 0; j < AMBIENTE_ALTO; j++) {
                ambiente[i][j] = 0; // Inicialmente todas las celdas están vacías
            }
        }
        // Colocamos un agente en una posición aleatoria
        agenteX = (int) (Math.random() * AMBIENTE_ANCHO);
        agenteY = (int) (Math.random() * AMBIENTE_ALTO);
        ambiente[agenteX][agenteY] = 1; // Marcamos la celda ocupada por el agente
        actualizarInterfaz();
    }

    private void celdaClicada(int x, int y) {
        if (ambiente[x][y] == 0) { // Si la celda está vacía
            moverAgente(x, y);
        } else {
            estadoLabel.setText("Estado: Celda ocupada o bloqueada.");
        }
    }

    private void moverAgente(int newX, int newY) {
        ambiente[agenteX][agenteY] = 0; // Desocupar la celda actual del agente
        agenteX = newX;
        agenteY = newY;
        ambiente[agenteX][agenteY] = 1; // Marcar la nueva posición del agente
        actualizarInterfaz();
        estadoLabel.setText("Estado: Agente movido a (" + newX + ", " + newY + ")");
    }

    private void actualizarInterfaz() {
        for (int i = 0; i < AMBIENTE_ANCHO; i++) {
            for (int j = 0; j < AMBIENTE_ALTO; j++) {
                if (ambiente[i][j] == 0) {
                    celdas[i][j].setText(""); // Celda vacía
                } else if (ambiente[i][j] == 1) {
                    celdas[i][j].setText("A"); // Celda ocupada por agente
                } else {
                    celdas[i][j].setText("X"); // Celda bloqueada
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SistemaMultiagenteAmbiente().setVisible(true);
            }
        });
    }
}
