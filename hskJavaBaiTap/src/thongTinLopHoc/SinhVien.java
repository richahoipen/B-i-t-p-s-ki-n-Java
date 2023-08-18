package thongTinLopHoc;

import java.util.Objects;

public class SinhVien 
{
	@Override
	public int hashCode() {
		return Objects.hash(maSV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		return Objects.equals(maSV, other.maSV);
	}

	/**
	 * @return the maSV
	 */
	public String getMaSV() {
		return maSV;
	}

	/**
	 * @param maSV the maSV to set
	 */
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	/**
	 * @return the hoTen
	 */
	public String getHoTen() {
		return hoTen;
	}

	/**
	 * @param hoTen the hoTen to set
	 */
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	/**
	 * @return the diaChi
	 */
	public String getDiaChi() {
		return diaChi;
	}

	/**
	 * @param diaChi the diaChi to set
	 */
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

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

	private String maSV, hoTen, diaChi, email, maLop;

	public SinhVien(String maSV, String hoTen, String diaChi, String email, String maLop) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.email = email;
		this.maLop = maLop;
	}
	
}
