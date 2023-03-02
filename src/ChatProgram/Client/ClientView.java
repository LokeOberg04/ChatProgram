package ChatProgram.Client;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView {
    private JTextPane textPane1;
    private JPanel panel1;
    private JTextField writeYourTextHereTextField;
    private JButton sendButton;
    private JTextPane list1;


    public ClientView() {
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

    public void listaddMessage(String c) {
        list1.setText(c);
    }

    public void settextPane1(String c) {
        textPane1.setText(c);
    }


    public void sendListener(ActionListener sendListener) {
        writeYourTextHereTextField.addActionListener(sendListener);
    }

    public AbstractButton getsendButton() {
        return sendButton;
    }

    public JTextField getEnter() {
        return writeYourTextHereTextField;
    }
}

