package thongTinLopHoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmChinh extends JFrame implements ActionListener
{
	private JTextField txtTenUser;
	private JButton btnLopHoc, btnSinhVien, btnThoat;
	private JPasswordField txtMatKhau;
	private JLabel lblTieuDe, lblTenUser, lblMatKhau;
	public FrmChinh()
	{
		setTitle("Thông tin lớp học và sinh viên: richa-richa123");
		setSize(400,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
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
		
		
		b1.add(lblTieuDe=new JLabel("THÔNG TIN LỚP VÀ SINH VIÊN",JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,15));
		int x=10;
		b2.add(lblTenUser=new JLabel("Tên đăng nhập:",JLabel.RIGHT));
		b2.add(Box.createHorizontalStrut(x));
		b2.add(txtTenUser=new JTextField());
		
		b3.add(lblMatKhau=new JLabel("Mật Khẩu:",JLabel.RIGHT));
		b3.add(Box.createHorizontalStrut(x+30));
		b3.add(txtMatKhau=new JPasswordField());
		
		b4.add(Box.createHorizontalStrut(10));
		b4.add(btnLopHoc=new JButton("Lớp Học"));
		b4.add(btnSinhVien=new JButton("Sinh Viên"));
		b4.add(btnThoat=new JButton("Thoát"));
		
		btnLopHoc.addActionListener(this);
		btnSinhVien.addActionListener(this);
		btnThoat.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object o=e.getSource();
		String username = txtTenUser.getText();
        String password = new String(txtMatKhau.getPassword());
        if(o.equals(btnLopHoc))
        {
        	if (username.equals("richa") && password.equals("richa123")) 
            {
        			ListLopHoc dao=new ListLopHoc();
        		new FrmGiaoDienLopHoc(dao).setVisible(true);
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        }
        if(o.equals(btnSinhVien))
        {
        	if (username.equals("richa") && password.equals("richa123")) 
            {
        			ListSinhVien dao=new ListSinhVien();
        		new FrmGiaoDienSinhVien(dao).setVisible(true);
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        }
        if(o.equals(btnThoat))
        {
        	System.exit(0);
        }
		
	}

}
