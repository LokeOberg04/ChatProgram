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
        this.setContentPane(GUI.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }
    public static void main(String[] args) {


        ServerModel s = new ServerModel(4738);
        s.acceptClient();
        s.getStreams();
        ServerListenerThread l = new ServerListenerThread(s.in, System.out);
        Thread listener = new Thread(l);
        listener.start();
        s.runProtocol();
        listener.stop();
        s.shutdown();

        ServerModel m = new ServerModel(4738);
        ServerView v = new ServerView();
        ServerController thisIsTheProgram = new ServerController(m,v);
        thisIsTheProgram.setVisible(true);
    }
}
