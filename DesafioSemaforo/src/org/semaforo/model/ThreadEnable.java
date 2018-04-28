package org.semaforo.model;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThreadEnable extends Thread {

    private Exec exec;
    private JTextField txtUno;
    private JTextField txtDos;
    private JTextField txtTres;
    private JButton btn;
    private JPanel pnl;
    private JLabel lbl;

    public ThreadEnable(JTextField txtUno, JTextField txtDos, JTextField txtTres, JButton btn, JPanel pnl, JLabel lbl, Exec exec) {
        this.txtUno = txtUno;
        this.txtDos = txtDos;
        this.txtTres = txtTres;
        this.btn = btn;
        this.pnl = pnl;
        this.lbl = lbl;
        this.exec = exec;
    }

    @Override
    public void run() {
        synchronized (exec) {
            try {
                while (exec.getExec() != 4) {
                    exec.wait();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            
            txtUno.setEnabled(true);
            txtDos.setEnabled(true);
            txtTres.setEnabled(true);
            btn.setEnabled(true);
            pnl.setBackground(new Color(255, 0, 0));
            lbl.setVisible(true);
        }
    }
}
