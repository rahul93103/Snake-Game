//package snakegame;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.awt.event.*;
//public class Board extends JPanel implements ActionListener {
//    private Image apple;
//    private Image dot;
//    private Image head;
//
//    private final int ALL_DOTS=900;
//    private final int DOT_SIZE=10;
//    private final int RANDOM_POSITION=29;
//    private int apple_x;
//    private int apple_y;
//
//    private final int x[] =new int[ALL_DOTS];
//    private final int y[] =new int[ALL_DOTS];
//
//    private boolean leftDirection =false;
//    private boolean rightDirection=true;
//    private boolean upDirection=false;
//
//    private boolean downDirection=false;
//    private boolean inGame=true;
//
//    private int dots;
//    private Timer timer;
//    Board(){
//
//
//        addKeyListener(new TAdaptor());
//        setBackground(Color.BLACK);
//        setPreferredSize(new Dimension(300,300));
//        setFocusable(true);
//        loadImages();
//        initGame();
//    }
//    public void loadImages(){
//        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
//        apple=i1.getImage();
//
//        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
//        dot =i2.getImage();
//
//        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
//        head=i3.getImage();
//
//    }
//    public void initGame(){
//        dots =3;
//
//        for(int i=0;i<dots;i++){
//            y[i]=50;
//            x[i]=50-i*DOT_SIZE;
//
//        }
//        locateApple();
//
//        Timer timer =new Timer(140,this);
//        timer.start();
//    }
//    public void locateApple(){
//        int r=(int)(Math.random()*RANDOM_POSITION);
//        apple_x=r*DOT_SIZE;
//        r=(int)(Math.random()*RANDOM_POSITION);
//        apple_y=r*DOT_SIZE;
//    }
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        draw(g);
//    }
//    public void draw(Graphics g) {
//        if(inGame) {
//            g.drawImage(apple, apple_x, apple_y, this);
//            for (int i = 0; i < dots; i++) {
//                if (i == 0) {
//                    g.drawImage(head, x[i], y[i], this);
//                } else {
//                    g.drawImage(dot, x[i], y[i], this);
//                }
//            }
//            Toolkit.getDefaultToolkit().sync();
//        }
//        else{
//            gameOver(g);
//        }
//    }
//    public void gameOver(Graphics g){
//        String msg="Game Over!";
//        Font font =new Font("SAN SERIF",Font.BOLD,14);
//        FontMetrics metrices=getFontMetrics(font);
//        g.drawString(msg,(300-metrices.stringWidth(msg))/2,300/2);
//    }
//    public void move(){
//        for(int i=dots;i>0;i--){
//            x[i]=x[i-1];
//            y[i]=y[i-1];
//        }
//        if(leftDirection){
//            x[0]=x[0]-DOT_SIZE;
//        }
//        if(rightDirection){
//            x[0]=x[0]+DOT_SIZE;
//        }
//        if(upDirection){
//            y[0]=y[0]-DOT_SIZE;
//        }
//        if(downDirection){
//            y[0]=y[0]+DOT_SIZE;
//        }
//
//      //  x[0]+=DOT_SIZE;
//       // y[0]+=DOT_SIZE;
//    }
//    public void checkApple(){
//        if((x[0]==apple_x)&& (y[0] == apple_y)){
//            dots++;
//            locateApple();
//        }
//    }
//    public void checkCollision(){
//        for(int i=dots;i>0;i--){
//            if((i>4) && (x[0]==x[i]) && (y[0]==y[i])){
//                inGame=false;
//            }
//        }
//        if(y[0]>=300){
//            inGame=false;
//        }
//        if(x[0]>=300){
//            inGame=false;
//        }
//        if(y[0]<0){
//            inGame=false;
//        }
//        if(x[0]<0){
//            inGame=false;
//        }
//        if(!inGame){
//            timer.stop();
//        }
//
//    }
//    public void actionPerformed(ActionEvent ae){
//        if(inGame) {
//            checkApple();
//            checkCollision();
//            move();
//        }
//            repaint();
//    }
//
//    public class TAdaptor extends KeyAdapter{
//        @Override
//        public void keyPressed(KeyEvent e){
//            int key=e.getKeyCode();
//
//            if(key==KeyEvent.VK_LEFT && (!rightDirection)){
//                leftDirection=true;
//                upDirection=false;
//                downDirection=false;
//            }
//
//            if(key==KeyEvent.VK_RIGHT && (!leftDirection)){
//                rightDirection=true;
//                upDirection=false;
//                downDirection=false;
//            }
//
//            if(key==KeyEvent.VK_UP && (!downDirection)){
//                upDirection=true;
//                leftDirection=false;
//                rightDirection=false;
//            }
//
//            if(key==KeyEvent.VK_DOWN && (!upDirection)){
//                downDirection=true;
//                leftDirection=false;
//                rightDirection=false;
//            }
//
//        }
//    }
//}


