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

public class FrmSuaLopHoc extends JFrame implements ActionListener
{
	
	private JTextField txtMaLop, txtTenLop, txtGVCN;
	private JButton btnSua, btnThoat;
	public FrmSuaLopHoc()
	{
		setTitle("Thông tin lớp học");
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		
		Box b, b1, b2, b3, b4, b5;
		//Dùng BoxLayout
		int x=5;
		add(b=Box.createVerticalBox());//Box theo chiều dọc
		b.add(Box.createVerticalStrut(x));//Tạo khoảng cách theo chiều dọc
		
		b.add(b1=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(x));
		
		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(x));
		
		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(x));
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(x));
		
		b.add(b5=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(x));
		
		JLabel lblTieuDe, lblMaLop, lblGVCN, lblTenLop;
		b1.add(lblTieuDe=new JLabel("CẬP NHẬT LỚP HỌC",JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,26));
		
		b2.add(lblMaLop=new JLabel("Mã lớp:",JLabel.RIGHT));
		b2.add(txtMaLop=new JTextField());
		
		b3.add(lblTenLop=new JLabel("Tên lớp:",JLabel.RIGHT));
		b3.add(txtTenLop=new JTextField());
		
		b4.add(lblGVCN=new JLabel("Giáo viên chủ nhiệm:",JLabel.RIGHT));
		b4.add(txtGVCN=new JTextField());
		
		lblMaLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblTenLop.setPreferredSize(lblGVCN.getPreferredSize());
		
		b5.add(Box.createHorizontalStrut(x));
		b5.add(btnSua=new JButton("Cập nhật"));
		b5.add(Box.createHorizontalStrut(5));
		b5.add(btnThoat=new JButton("Thoát"));
		btnSua.addActionListener(this);
		btnThoat.addActionListener(this);
	}
	private void suaActions()
	{
		
        
        String maLop=txtMaLop.getText();
        String tenLop=txtTenLop.getText();
        String gVCN=txtGVCN.getText();
        
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
            
                sql2 += "UPDATE LopHoc SET tenLop='" +
                      tenLop + "',giaoVienCN='" +
                      gVCN + "' WHERE maLop='" +
                      maLop +          
                      "';";
                
            
            stmt.executeUpdate(sql2);
            // Đóng kết nối
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null,"Cập nhật thành công");
			}catch(SQLException e) 
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Không tìm thấy mã cần sửa");
			}
	}
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
