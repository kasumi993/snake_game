

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.*;

/**
 * Gameplay
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener{

    private int[] snakexpos= new int[750];
    private int[] snakeypos= new int[750];


    private int[] applexpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,475,500,525,575,600,625,675,700};
    
    private int[] appleypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,475,500,525,575,600};

    private Random random=new Random();
    private int xpos= random.nextInt(25);
    private int ypos= random.nextInt(20);

    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;

    private ImageIcon titre;
    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon body;
    private ImageIcon downtail;
    private ImageIcon uptail;
    private ImageIcon lefttail;
    private ImageIcon righttail;
    private ImageIcon apple;


    private int snakeLength=3;
    private int mouthlength=60;
    private int bodylength=37;
    private int taillength=25;

    private int moves=0;

    private Timer timer;
    private int delai=100;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delai, this);
        timer.start();
    };


    @Override
    public void paint(Graphics g) {


        //barre de titre
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        titre=new ImageIcon("snaketitre.png");
        titre.paintIcon(this, g, 25, 11);


        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);


        //definir la position initiale du serpent

        if (moves==0) {
            snakexpos[0]=100;
            snakexpos[1]=75;
            snakexpos[2]=50;

            snakeypos[0]=102;
            snakeypos[1]=100;
            snakeypos[2]=107;

            //draw snake
        rightMouth= new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, snakexpos[0], snakeypos[0]);
        body= new ImageIcon("body-hori.png");
        body.paintIcon(this, g, snakexpos[1], snakeypos[1]);
        righttail= new ImageIcon("righttail.png");
        righttail.paintIcon(this, g, snakexpos[2], snakeypos[2]);
        }

        

        for(int i=0;i<snakeLength;i++){
            if (i==0) {
                if (right) {
                    rightMouth= new ImageIcon("rightmouth.png");
                    rightMouth.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                if (left) {
                    leftMouth= new ImageIcon("leftmouth.png");
                    leftMouth.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                if (up) {
                    upMouth= new ImageIcon("upmouth.png");
                    upMouth.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                if (down) {
                    downMouth= new ImageIcon("downmouth.png");
                    downMouth.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
            }else if(i==snakeLength){
                if (right) {
                    righttail= new ImageIcon("righttail.png");
                    righttail.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                if (left) {
                    lefttail= new ImageIcon("lefttail.png");
                    lefttail.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                if (up) {
                    uptail= new ImageIcon("uptail.png");
                    uptail.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                if (down) {
                    downtail= new ImageIcon("downtail.png");
                    downtail.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
            }else{
                if (right || left) {
                    body= new ImageIcon("body-hori.png");
                    body.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                
                if (up || down) {
                    body= new ImageIcon("body-verti.png");
                    body.paintIcon(this, g, snakexpos[i], snakeypos[i]);
                }
                
            }
        
        }

        //draw apple

        apple=new ImageIcon("apple.png");
        apple.paintIcon(this, g, applexpos[xpos], appleypos[ypos]);

        if (applexpos[xpos]==snakexpos[0] && appleypos[ypos]==snakeypos[0] ) {
            snakeLength++;
            xpos=random.nextInt(25);
            ypos=random.nextInt(20);
            repaint();
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for(int r=snakeLength-1;r>=0;r--){
                snakeypos[r+1]=snakeypos[r];
            }
            for(int r=snakeLength-1;r>=0;r--){
                if (r==0) {
                    snakexpos[r]=snakexpos[r]+25;
                }else{
                    snakexpos[r]=snakexpos[r-1]-25;
                }
                if (snakexpos[r]>850) {
                    snakexpos[r]=25;
                }
                
            }
            repaint();
        }
        if (left) {
            for(int r=snakeLength-1;r>=0;r--){
                snakeypos[r+1]=snakeypos[r];
            }
            for(int r=snakeLength-1;r>=0;r--){
                if (r==0) {
                    snakexpos[r]=snakexpos[r]-25;
                }else{
                    snakexpos[r]=snakexpos[r-1];
                }
                if (snakexpos[r]<25) {
                    snakexpos[r]=850;
                }
                
            }
            repaint();
        }
        if (down) {
            for(int r=snakeLength-1;r>=0;r--){
                snakexpos[r+1]=snakexpos[r];
            }
            for(int r=snakeLength-1;r>=0;r--){
                if (r==0) {
                    snakeypos[r]=snakeypos[r]+25;
                }else{
                    snakeypos[r]=snakeypos[r-1];
                }
                if (snakeypos[r]>600) {
                    snakeypos[r]=75;
                }
                
            }
            repaint();
        }
        if (up) {
            for(int r=snakeLength-1;r>=0;r--){
                snakexpos[r+1]=snakexpos[r];
            }
            for(int r=snakeLength-1;r>=0;r--){
                if (r==0) {
                    snakeypos[r]=snakeypos[r]-25;
                }else{
                    snakeypos[r]=snakeypos[r-1];
                }
                if (snakeypos[r]<75) {
                    snakeypos[r]=600;
                }
                
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_RIGHT) {
            moves++;
            if (!left) {
                right=true;
            }else{
                right=false;
                left=true;
            }
           
            up=false;
            down=false;
        }
        if (e.getKeyCode()== KeyEvent.VK_LEFT) {
            moves++;
            if (!right) {
                left=true;
            }else{
                left=false;
                right=true;
            }
           
            up=false;
            down=false;
        }
        if (e.getKeyCode()== KeyEvent.VK_UP) {
            moves++;
            if (!down) {
                up=true;
            }else{
                up=false;
                down=true;
            }
           
            left=false;
            right=false;
        }
        if (e.getKeyCode()== KeyEvent.VK_DOWN) {
            moves++;
            if (!up) {
                down=true;
            }else{
                down=false;
                up=true;
            }
           
            left=false;
            right=false;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
    
