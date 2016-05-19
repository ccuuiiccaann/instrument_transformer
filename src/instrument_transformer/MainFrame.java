package instrument_transformer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -5238600605228685760L;

	/**
	 * Launch the application.
	 */
	private static UFrame uFrame;
	public static MainFrame frame;
	public static void init(){
		if(uFrame==null){
			uFrame=new UFrame();
		}
		uFrame.setVisible(false);
	}
	public static void main(String[] args) {
		init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(frame==null){
						frame = new MainFrame();
					}
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("main frame closed");
				System.exit(0);
			}
		});
		setTitle("互感器测试");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnUTest = new JButton("电压互感器");
		btnUTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				uFrame.setVisible(true);
			}
		});
		btnUTest.setBounds(34, 41, 141, 23);
		getContentPane().add(btnUTest);
		
		JButton btnATest = new JButton("电流互感器");
		btnATest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnATest.setBounds(264, 41, 132, 23);
		getContentPane().add(btnATest);
		
		//初始化电压的frame
	}
}
