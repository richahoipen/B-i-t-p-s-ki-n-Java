package thongTinLopHoc;

import java.util.Objects;

public class LopHoc1 
{
	
	/**
	 * @return the maLop
	 */
	public String getMaLop() {
		return maLop;
	}

	/**
	 * @param maLop the maLop to set
	 */
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	/**
	 * @return the tenLop
	 */
	public String getTenLop() {
		return tenLop;
	}

	/**
	 * @param tenLop the tenLop to set
	 */
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	/**
	 * @return the giaoVienCN
	 */
	public String getGiaoVienCN() {
		return giaoVienCN;
	}

	/**
	 * @param giaoVienCN the giaoVienCN to set
	 */
	public void setGiaoVienCN(String giaoVienCN) {
		this.giaoVienCN = giaoVienCN;
	}
	
	private String maLop, tenLop, giaoVienCN;
	public LopHoc1()
	{
		this.maLop="";
		this.tenLop="";
		this.giaoVienCN="";
	}
	public LopHoc1 (String maLop,String tenLop,String giaoVienCN)
	{
		setGiaoVienCN(giaoVienCN);
		setMaLop(maLop);
		setTenLop(tenLop);
	}
	
}
