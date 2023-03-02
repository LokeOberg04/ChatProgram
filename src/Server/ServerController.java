package Server;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

// import static com.sun.xml.internal.org.jvnet.mimepull.FactoryFinder.cl;


public class ServerController extends JFrame {

    ServerModel model;
    ServerView GUI;

    public ServerController(ServerModel m, ServerView v) {
        ClassLoader cl = this.getClass().getClassLoader();
        ImageIcon icon = null;
        // try {
        //     icon = new ImageIcon(ImageIO.read(cl.getResource("icon.png")));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        this.model = m;
        this.GUI = v;
        this.setTitle("Server");
        //this.setIconImage(icon.getImage());
        m.minChef = this;
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

        v.getEnter().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    model.setmsg(GUI.getMessage());
                    if (model.getmessage().length() > 0) {
                        model.addmessagetochat(model.getname() + ": " + model.getmessage());
                        GUI.settextPane1(model.getchat());
                        model.SendMessage(model.getmessage());
                        GUI.setMessage("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    GUI.setMessage("");
                }
            }
        });

        this.setContentPane(GUI.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);


        m.setname(JOptionPane.showInputDialog("Namn?"));

        v.listaddMessage(m.getname());

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
    public void newName(String name) {
        model.addtonames(name);
        GUI.listaddMessage(model.getnames());
    }
}
