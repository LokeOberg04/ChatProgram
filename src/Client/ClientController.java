package Client;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientController extends JFrame {

    ClientModel model;
    ClientView GUI;
    public ClientController(ClientModel m, ClientView v) {

        this.model = m;
        this.GUI = v;
        this.setTitle("Client");
        v.getsendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setmsg(GUI.getMessage());
                if(model.getmessage().length() > 0) {
                    model.addmessagetochat(model.getname() + ": " + model.getmessage());
                    GUI.settextPane1(model.getchat());
                    model.SendMessage(model.getmessage());
                    GUI.setMessage("");
                }
            }
        });

        this.setContentPane(GUI.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        v.getsendButton();
}
    public static void main(String[] args) {
         ClientModel m = new ClientModel("10.80.46.193", 4738); // me
        // ClientModel m = new ClientModel("10.80.47.10", 5858); // Alexandro
        // ClientModel m = new ClientModel("10.80.46.47", 1234); // Tim
        ClientView v = new ClientView();
        ClientController thisIsTheProgram = new ClientController(m,v);
        thisIsTheProgram.setVisible(true);
        m.setname(JOptionPane.showInputDialog("Namn?"));

        v.listaddMessage(m.getname());
            m.getStreams();
            ClientListenerThread l = new ClientListenerThread(m.in, thisIsTheProgram);
            Thread listener = new Thread(l);
            listener.start();
            m.runProtocol();
            listener.stop();
            m.shutDown();
        }

    public void newMessage(String msg) {
        model.addmessagetochat(msg);
        GUI.settextPane1(model.getchat());
    }
    public void newName(String name) {
        model.addtonames(name);
        GUI.listaddMessage(model.getnames());
    }
}