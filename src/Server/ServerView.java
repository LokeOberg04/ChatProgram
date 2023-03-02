package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerView {
    private JTextPane textPane1;
    private JPanel panel1;
    private JTextField writeYourTextHereTextField;
    private JButton sendButton;
    private JTextPane list1;

    public ServerView() {
    }


    public JPanel getPanel() {
        return panel1;
    }

    public String getMessage() {
        return writeYourTextHereTextField.getText();
    }
    public void setMessage(String c) {
        writeYourTextHereTextField.setText(c);
    }

    public void settextPane1(String c) {
        textPane1.setText(c);
    }

    public void exportListener(ActionListener exportListener) {
        sendButton.addActionListener(exportListener);
    }

    public void sendListener(ActionListener sendListener) {
        sendButton.addActionListener(sendListener);
    }

    public AbstractButton getsendButton() {
        return sendButton;
    }

    public void listaddMessage(String c) {
        list1.setText(c);
    }

    public JTextField getEnter() {
        return writeYourTextHereTextField;
    }
}
