package thongTinLopHoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmSuaSinhVien extends JFrame implements ActionListener
{
	private JTextField txtMaSV, txtHoTen, txtDiaChi,txtEmail,txtMaLop;
	private JLabel lblTieuDe,lblMaSV, lblHoTen, lblDiaChi,lblEmail, lblMaLop;
	private JButton btnSua,btnThoat;
	public FrmSuaSinhVien()
	{
		setTitle("Thông tin sinh viên");
		setSize(500,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
			
		Box b, b1,b2, b3, b4, b5,b6,b7,b8;
		
		
		
		//Dùng BoxLayout
		int x=30,y=10;
		add(b=Box.createVerticalBox());//Box theo chiều dọc
		b.add(Box.createVerticalStrut(x));//Tạo khoảng cách theo chiều dọc
		
		b.add(b1=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(y));
		
		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(y));
		
		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(y));
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(y));
		
		b.add(b5=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(y));
		
		b.add(b6=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(y));
		
		b.add(b7=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(y));
		
		b1.add(lblTieuDe=new JLabel("CẬP NHẬT SINH VIÊN",JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,26));
		
		b2.add(lblMaSV=new JLabel("Mã:",JLabel.RIGHT));
		b2.add(Box.createHorizontalStrut(x));
		b2.add(txtMaSV=new JTextField());
		
		b3.add(lblHoTen=new JLabel("Tên:",JLabel.RIGHT));
		b3.add(Box.createHorizontalStrut(x-4));
		b3.add(txtHoTen=new JTextField());
		
		b4.add(lblDiaChi=new JLabel("Địa chỉ:",JLabel.RIGHT));
		b4.add(Box.createHorizontalStrut(x-22));
		b4.add(txtDiaChi=new JTextField());
		
		b5.add(lblEmail=new JLabel("Email:",JLabel.RIGHT));
		b5.add(Box.createHorizontalStrut(x-14));
		b5.add(txtEmail=new JTextField());
		
		b6.add(lblMaLop=new JLabel("Mã lớp:",JLabel.RIGHT));
		b6.add(Box.createHorizontalStrut(x-22));
		b6.add(txtMaLop=new JTextField());
		
				
		b7.add(Box.createHorizontalStrut(x));
		b7.add(btnSua=new JButton("Cập nhật"));
		b7.add(Box.createHorizontalStrut(5));
		b7.add(btnThoat=new JButton("Thoát"));
		
		
		
		btnSua.addActionListener(this);
		btnThoat.addActionListener(this);
	}
	private void suaActions()
	{
		
        
        String maSV=txtMaLop.getText();
        String hoTen=txtHoTen.getText();
        String email=txtEmail.getText();
        String diaChi=txtDiaChi.getText();
        String maLop=txtMaLop.getText();
        
		Connection conn=null;		
		try {
			String url = "jdbc:sqlserver://localhost:3000;databaseName=qlsv";
	        String username = "siu";
	        String password = "messivodichworldcupUCL0981";
			String sql2="";
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
            //UPDATE KhachHang
            //SET HoTen = 'Nguyen Van A', DiaChi = 'Ha Noi'
            //		WHERE MaKH = 'KH001';
            //INTO LopHoc (maLop,tenLop,giaoVienCN)
            
			sql2 += "UPDATE SinhVien SET hoTen='"+hoTen+"', diaChi='"+diaChi+"', email='"+email+"', maLop='"+maLop+"' WHERE maSV='"+maSV+"';";
                      
                
            
            stmt.executeUpdate(sql2);
            // Đóng kết nối
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null,"Cập nhật thành công");
			}catch(SQLException e) 
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Không tìm thấy mã cần sửa hoặc mã lớp không hợp lệ");
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object o=e.getSource();
		if(o.equals(btnSua))
			suaActions();
		if(o.equals(btnThoat))
		{
			System.exit(0);
		}
		
	}

}
