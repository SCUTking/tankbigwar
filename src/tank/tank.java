package tank;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;


public class tank  {
    int width=100;int hight=150;
    public int x;
    public int y;
    public int speed;
    public  Direction direction;
    public boolean islive=true;
    public Direction getDirection() {
        return direction;
    }

    public tank(int x, int y, int speed, Direction direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHight() {
        return hight;
    }

    public int getWidth() {
        return width;
    }
}
class hero extends tank implements Runnable{
    @Override
    public void run() {
        while(true){
            //被击中后退出线程
            if (!this.islive){
                break;
            }
        }
    }

    public hero(int x, int y, int speed, Direction direction) {
        super(x, y, speed,direction);
    }
}
class enemy extends tank implements Runnable{
    int numberezd=1;
    boolean isrun=true;

    static Vector<enemy> myenemy=new Vector<enemy>();
    Vector<zhidan> ezd=new Vector<zhidan>();//new zhidan[numberezd];
    public void createzd(){
        for (int i = 0; i < numberezd; i++) {
            zhidan zd1=null;
            switch (this.direction){
                //将英雄的方向赋值给子弹
                case U:
                    zd1=new zhidan(this.getX()+15,this.getY(),100,10,this.direction);
                    break;
                case L:
                    zd1=new zhidan(this.getX(),this.getY()+15,100,10,this.direction);
                    break;
                case R:
                    zd1=new zhidan(this.getX()+40,this.getY()+15,100,10,this.direction);
                    break;
                case D:
                    zd1=new zhidan(this.getX()+15,this.getY()+60,100,10,this.direction);
                    break;
            }
            ezd.add(zd1);//添加子弹
            //启动
            new Thread(zd1).start();
        }
    }

