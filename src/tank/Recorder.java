package tank;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static  String path="src\\sql.txt";
    private static int koenemynum=0;
    private static int restenemy=0;
    private static FileWriter fileWriter=null;
    private static BufferedWriter bufferedWriter=null;

    private static FileReader fileReader=null;
    private static BufferedReader bufferedReader=null;

    static Vector<enemy> myenemy=new Vector<enemy>();
    static Vector<enemy> restmyenemy=new Vector<enemy>();

    public static int getRestenemy() {
        return restenemy;
    }

    public static String getPath() {
        return path;
    }

    public static void setMyenemy(Vector<enemy> myenemy) {
        Recorder.myenemy = myenemy;
    }

    public static Vector<enemy> getRestmyenemy() {
        return restmyenemy;
    }

    public static void setKoenemynum(int koenemynum) {
        Recorder.koenemynum = koenemynum;
    }

    public static int getKoenemynum() {
        return koenemynum;
    }
    public static void addKoenemynum(){
        Recorder.setKoenemynum(++koenemynum);
    }
    public static void writefile() throws IOException {

        try {
            fileWriter=new FileWriter(path);
            bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(koenemynum+"\r");
            for (int i = 0; i < myenemy.size(); i++) {
                enemy e=myenemy.get(i);
                if(e.islive){
                    String recorder=e.getX()+" "+e.getY()+" "+e.getDirection();
                    bufferedWriter.write(recorder+"\r\n");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.close();
        }
    }
    public static void readfile() throws IOException {

        if(mypanel.isnew){
            //重置Recorder的销毁数量
            Recorder.setKoenemynum(0);
        }
        else{
            fileReader=new FileReader(path);
            bufferedReader=new BufferedReader(fileReader);
            //将第一个数据传入
            koenemynum=Integer.parseInt(bufferedReader.readLine());
        }
        //还活者的数
        restenemy=mypanel.Enemysize-koenemynum;
        if(!mypanel.isnew){
            //遍历所有的数据
            for (int i = 0; i < restenemy; i++) {
                String temp;
                if((temp=bufferedReader.readLine())!=null){
                    String [] recorder=temp.split(" ");
                    if (recorder[2].equals("D"))
                        restmyenemy.add(new enemy(Integer.parseInt(recorder[0]),Integer.parseInt(recorder[1]),10,Direction.D));
                    else if (recorder[2].equals("R"))
                        restmyenemy.add(new enemy(Integer.parseInt(recorder[0]),Integer.parseInt(recorder[1]),10,Direction.R));
                    else if (recorder[2].equals("L"))
                        restmyenemy.add(new enemy(Integer.parseInt(recorder[0]),Integer.parseInt(recorder[1]),10,Direction.L));
                    else if (recorder[2].equals("U"))
                        restmyenemy.add(new enemy(Integer.parseInt(recorder[0]),Integer.parseInt(recorder[1]),10,Direction.U));
                }
            }
        }
    }
}
