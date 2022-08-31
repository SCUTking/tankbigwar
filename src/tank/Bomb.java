package tank;

class Bomb {
    int x;
    int y;
    int life=9;
    boolean islivew=true;
    public void lifedown(){
        if(life>0)
        life--;
        else{
            islivew=false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