    public void setMyenemy(Vector<enemy> myenemy) {
        this.myenemy = myenemy;
    }
    public boolean tankwithtank(tank a,tank b){
        if((a.getDirection()==Direction.U&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.U&&b.getDirection()==Direction.D)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.D)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+40
                    &&a.getY()>b.getY()-60&&a.getY()<b.getY()+60){
                return true;
            }
        }
        else if((a.getDirection()==Direction.L&&b.getDirection()==Direction.L)||
                (a.getDirection()==Direction.L&&b.getDirection()==Direction.R)||
                (a.getDirection()==Direction.R&&b.getDirection()==Direction.L)||
                (a.getDirection()==Direction.R&&b.getDirection()==Direction.R)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+60
                    &&a.getY()>b.getY()-40&&a.getY()<b.getY()+40){
                return true;
            }
        }
        else if((a.getDirection()==Direction.R&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.R&&b.getDirection()==Direction.D)||
                (a.getDirection()==Direction.L&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.L&&b.getDirection()==Direction.D)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+60
                    &&a.getY()>b.getY()-60&&a.getY()<b.getY()+40) {
                return true;
            }
        }
        else if((a.getDirection()==Direction.U&&b.getDirection()==Direction.R)||
                (a.getDirection()==Direction.U&&b.getDirection()==Direction.L)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.R)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.L)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+60
                    &&a.getY()>b.getY()-60&&a.getY()<b.getY()+40) {
                return true;
            }
        }
        return false;
    }
    public boolean iscollsion() {
        for (int i = 0; i < myenemy.size(); i++) {
            enemy enemytank = myenemy.get(i);
            if (tankwithtank(this, enemytank)||tankwithtank(enemytank,this)) {
                return true;
            }
        }
        return false;
    }

    public enemy(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
        createzd();
        }

    @Override
    public void run() {
        while(true){
            //敌方连续发射子弹  子弹消失后 创建新的敌方子弹
            //可以修改子弹数量，在下面修改
                if(this.ezd.size()<1){
                    createzd();
                }


            int longs=20;
            //获取方向，让它往前走
                switch (this.getDirection()){
                    case U:
                        for (int i = 0; i < longs; i++) {
                            if(y>1&&!iscollsion()){
                                this.y--;
                            }
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case L:
                        for (int i = 0; i < longs; i++) {
                            if(x>1&&!iscollsion())
                                this.x--;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case R:
                        for (int i = 0; i < longs; i++) {
                            if(x<999&&!iscollsion())
                                this.x++;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case D:
                        for (int i = 0; i < longs; i++) {
                            if(y<749&&!iscollsion())
                                this.y++;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;

            }
//            else{
//                switch (this.getDirection()){
//                    case U:
//                        for (int i = 0; i < longs; i++) {
//                            if(y<749)
//                                this.y++;
//                            try {
//                                Thread.sleep(50);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        this.isrun=true;
//                        break;
//                    case L:
//                        for (int i = 0; i < longs; i++) {
//                            if(x<999)
//                                this.x++;
//                            try {
//                                Thread.sleep(50);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        this.isrun=true;
//
//                        break;
//                    case R:
//                        for (int i = 0; i < longs; i++) {
//                            if(x>1)
//                                this.x--;
//                            try {
//                                Thread.sleep(50);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        this.isrun=true;
//
//                        break;
//                    case D:
//                        for (int i = 0; i < longs; i++) {
//                            if(y>1){
//                                this.y--;
//                            }
//                            try {
//                                Thread.sleep(50);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        this.isrun=true;
//
//                        break;
//                }
//            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int temp=(int)(Math.random()*4);
            //随机改变方向
            if(1==temp){
                this.setDirection(Direction.U);
            }
            if(2==temp){
                this.setDirection(Direction.D);

            }
            if(3==temp){
                this.setDirection(Direction.L);
            }
            if(0==temp){
                this.setDirection(Direction.R);
            }

            //结束线程
            if(!this.islive){
                break;
            }

        }

    }
}


enum Direction{
    U,D,L,R;
}

//将该类变为线程类，让它一直刷新
class mypanel extends JPanel implements KeyListener,Runnable {
    private hero myhero=null;//英雄

    //是否开启起的游戏
    public static boolean isnew=false;

    Vector<zhidan> mzd=new Vector<zhidan>();

    Vector<enemy> myenemy=new Vector<enemy>();
    Vector<Bomb> bombs=new Vector<Bomb>();
    public static int Enemysize=3;
    //三张图片
    Image image1;
    Image image2;
    Image image3;

    //对坦克进行初始化
    public mypanel() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(isnew){
            //music.start("src\\tank.wav");
            myhero=new hero(480,500,20,Direction.U);//坦克地初始位置
            new Thread(myhero).start();

            for (int i = 0; i < Enemysize; i++) {
                myenemy.add(new enemy(0+i*300,50,10,Direction.D));
            }
            //让enemy类获取myenmey向量
            myenemy.get(0).setMyenemy(myenemy);

            //让recorder类获取myenemy向量
            Recorder.setMyenemy(myenemy);


            //打开坦克的随机移动
            for (int i = 0; i < Enemysize; i++) {
                new Thread(myenemy.get(i)).start();
            }
            image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/1.jpg"));
            image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/2.jpg"));
            image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/3.jpg"));
        }
        else{
            myhero=new hero(480,500,20,Direction.U);//坦克地初始位置
            new Thread(myhero).start();

            image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/1.jpg"));
            image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/2.jpg"));
            image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/3.jpg"));
        }
    }
    public void contructenemy(){
        setMyenemy(Recorder.getRestmyenemy());
        //让enemy类获取myenmey向量
        if(myenemy.size()>0)
            myenemy.get(0).setMyenemy(myenemy);
        //让recorder类获取myenemy向量
        Recorder.setMyenemy(myenemy);
        //打开坦克的随机移动
        for (int i = 0; i < Recorder.getRestenemy(); i++) {
            new Thread(myenemy.get(i)).start();
        }
    }
    public void setMyenemy(Vector<enemy> myenemy) {
        this.myenemy = myenemy;
    }

    public void showinfo(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("宋体",Font.BOLD,25));
        g.drawString("您已击毁的坦克数为：",1020,30);
        drawtank(1020,60,Direction.D,0,g);
        g.setColor(Color.black);
        g.drawString(Recorder.getKoenemynum()+"",1080,100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(Color.white);
        g.fillRect(0,0,1000,750);
        showinfo(g);
        //g.drawLine(myhero.getX()+ myhero.getWidth()/2, myhero.getY(), myhero.getX()+ myhero.getWidth()/2, myhero.getY()+ myhero.getHight() );
        if (myhero.islive){
            drawtank(myhero.getX(),myhero.getY(),myhero.getDirection(),0,g);
        }

        for (int i = 0; i < mzd.size(); i++) {
            if(mzd.get(i)!=null&& mzd.get(i).islive){
                drawzhidan(mzd.get(i).getDirection(),g,mzd.get(i));
            }
            else{
                mzd.remove(mzd.get(i));
            }
        }

        //画出爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bomb bomb=bombs.get(i);
            if(bomb.life>6){
                g.drawImage(image1,bomb.getX(),bomb.getY(),60,60,this);
            }
            else if(bomb.life>3){
                g.drawImage(image2,bomb.getX(),bomb.getY(),60,60,this);
            }
            else{
                g.drawImage(image3,bomb.getX(),bomb.getY(),60,60,this);
            }
            //减少爆炸效果的时间
            bomb.lifedown();
            if(!bomb.islivew){
                bombs.remove(bomb);
            }
        }

        //画出敌方的坦克和敌方的子弹
        for (int i = 0; i < myenemy.size(); i++) {
            if(myenemy.get(i).islive){
                drawtank(myenemy.get(i).getX(),myenemy.get(i).getY(),myenemy.get(i).getDirection(),1,g);
                for (int j = 0; j < myenemy.get(i).ezd.size(); j++) {
                    drawzhidan(myenemy.get(i).ezd.get(j).getDirection(),g,myenemy.get(i).ezd.get(j));
                    if(!myenemy.get(i).ezd.get(j).islive){
                        myenemy.get(i).ezd.remove(myenemy.get(i).ezd.get(j));
                    }

                }
            }

        }

    }
    /**
     * type控制颜色
     * Direction控制绘画
     * */
    private void drawtank(int x,int y,Direction d,int type,Graphics g){
        int danwei=10;
        int danwei1=60;
        //根据坦克的类型编制颜色
        //Enum Direation{Up=1,Down,Left,right};
        switch (type){
            case 0://自己坦克
                //方向时肯定会用的，所以可以不用在这里画
                g.setColor(Color.cyan);
                break;
            case 1://敌人坦克
                g.setColor(Color.red);
                break;
        }
        switch (d){
            case U:
                g.fill3DRect(x,y,danwei,danwei1,false);
                g.fill3DRect(x+danwei,y+danwei,danwei*2,danwei*4,false);
                g.fill3DRect(x+danwei*3,y,danwei,danwei1,false);
                g.drawOval(x+danwei,y+danwei*2,danwei*2,danwei*2);
                g.drawLine(x+20,y,x+20,y+30);
                break;
            case L:
                g.fill3DRect(x,y,danwei1,danwei,false);
                g.fill3DRect(x,y+danwei*3,danwei*6,danwei,false);
                g.fill3DRect(x+danwei,y+danwei,danwei*4,danwei*2,false);
                g.drawOval(x+danwei*2,y+danwei,danwei*2,danwei*2);
                g.drawLine(x,y+20,x+30,y+20);
                break;

            case R:
                g.fill3DRect(x,y,danwei1,danwei,false);
                g.fill3DRect(x,y+danwei*3,danwei*6,danwei,false);
                g.fill3DRect(x+danwei,y+danwei,danwei*4,danwei*2,false);
                g.drawOval(x+danwei*2,y+danwei,danwei*2,danwei*2);
                g.drawLine(x+60,y+20,x+30,y+20);
                break;

            case D:
                g.fill3DRect(x,y,danwei,danwei1,false);
                g.fill3DRect(x+danwei,y+danwei,danwei*2,danwei*4,false);
                g.fill3DRect(x+danwei*3,y,danwei,danwei1,false);
                g.drawOval(x+danwei,y+danwei*2,danwei*2,danwei*2);
                g.drawLine(x+20,y+60,x+20,y+30);
                break;

        }

    }
    /**
     * number子弹的数量
     * */
    private void drawzhidan(Direction d,Graphics g,zhidan zd){
        int temp;
        if(zd!=null){
            switch (d){
                case U:
                    temp=zd.getY()-zd.getSpeed();
                    zd.setY(temp);
                    break;
                case L:
                    temp=zd.getX()-zd.getSpeed();
                    zd.setX(temp);
                    break;
                case R:
                    temp=zd.getX()+zd.getSpeed();
                    zd.setX(temp);
                    break;
                case D:
                    temp=zd.getY()+zd.getSpeed();
                    zd.setY(temp);
                    break;
            }
        }
        g.drawOval(zd.getX(),zd.getY(),10,10);
    }
    public  void hittank(zhidan zd,hero e){
        switch (e.getDirection()){
            case U:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+40
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+60){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));
                }

                break;
            case D:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+40
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+60){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));

                }
                break;
            case L:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+60
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+40){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));
                }
                break;
            case R:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+60
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+40){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));
                }
                break;
        }
    }
    public  void hittank(zhidan zd,enemy e){
        switch (e.getDirection()){
            case U:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+40
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+60){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));
                }

                break;
            case D:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+40
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+60){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));

                }
                break;
            case L:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+60
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+40){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));
                }
                break;
            case R:
                if(zd.getX()>e.getX()&&zd.getX()<e.getX()+60
                        &&zd.getY()>e.getY()&&zd.getY()<e.getY()+40){
                    e.islive=false;
                    zd.islive=false;
                    bombs.add(new Bomb(e.x,e.y));
                }
                break;
        }
    }
