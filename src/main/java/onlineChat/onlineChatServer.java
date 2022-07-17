package onlineChat;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class onlineChatServer extends MyFrame{
    public static void main(String[] args) {
        new onlineChatServer("服务器端");
    }

    public onlineChatServer(String tag){
        super(tag);

        try(ServerSocket serverSocket = new ServerSocket(serverPort);){

            Socket socket = serverSocket.accept();
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while((message = reader.readLine()) != null){
                jTextArea.append(message+System.lineSeparator());
            }

        }catch (Exception e){

        }

    }

    @Override
    public void actionPerformed(ActionEvent e){
        send();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10){
            send();
        }
    }


}
