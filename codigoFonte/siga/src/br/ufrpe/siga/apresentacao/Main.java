package br.ufrpe.siga.apresentacao;

public class Main {

	public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
				FrmLogin window = new FrmLogin();
				window.setVisible(true);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }); 
    }

}