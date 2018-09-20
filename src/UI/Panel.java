package UI;

import Story.Dialog;
import Story.Scenario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Panel extends JPanel {
    Image background = null;
    String stringText = "";
    ArrayList<Drawable> drawingObjects = new ArrayList<>();
    private Graphics graphics;
    private int scenarioLap = 0;
    private boolean drawingScenarios = false;
    private int selectedChoice;
    private boolean currentScenarioDone = false;
    private int scenarioSize;
    private boolean firstDraw = true;
    private Image dialogBackground = null;
    private Image princessImage;
    private Font font;


    public Panel() {
        setPreferredSize(new Dimension(800, 600));
        try {
            dialogBackground = ImageIO.read(new File("res/images/dialogBackground.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDrawable(ArrayList<Drawable> drawable) {
        this.drawingObjects = drawable;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(dialogBackground, 0, 0, 785, 600, null);
        drawText(graphics, "Asadotterns omen", 250, 150, 36);

        for (Drawable drawable : drawingObjects) {
            if (drawable instanceof Dialog) {
                Dialog dialog = (Dialog) (drawable);
                drawDialog(dialog, graphics);
            } else if (drawable instanceof Scenario) {
                currentScenarioDone = false;
                drawingScenarios = true;
                Scenario scenario = (Scenario) (drawable);
                if (scenario.getScenarioDone()) {
                    currentScenarioDone = true;
                    if (firstDraw) {
                        selectedChoice++;
                        firstDraw = false;
                    }
                }
                drawScenario(scenario, graphics);
            }
        }
        scenarioLap = 0;
        drawingScenarios = false;
    }

    public void setText(String string) {
        stringText = string;
    }

    public void render(ArrayList<Drawable> drawingObjects) {
        if (drawingObjects.size() > 0) {
            Drawable drawable = drawingObjects.get(0);
            if (drawable instanceof Story.Dialog) {
                setText(((Dialog) drawable).getText());
            } else if (drawable instanceof Story.Scenario) {
                scenarioSize = drawingObjects.size();
            }
        }
        repaint();
    }


    private void drawDialog(Dialog dialog, Graphics graphics) {
        graphics.setColor(Color.gray);
        int rectX = (int) (WIDTH / 2 - WIDTH * 0.6);
        int rectY = HEIGHT / 2 + 40;
        graphics.fillRoundRect(rectX, rectY, (int) (WIDTH * 0.8), (int) (HEIGHT * 0.35), 30, 30);
        drawText(graphics, dialog.getText(), rectX + 115, rectY + 200, 20);
    }

    private void drawText(Graphics graphics, String text, int x, int y, int size) {
        int length = 61;
        int lineLength = length;
        graphics.setColor(Color.BLACK);
        Font font = new Font("Book Antiqua", Font.PLAIN, size);
        graphics.setFont(font);
        while (text.length() > length) {
            for (int i = length; i > 0; i--) {
                if (text.charAt(i) == ' ') {
                    lineLength = i;
                    break;
                }
            }
            String viewLine = text.substring(0, lineLength);
            text = text.substring(lineLength, text.length());
            graphics.drawString(viewLine, x, y);
            y = y + 25;
        }

        if (drawingScenarios && currentScenarioDone) {
            graphics.setColor(Color.RED);
        } else if (drawingScenarios && scenarioLap == selectedChoice) {
            graphics.setColor(Color.GREEN);
        }
        graphics.drawString(text, x, y);
    }

    public void drawScenario(Scenario scenario, Graphics graphics) {
        int rectX = (int) (WIDTH / 2 - WIDTH * 0.2);
        int rectY = HEIGHT / 2 + 40;
        drawText(graphics, scenario.getText(), rectX + 325, (rectY + 250) + (scenarioLap * 25), 25);
        scenarioLap++;
    }

    public int getSelectedChoice() {
        firstDraw = true;
        return selectedChoice;
    }

    public void decrementChoice() {
        if (selectedChoice > 0) {
            selectedChoice--;
            repaint();
        }
    }

    public void incrementChoice() {
        if (selectedChoice < scenarioSize - 1) {
            selectedChoice++;
            repaint();
        }
    }
}



