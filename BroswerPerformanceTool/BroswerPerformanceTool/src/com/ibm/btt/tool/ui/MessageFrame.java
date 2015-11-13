package com.ibm.btt.tool.ui;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class MessageFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageFrame() {
		setTitle("IE\u7684\u914D\u7F6E\u65B9\u5F0F");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 284, 362);
		getContentPane().add(scrollPane_1);
		
		JTextArea txtrAbc = new JTextArea();
		scrollPane_1.setViewportView(txtrAbc);
		txtrAbc.setLineWrap(true);
		txtrAbc.setText("IE安全保护都去掉：   internet选项——安全 internet-启用保护模式 勾去掉   本地internet-启用保护模式 勾去掉  可信站点-启用保护模式 勾去掉  受限站点-启用保护模式 勾去掉 ");
		
		setBounds(200, 200, 300, 392);
		setVisible(true);
	}
}
