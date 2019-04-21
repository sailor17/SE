package com.sailor.view;

import javax.swing.*;
import java.awt.*;

public class BlockView extends JPanel {
    JLabel blockName;
    JButton blockCover;
    CardLayout card; // 卡片式布局

    /**
     * 从初始化
     */
    BlockView(){
        card = new CardLayout();
        setLayout(card);
        blockName = new JLabel("",JLabel.CENTER);
        blockCover = new JButton();
        add("cover",blockCover);
        add("name",blockName);
    }

    public void setName(String name){
        blockName.setText(name);
    }

    public String getName(){
        return blockName.getText();
    }

    public void seeBlockName(){
        card.show(this, "name");
        validate();
    }

    public void seeBlockCover(){
        card.show(this, "cover");
        validate();
    }

    public JButton getBlockCover(){
        return blockCover;
    }
}
