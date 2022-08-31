package tank;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class tankgameFrame extends JFrame {
    mypanel mp=null;

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请选择:(1)开始新游戏(2)上局游戏");
        switch (scanner.next())
        {
            case "1":
                mypanel.isnew=true;
                break;
            case "2":
                mypanel.isnew=false;
                //如果文件不存在
                if(!new File(Recorder.getPath()).exists()){
                    System.out.println("数据文件不存在，已自动为你开启新游戏");
                    mypanel.isnew=true;
                }
                break;
            default:
                System.out.println("请您重新输入！");
        }
    new tankgameFrame();
    }

tankgameFrame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        mp=new mypanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击X按钮关闭程序
        this.setSize(1300,750);
        this.addKeyListener(mp);

        //在JFame中增加 相应的关闭窗口的处理
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            try {
                Recorder.writefile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    });

    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowOpened(WindowEvent e) {
            try {
                Recorder.readfile();
                //旧游戏
                if (!mypanel.isnew)
                mp.contructenemy();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });
}
}
