package onlineChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Properties;

public class MyFrame extends JFrame implements ActionListener, KeyListener {

    protected static int serverPort;

    protected static String clientIp;

    protected static int clientPort;

    static{

        try {
            Properties prop = new Properties();
            //String path = MyFrame.class.getResource("").getPath()+ "chat.properties";;
            //System.out.println(System.getProperty("user.dir"));
            InputStream is = new FileInputStream("chat.properties");
            prop.load(is);
            serverPort = Integer.parseInt(prop.getProperty("serverPort"));
            clientIp = new String(prop.getProperty("clientIp"));
            clientPort = Integer.parseInt(prop.getProperty("clientPort"));
            is.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //文本域
    protected   JTextArea jTextArea;
    //滚动条
    protected JScrollPane jScrollPane;
    //面板
    protected JPanel jPanel;
    //文本框
    protected  JTextField jTextField;
    //按钮
    protected JButton jButton;

    protected BufferedReader reader;

    protected BufferedWriter writer;

    protected String tag;
    public MyFrame(String tag){
        this.tag = tag;
        jTextArea = new JTextArea();
        //设置文本域不可编辑
        jTextArea.setEditable(false);
        //将文本域添加到滚动面板中
        jScrollPane = new JScrollPane(jTextArea);

        jPanel = new JPanel();
        jTextField = new JTextField(20);

        jButton = new JButton("发送");
        //给按钮添加点击事件
        jButton.addActionListener(this);
        //给文本框绑定键盘点击事件
        jTextField.addKeyListener(this);

        //将文本框和按钮添加到面板中
        jPanel.add(jTextField);
        jPanel.add(jButton);
        //将滚动条与面板添加到窗体中
        this.add(jScrollPane, BorderLayout.CENTER);
        this.add(jPanel,BorderLayout.SOUTH);

        //设置标题，大小，位置，关闭，是否可见
        this.setTitle(tag+"聊天");
        this.setSize(500,500);
        this.setLocation(1000,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public JButton getjButton() {
        return jButton;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        send();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10){
            send();
        }
    }

    protected void send(){
        try {
            String message = tag+":"+jTextField.getText();
            writer.write(message);
            jTextArea.append(message+System.lineSeparator());
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
