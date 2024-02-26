
package src;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class Snake extends JPanel implements ActionListener,KeyListener{
    private class tile {
        int x,y;
        tile(int x,int y){
            this.x=x;
            this.y=y;

        }
        
    }
    int bw,bh,ts=25;
    tile sh;
    ArrayList<tile> a;
    tile fo;
    Random r;
    Timer gl;
    int vx,vy;
    boolean go=false;
    Snake(int bw,int bh){
        this.bw=bw;
        this.bh=bh;
        setPreferredSize(new Dimension(this.bw,this.bh));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        sh=new tile(5, 5);
        a=new ArrayList<>();
        fo=new tile(10,10);
        r=new Random();
        pf();
        vx=0;
        vy=0;
        gl=new Timer(100,this);
        gl.start();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fill3DRect(fo.x*ts, fo.y*ts, ts, ts,true);
        g.setColor(Color.green);
        g.fill3DRect(sh.x*ts,sh.y*ts,ts,ts,true);

        for(int i=0;i<a.size();i++){
            tile sp=a.get(i);
            g.fill3DRect(sp.x*ts, sp.y*ts, ts, ts,true);
        }
        g.setFont(new Font("Arial",Font.PLAIN,16));
        if(go){
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(a.size()),ts-16,ts);
        }
        else{
            g.drawString("Score : "+ String.valueOf(a.size()),ts-16,ts);
        }

    }
    public void pf(){
        fo.x=r.nextInt(bw/ts);
        fo.y=r.nextInt(bh/ts);
    }
    public boolean collision(tile t1, tile t2){
        return t1.x==t2.x && t1.y==t2.y;
    }
    public void move(){
        if(collision(sh, fo)){
            a.add(new tile(fo.x,fo.y));
            pf();

        }
        for(int i=a.size()-1;i>=0;i--){
            tile sp=a.get(i);
            if(i==0){
                sp.x=sh.x;
                sp.y=sh.y;
            }
            else{
                tile psp=a.get(i-1);
                sp.x=psp.x;
                sp.y=psp.y;
            }
        }

        sh.x+=vx;
        sh.y+=vy;
        for(int i=0;i<a.size();i++){
            tile sp=a.get(i);
            if(collision(sh, sp)){
                go=true;
            }
        }
        if(sh.x*ts<0 || sh.x*ts>bw || sh.y*ts<0|| sh.y*ts>bh){
            go=true;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
        if(go){
            gl.stop();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP && vy!=1){
            vx=0;
            vy=-1;
        }        
        else if(e.getKeyCode()==KeyEvent.VK_DOWN && vy!=-1){
            vx=0;
            vy=1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT && vx!=1){
            vx=-1;
            vy=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT && vx!=-1){
            vx=1;
            vy=0;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {      
    }
}
