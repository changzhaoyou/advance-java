package com.ycz.data.structure.design.memento;

/**
 * @Description:测试
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class Test {

    public static void main(String[] args) {
        ChessCaretaker caretaker = new ChessCaretaker();
        Chessman chessman = new Chessman("兵", 1, 1);
        display(chessman);
        caretaker.setChessMemento(chessman.save());
        chessman.setX(2);
        chessman.setY(4);
        display(chessman);
        System.out.println("/*********回退一步***********/");
        chessman.restore(caretaker.getChessMemento());
        display(chessman);

        System.out.println("/*********************************/");

        ChessCaretaker caretaker1 = new ChessCaretaker();
        Chessman chessman1 = new Chessman("车", 1, 1);
        display(chessman1);
        caretaker1.setChessMemento(chessman1.save1());
        Chessman chessman2 = new Chessman("车", 1, 1);
        chessman2.setX(2);
        chessman2.setY(4);
        display(chessman2);
        System.out.println("/*********回退一步***********/");
        chessman2.restore1(caretaker1.getChessMemento());
        display(chessman1);

    }

    public static void display(Chessman chessman) {
        System.out.println("当前棋子" + chessman.getLabel() + ",第" + chessman.getX() + "行,列" + chessman.getY() + "行");
    }
}
