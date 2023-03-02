package ChatProgram;

import ChatProgram.Client.ClientController;
import ChatProgram.Server.ServerController;

import java.io.IOException;

public class Executable {
    public static void main(String[] args) throws InterruptedException {
        ServerController.main(null);
        ClientController.main(null);
     //   String command = "C:/Users/loke.oberg/IdeaProjects/ChatProgram/out/artifacts/ServerChatProgram_jar/ChatProgram.exe";
     //   try {
     //       Process p = Runtime.getRuntime().exec(command);
     //   } catch (IOException e) {
     //       throw new RuntimeException(e);
     //   }
//
     //   String command2 = "C:/Users/loke.oberg/IdeaProjects/ChatProgram/out/artifacts/ClientChatProgram_jar/ChatProgram.exe";
     //   try {
     //       Process p2 = Runtime.getRuntime().exec(command2);
     //   } catch (IOException e) {
     //       throw new RuntimeException(e);
     //   }
    }
}
