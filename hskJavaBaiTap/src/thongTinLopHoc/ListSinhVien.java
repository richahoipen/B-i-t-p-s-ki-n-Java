package thongTinLopHoc;


import java.util.ArrayList;



public class ListSinhVien 
{
	/**
	 * @return the list
	 */
	public ArrayList<SinhVien> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<SinhVien> list) {
		this.list = list;
	}

	private ArrayList<SinhVien> list;
	public ListSinhVien()
	{
		setList(new ArrayList<SinhVien>());
	}
	public boolean themLopHoc(SinhVien l)
	{
		if(list.contains(l))
		{
			return false;
		}
		else
		{
			list.add(l);
			return true;
		}
	}
	public SinhVien timKiem(String maSinhVien)
	{
		for(SinhVien l:list)
		{
			if(l.getMaLop().equalsIgnoreCase(maSinhVien))
				return l;
		}
		return null;
	}
	public boolean xoaSinhVien(String maCanXoa)
	{
		SinhVien l=timKiem(maCanXoa);
		if(list!=null)
		{
			list.remove(l);
			return true;
		}
		return false;
	}
}

