package thongTinLopHoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmDSSV extends JFrame implements ActionListener
{
	private JLabel lblTieuDe, lblMaLop;
	private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnXem;
    private JTextField txtMaLop;
	public FrmDSSV()
	{
		setTitle("Danh sách sinh viên");
		setSize(1000,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		Box b=Box.createVerticalBox();//tao box hang doc
		add(b=Box.createVerticalBox());
		Box  b1, b2, b3, b4;
		b.add(b1=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b1.add(lblTieuDe=new JLabel("DANH SÁCH SINH VIÊN",JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,26));
		
		b2.add(lblMaLop=new JLabel("Nhập mã lớp để xem:",JLabel.RIGHT));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtMaLop=new JTextField());
		
		b3.add(Box.createHorizontalStrut(10));
		b3.add(btnXem=new JButton("Xem"));
		
		String[] headers= {"Mã sinh viên","Tên sinh viên","Địa chỉ","Email","Mã lớp"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll;
		b4.add(scroll=new JScrollPane(table=new JTable(tableModel)));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lớp học"));
		btnXem.addActionListener(this);
		
	}
	private void xemActions()
	{
		String url = "jdbc:sqlserver://localhost:3000;databaseName=qlsv";
        String username = "siu";
        String password = "messivodichworldcupUCL0981";
        String maLopCanXem=txtMaLop.getText();
		Connection conn=null;
		
		
		try {
            // Kết nối tới cơ sở dữ liệu
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SinhVien Where MaLop='"+maLopCanXem+"'");
            
            ResultSetMetaData meta = rs.getMetaData();
            
            int columnCount = meta.getColumnCount();
			
         // add column names to table model
         /*   for (int i = 1; i <= columnCount; i++) 
            {
                tableModel.addColumn(meta.getColumnName(i));
            }*/
            
            // add rows to table model
            while (rs.next()) 
            {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) 
                {
                    rowData[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(rowData);
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        
        } catch (SQLException e) 
		{
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Không có dữ liệu");
        
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object o=e.getSource();
		if(o.equals(btnXem))
		{
			xemActions();
		}
	}
}
