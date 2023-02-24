package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientModel {
    String msg = "";
    String chat = "";

    String name = "";
    String names = "";
    Socket socket;

    PrintWriter out;
    BufferedReader in;

    public ClientModel(String ip, int port) {
        try {
            socket = new Socket(ip,port);
        } catch (IOException e) {
            System.err.println("Failed to connect to server");
            e.printStackTrace();
        }
        System.out.println("Connection ready...");
    }


    public String getmessage() {
        return msg;
    }

    public String getname() {return this.name;}

    public String getnames() {return this.names;}

    public void setname(String name) {
        this.name = name;
        this.names += name;
    }

    public void addtonames(String name) {
        this.names += "\n" + name;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }
    public void addmessagetochat(String msg) {
        chat += msg + "\n";
    }

    public String getchat() {return chat;}

    public void getStreams() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Streams ready...");
    }

    public void SendMessage(String msg) {
        out.println(name + ": " + msg);
    }

    public void runProtocol() {
        Scanner tgb = new Scanner(System.in);
        System.out.println("chatting...");
        String msg = "";
        while (!msg.equals("QUIT")) {
            msg = tgb.next();
            out.println("Client: " + msg);
            if (msg.endsWith("ball")) {
                try {
                    Process p = Runtime.getRuntime().exec("/Program Files (x86)/Google/Chrome/Application/chrome.exe https://www.youtube.com/watch?v=-8PibZVCWuI");
                    p.waitFor();
                    System.out.println("Google Chrome launched!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void shutDown() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}