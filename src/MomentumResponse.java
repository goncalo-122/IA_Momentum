import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MomentumResponse {

    private JFrame jf;
    private JPanel jMainPanel;
    private JLabel jt;
    private JLabel jplayer;
    private JLabel jc;

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
        jt = new JLabel(" ");
        jnorth.add(jt);

        jnorth.add(new JLabel("Counting: "));
        jc = new JLabel(String.valueOf(counting));
        jnorth.add(jc);

        jnorth.add(new JLabel("Player: "));
        jplayer = new JLabel(String.valueOf(currentPlayer));
        jnorth.add(jplayer);

        JPanel jsouth = new JPanel();
        jMainPanel.add(jsouth, BorderLayout.SOUTH);
        JButton jok = new JButton("  OK  ");
        jsouth.add(jok);
        JButton jend = new JButton("  Exit  ");
        jsouth.add(jend);

        jok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String move = JOptionPane.showInputDialog(jf, "Insira sua jogada (linha,coluna):", "Entrada de Jogada", JOptionPane.PLAIN_MESSAGE);
                if (move != null && isValidMove(move)) {
                    jt.setText(move); // Exibe a jogada no JLabel
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
        jf.setLocationRelativeTo(null); // posicionar janela no centro da tela
    }

    public void begin() {
        // Nenhuma necessidade de desativar JLabels
    }

    private void showServerMessage() {
        String player = jplayer.getText();
        String count = jc.getText();
        String move = jt.getText().trim();

        StringBuilder sb = new StringBuilder();
        sb.append("A seguinte informação será enviada ao servidor:\n");
        sb.append("Jogador: ").append(player).append("\n");
        sb.append("Contagem: ").append(count).append("\n");
        sb.append("Movimento: ").append(move).append("\n");

        restart();

        System.out.println(sb.toString());
        JOptionPane.showMessageDialog(jf, sb.toString(), "Informações Enviadas", JOptionPane.INFORMATION_MESSAGE);
    }

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
        counting++;
        jc.setText(String.valueOf(counting));
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        jplayer.setText(String.valueOf(currentPlayer));
    }

    private boolean isValidMove(String move) {
        String[] parts = move.split(",");
        if (parts.length != 2) {
            JOptionPane.showMessageDialog(null, "Formato de jogada inválido. Use o formato 'linha,coluna'.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;

            if (row < 0 || row >= 7 || col < 0 || col >= 7) {
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
