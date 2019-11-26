package com.javarush.task.task22.task2213;

public class Figure {
    private int x;
    private int y;
    private int[][] matrix;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    public void left(){}//для движения фигурки влево.
    public void right(){} //для движения фигурки вправо.
    public void down(){} //для движения фигурки вниз.
    public void up(){} //движения фигурки вверх.
    public void rotate(){} //для поворота фигурки вокруг главной диагонали.
    public void downMaximum(){} //падение фигурки в низ до дна.
    public boolean isCurrentPositionAvailable() { //проверка - может ли фигурка быть помещена в текущую позицию. Для теста захардкодь результат в true, пока мы не реализовали логику.
        return true;
    }
    public void landed(){} //вызывается, когда фигурка достигла дна или уперлась в другую фигурку
}
