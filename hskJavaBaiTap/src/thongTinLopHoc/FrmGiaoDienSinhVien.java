package thongTinLopHoc;

import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmGiaoDienSinhVien extends JFrame implements ActionListener
{
	private JTextField txtMaSV, txtHoTen, txtDiaChi,txtEmail,txtMaLop;
	private JLabel lblTieuDe,lblMaSV, lblHoTen, lblDiaChi,lblEmail, lblMaLop;
	private JButton btnThem, btnTimKiem, btnSua, btnXoa, btnFrmLopHoc;
	
	private DefaultTableModel tableModel;
	private JTable table;
	ListSinhVien dao;
	private List<SinhVien> list;
	public FrmGiaoDienSinhVien(ListSinhVien dao) 
	{
		setTitle("Thông tin sinh viên");
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.dao=dao;//set dao
		list=dao.getList();
		taoTieuDe();
	}
	private void taoTieuDe()
	{
	
		Box b=Box.createVerticalBox();//tao box hang doc
		Box  b1, b2, b3, b4, b5, b6, b7,b8;
		
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
		
		b.add(b8=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		int x=10;
	
		b1.add(lblTieuDe=new JLabel("THÔNG TIN SINH VIÊN",JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,26));
		
		b2.add(lblMaSV=new JLabel("Mã sinh viên:",JLabel.RIGHT));
		b2.add(Box.createHorizontalStrut(x));
		b2.add(txtMaSV=new JTextField());
		
		b3.add(lblHoTen=new JLabel("Tên sinh viên:",JLabel.RIGHT));
		b3.add(Box.createHorizontalStrut(x-4));
		b3.add(txtHoTen=new JTextField());
		
		b4.add(lblDiaChi=new JLabel("Địa chỉ:",JLabel.RIGHT));
		b4.add(Box.createHorizontalStrut(x+31));
		b4.add(txtDiaChi=new JTextField());
		
		b5.add(lblEmail=new JLabel("Email:",JLabel.RIGHT));
		b5.add(Box.createHorizontalStrut(x+39));
		b5.add(txtEmail=new JTextField());
		
		b6.add(lblMaLop=new JLabel("Mã Lớp:",JLabel.RIGHT));
		b6.add(Box.createHorizontalStrut(x+27));
		b6.add(txtMaLop=new JTextField());	
		
		
		txtHoTen.setPreferredSize(txtMaSV.getPreferredSize());
		txtDiaChi.setPreferredSize(txtMaSV.getPreferredSize());
		txtEmail.setPreferredSize(txtMaSV.getPreferredSize());
		txtMaLop.setPreferredSize(txtMaSV.getPreferredSize());
		
		b7.add(Box.createHorizontalStrut(70));
		b7.add(btnThem=new JButton("Thêm"));
		b7.add(btnSua=new JButton("Sửa"));
		b7.add(btnTimKiem=new JButton("Tìm kiếm"));
		b7.add(btnXoa=new JButton("Xoá"));
		b7.add(btnFrmLopHoc=new JButton("Giao diện lớp học"));
		
		String[] headers= {"Mã sinh viên","Tên sinh viên","Địa chỉ","Email","Mã Lớp"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll;
		b8.add(scroll=new JScrollPane(table=new JTable(tableModel)));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lớp học"));
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnFrmLopHoc.addActionListener(this);
	//	txtPopulation.setPreferredSize(txtCapital.getPreferredSize());
	//	democracy.setPreferredSize(txtCountry.getPreferredSize());
		

	}
	private void themActions()
	{
		try
		{
			String maSV=txtMaSV.getText();
			String hoTen=txtHoTen.getText();
			String diaChi=txtDiaChi.getText();
			String email=txtEmail.getText();
			String maLop=txtMaLop.getText();
		
			SinhVien sv=new SinhVien(maSV, hoTen, diaChi, email, maLop);
			if(maSV.trim().equals("")||hoTen.trim().equals("")||diaChi.trim().equals("")||email.trim().equals("")||maLop.trim().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Phải nhập lại dữ liệu");
			}
			else
			{
				if(dao.themLopHoc(sv))
				{
					String[] row ={maSV,hoTen,diaChi,email,maLop+""};
					tableModel.addRow(row);
					xoaTrangActions();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Trùng mã sinh viên");
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
                sql += "INSERT INTO SinhVien (maSV, hoTen, diaChi, email, maLop) VALUES ('" +
                      table.getValueAt(i, 0) + "','" +
                      table.getValueAt(i, 1) + "','" +
                      table.getValueAt(i, 2) + "','" +
                      table.getValueAt(i, 3) + "','" +
                      table.getValueAt(i, 4) +          
                      "')";
                
            }
            stmt.executeUpdate(sql);
            // Đóng kết nối
            stmt.close();
            conn.close();
            
        
        } catch (SQLException e) 
		{
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Không thể lưu do không tìm thấy mã lớp");
        
        }
	}
	private void xoaActions()
	{
		Connection conn=null;
		int row=table.getSelectedRow();
		if(row!=-1)
		{
			String maSV=((String) table.getModel().getValueAt(row,0));
			int kTra=JOptionPane.showConfirmDialog(this, "Chắc chắn xoá không","Chú ý",JOptionPane.YES_NO_OPTION);
			if (kTra==JOptionPane.YES_OPTION)
			{
				if(dao.xoaSinhVien(maSV))
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
	            
	            sql="DELETE FROM SinhVien WHERE maSV = '"+maSV+"'";
	            
	            stmt.executeUpdate(sql);
	            // Đóng kết nối
	            stmt.close();
	            conn.close();
	            
	        
	        } catch (SQLException e) 
			{
	            e.printStackTrace();
	           
	        }
		}	
	}
	private void timKiemActions()
	{
		String searchTerm = JOptionPane.showInputDialog(null, "Nhập mã sinh viên cần tìm kiếm:");
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
	private void xoaTrangActions()
	{
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtHoTen.setText("");
		txtMaLop.setText("");
		txtMaSV.setText("");
		txtMaSV.requestFocus();
		
	}
	@Override
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
			new FrmSuaSinhVien().setVisible(true);
		}
		if(o.equals(btnFrmLopHoc))
		{
			ListLopHoc dao=new ListLopHoc();
    		new FrmGiaoDienLopHoc(dao).setVisible(true);
		}
	}

}
