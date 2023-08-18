package thongTinLopHoc;

import java.util.ArrayList;



public class ListLopHoc 
{
	/**
	 * @return the list
	 */
	public ArrayList<LopHoc1> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<LopHoc1> list) {
		this.list = list;
	}

	private ArrayList<LopHoc1> list;
	public ListLopHoc()
	{
		setList(new ArrayList<LopHoc1>());
	}
	public boolean themLopHoc(LopHoc1 l)
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
	public LopHoc1 timKiem(String maLop)
	{
		for(LopHoc1 l:list)
		{
			if(l.getMaLop().equalsIgnoreCase(maLop))
				return l;
		}
		return null;
	}
	public boolean xoaNhanVien(String maCanXoa)
	{
		LopHoc1 l=timKiem(maCanXoa);
		if(list!=null)
		{
			list.remove(l);
			return true;
		}
		return false;
	}
}
