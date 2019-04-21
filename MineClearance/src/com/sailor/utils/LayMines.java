package com.sailor.utils;

import com.sailor.entity.Block;

import java.util.LinkedList;

/**
 * 随机生成雷区
 */
public class LayMines {
    public void layMinesForBlock(Block block[][],int mineCount){
        int row = block.length; // 行
        int column = block[0].length; // 列
        LinkedList<Block> list = new LinkedList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                list.add(block[i][j]);
            }
        }
        while (mineCount>0){ // 设置雷
            int size = list.size();
            int randomIndex = (int)(Math.random()*size);
            Block b = list.get(randomIndex);
            b.setName("雷");
            b.setMark(true);
            list.remove(randomIndex);
            mineCount--;
        }
        // 统计格子附近的雷数
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(block[i][j].getMark()){ } // 雷
                else {
                    int mineNumber = 0;
                    // 遍历以black[i][j]为中心点的九宫格
                    for(int k=Math.max(i-1,0);k<=Math.min(i+1, row-1);k++){
                        for(int t = Math.max(j-1, 0);t<=Math.min(j+1, column-1);t++){
                            if(block[k][t].getMark()){
                                mineNumber++;
                            }
                        }
                    }
                    if(mineNumber>0){
                        block[i][j].setName(""+mineNumber);
                        block[i][j].setNumber(mineNumber);
                    }else {
                        block[i][j].setName("");
                        block[i][j].setNumber(mineNumber);
                    }
                }
            }
        }
    }
}
