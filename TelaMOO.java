/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Batalha;

/**
 *
 * @author victor
 */
import Batalha.Alternativa;
import Batalha.Pergunta;
import View.GameOver;
import View.YouWin;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;



public class TelaMOO extends javax.swing.JFrame{
            
    /**
     * Creates new form TelaBD
     */
    private int valorNovoA;
    private int valorNovoP;
    private Map <JButton, Alternativa> botoesAlternativas;
    
    private double notaAluno = 10.0;
    private int indicePerguntaAtual = 0;
    private double novaNota;
    
    private void alterarTela() {
        if (vidaProfessor.getValue() == 0) {
            if(vidaAluno.getValue() == 100){
                JOptionPane.showMessageDialog(null, "Sua nota é: " + notaAluno);
            }else{
                JOptionPane.showMessageDialog(null, "Sua nota é: " + novaNota);
            }
            YouWin telavencer = new YouWin();
            telavencer.setVisible(true);
        } else if (vidaAluno.getValue() == 0) {
            GameOver telagameover = new GameOver();
            telagameover.setVisible(true);
        }
    }

    private void alterarIndice() {
        int incremento = 4;
        if (vidaProfessor.getValue() > 0 && vidaAluno.getValue() > 0) {
            if (indicePerguntaAtual <= 31) {
                indicePerguntaAtual += incremento;
                try {
                    carregarPergunta();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void carregarPergunta() throws Exception {
        String sqlPerguntas = "SELECT * FROM perguntas WHERE idMateria = 1 ORDER BY idPergunta";
        var perguntas = new ArrayList<Pergunta>();
        try (
                Connection conexao = ConnectionFactory.obterConexao(); var psPerguntas = conexao.prepareStatement(sqlPerguntas); var rsPerguntas = psPerguntas.executeQuery();) {
            while (rsPerguntas.next()) {
                int idPergunta = rsPerguntas.getInt("idPergunta");
                String pergunta = rsPerguntas.getString("pergunta");
                String sqlAlternativas = "SELECT * FROM respostas WHERE idMateria = 1 AND idPergunta = ?";
                try (
                        var psAlternativas = conexao.prepareStatement(sqlAlternativas);) {
                    psAlternativas.setInt(1, idPergunta);
                    try (
                            var rsAlternativas = psAlternativas.executeQuery();) {
                        var alternativas = new ArrayList<Alternativa>();
                        while (rsAlternativas.next()) {
                            String alternativa = rsAlternativas.getString("alternativa");
                            boolean resposta = rsAlternativas.getBoolean("resposta");
                            alternativas.add(new Alternativa(alternativa, resposta));
                            perguntas.add(new Pergunta(pergunta, alternativas));
                        }
                    }

                }
            }
            var perguntaAtual = perguntas.get(this.indicePerguntaAtual);
            questoes.setText(perguntaAtual.getTexto());
            Collections.shuffle(perguntaAtual.getAlternativas());
            botoesAlternativas = new HashMap<>();
            botoesAlternativas.put(alternativaA, perguntaAtual.getAlternativas().get(0));
            botoesAlternativas.put(alternativaB, perguntaAtual.getAlternativas().get(1));
            botoesAlternativas.put(alternativaC, perguntaAtual.getAlternativas().get(2));
            botoesAlternativas.put(alternativaD, perguntaAtual.getAlternativas().get(3));
            alternativaA.setText(perguntaAtual.getAlternativas().get(0).getTexto());
            alternativaB.setText(perguntaAtual.getAlternativas().get(1).getTexto());
            alternativaC.setText(perguntaAtual.getAlternativas().get(2).getTexto());
            alternativaD.setText(perguntaAtual.getAlternativas().get(3).getTexto());
        }
    }
    
    public TelaMOO() {
       super("Luta");
       initComponents();
       setVisible(true);
       
       
       
       vidaProfessor.setValue(100);
       vidaProfessor.setBackground(new Color(0, 0, 0, 0));
       vidaProfessor.setForeground(Color.GREEN);
       vidaProfessor.setBorderPainted(false);

       vidaAluno.setValue(100);
       vidaAluno.setBackground(new Color(0, 0, 0, 0));
       vidaAluno.setForeground(Color.GREEN);
       vidaAluno.setBorderPainted(false);

        try {
            carregarPergunta();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao exibir as perguntas");
            ex.printStackTrace();
            Logger.getLogger(TelaPOO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateProgressBarColor(){
        if (valorNovoA >= 75) {
            vidaAluno.setForeground(Color.GREEN);
        }
        else if (valorNovoA >= 50 ) {
        vidaAluno.setForeground(Color.YELLOW);
        }
        else if (valorNovoA >= 25) {
        vidaAluno.setForeground(Color.RED);
        }

        if (valorNovoP >= 75) {
        vidaProfessor.setForeground(Color.GREEN);
        }
        else if (valorNovoP >= 50 ) {
        vidaProfessor.setForeground(Color.YELLOW);
        }
        else if (valorNovoP >= 25) {
        vidaProfessor.setForeground(Color.RED);
        }
    }

        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        alternativaA = new javax.swing.JButton();
        alternativaC = new javax.swing.JButton();
        alternativaD = new javax.swing.JButton();
        alternativaB = new javax.swing.JButton();
        painel2 = new javax.swing.JPanel();
        vidaAluno = new javax.swing.JProgressBar();
        porcentagemA = new javax.swing.JLabel();
        painel = new javax.swing.JPanel();
        vidaProfessor = new javax.swing.JProgressBar();
        porcentagemP = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        questoes = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alternativaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alternativaAActionPerformed(evt);
            }
        });
        getContentPane().add(alternativaA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 520, 90));

        alternativaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alternativaCActionPerformed(evt);
            }
        });
        getContentPane().add(alternativaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 520, 90));

        alternativaD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alternativaDActionPerformed(evt);
            }
        });
        getContentPane().add(alternativaD, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 520, 90));

        alternativaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alternativaBActionPerformed(evt);
            }
        });
        getContentPane().add(alternativaB, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 520, 90));

        porcentagemA.setText("100%");

        javax.swing.GroupLayout painel2Layout = new javax.swing.GroupLayout(painel2);
        painel2.setLayout(painel2Layout);
        painel2Layout.setHorizontalGroup(
            painel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vidaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(porcentagemA, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painel2Layout.setVerticalGroup(
            painel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(vidaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addComponent(porcentagemA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(painel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 380, 40));

        porcentagemP.setText("100%");

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(porcentagemP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(vidaProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(porcentagemP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(vidaProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(painel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 400, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/pixil-frame-0 (7).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 60, 110, -1));

        jScrollPane1.setViewportView(questoes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1090, 100));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/IMG_9701.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alternativaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alternativaAActionPerformed
        // TODO add your handling code here:
        if(botoesAlternativas.get(alternativaA).isCorreta()){
            int valorAtualP = vidaProfessor.getValue();
            valorNovoP = valorAtualP - 25;
            if (valorNovoP < 0){
                valorNovoP = 0;
            }
            vidaProfessor.setValue(valorNovoP);
            porcentagemP.setText(valorNovoP+"%");
        }
        else{
            novaNota = notaAluno - 2.5;
            if(novaNota<0){
            novaNota = 0;
            }  
            int valorAtualA = vidaAluno.getValue();
            valorNovoA = valorAtualA - 25;
            if (valorNovoA < 0){
                valorNovoA = 0;
            }
            vidaAluno.setValue(valorNovoA);
            porcentagemA.setText(valorNovoA+"%");
        }
        updateProgressBarColor();  
        alterarIndice();
        alterarTela();
    }//GEN-LAST:event_alternativaAActionPerformed

    private void alternativaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alternativaBActionPerformed
        // TODO add your handling code here:
        if(botoesAlternativas.get(alternativaB).isCorreta()){
            int valorAtualP = vidaProfessor.getValue();
            valorNovoP = valorAtualP - 25;
            if (valorNovoP < 0){
                valorNovoP = 0;
            }
            vidaProfessor.setValue(valorNovoP);
            porcentagemP.setText(valorNovoP+"%");
        }
        else{
            novaNota = notaAluno - 2.5;
            if(novaNota<0){
            novaNota = 0;
            }  
            int valorAtualA = vidaAluno.getValue();
            valorNovoA = valorAtualA - 25;
            if (valorNovoA < 0){
                valorNovoA = 0;
            }
            vidaAluno.setValue(valorNovoA);
            porcentagemA.setText(valorNovoA+"%");
        }
        updateProgressBarColor(); 
        alterarIndice();
        alterarTela();
    }//GEN-LAST:event_alternativaBActionPerformed

    private void alternativaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alternativaCActionPerformed
        // TODO add your handling code here:
        if(botoesAlternativas.get(alternativaC).isCorreta()){
            int valorAtualP = vidaProfessor.getValue();
            valorNovoP = valorAtualP - 25;
            if (valorNovoP < 0){
                valorNovoP = 0;
            }
            vidaProfessor.setValue(valorNovoP);
            porcentagemP.setText(valorNovoP+"%");
        }
        else{
            novaNota = notaAluno - 2.5;
            if(novaNota<0){
            novaNota = 0;
            }  
            int valorAtualA = vidaAluno.getValue();
            valorNovoA = valorAtualA - 25;
            if (valorNovoA < 0){
                valorNovoA = 0;
            }
            vidaAluno.setValue(valorNovoA);
            porcentagemA.setText(valorNovoA+"%");
        }
        updateProgressBarColor(); 
        alterarIndice();
        alterarTela();
    }//GEN-LAST:event_alternativaCActionPerformed

    private void alternativaDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alternativaDActionPerformed
        // TODO add your handling code here:
        if(botoesAlternativas.get(alternativaD).isCorreta()){
            int valorAtualP = vidaProfessor.getValue();
            valorNovoP = valorAtualP - 25;
            if (valorNovoP < 0){
                valorNovoP = 0;
            }
            vidaProfessor.setValue(valorNovoP);
            porcentagemP.setText(valorNovoP+"%");
        }
        else{
            novaNota = notaAluno - 2.5;
            if(novaNota<0){
            novaNota = 0;
            }  
            int valorAtualA = vidaAluno.getValue();
            valorNovoA = valorAtualA - 25;
            if (valorNovoA < 0){
                valorNovoA = 0;
            }
            vidaAluno.setValue(valorNovoA);
            porcentagemA.setText(valorNovoA+"%");
        }
        updateProgressBarColor(); 
        alterarIndice();
        alterarTela();
    }//GEN-LAST:event_alternativaDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TelaBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMOO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alternativaA;
    private javax.swing.JButton alternativaB;
    private javax.swing.JButton alternativaC;
    private javax.swing.JButton alternativaD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel painel2;
    private javax.swing.JLabel porcentagemA;
    private javax.swing.JLabel porcentagemP;
    private javax.swing.JLabel questoes;
    private javax.swing.JProgressBar vidaAluno;
    private javax.swing.JProgressBar vidaProfessor;
    // End of variables declaration//GEN-END:variables

}

