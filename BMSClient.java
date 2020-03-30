package BMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class BMSClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane;
	private JPanel panel1, panel2;
	private String[] ct = null;
	private JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6;
	private JTextField textField1,textField2,textField3,textField4;
	@SuppressWarnings("rawtypes")
	private JComboBox jcb1=null;
	private JRadioButton dx1,dx2;
	private ButtonGroup dxz;
	private JTable table;
	
 	public BMSClient() // 构造函数
	{
		panel1 = new JPanel();
		panel2 = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		this.setTitle("Book Management System");
		this.getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

		splitPane.setLeftComponent(panel1); // 布局中添加组件，面板1
		splitPane.setRightComponent(panel2); // 布局中添加组件，面板2
		panel1.setBorder(BorderFactory.createLineBorder(Color.white));
		panel2.setBorder(BorderFactory.createLineBorder(Color.white));
		splitPane.setDividerSize(10);// 设置分割线的宽度

		JButton jButton1 = new JButton("Add Book");
		JButton jButton2 = new JButton("Add Kind");
		JButton jButton3 = new JButton("Delete Book");
		JButton jButton4 = new JButton("Find Book");

		jButton1.setEnabled(true);
		jButton2.setEnabled(true);
		jButton3.setEnabled(true);

		panel1.add(jButton1);
		panel1.add(jButton2);
		panel1.add(jButton3);
		panel1.add(jButton4);

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBooks();
			}
		});
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addKind();
			}
		});
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBooks();
			}
		});
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});

		splitPane.setOneTouchExpandable(true);
		setVisible(true);
		splitPane.setDividerLocation(0.8);
		// 设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。
		splitPane.setEnabled(false);
		// 设置分隔条禁止拖动

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initAddBooksPanel(){
		panel2.removeAll();
		panel2.repaint();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));

		jLabel1 = new JLabel("Book Name");
		jLabel1.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(jLabel1);

		textField1 = new JTextField(8);
		textField1.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(textField1);

		jLabel2 = new JLabel("Book ID");
		jLabel2.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(jLabel2);

		textField2 = new JTextField(8);
		textField2.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(textField2);

		jLabel3 = new JLabel("Book Author");
		jLabel3.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(jLabel3);

		textField3 = new JTextField(8);
		textField3.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(textField3);

		jLabel4 = new JLabel("Author Sex");
		jLabel4.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(jLabel4);

		dx1 = new JRadioButton("Male");
		dx2 = new JRadioButton("Female");
		dx1.setFont(new Font(null, Font.PLAIN, 18));
		dx2.setFont(new Font(null, Font.PLAIN, 18));
		dxz = new ButtonGroup();
		dxz.add(dx1);
		dxz.add(dx2);
		panel2.add(dx1);
		panel2.add(dx2);

		jLabel5 = new JLabel("Book Kind");
		jLabel5.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(jLabel5);

		
		jcb1 = new JComboBox(ct);
		jcb1.setPreferredSize(new Dimension(320, 30));
		jcb1.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(jcb1);
		
		jcb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println(jcb1.getSelectedItem().toString());
			}
		});

		jLabel6 = new JLabel("Book Description");
		jLabel6.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(jLabel6);

		textField4 = new JTextField(20);
		textField4.setFont(new Font(null, Font.PLAIN, 18));
		panel2.add(textField4);
	}
	private void initDeleteBooksPanel(){
		jLabel1 = new JLabel("Enter the id of the book");
		jLabel1.setFont(new Font(null, Font.PLAIN, 20));
		jLabel1.setBounds(10, 150, 250, 30);
		panel2.add(jLabel1);

		final JTextField textField1 = new JTextField(8);
		textField1.setFont(new Font(null, Font.PLAIN, 20));
		textField1.setBounds(260,150,120,30);
		panel2.add(textField1);
	}
	private void initAddKindPanel(){
		jLabel1 = new JLabel("Enter the kind of book");
		jLabel1.setFont(new Font(null, Font.PLAIN, 20));
		jLabel1.setBounds(10, 150, 250, 30);
		panel2.add(jLabel1);

		textField1 = new JTextField(8);
		textField1.setFont(new Font(null, Font.PLAIN, 20));
		textField1.setBounds(260,150,120,30);
		panel2.add(textField1);
	}
	private void initQueryPanel(){
		jLabel1 = new JLabel("By Book ID");
		jLabel1.setFont(new Font(null, Font.PLAIN, 12));
		jLabel1.setBounds(10, 10, 80, 30);
		panel2.add(jLabel1);

		textField1 = new JTextField(8);
		textField1.setFont(new Font(null, Font.PLAIN, 12));
		textField1.setBounds(100, 10, 80, 30);
		panel2.add(textField1);

		jLabel2 = new JLabel("By Book Name");
		jLabel2.setFont(new Font(null, Font.PLAIN, 12));
		jLabel2.setBounds(190, 10, 90, 30);
		panel2.add(jLabel2);

		textField2 = new JTextField(8);
		textField2.setFont(new Font(null, Font.PLAIN, 12));
		textField2.setBounds(290, 10, 80, 30);
		panel2.add(textField2);

	}
	private void initTable(){
		table.getTableHeader().setBounds(10, 0, 500, 30);
		table.setBounds(10, 30, 500, 350);

		// 设置table内容居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		// 这句和上句作用一样
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);

		// 设置表格内容颜色
		table.setForeground(Color.BLACK); // 字体颜色
		table.setFont(new Font(null, Font.PLAIN, 14)); // 字体样式
		table.setSelectionForeground(Color.DARK_GRAY); // 选中后字体颜色
		table.setSelectionBackground(Color.LIGHT_GRAY); // 选中后字体背景
		table.setGridColor(Color.GRAY); // 网格颜色

		// 设置表头
		table.getTableHeader().setFont(new Font(null, Font.BOLD, 12)); // 设置表头名称字体样式
		table.getTableHeader().setForeground(Color.RED); // 设置表头名称字体颜色
		table.getTableHeader().setResizingAllowed(false); // 设置不允许手动改变列宽
		table.getTableHeader().setReorderingAllowed(false); // 设置不允许拖动重新排序各列

		table.setRowHeight(30);

		for (int i = 0; i < 6; i++) {
			TableColumn tableColumn = table.getColumnModel().getColumn(i);
			tableColumn.setPreferredWidth(60);
		}
		TableColumn tableColumn = table.getColumnModel().getColumn(1);
		tableColumn.setPreferredWidth(80);
		tableColumn = table.getColumnModel().getColumn(4);
		tableColumn.setPreferredWidth(80);
		tableColumn = table.getColumnModel().getColumn(5);
		tableColumn.setPreferredWidth(160);
	}
	private void addBooks() {
		try {
			BMSService BMSObj = (BMSService) Naming.lookup("rmi://localhost:8889/BMSService");
			ct = BMSObj.getKind();
		} catch (Exception x) {
			x.printStackTrace();
		}
		initAddBooksPanel();
		
		JButton jButton1 = new JButton("Submit");
		jButton1.setFont(new Font(null, Font.PLAIN, 18));
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = textField1.getText();
				String s2 = textField2.getText();
				String s3 = textField3.getText();
				String sex = null;
				if (dx1.isSelected()) {
					sex = "Male";
				} else if (dx2.isSelected()) {
					sex = "Female";
				}
				String s4 = jcb1.getSelectedItem().toString();
				String s5 = textField4.getText();
				try {
					BMSService BMSObj = (BMSService) Naming.lookup("rmi://localhost:8889/BMSService");
					if(BMSObj.add(s1, s2, s3, sex, s4, s5).equals("success")){
						JOptionPane.showMessageDialog(null, "Added successfully");
					}
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		panel2.add(jButton1);

		JButton jButton2 = new JButton("Reset");
		jButton2.setFont(new Font(null, Font.PLAIN, 18));
		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
			}
		});
		panel2.add(jButton2);
		setVisible(true);
	}

	private void deleteBooks() {
		panel2.removeAll();
		panel2.repaint();
		panel2.setLayout(null);
		initDeleteBooksPanel();
		JButton jButton1 = new JButton("Delete");
		jButton1.setFont(new Font(null, Font.PLAIN, 20));
		jButton1.setBounds(350, 300, 100, 50);
		panel2.add(jButton1);
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = textField1.getText();
				if(s1.equals("")){
					JOptionPane.showMessageDialog(null, "Please enter information !╮(╯▽╰)╭");
					return;
				}
				try {
					BMSService BMSObj = (BMSService) Naming.lookup("rmi://localhost:8889/BMSService");
					String state = BMSObj.delete(s1);
					if(state.equals("success")){
						JOptionPane.showMessageDialog(null, "Deleted successfully");
					}
				} catch (Exception x) {
					x.printStackTrace();
				}

			}
		});
		setVisible(true);
	}

	private void addKind() {
		panel2.removeAll();
		panel2.repaint();
		panel2.setLayout(null);
		initAddKindPanel();
		JButton jButton1 = new JButton("Add");
		jButton1.setFont(new Font(null, Font.PLAIN, 20));
		jButton1.setBounds(350, 300, 100, 50);
		panel2.add(jButton1);
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BMSService BMSObj;
				try {
					BMSObj = (BMSService) Naming.lookup("rmi://localhost:8889/BMSService");
					String s1 = textField1.getText();
					if(BMSObj.addKind(s1).equals("success")){
						JOptionPane.showMessageDialog(null, "Added successfully");
					}
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});	
		setVisible(true);
	}

	private void query() {
		panel2.removeAll();
		panel2.repaint();
		// 创建内容面板，使用边界布局
		panel2.setLayout(null);
		initQueryPanel();
		JButton jButton1 = new JButton("Find");
		jButton1.setFont(new Font(null, Font.PLAIN, 12));
		jButton1.setBounds(380, 10, 80, 30);
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = textField1.getText();
				String s2 = textField2.getText();
				String[][] rowData2 = null;
				try {
					BMSService BMSObj = (BMSService) Naming.lookup("rmi://localhost:8889/BMSService");
					if(s1.equals("")&&s2.equals("")){
						JOptionPane.showMessageDialog(null, "Please enter information !╮(╯▽╰)╭");
						return;
					}
					else if(s1.equals("")&&!s2.equals("")){
						 rowData2 = BMSObj.query(s2);
					}
		            else{
		            	 rowData2 = BMSObj.query(s1, s2);
		             }
				} catch (Exception x) {
					x.printStackTrace();
				}
				Object[] columnNames = { "Book ID", "Book Name", "Book Author", "Author Sex", "Book Kind", "Book Description" };
				table = new JTable(rowData2, columnNames);
				initTable();
				panel2.add(table.getTableHeader(), BorderLayout.NORTH);
				panel2.add(table, BorderLayout.CENTER);

				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				JScrollPane scrollPane_2 = new JScrollPane();
				panel2.add(scrollPane_2, BorderLayout.CENTER);
				scrollPane_2.setViewportView(table);
				scrollPane_2.setBounds(10, 50, 450, 300);
			}
		});
		panel2.add(jButton1);
		setVisible(true);
	}

	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
		// TODO Auto-generated method stub
		BMSClient instance = new BMSClient();
		instance.setSize(600, 400);
		instance.setResizable(false);
		instance.setLocationRelativeTo(null);
		instance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// System.out.println(BMSObj.add(5, 6));

	}

}
