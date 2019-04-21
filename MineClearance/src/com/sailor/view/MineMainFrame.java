package com.sailor.view;

import com.sailor.entity.Block;
import com.sailor.utils.LayMines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MineMainFrame extends JFrame implements ActionListener {
    JButton reStart; // 开始
    Block block[][]; // 雷区
    BlockView blockView[][]; // 雷区按钮
    LayMines layMines; // 初始化雷区
    int row = 16;
    int colunm = 16;
    int mineCount = 40;
    JLabel rowLabel;
    JLabel colunmLabel;
    JLabel mineCountLabel;
    JTextField rowInput ;
    JTextField colunmInput;
    JTextField mineCountInput;
    int colorSwitch = 0;
    JPanel pCenter, pNorth,pSouth,pEast,pWest;

    // 构造
    public MineMainFrame() {
        reStart = new JButton("重新开始");
        pCenter = new JPanel();
        pNorth = new JPanel();
        pSouth = new JPanel();
        pEast = new JPanel();
        pWest = new JPanel();
        pNorth.setBackground(Color.cyan);
        pSouth.setBackground(Color.cyan);
        pEast.setBackground(Color.cyan);
        pWest.setBackground(Color.cyan);
        block = new Block[row][colunm];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colunm; j++) {
                block[i][j] = new Block();
            }
        }
        // 随机放置地雷
        layMines = new LayMines();
        layMines.layMinesForBlock(block, mineCount);
        // 绑定雷区对应按钮
        blockView = new BlockView[row][colunm];
        pCenter.setLayout(new GridLayout(row, colunm)); // 设置网格布局
//        pCenter.setPreferredSize(new Dimension(300, 150));//关键代码,设置JPanel的大小
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colunm; j++) {
                blockView[i][j] = new BlockView();
                blockView[i][j].setName(block[i][j].getName());
                pCenter.add(blockView[i][j]);
                blockView[i][j].getBlockCover().addActionListener(this);
            }
        }
        reStart.addActionListener(this);
        pNorth.add(reStart);
        setSouthPanel();
        add(pNorth, BorderLayout.NORTH);
        add(pCenter, BorderLayout.CENTER);
        add(pSouth,BorderLayout.SOUTH);
        add(pEast, BorderLayout.EAST);
        add(pWest,BorderLayout.WEST);
        setSize(500, 500);// 大小
        setVisible(true); // 可见
        validate();
        setLocationRelativeTo(null); // 窗体居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 停止程序

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        // 判断是否踩雷
        if (source != reStart) {
            int m = -1;
            int n = -1;
            // 寻找点击的块
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < colunm; j++) {
                    if (source == blockView[i][j].getBlockCover()) {
                        m = i;
                        n = j;
                        break;
                    }
                }
            }
            if (block[m][n].getMark()) { // 判断点击块是否有雷
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < colunm; j++) {
                        blockView[i][j].getBlockCover().removeActionListener(this); // 移除监听
                        if (block[i][j].getMark()) {
                            blockView[i][j].seeBlockName();
                        }
                    }
                }
            } else { //  安全
                if (block[m][n].getNumber() > 0) {
                    blockView[m][n].seeBlockName();
                } else if (block[m][n].getNumber() == 0) { // 当前块附近没有雷
                    for (int k = Math.max(m - 1, 0); k <= Math.min(m + 1, row - 1); k++) {
                        for (int t = Math.max(n - 1, 0); t <= Math.min(n + 1, colunm - 1); t++) {
                            blockView[k][t].seeBlockName();
                        }
                    }
                }
            }
        }
        if (source == reStart) { // 重置雷区
            setReStart();
        }
    }
    public void setSouthPanel(){
        rowLabel = new JLabel("行:");
        colunmLabel = new JLabel("列:");
        mineCountLabel = new JLabel("雷数:");
        rowInput = new JTextField(String.valueOf(row),5);
        colunmInput = new JTextField(String.valueOf(colunm),5);
        mineCountInput = new JTextField(String.valueOf(mineCount),5);
        pSouth.add(rowLabel);
        pSouth.add(rowInput);
        pSouth.add(colunmLabel);
        pSouth.add(colunmInput);
        pSouth.add(mineCountLabel);
        pSouth.add(mineCountInput);
        // 添加回车监听器
        rowInput.addKeyListener(new MyKeyListener());
        colunmInput.addKeyListener(new MyKeyListener());
        mineCountInput.addKeyListener(new MyKeyListener());
    }

    class MyKeyListener implements KeyListener {

        /**
         * Invoked when a key has been typed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key typed event.
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {
            char k =  e.getKeyChar();
            if( k == KeyEvent.VK_ENTER) {
                int r = Integer.parseInt(rowInput.getText());
                int c = Integer.parseInt(colunmInput.getText());
                int mc = Integer.parseInt(mineCountInput.getText());
                if (r * c >= mc) {

                }
            }
        }

        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {

        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link KeyEvent} for a definition of
         * a key released event.
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    /**
     * 重置
     */
    public void setReStart(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colunm; j++) {
                block[i][j].setMark(false);
            }
        }
        layMines.layMinesForBlock(block, mineCount);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colunm; j++) {
                blockView[i][j].setName(block[i][j].getName());
                blockView[i][j].seeBlockCover();
                blockView[i][j].getBlockCover().addActionListener(this);
            }
        }
    }

    public static void main (String[] args){
        new MineMainFrame();
    }
}

