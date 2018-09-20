package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Story.Dialog;

public class Window extends JFrame implements Runnable {

    JPanel jPanel;
    private final int WIDTH = 800;
    private final int HEIGHT = 500;
    private final Dimension windowSize = new Dimension(WIDTH, HEIGHT);
    private Canvas canvas;
    private Graphics graphics;
    private BufferStrategy buffStrat;
    private final String title = "Princess Knights";
    Font font;
    private JButton optionOne, optionTwo;
    private int buttonWidth = 100;
    private int buttonHeigth = 40;

    Image image;

    static boolean running = false;
    private ArrayList<Drawable> drawingObjects;


    @Override
    public void run() {
        while (running) {
            render(new ArrayList<>());
            try {
                Thread.sleep(7);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        running = true;
    }

    public static synchronized void stop() {
        running = false;
    }

    public Window(Image backgroundImage) {
        getContentPane().setLayout(null);
        if (backgroundImage == null) {
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        } else {
            image = backgroundImage;
        }

        canvas = new Canvas();
        canvas.setMinimumSize(windowSize);
        canvas.setMaximumSize(windowSize);
        canvas.setPreferredSize(windowSize);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle(title);
        this.setLocationRelativeTo(null);

        canvas.createBufferStrategy(2);
        buffStrat = canvas.getBufferStrategy();
        graphics = buffStrat.getDrawGraphics();
        jPanel = new JPanel();
    }

    @Override
    public void paintComponents(Graphics graphics) {
        super.paintComponents(graphics);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        drawBackground();

        for (Drawable drawable : drawingObjects) {
            if (drawable instanceof Dialog) {
                drawDialog((Dialog) drawable);
            }
        }
        graphics.dispose();
    }

    public void render(ArrayList<Drawable> drawingObjects) {
        buffStrat.dispose();
        canvas.createBufferStrategy(2);
        buffStrat = canvas.getBufferStrategy();
        graphics = buffStrat.getDrawGraphics();
        this.drawingObjects = drawingObjects;
        paintComponents(graphics);
        buffStrat.show();

    }

    private void drawDialog(Dialog dialog) {
        graphics.setColor(Color.gray);
        int rectX = WIDTH / 2 - (int) (WIDTH * 0.4);
        int rectY = HEIGHT / 2 + 40;
        graphics.fillRoundRect(rectX, rectY, (int) (WIDTH * 0.8), (int) (HEIGHT * 0.35), 30, 30);
        drawText(dialog.getText(), rectX + 20, rectY + 40, 20);
    }


    private void drawText(String text, int x, int y, int size) {
        Font font = new Font("Consolas", Font.PLAIN, size);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(text, x, y);
    }

    private void drawBackground() {
        graphics.setColor(Color.BLACK);
        graphics.drawImage(image, 0, 0, 800, 500, null);
        drawText("PRINCESS KNIGHTS", (int) (WIDTH / 3.5), 50, 36);
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public static void main(String[] args) {
        Window wind = new Window(null);
        Dialog dialog = new Dialog("Banankaka", 2, "4", "3", "5", "r");
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(dialog);
        wind.render(drawables);
    }
}


