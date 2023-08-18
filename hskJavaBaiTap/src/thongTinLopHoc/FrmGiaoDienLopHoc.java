package thongTinLopHoc;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

public class FrmGiaoDienLopHoc extends JFrame implements ActionListener
{
	private JTextField txtMaLop, txtTenLop, txtGVCN;
	private JButton btnThem, btnTimKiem, btnSua, btnXoa, btnXemDSSV,btnFrameSV;
	private DefaultTableModel tableModel;
	private JTable table;
	ListLopHoc dao;
	private List<LopHoc1> list;
	public FrmGiaoDienLopHoc(ListLopHoc dao)
	{
		setTitle("Thông tin lớp học");
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.dao=dao;//set dao
		list=dao.getList();
		Box b, b1, b2, b3, b4, b5, b6, b7;
		//Dùng BoxLayout
		add(b=Box.createVerticalBox());//Box theo chiều dọc
		b.add(Box.createVerticalStrut(10));//Tạo khoảng cách theo chiều dọc
		
		b.add(b1=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b5=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b6=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		b.add(b7=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		JLabel lblTieuDe, lblMaLop, lblGVCN, lblTenLop;
		b1.add(lblTieuDe=new JLabel("THÔNG TIN LỚP HỌC",JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,26));
		
		b2.add(lblMaLop=new JLabel("Mã lớp:",JLabel.RIGHT));
		b2.add(txtMaLop=new JTextField());
		
		b3.add(lblTenLop=new JLabel("Tên lớp:",JLabel.RIGHT));
		b3.add(txtTenLop=new JTextField());
		
		b4.add(lblGVCN=new JLabel("Giáo viên chủ nhiệm:",JLabel.RIGHT));
		b4.add(txtGVCN=new JTextField());
		
		lblMaLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblTenLop.setPreferredSize(lblGVCN.getPreferredSize());
		
		b5.add(Box.createHorizontalStrut(70));
		b5.add(btnThem=new JButton("Thêm"));
		b5.add(btnSua=new JButton("Sửa"));
		b5.add(btnTimKiem=new JButton("Tìm kiếm"));
		b5.add(btnXoa=new JButton("Xoá"));
		b5.add(btnFrameSV=new JButton("Giao diện sinh viên"));
		
		String[] headers= {"Mã số lớp","Tên lớp","Giáo viên chủ nhiệm"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll;
		b6.add(scroll=new JScrollPane(table=new JTable(tableModel)));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lớp học"));
		
		b7.add(Box.createHorizontalStrut(600));
		b7.add(btnXemDSSV=new JButton("Xem danh sách sinh viên của lớp hiện tại"));
		
		btnXemDSSV.setForeground(Color.red);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXemDSSV.addActionListener(this);
		btnFrameSV.addActionListener(this);
		//
	}
	private void themActions()
	{
		try
		{
			
			String maLop=txtMaLop.getText();
			String tenLop=txtTenLop.getText();
			String gVCN=txtGVCN.getText();
			
			LopHoc1 l=new LopHoc1(maLop,tenLop,gVCN);
			if(maLop.trim().equals("")||tenLop.trim().equals("")||gVCN.trim().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Phải nhập lại dữ liệu");
			}
			else
			{
				if(dao.themLopHoc(l))
				{
					String[] row ={maLop,tenLop,gVCN+""};
					tableModel.addRow(row);
					xoaTrangActions();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Trùng mã lớp");
					txtMaLop.selectAll();
					txtMaLop.requestFocus();
				}
			}	
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Lỗi nhập liệu");
			return;
		}
	}
	
	private void timKiemActions()
	{
		String searchTerm = JOptionPane.showInputDialog(null, "Nhập mã lớp cần tìm kiếm:");
		try
		{
		//	int a=table.getRowCount();
		//	int maCanTim=Integer.parseInt(txtTim.getText());
			
			for (int i = 0; i < table.getRowCount(); i++) 
			{
				for (int j = 0; j < table.getColumnCount(); j++) 
				{
					if(table.getValueAt(i, j).toString().equals(searchTerm)) 
					{
						table.setRowSelectionInterval(i, i);
					}
				}
			
			}	
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Lỗi nhập liệu, không thể tìm thấy");
		}
	}
	private void luuActions()
	{
		String url = "jdbc:sqlserver://localhost:3000;databaseName=qlsv";
        String username = "siu";
        String password = "messivodichworldcupUCL0981";
		Connection conn=null;
		
		
		try {
            // Kết nối tới cơ sở dữ liệu
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
            String sql="";
            for (int i = 0; i < table.getRowCount(); i++) 
            {
                sql += "INSERT INTO LopHoc (maLop,tenLop,giaoVienCN) VALUES ('" +
                      table.getValueAt(i, 0) + "','" +
                      table.getValueAt(i, 1) + "','" +
                      table.getValueAt(i, 2) +          
                      "')";
                
            }
            stmt.executeUpdate(sql);
            // Đóng kết nối
            stmt.close();
            conn.close();
            
        
        } catch (SQLException e) 
		{
            e.printStackTrace();
        
        }

	}
	
	private void xoaActions()
	{
		Connection conn=null;
		int row=table.getSelectedRow();
		if(row!=-1)
		{
			String maLop=((String) table.getModel().getValueAt(row,0));
			int kTra=JOptionPane.showConfirmDialog(this, "Chắc chắn xoá không","Chú ý",JOptionPane.YES_NO_OPTION);
			if (kTra==JOptionPane.YES_OPTION)
			{
				if(dao.xoaNhanVien(maLop))
					tableModel.removeRow(row);
			}
			try {
	            // Kết nối tới cơ sở dữ liệu
	            String url = "jdbc:sqlserver://localhost:3000;databaseName=qlsv";
	            String username = "siu";
	            String password = "messivodichworldcupUCL0981";
	            conn = DriverManager.getConnection(url, username, password);
	    
	            
	            Statement stmt = conn.createStatement();
	            String sql="";
	            //DELETE FROM TenBang WHERE KhoaChinh = 'GiaTriKhoaChinh';
	            
	            sql="DELETE FROM LopHoc WHERE maLop = '"+maLop+"'";
	            
	            stmt.executeUpdate(sql);
	            // Đóng kết nối
	            stmt.close();
	            conn.close();
	            
	        
	        } catch (SQLException e) 
			{
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Lỗi dữ liệu.");
	           
	        }
		}	
	}
	private void xoaTrangActions()
	{
		txtMaLop.setText("");
		txtTenLop.setText("");
		txtGVCN.setText("");
		txtMaLop.requestFocus();
	}
	public void actionPerformed(ActionEvent e) 
	{
		Object o=e.getSource();
		if(o.equals(btnThem))
		{
			themActions();
			luuActions();
		}		
		if(o.equals(btnXoa))
		{
			xoaActions();
		}
			
		if(o.equals(btnTimKiem))
			timKiemActions();
		if(o.equals(btnSua))
		{
			new FrmSuaLopHoc().setVisible(true);
		}
		if(o.equals(btnXemDSSV))
		{
			new FrmDSSV().setVisible(true);;
		}
		if(o.equals(btnFrameSV))
		{
			ListSinhVien dao=new ListSinhVien();
    		new FrmGiaoDienSinhVien(dao).setVisible(true);
		}
	}

}

