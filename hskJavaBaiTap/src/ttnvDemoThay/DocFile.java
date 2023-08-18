package ttnvDemoThay;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

public class DocFile
{
	public Object readFile(String fileName) 
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object object = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			object = ois.readObject();
			ois.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không có dữ liệu");
		}
		return object;
	}
}
