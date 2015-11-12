package com.ibm.btt.tool.ui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ibm.btt.tool.webcontrol.StartPerformanceTest;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField text_ie_path;
	private JTextField textField_4;
	private JTextField text_import_batch_files;
	private JTextField text_widget_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("BTT Broswer Performance Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 476);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		mnNewMenu.setEnabled(false);
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Menu2");
		mnNewMenu_2.setEnabled(false);
		menuBar.add(mnNewMenu_2);
		
		final JMenu Menu_Help = new JMenu("Help");
		menuBar.add(Menu_Help);
		
		JMenuItem menu_About = new JMenuItem(Messages.getString("About")); //$NON-NLS-1$
		menu_About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInfoMessage(rootPane, getAboutMessage(), Messages.getString("About"));
			}

			private String getAboutMessage() {
				// TODO Auto-generated method stub
				return "Author:Yan Dongpeng,Shang jing\n2015.11.12\nCopyright IBM BTT";
			}
		});
		
		Menu_Help.add(menu_About);
		
		JMenu mnNewMenu_3 = new JMenu(Messages.getString("MainFrame.mnNewMenu_3.text")); //$NON-NLS-1$
		Menu_Help.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem(Messages.getString("MainFrame.mntmNewMenuItem.text"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MessageFrame();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u4E3B\u9875\u5730\u5740");
		label.setBounds(32, 39, 66, 14);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(96, 36, 189, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u70B9\u51FB\u95F4\u9694");
		label_1.setBounds(32, 77, 66, 14);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(96, 74, 189, 20);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("\u8BB0\u5F55\u5468\u671F");
		label_2.setBounds(32, 122, 66, 14);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(96, 119, 189, 20);
		contentPane.add(textField_2);
		
		JLabel lblIe = new JLabel("IE\u8DEF\u5F84");
		lblIe.setBounds(32, 164, 66, 14);
		contentPane.add(lblIe);
		
		text_ie_path = new JTextField();
		text_ie_path.setEnabled(false);
		text_ie_path.setColumns(10);
		text_ie_path.setBounds(96, 161, 189, 20);
		contentPane.add(text_ie_path);
		
		final JCheckBox checkbox_ie_default_path = new JCheckBox("IE\u662F\u9ED8\u8BA4\u8DEF\u5F84");
		checkbox_ie_default_path.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evaent) {
				if (checkbox_ie_default_path.isSelected()) {
					widgets_disable(text_ie_path);
				}else {
					widgets_enable(text_ie_path);
				}
			}
		});
		checkbox_ie_default_path.setSelected(true);
		checkbox_ie_default_path.setBackground(SystemColor.info);
		checkbox_ie_default_path.setBounds(296, 160, 130, 23);
		contentPane.add(checkbox_ie_default_path);
		
		JLabel label_3 = new JLabel("\u62A5\u544A\u7ED3\u679C\u8DEF\u5F84");
		label_3.setBounds(321, 39, 105, 14);
		contentPane.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(452, 36, 189, 20);
		contentPane.add(textField_4);
		
		JLabel label_improt_batch_files = new JLabel("\u5BFC\u5165\u6279\u91CF\u6587\u4EF6");
		label_improt_batch_files.setBounds(412, 74, 102, 14);
		contentPane.add(label_improt_batch_files);
		
		text_import_batch_files = new JTextField();
		text_import_batch_files.setColumns(10);
		text_import_batch_files.setBounds(496, 71, 189, 20);
		contentPane.add(text_import_batch_files);
		
		JLabel lblWidgetId = new JLabel("widget ID");
		lblWidgetId.setBounds(412, 119, 102, 14);
		contentPane.add(lblWidgetId);
		
		text_widget_id = new JTextField();
		text_widget_id.setEnabled(false);
		text_widget_id.setColumns(10);
		text_widget_id.setBounds(496, 116, 189, 20);
		contentPane.add(text_widget_id);
		
		JRadioButton radio_batch_record = new JRadioButton("\u6279\u91CF\u8BB0\u5F55");
		radio_batch_record.setBackground(SystemColor.info);
		radio_batch_record.setSelected(true);
		radio_batch_record.setBounds(317, 73, 109, 23);
		contentPane.add(radio_batch_record);
		
		JRadioButton radio_single_record = new JRadioButton("\u5355\u4E2A\u8BB0\u5F55");
		radio_single_record.setBackground(SystemColor.info);
		radio_single_record.setBounds(317, 118, 109, 23);
		contentPane.add(radio_single_record);
		
		final JButton button_upload = new JButton(Messages.getString("Upload")); //$NON-NLS-1$
		button_upload.setBounds(707, 70, 77, 23);
		contentPane.add(button_upload);
		
		radio_batch_record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				widgets_disable(text_widget_id);
				widgets_enable(text_import_batch_files,button_upload);
			}

			
		});
		
		radio_single_record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				widgets_disable(text_import_batch_files,button_upload);
				widgets_enable(text_widget_id);
			}

			
		});
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(radio_batch_record);
		bg.add(radio_single_record);
		
		JCheckBox checkBox = new JCheckBox("\u751F\u6210\u65B0\u62A5\u544A");
		checkBox.setBackground(SystemColor.info);
		checkBox.setBounds(647, 35, 97, 23);
		contentPane.add(checkBox);
		
		
		
		final JButton button_start_record = new JButton("\u5F00\u59CB\u8BB0\u5F55");
		button_start_record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				widgets_disable(button_start_record);
				try {
					new StartPerformanceTest().start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showErrorMessage(rootPane, e,"exception");
					
				}finally{
					widgets_enable(button_start_record);						
				}
			}
		});
		button_start_record.setBounds(449, 160, 89, 23);
		contentPane.add(button_start_record);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 192, 730, 211);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
	}
	
	private void widgets_disable(JComponent...jcom) {
		// TODO Auto-generated method stub
		for (JComponent jComponent : jcom) {
			jComponent.setEnabled(false);
		}
	}
	private void widgets_enable(JComponent...jcom) {
		// TODO Auto-generated method stub
		for (JComponent jComponent : jcom) {
			jComponent.setEnabled(true);
		}
	}
	private void showErrorMessage(JComponent jwidget,Exception e,String title){
		JOptionPane.showMessageDialog(jwidget, e,title, JOptionPane.ERROR_MESSAGE);
		
	}
	private void showInfoMessage(JComponent jwidget,String message,String title){
		JOptionPane.showMessageDialog(jwidget, message,title, JOptionPane.INFORMATION_MESSAGE);
		
	}
}
