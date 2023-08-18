package thongTinCountry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import ttnvDemoThay.DanhSachNhanVien;
import ttnvDemoThay.NhanVien;

public class FrmCountry extends JFrame implements ActionListener
{
	private ListCountry dao;
	private List<Country> list;
	private JPanel pnlNorth,pnlCenter;
	private JTextField txtCountry, txtCapital, txtPopulation;
	private JComboBox democracy;
	private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnSearch, btnSave;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public FrmCountry(ListCountry dao)
	{
		setTitle("Using JTable Componet & IO Stream");
		setSize(600,450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		this.dao=dao;//set dao
		list=dao.getList();
		taoTieuDe();
	//	taoGiaoDien();
		taoVungSouth();
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSearch.addActionListener(this);
		btnSave.addActionListener(this);
		democracy.addActionListener(this);
	}
	private void taoTieuDe()
	{
		
		add(pnlNorth=new JPanel(), BorderLayout.NORTH);//add Panel North vao JFrame lon
		add(pnlCenter=new JPanel(), BorderLayout.CENTER);
		JLabel lblTieuDe;
		pnlNorth.add(lblTieuDe=new JLabel("Using JTable Component"));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,20));
		lblTieuDe.setForeground(Color.BLUE);
		
		Box b=Box.createVerticalBox();//tao box hang doc
		Box b1,b2,b3,b4,b5;
		JLabel lblCountry, lblCapital, lblPopulation, lblSelect;
		//tao doi tuong cho box con
		b.add(b1=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));// khoang cach doc giua box va box duoi no
		b1.add(lblCountry=new JLabel("Enter country:"));
		b1.add(Box.createHorizontalStrut(27));// khoang cach giua Label va TextField
		b1.add(txtCountry=new JTextField(10));
		
		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b2.add(lblCapital=new JLabel("Enter capital:"));
		b2.add(Box.createHorizontalStrut(32));
		b2.add(txtCapital=new JTextField(40));
		
		b.add(b3=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));		
		b3.add(lblPopulation=new JLabel("Enter population:"));
		b3.add(Box.createHorizontalStrut(11));
		b3.add(txtPopulation=new JTextField(40));
		
		b.add(b4=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b4.add(lblSelect=new JLabel("Select democracy:"));
		
		
		b4.add(democracy=new JComboBox<>());
		democracy.addItem("true");
		democracy.addItem("false");
		b.add(b5=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		
		String[] headers="Country;Capital;Population;Democracy".split(";");
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll=new JScrollPane();
		scroll.setViewportView(table=new JTable(tableModel));
		table.setRowHeight(25);
		
		b5.add(scroll);
		
		pnlCenter.add(b);
		
		
	//	txtPopulation.setPreferredSize(txtCapital.getPreferredSize());
	//	democracy.setPreferredSize(txtCountry.getPreferredSize());
		

	}
	private void taoVungSouth()
	{
		JPanel pnlSouth;
		add(pnlSouth=new JPanel(), BorderLayout.SOUTH);//add Panel North vao JFrame lon
		pnlSouth.add(btnAdd=new JButton("Add"));
		pnlSouth.add(btnClear=new JButton("Clear"));
		pnlSouth.add(btnUpdate=new JButton("Update"));
		pnlSouth.add(btnDelete=new JButton("Delete"));
		pnlSouth.add(btnSearch=new JButton("Search"));
		pnlSouth.add(btnSave=new JButton("Save in txt file"));
		
	}
	private boolean demo()
	{
		String ma= (String) democracy.getSelectedItem();
		if(ma.equalsIgnoreCase("true"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private void themActions()
	{
		
		try
		{
			String country=txtCountry.getText();
			String capital=txtCapital.getText();
			int population=Integer.parseInt(txtPopulation.getText());
			boolean demo=demo();	
			
			Country c=new Country(country,capital,population,demo);
			if(dao.themCountry(c))
			{
				TableColumn demoColumn = table.getColumnModel().getColumn(3);
		        demoColumn.setCellRenderer(new CheckBoxRenderer(demo));
				String[] row ={country,capital,Integer.toString(population),demoColumn+""};
				tableModel.addRow(row);
				xoaRongCountry();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Trùng country");
				txtCountry.selectAll();
				txtCountry.requestFocus();
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Lỗi nhập liệu");
			return;
		}
	}
	private void xoaActions()
	{
		int row=table.getSelectedRow();
		if(row!=-1)
		{
			String country=((String) table.getModel().getValueAt(row,0));
			int kTra=JOptionPane.showConfirmDialog(this, "Chắc chắn xoá không","Chú ý",JOptionPane.YES_NO_OPTION);
			if (kTra==JOptionPane.YES_OPTION)
			{
				if(dao.xoaCountry(country))
					tableModel.removeRow(row);
			}
		}
	}
	private void xoaRongCountry()
	{
		txtCountry.setText("");
		txtCapital.setText("");
		txtPopulation.setText("");	
		txtCountry.requestFocus();
		democracy.setSelectedItem("true");
	}
	private void timActions()
	{
		String searchTerm = JOptionPane.showInputDialog(null, "Nhập country cần tìm kiếm:");
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
	private void luuActions(String tep)
	{
		try {
           
            FileWriter writer = new FileWriter(tep);
            PrintWriter out = new PrintWriter(writer);

            // Ghi tiêu đề của các cột
            for (int i = 0; i < table.getColumnCount(); i++) {
                out.print(table.getColumnName(i) + "\t");
            }
            out.println();

            // Ghi dữ liệu từ các hàng
            //Duyệt bảng
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    out.print(table.getValueAt(i, j).toString() + "\t");
                }
                out.println();
            }
            out.close();
            JOptionPane.showMessageDialog(null, "Đã ghi file");
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Lỗi file");
        }
	}
	private void suaActions()
	{
		JOptionPane.showMessageDialog(null,"Bạn có thể click chuột vào bảng để sửa");
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object o=e.getSource();
		if(o.equals(btnAdd))
			themActions();
		if(o.equals(btnDelete))
			xoaActions();
		if(o.equals(btnClear))
			xoaRongCountry();
		if(o.equals(btnSearch))
			timActions();
		if(o.equals(btnUpdate))
			suaActions();
		if(o.equals(btnSave))
			luuActions("country.txt");
		
	}

}
