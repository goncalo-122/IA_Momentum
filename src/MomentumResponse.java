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

    public MomentumResponse() {
        jf = new JFrame("UPT - IA - MomentumAB");

        // Listener para o fechamento da janela
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

        jt = new JTextField("    ", 24);
        jnorth.add(jt);

        jnorth.add(new JLabel("Counting: "));
        jc = new JTextField("      ", 24);
        jnorth.add(jc);

        jnorth.add(new JLabel("Player: "));
        jplayer = new JTextField("  ", 3);
        jnorth.add(jplayer);

        JPanel jsouth = new JPanel();
        jMainPanel.add(jsouth, BorderLayout.SOUTH);
        JButton jok = new JButton("  OK  ");
        jsouth.add(jok);
        JButton jend = new JButton("  Exit  ");
        jsouth.add(jend, BorderLayout.SOUTH);

        // ActionListener para o botão OK
        jok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showServerMessage();  // Exibe a mensagem com os valores inseridos
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
    }

    /**
     * Método para exibir a mensagem com os valores inseridos
     */
    private void showServerMessage() {
        // Obter os valores inseridos pelo usuário
        String player = jplayer.getText();
        String count = jc.getText();
        String move = jt.getText();

        // Criar a mensagem
        StringBuilder sb = new StringBuilder();
        sb.append("A seguinte informação será enviada ao servidor:\n");
        sb.append("Jogador: " + player + "\n");
        sb.append("Contagem: " + count + "\n");
        sb.append("Movimento: " + move + "\n");

        // Mostrar a informação no console ou em uma caixa de diálogo
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

    /**
     * Método principal
     */
    public static void main(String[] args) {
        MomentumResponse r = new MomentumResponse();
        r.begin();
    }
}


//o que é a contagem