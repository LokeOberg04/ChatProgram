package Client;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView {
    private JTextPane textPane1;
    private JPanel panel1;
    private JTextField writeYourTextHereTextField;
    private JButton sendButton;
    private JList list1;


    public JPanel getPanel() {
        return panel1;
    }

    public String getMessage() {
        return writeYourTextHereTextField.getText();
    }

    public void setmessage(String c) {
        textPane1.setText(c);
    }

    public void exportListener(ActionListener exportListener) {
        sendButton.addActionListener(exportListener);
    }

    public void sendListener(ActionListener sendListener) {
        sendButton.addActionListener(sendListener);
    }
}
