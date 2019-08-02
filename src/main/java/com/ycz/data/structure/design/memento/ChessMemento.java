package com.ycz.data.structure.design.memento;

/**
 * @Description:备忘录类
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class ChessMemento {

    private Chessman chessman;

    /**
     * 棋子名称
     */
    private String label;
    /**
     * 行位置
     */
    private int x;
    /**
     * 列位置
     */
    private int y;

    public ChessMemento(Chessman chessman) {
        this.chessman = chessman;
    }

    public ChessMemento(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Chessman getChessman() {
        return chessman;
    }

    public void setChessman(Chessman chessman) {
        this.chessman = chessman;
    }
}
