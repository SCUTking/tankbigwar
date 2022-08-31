package tank;

import javax.swing.*;
import java.awt.*;

class zhidan implements Runnable {

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private int x;
    private int y;
    private int number1;
    private int speed;
    private Direction direction;
    public boolean islive=true;

//运动轨迹
    @Override
    public void run() {
        while (islive){
            //休眠很关键，要不然直接飞走了。
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int temp;
            switch (direction){
                case U:
                    temp=this.getY()-this.getSpeed();
                    this.setY(temp);
                    break;
                case L:
                    temp=this.getX()-this.getSpeed();
                    this.setX(temp);
                    break;
                case R:
                    temp=this.getX()+this.getSpeed();
                    this.setX(temp);
                    break;
                case D:
                    temp=this.getY()+this.getSpeed();
                    this.setY(temp);
                    break;
            }

            if (this.x<0||this.y<0||this.x>1000||this.y>750||!islive){
                islive=false;
                break;
            }
        }


    }

    public zhidan(int x, int y, int number1, int speed, Direction direction) {
        this.x = x;
        this.y = y;
        this.number1 = number1;
        this.speed = speed;
        this.direction = direction;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber1() {
        return number1;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }
}
