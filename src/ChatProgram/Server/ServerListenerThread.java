package ChatProgram.Server;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListenerThread implements Runnable {

    private BufferedReader in;
    private ServerController out;

    public ServerListenerThread(BufferedReader in, ServerController out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        int used = 0;
        int nameend = 0;
        String newname = "";
        String msg = null;
        while (true) {
            try {
                msg = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(used == 0) {
                used = 1;
                for(int i = 0; i<msg.length(); i++) {
                    if(msg.charAt(i) == ':') {
                        nameend = i;
                    }
                }
                for(int o = 0; o<nameend; o++) {
                    newname += msg.charAt(o);
                }
                out.newName(newname);
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
