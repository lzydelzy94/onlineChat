package onlineChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class onLineChaClient extends MyFrame{
    public static void main(String[] args) {
        new onLineChaClient("客户端");
    }

    public onLineChaClient(String tag){
        super(tag);

        try{
            Socket socket = new Socket(clientIp,clientPort);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = null;
            while((message = reader.readLine()) != null){
                jTextArea.append(message+System.lineSeparator());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
