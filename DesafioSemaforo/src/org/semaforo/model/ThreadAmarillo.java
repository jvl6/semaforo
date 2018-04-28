package org.semaforo.model;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThreadAmarillo extends Thread {

    private JLabel lbl;
    private int timer;
    private Exec exec;
    private JPanel pnl;

    public ThreadAmarillo(JPanel pnl, JLabel lbl, int timer, Exec exec) {
        this.lbl = lbl;
        this.timer = timer;
        this.exec = exec;
        this.pnl = pnl;
    }

    @Override
    public void run() {
        synchronized (exec) {
            try {
                while (exec.getExec() != 3) {
                    exec.wait();
                }
            } catch (Exception e) {
                System.err.println(e);
            }

            lbl.setVisible(true);
            
            for (int i = timer; i >= 0; i--) {
                try {
                    pnl.setBackground(new Color(255, 255, 0));
                    lbl.setText(String.valueOf(i));
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadVerde.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            pnl.setBackground(new Color(0, 0, 0));
            lbl.setVisible(false);

            exec.setExec(4);
            exec.notifyAll();
        }
    }
}
