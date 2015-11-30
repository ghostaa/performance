package com.ibm.btt.tool.ui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

import org.json.JSONObject;

import com.ibm.btt.tool.common.JSONObjectKey;
import com.ibm.btt.tool.common.ToolProperty;
import com.ibm.btt.tool.util.ConfigManager;
import com.ibm.btt.tool.webcontrol.StartPerformanceTest;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_url;
	private JTextField text_recordInterval;
	private JTextField text_waitTime;
	private JTextField text_ie_path;
	private JTextField textField_4;
	private JTextField text_import_batch_files;
	private JTextField text_widget_id;
	private JTextField text_totaltimes;
	private JCheckBox singleThread;//是否单线程运行
	public static JTextArea textArea = new JTextArea();
	private ConfigManager configMng=new ConfigManager();
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
		loadConfigFile();
		
		setTitle("BTT Broswer Performance Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 476);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu(Messages.getString("MainFrame.mnNewMenu.text")); //$NON-NLS-1$
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu(Messages.getString("MainFrame.mnNewMenu_1.text")); //$NON-NLS-1$
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem menuItem_save_config = new JMenuItem(Messages.getString("MainFrame.menuItem.text"));
		menuItem_save_config.setSelected(true);
		menuItem_save_config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					configMng.saveConfigFileAsJSONObject(getConfigJSONObjectFromFront());
					showInfoMessage(rootPane, "保存成功", "配置文件保存结果");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		mnNewMenu_1.add(menuItem_save_config);
		
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
		
		
		
		final JButton button_start_record = new JButton("\u5F00\u59CB\u8BB0\u5F55");
		button_start_record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				widgets_disable(button_start_record);
				gatherInformation();
				if(singleThread.isSelected()){
					try {
						new StartPerformanceTest().start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						widgets_enable(button_start_record);						
					}
				}else {
					
					try {
						
						Thread thread=new Thread(new StartPerformanceTest());
						thread.start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						showErrorMessage(rootPane, e,"exception");
						
					}finally{
						widgets_enable(button_start_record);						
					}
				}
			}

			
		});
		button_start_record.setBounds(402, 160, 88, 23);
		contentPane.add(button_start_record);
		
		JLabel label = new JLabel("\u4E3B\u9875\u5730\u5740");
		label.setBounds(32, 39, 66, 14);
		contentPane.add(label);
		
		text_url = new JTextField();
		text_url.setBounds(96, 36, 688, 20);
		text_url.setText(ToolProperty.url);
		text_url.setColumns(10);
		contentPane.add(text_url);
		
		JLabel label_1 = new JLabel(Messages.getString("MainFrame.label_1.text")); //$NON-NLS-1$
		label_1.setBounds(32, 111, 66, 14);
		contentPane.add(label_1);
		
		text_recordInterval = new JTextField();
		text_recordInterval.setColumns(10);
		text_recordInterval.setBounds(96, 108, 189, 20);
		text_recordInterval.setText(String.valueOf(ToolProperty.recordInterval));
		contentPane.add(text_recordInterval);
		
		JLabel label_2 = new JLabel(Messages.getString("MainFrame.label_2.text")); //$NON-NLS-1$
		label_2.setBounds(32, 139, 66, 14);
		contentPane.add(label_2);
		
		text_waitTime = new JTextField();
		text_waitTime.setColumns(10);
		text_waitTime.setBounds(96, 136, 189, 20);
		text_waitTime.setText(String.valueOf(ToolProperty.waitTime));
		contentPane.add(text_waitTime);
		
		JLabel lblIe = new JLabel("IE\u8DEF\u5F84");
		lblIe.setBounds(32, 164, 66, 14);
		contentPane.add(lblIe);
		
		text_ie_path = new JTextField();
		text_ie_path.setEnabled(false);
		text_ie_path.setColumns(10);
		text_ie_path.setBounds(96, 161, 189, 20);
		contentPane.add(text_ie_path);
		
		final JCheckBox checkbox_ie_default_path = new JCheckBox("IE\u662F\u9ED8\u8BA4\u8DEF\u5F84");
		checkbox_ie_default_path.setEnabled(false);
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
		checkbox_ie_default_path.setBounds(296, 160, 126, 23);
		contentPane.add(checkbox_ie_default_path);
		
		JLabel label_3 = new JLabel("\u62A5\u544A\u7ED3\u679C\u8DEF\u5F84");
		label_3.setBounds(317, 83, 105, 14);
		contentPane.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(412, 80, 189, 20);
		contentPane.add(textField_4);
		
		JLabel label_improt_batch_files = new JLabel("\u5BFC\u5165\u6279\u91CF\u6587\u4EF6");
		label_improt_batch_files.setBounds(412, 111, 126, 14);
		contentPane.add(label_improt_batch_files);
		
		text_import_batch_files = new JTextField();
		text_import_batch_files.setColumns(10);
		text_import_batch_files.setBounds(496, 108, 189, 20);
		contentPane.add(text_import_batch_files);
		
		JLabel lblWidgetId = new JLabel("widget ID");
		lblWidgetId.setBounds(412, 139, 105, 14);
		contentPane.add(lblWidgetId);
		
		text_widget_id = new JTextField();
		text_widget_id.setEnabled(false);
		text_widget_id.setColumns(10);
		text_widget_id.setBounds(496, 136, 189, 20);
		contentPane.add(text_widget_id);
		
		JRadioButton radio_batch_record = new JRadioButton("\u6279\u91CF\u8BB0\u5F55");
		radio_batch_record.setBackground(SystemColor.info);
		radio_batch_record.setSelected(true);
		radio_batch_record.setBounds(317, 107, 109, 23);
		contentPane.add(radio_batch_record);
		
		JRadioButton radio_single_record = new JRadioButton("\u5355\u4E2A\u8BB0\u5F55");
		radio_single_record.setBackground(SystemColor.info);
		radio_single_record.setBounds(317, 135, 109, 23);
		contentPane.add(radio_single_record);
		
		final JButton button_upload = new JButton(Messages.getString("Upload")); //$NON-NLS-1$
		button_upload.setBounds(707, 107, 77, 23);
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
		checkBox.setEnabled(false);
		checkBox.setSelected(true);
		checkBox.setBackground(SystemColor.info);
		checkBox.setBounds(647, 78, 97, 23);
		contentPane.add(checkBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 192, 752, 211);
		contentPane.add(scrollPane);
		
		
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		
		JLabel label_4 = new JLabel(Messages.getString("MainFrame.label_4.text")); //$NON-NLS-1$
		label_4.setBounds(32, 86, 66, 14);
		contentPane.add(label_4);
		
		text_totaltimes = new JTextField();
		text_totaltimes.setColumns(10);
		text_totaltimes.setBounds(96, 80, 189, 20);
		text_totaltimes.setText(String.valueOf(ToolProperty.totalTimes));
		contentPane.add(text_totaltimes);
		
		singleThread = new JCheckBox(Messages.getString("MainFrame.singleThread.text")); //$NON-NLS-1$
		singleThread.setBackground(SystemColor.info);
		singleThread.setSelected(ToolProperty.singleThread);
		singleThread.setBounds(496, 160, 116, 23);
		contentPane.add(singleThread);
	}
	private void loadConfigFile() {
		JSONObject jsonObject=configMng.loadConfigFileAsJSONObject();
		ToolProperty.url=jsonObject.getString(JSONObjectKey.CONFIG_URL);
		ToolProperty.recordInterval=jsonObject.getInt(JSONObjectKey.CONFIG_RECORD_INTERVAL);
		ToolProperty.totalTimes=jsonObject.getInt(JSONObjectKey.CONFIG_TOTAL_TIMES);
		ToolProperty.waitTime=jsonObject.getInt(JSONObjectKey.CONFIG_WAIT_TIME);
		ToolProperty.singleThread=jsonObject.getBoolean(JSONObjectKey.CONFIG_SINGLE_THREAD);
		
	}

	/**
	 * 批量disable控件
	 * @param jcom 可以传入多个控件
	 */
	private void widgets_disable(JComponent...jcom) {
		// TODO Auto-generated method stub
		for (JComponent jComponent : jcom) {
			jComponent.setEnabled(false);
		}
	}
	/**
	 * 批量enable控件
	 * @param jcom 可以传入多个空间
	 */
	private void widgets_enable(JComponent...jcom) {
		// TODO Auto-generated method stub
		for (JComponent jComponent : jcom) {
			jComponent.setEnabled(true);
		}
	}
	/**
	 * 显示错误信息
	 * @param jwidget 基于哪个控件
	 * @param e 异常类
	 * @param title 题目
	 */
	private void showErrorMessage(JComponent jwidget,Exception e,String title){
		JOptionPane.showMessageDialog(jwidget, e,title, JOptionPane.ERROR_MESSAGE);
		
	}
	/**
	 * 显示信息
	 * @param jwidget 基于哪个控件
	 * @param message 信息内容
	 * @param title 题目
	 */
	private void showInfoMessage(JComponent jwidget,String message,String title){
		JOptionPane.showMessageDialog(jwidget, message,title, JOptionPane.INFORMATION_MESSAGE);
		
	}
	/**
	 * 收集前端所有数据到系统变量
	 */
	private void gatherInformation() {
		if (text_url.getText()!=null && !"".equals(text_url.getText())) {
			ToolProperty.url=text_url.getText();
			ToolProperty.totalTimes= Integer.valueOf(text_totaltimes.getText());
			ToolProperty.recordInterval=Integer.valueOf(text_recordInterval.getText());
			ToolProperty.waitTime= Integer.valueOf(text_waitTime.getText());
			
		}else {
			//load default file url;
		}
	}
	/**
	 * 获得前端所有数据，并返回他的json对象
	 * @return 前端所有数据对应的json对象
	 */
	private JSONObject getConfigJSONObjectFromFront() {
		JSONObject configJsonObject=new JSONObject();
		configJsonObject.put(JSONObjectKey.CONFIG_URL, text_url.getText());
		configJsonObject.put(JSONObjectKey.CONFIG_TOTAL_TIMES, text_totaltimes.getText());
		configJsonObject.put(JSONObjectKey.CONFIG_RECORD_INTERVAL, text_recordInterval.getText());
		configJsonObject.put(JSONObjectKey.CONFIG_WAIT_TIME, text_waitTime.getText());
		configJsonObject.put(JSONObjectKey.CONFIG_SINGLE_THREAD, singleThread.isSelected());
		
		return configJsonObject;
	}
}
