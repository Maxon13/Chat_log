package ru.geekbrains.java_two.lesson_b.games.circles;

import ru.geekbrains.java_two.lesson_b.games.common.CommonObject;
import ru.geekbrains.java_two.lesson_b.games.common.Controller;
import ru.geekbrains.java_two.lesson_b.games.common.GameCanvas;
import ru.geekbrains.java_two.lesson_b.games.common.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame implements Controller {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private CommonObject[] objects = new CommonObject[1];
    private int objectsCount;

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < objectsCount; i++) {
            objects[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < objectsCount; i++) {
            objects[i].render(canvas, g);
        }
    }

    @Override
    public void onDrawCanvas(GameCanvas c, Graphics g, float deltaTime) {
        update(c, deltaTime);
        render(c, g);
    }

    private void initApplication() {
        addObjects(new Background());
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        GameCanvas canvas = new GameCanvas(this);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    addObjects(new Ball(e.getX(), e.getY()));
                else if (e.getButton() == MouseEvent.BUTTON3)
                    removeSprite();
            }
        });
        add(canvas);
        initApplication();
        setVisible(true);
    }

    private void addObjects(CommonObject s) {
        if (objectsCount == objects.length) {
            CommonObject[] temp = new CommonObject[objects.length * 2];
            System.arraycopy(objects, 0, temp, 0, objects.length);
            objects = temp;
        }
        objects[objectsCount++] = s;
    }

    private void removeSprite() {
        if (objectsCount > 1) objectsCount--;
    }

    public static void main(String[] args) {
        new MainCircles();
    }

}