/***
 * 坦克之间的碰撞检测，返回bool，在移动后检测，如果碰撞了，在减去移动的值
 */
    public boolean tankwithtank(tank a,tank b){
        if((a.getDirection()==Direction.U&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.U&&b.getDirection()==Direction.D)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.D)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+40
                    &&a.getY()>b.getY()-60&&a.getY()<b.getY()+60){

                return true;
            }
        }
        else if((a.getDirection()==Direction.L&&b.getDirection()==Direction.L)||
                (a.getDirection()==Direction.L&&b.getDirection()==Direction.R)||
                (a.getDirection()==Direction.R&&b.getDirection()==Direction.L)||
                (a.getDirection()==Direction.R&&b.getDirection()==Direction.R)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+60
                    &&a.getY()>b.getY()-40&&a.getY()<b.getY()+40){
                return true;
            }
        }
        else if((a.getDirection()==Direction.R&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.R&&b.getDirection()==Direction.D)||
                (a.getDirection()==Direction.L&&b.getDirection()==Direction.U)||
                (a.getDirection()==Direction.L&&b.getDirection()==Direction.D)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+60
                    &&a.getY()>b.getY()-60&&a.getY()<b.getY()+40) {
                return true;
            }
        }
        else if((a.getDirection()==Direction.U&&b.getDirection()==Direction.R)||
                (a.getDirection()==Direction.U&&b.getDirection()==Direction.L)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.R)||
                (a.getDirection()==Direction.D&&b.getDirection()==Direction.L)){
            if(a.getX()>b.getX()&&a.getX()<b.getX()+60
                    &&a.getY()>b.getY()-60&&a.getY()<b.getY()+40) {
                return true;
            }
        }
            return false;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
