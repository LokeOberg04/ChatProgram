package Client;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientController extends JFrame {

    ClientModel model;
    ClientView GUI;

    private class exportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // do stuff
        }
    }

    private class messageListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setmsg(GUI.getMessage());
            GUI.setmessage(model.getmessage());
        }
    }

    private class sendListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GUI.setmessage(model.getmessage());
            model.addmessage(GUI.getMessage());
        }
    }

    public ClientController(ClientModel m, ClientView v) {

        this.model = m;
        this.GUI = v;
        this.setContentPane(GUI.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        v.sendListener(new sendListener());
}
    public static void main(String[] args) {
        ClientModel m = new ClientModel(JOptionPane.showInputDialog("ip?"), Integer.parseInt(JOptionPane.showInputDialog("port?")));
        ClientView v = new ClientView();
        ClientController thisIsTheProgram = new ClientController(m,v);
        thisIsTheProgram.setVisible(true);

            // Client me = new Client("10.80.47.10", 5858); // alexandro
            // ClientModel me = new ClientModel("10.80.46.193", 4738); // me
            // Client me = new Client("10.80.46.47", 1234); // tim
            m.getStreams();
            ClientListenerThread l = new ClientListenerThread(m.in, System.out);
            Thread listener = new Thread(l);
            listener.start();
            m.runProtocol();
            listener.stop();
            m.shutDown();
        }
    }