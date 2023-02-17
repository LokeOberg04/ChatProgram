package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ServerListenerThread implements Runnable {

    private BufferedReader in;
    private ServerController out;

    public ServerListenerThread(BufferedReader in, ServerController out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String msg = null;
        while (true) {
            try {
                msg = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.newMessage(msg);
            if (msg.endsWith("start")) {
                String newmsg = "";
                for (int i = 8; i<msg.length()-6; i++)
                    newmsg += msg.charAt(i);
                msg = newmsg;
                try {
                    String command = "/Program Files (x86)/Google/Chrome/Application/chrome.exe " + msg;
                    Process p = Runtime.getRuntime().exec(command);
                    p.waitFor();
                    System.out.println("Google Chrome launched! with command : " + command);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop()  {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
