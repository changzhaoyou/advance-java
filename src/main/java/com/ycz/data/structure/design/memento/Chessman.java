package com.ycz.data.structure.design.memento;

/**
 * @Description:原发器
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class Chessman {
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

    public Chessman() {
    }

    public Chessman(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    /**
     * 保存备忘录
     *
     * @return
     */
    public ChessMemento save() {
        return new ChessMemento(this.label, this.x, this.y);
    }


    /**
     * 保存备忘录
     *
     * @return
     */
    public ChessMemento save1() {
        return new ChessMemento(this);
    }
    /**
     * 恢复备忘录
     *
     * @return
     */
    public void restore(ChessMemento chessMemento) {
        this.label = chessMemento.getLabel();
        this.x = chessMemento.getX();
        this.y = chessMemento.getY();
    }

    /**
     * 恢复备忘录
     *
     * @return
     */
    public void restore1(ChessMemento chessMemento) {
        this.label = chessMemento.getChessman().getLabel();
        this.x = chessMemento.getChessman().getX();
        this.y = chessMemento.getChessman().getY();
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
}
