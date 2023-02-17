package Server;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerController extends JFrame {

    ServerModel model;
    ServerView GUI;

    public ServerController(ServerModel m, ServerView v) {

        this.model = m;
        this.GUI = v;
        this.setTitle("Server");
        m.minChef = this;
        v.getsendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setmsg(GUI.getMessage());
                model.addmessagetochat("You: " + model.getmessage());
                GUI.settextPane1(model.getchat());
                model.SendMessage(model.getmessage());
                GUI.setMessage("");
            }
        });


        this.setContentPane(GUI.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        v.getsendButton();
    }
    public static void main(String[] args) {


        ServerModel m = new ServerModel(4738);
        ServerView v = new ServerView();
        ServerController thisIsTheProgram = new ServerController(m,v);
        thisIsTheProgram.setVisible(true);

        //ServerModel s = new ServerModel(4738);
        m.acceptClient();
        m.getStreams();
        ServerListenerThread l = new ServerListenerThread(m.in, thisIsTheProgram);
        Thread listener = new Thread(l);
        listener.start();
        m.runProtocol();
        listener.stop();
        m.shutdown();
    }

    public void newMessage(String msg) {
    model.addmessagetochat(msg);
    GUI.settextPane1(model.getchat());
    }
}
