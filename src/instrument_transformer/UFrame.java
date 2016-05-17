package instrument_transformer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class UFrame extends JFrame {

	private static final long serialVersionUID = -7263015389943520596L;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public UFrame() {
		getContentPane().setFont(new Font("SimSun", Font.PLAIN, 10));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				MainFrame.frame.setVisible(true);
			}
		});
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("测试数据导入");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("导入测试数据");
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("批量导入测试数据");
		mnNewMenu.add(menuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("导出报告");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmword = new JMenuItem("导出数据到word模板");
		mnNewMenu_1.add(mntmword);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0,0,0,0,0,0,0,0,0,0,0};
		gridBagLayout.rowHeights = new int[]{10, 6, 0,0,0,0,0,0,0,0,0,0};
		gridBagLayout.columnWeights = new double[]{1.0, 1,1,1.0,1,1,1,1,1,1,1,1};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0,1,1,1,1,1,1,1,1,1,1};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("互感器列表");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		gbc_lblNewLabel.gridwidth=3;
		gbc_lblNewLabel.gridheight=1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		gbc_table.gridwidth=3;
		gbc_table.gridheight=11;
		getContentPane().add(table, gbc_table);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 12;
		gbc_tabbedPane.gridwidth = 9;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 3;
		gbc_tabbedPane.gridy = 0;
		getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPane_1, null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPane_2, null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPane_3, null);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPane_4, null);
	}

}
