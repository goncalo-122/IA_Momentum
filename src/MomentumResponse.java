import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MomentumResponse {

    private JFrame jf;
    private JPanel jMainPanel;
    private JTextField jt;
    private JTextField jplayer;
    private JTextField jc;

    private int currentPlayer = 1;
    private int counting = 0;


    public MomentumResponse() {
        jf = new JFrame("UPT - IA - MomentumAB");

        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int res = confirmExit();
                if (res == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        jMainPanel = new JPanel();
        jMainPanel.setLayout(new BorderLayout());
        jf.add(jMainPanel);

        JPanel jnorth = new JPanel();
        jMainPanel.add(jnorth, BorderLayout.NORTH);

        jnorth.add(new JLabel("Play: "));
        jt = new JTextField("    ", 24);
        jnorth.add(jt);

        jnorth.add(new JLabel("Counting: "));
        jc = new JTextField("      ", 24);
        jnorth.add(jc);

        jnorth.add(new JLabel("Player: "));
        jplayer = new JTextField(String.valueOf(currentPlayer), 3);
        jnorth.add(jplayer);

        JPanel jsouth = new JPanel();
        jMainPanel.add(jsouth, BorderLayout.SOUTH);
        JButton jok = new JButton("  OK  ");
        jsouth.add(jok);
        JButton jend = new JButton("  Exit  ");
        jsouth.add(jend, BorderLayout.SOUTH);

        jok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isValidMove(jt.getText())) {
                    showServerMessage();
                    togglePlayer();
                }
            }
        });

        jend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int res = confirmExit();
                if (res == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        jf.pack();
        jf.setVisible(true);
    }


    public void begin() {
        jc.setEnabled(false);
        jplayer.setEnabled(false);
    }



    /**
     * Método para exibir a mensagem com os valores inseridos
     */
    private void showServerMessage() {

        String player = jplayer.getText();
        String count = jc.getText();
        String move = jt.getText();
        StringBuilder sb = new StringBuilder();
        sb.append("A seguinte informação será enviada ao servidor:\n");
        sb.append("Jogador: " + player + "\n");
        sb.append("Contagem: " + count + "\n");
        sb.append("Movimento: " + move + "\n");
        restart();

        System.out.println(sb.toString());
        JOptionPane.showMessageDialog(jf, sb.toString(), "Informações Enviadas", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Diálogo que pergunta para confirmar o fim do programa
     *
     * @return int com a escolha do jogador
     */
    protected int confirmExit() {
        return JOptionPane.showConfirmDialog(
                null,
                "Confirmar o fim do programa?",
                "UPT - IA - Momentum",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }


    private void restart() {
        jt.setText(" ");
        jc.setText(" ");
    }


    private void togglePlayer() {

        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        jplayer.setText(String.valueOf(currentPlayer));
    }


    private boolean isValidMove(String move) {
        // Validar o formato da jogada (ex: "1,1")
        String[] parts = move.split(",");
        if (parts.length != 2) {
            JOptionPane.showMessageDialog(null, "Formato de jogada inválido. Use o formato 'linha,coluna'.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;

            if (row < 0 || row >= 7 || col < 0 || col >= 7) {  // Verifica se está dentro dos limites do tabuleiro
                JOptionPane.showMessageDialog(null, "Jogada fora dos limites do tabuleiro! O tabuleiro é 7x7.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }


            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Jogada inválida. Insira números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public static void main(String[] args) {
        MomentumResponse r = new MomentumResponse();
        r.begin();
    }
}
