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
		txtrAbc.setText("IE��ȫ������ȥ����   internetѡ�����ȫ internet-���ñ���ģʽ ��ȥ��   ����internet-���ñ���ģʽ ��ȥ��  ����վ��-���ñ���ģʽ ��ȥ��  ����վ��-���ñ���ģʽ ��ȥ�� ");
		
		setBounds(200, 200, 300, 392);
		setVisible(true);
	}
}