//当按下某个健时会触发这个按钮
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode()+"被按下了"
        switch (e.getKeyCode()){
            case 87://w
                if(myhero.y>myhero.speed)
            myhero.y-=myhero.speed;
            myhero.direction=Direction.U;
                for (int i = 0; i < myenemy.size(); i++) {
                    if(tankwithtank(myhero,myenemy.get(i))||tankwithtank(myenemy.get(i),myhero)){
                        myhero.y+=myhero.speed;
                    }
                }
            break;
            case 83://s
                if(myhero.y<750-myhero.speed-100)
                    myhero.y+=myhero.speed;
                myhero.direction=Direction.D;
                for (int i = 0; i < myenemy.size(); i++) {
                    if(tankwithtank(myhero,myenemy.get(i))||tankwithtank(myenemy.get(i),myhero)){
                        myhero.y-=myhero.speed;
                    }
                }
                break;
            case 65://a
                if(myhero.x>myhero.speed)
                    myhero.x-=myhero.speed;
                myhero.direction=Direction.L;
                for (int i = 0; i < myenemy.size(); i++) {
                    if(tankwithtank(myhero,myenemy.get(i))||tankwithtank(myenemy.get(i),myhero)){
                        myhero.x+=myhero.speed;
                    }
                }
                break;
            case 68://d
                if(myhero.x<1000-myhero.speed-60)
                    myhero.x+=myhero.speed;
                myhero.direction=Direction.R;
                for (int i = 0; i < myenemy.size(); i++) {
                    if(tankwithtank(myhero,myenemy.get(i))||tankwithtank(myenemy.get(i),myhero)){
                        myhero.x-=myhero.speed;
                    }
                }
                break;
            case 74:
            {
                //控制子弹一次只能发射一次，或者在没创建的时候创建
                //if(mzd==null||!mzd.islive){
                if(mzd.size()<=5){
                    zhidan mzdtemp=null;
                    switch (myhero.direction){
                        //将英雄的方向赋值给子弹
                        case U:
                            mzdtemp=new zhidan(myhero.getX()+15,myhero.getY(),100,10,myhero.direction);
                            mzd.add(mzdtemp);
                            break;
                        case L:
                            mzdtemp=new zhidan(myhero.getX(),myhero.getY()+15,100,10,myhero.direction);
                            mzd.add(mzdtemp);
                            break;
                        case R:
                            mzdtemp=new zhidan(myhero.getX()+40,myhero.getY()+15,100,10,myhero.direction);
                            mzd.add(mzdtemp);
                            break;
                        case D:
                            mzdtemp=new zhidan(myhero.getX()+15,myhero.getY()+60,100,10,myhero.direction);
                            mzd.add(mzdtemp);
                            break;
                    }
                    new Thread(mzdtemp).start();

                    break;
                }
            }
        }
        //重新绘画坦克
        repaint();

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
/*
            }*/
    @Override
    public void run() {
        while (true){
            //循环便利所有的敌方坦克
            for (int i = 0; i < mzd.size(); i++) {
                if(mzd.get(i)!=null&& mzd.get(i).islive){
                    for (int j = 0; j < myenemy.size(); j++) {
                        //if(myenemy.get(i)!=null)
                        hittank(mzd.get(i), myenemy.get(j));
                        if(!myenemy.get(j).islive){
                            myenemy.remove(myenemy.get(j));
                            System.out.println(myenemy.size());
                            Recorder.addKoenemynum();

                        }
                    }
                }
            }
            //便利所有地敌方tank地子弹
            boolean wudi=true;
            if(myhero.islive){
                for (int i = 0; i < myenemy.size(); i++) {
                    for (int j = 0; j < myenemy.get(i).ezd.size(); j++) {
                        if(myenemy.get(i).ezd.get(j)!=null&& myenemy.get(i).ezd.get(j).islive){
                            //判断自己的坦克是否被击中
                            if(wudi){
                                hittank(myenemy.get(i).ezd.get(j), myhero);
                            }
                        }
                    }
                }
            }


            try {
                //调
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();

            //检查tank是否可以移动
        }

    }
}