package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    private Image food; // Changed 'apple' to 'food'
    private Image bodyPart; // Changed 'dot' to 'bodyPart'
    private Image snakeHead; // Changed 'head' to 'snakeHead'

    private final int MAX_UNITS = 900; // Changed constant names
    private final int UNIT_SIZE = 10;
    private final int RAND_POSITION = 29; // Simplified name

    private int foodX, foodY;
    private int[] snakeX = new int[MAX_UNITS];
    private int[] snakeY = new int[MAX_UNITS];

    private boolean isMovingLeft = false;
    private boolean isMovingRight = true;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean gameIsActive = true; // Changed 'inGame' to 'gameIsActive'

    private int currentSize;
    private Timer gameTimer;

    GamePanel() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.BLACK);
        setFocusable(true);
        initializeGame();
        loadGameAssets();
        addKeyListener(new KeyListenerAdapter()); // Changed KeyAdapter class name
    }

    private void loadGameAssets() {
        food = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png")).getImage();
        bodyPart = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png")).getImage();
        snakeHead = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png")).getImage();
    }

    private void initializeGame() {
        currentSize = 3;

        for (int i = 0; i < currentSize; i++) {
            snakeX[i] = 50 - i * UNIT_SIZE;
            snakeY[i] = 50;
        }

        spawnFood(); // Changed method name from locateApple()
        gameTimer = new Timer(140, this);
        gameTimer.start();
    }

    private void spawnFood() {
        int randomPosition = (int) (Math.random() * RAND_POSITION);
        foodX = randomPosition * UNIT_SIZE;
        randomPosition = (int) (Math.random() * RAND_POSITION);
        foodY = randomPosition * UNIT_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderGame(g); // Renamed draw() method to renderGame()
    }

    private void renderGame(Graphics g) {
        if (gameIsActive) {
            g.drawImage(food, foodX, foodY, this);

            for (int i = 0; i < currentSize; i++) {
                if (i == 0) {
                    g.drawImage(snakeHead, snakeX[i], snakeY[i], this);
                } else {
                    g.drawImage(bodyPart, snakeX[i], snakeY[i], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            displayGameOver(g); // Changed method name
        }
    }

    private void displayGameOver(Graphics g) {
        String message = "Game Over!";
        Font gameOverFont = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(gameOverFont);

        g.setColor(Color.RED);
        g.setFont(gameOverFont);
        g.drawString(message, (getWidth() - metrics.stringWidth(message)) / 2, getHeight() / 2);
    }

    private void moveSnake() {
        for (int i = currentSize; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        if (isMovingLeft) {
            snakeX[0] -= UNIT_SIZE;
        }
        if (isMovingRight) {
            snakeX[0] += UNIT_SIZE;
        }
        if (isMovingUp) {
            snakeY[0] -= UNIT_SIZE;
        }
        if (isMovingDown) {
            snakeY[0] += UNIT_SIZE;
        }
    }

    private void checkFoodCollision() {
        if (snakeX[0] == foodX && snakeY[0] == foodY) {
            currentSize++;
            spawnFood();
        }
    }

    private void checkWallAndBodyCollision() {
        for (int i = currentSize; i > 0; i--) {
            if (i > 4 && (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i])) {
                gameIsActive = false;
            }
        }

        if (snakeY[0] >= 300 || snakeY[0] < 0 || snakeX[0] >= 300 || snakeX[0] < 0) {
            gameIsActive = false;
        }

        if (!gameIsActive) {
            gameTimer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameIsActive) {
            checkFoodCollision();
            checkWallAndBodyCollision();
            moveSnake();
        }
        repaint();
    }

    private class KeyListenerAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyPressed = e.getKeyCode();

            if (keyPressed == KeyEvent.VK_LEFT && !isMovingRight) {
                isMovingLeft = true;
                isMovingUp = false;
                isMovingDown = false;
            }

            if (keyPressed == KeyEvent.VK_RIGHT && !isMovingLeft) {
                isMovingRight = true;
                isMovingUp = false;
                isMovingDown = false;
            }

            if (keyPressed == KeyEvent.VK_UP && !isMovingDown) {
                isMovingUp = true;
                isMovingLeft = false;
                isMovingRight = false;
            }

            if (keyPressed == KeyEvent.VK_DOWN && !isMovingUp) {
                isMovingDown = true;
                isMovingLeft = false;
                isMovingRight = false;
            }
        }
    }
}
