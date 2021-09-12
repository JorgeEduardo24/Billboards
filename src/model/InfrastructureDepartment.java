package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDepartment {
	private List<Billboard> billboards;
	private String FILE_IMPORT_TXT_PATH = "data/BillboardDataExported.csv";
	private String FILE_EXPORT_TXT_PATH = "data/ExportedBillboardsData.txt";
	private String FILE_SAVE_PATH = "data/Billboards.apo2";
	
	public InfrastructureDepartment() {
		billboards = new ArrayList<Billboard>();
	}
	
	public void addBillboard(double width, double height, boolean inUse, String brand) {
		billboards.add(new Billboard(width, height, inUse, brand));
	}
	
	public List<Billboard> getBillBoards(){
		return this.billboards;
	}
	
	//--------------------------------------------------------------------------------------
	//Manejo de archivos de texto plano
	public void importBillboards() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_TXT_PATH));
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String[] parts = line.split("|");
			double width = Double.parseDouble(parts[0]);
			double height = Double.parseDouble(parts[1]);
			boolean inUse = Boolean.parseBoolean(parts[2]);
			String brand = parts[3];
			addBillboard(width, height, inUse, brand);
			line = br.readLine();
		}
		br.close();
	}
	
	public void exportBillboars() throws IOException {
		FileWriter fw = new FileWriter(FILE_EXPORT_TXT_PATH, false);
		for(int i=0; i<billboards.size();i++) {
			Billboard billboard = billboards.get(i);
			fw.write(billboard.getHeight()+"++"+billboard.getWidth()+"++"+billboard.isInUse()+"++"+billboard.getBrand()+"\n");
		}
		fw.close();
	}
	
	//---------------------------------------------------------------------------------------------
	//Serializacion
	public void saveBillboars() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
		oos.writeObject(billboards);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadBillboarsdt() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(FILE_SAVE_PATH);
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			billboards = (List<Billboard>) ois.readObject(); 
			ois.close();
		}
	}
}

