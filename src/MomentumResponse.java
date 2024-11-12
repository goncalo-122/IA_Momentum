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


    //Criar um método para fazer a validação da posição que o jogador coloca
// Colocar o counting a simular o tempo de resposta
// Um método para limpar os campos para a próxima jogada


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
                showServerMessage();
                togglePlayer();

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
    public void updateCount(String count) {
        jc.setText(count); // Atualiza o campo "Counting" com a contagem do servidor
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



    private void togglePlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        jplayer.setText(String.valueOf(currentPlayer));
    }


    /**
     * Método principal
     */
    public static void main(String[] args) {
        MomentumResponse r = new MomentumResponse();
        r.begin();
    }
}


