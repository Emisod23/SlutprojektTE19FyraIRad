/**
 * Created 2021-04-27
 *
 * @author
 * Me
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FyraIRad extends Canvas implements Runnable{
    private final int width = 588;
    private final int height = 554;

    private Thread thread;
    int fps = 100;
    private boolean isRunning;

    private BufferStrategy bs;
    private BufferedImage fyrairad;



    public FyraIRad() {

        try {
            fyrairad = ImageIO.read(new File("fyrairad.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Fyra i rad");
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        isRunning = false;


    }

    public void update() {

    }

    public void draw() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        g.setColor(Color.white);
        g.drawImage(fyrairad,0,50, null);
        g.setColor(new Color(255, 0, 0));
        g.fillOval(7, 58, 70, 70);
        g.fillOval(92, 141, 70, 70);
        g.dispose();
        bs.show();
    }



    public static void main(String[] args) {
        FyraIRad painting = new FyraIRad();
        painting.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double deltaT = 1000.0/fps;
        long lastTime = System.currentTimeMillis();

        while (isRunning) {
            long now = System.currentTimeMillis();
            if (now-lastTime > deltaT) {
                update();
                draw();
                lastTime = now;
            }
        }
        stop();
    }

    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    private class ML implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    private class MML implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {

        }
    }

}
