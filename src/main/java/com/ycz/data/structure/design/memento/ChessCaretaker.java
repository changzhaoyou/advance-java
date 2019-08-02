package com.ycz.data.structure.design.memento;

/**
 * @Description:负责人
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class ChessCaretaker {
    /**
     * 备忘录
     */
    private ChessMemento chessMemento;

    public ChessMemento getChessMemento() {
        return chessMemento;
    }

    public void setChessMemento(ChessMemento chessMemento) {
        this.chessMemento = chessMemento;
    }
}
