package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Billboard;
import model.InfrastructureDepartment;

public class Menu {
	InfrastructureDepartment infrastructureDepartment;
	List<Billboard> billboards;
	
	
	public Menu() {
		infrastructureDepartment = new InfrastructureDepartment();
		billboards = new ArrayList<Billboard>();
	
	}
	
	
	public void showMenu() {
		System.out.println("\nEnter the option that you want to do: ");
		System.out.println("[1] Add billboard\n"+
		                   "[2] Display billboards\n"+
		                   "[3] Export the hazard report\n"+
		                   "[4] Exit app\n");
	}
	
	
	public int readOption(BufferedReader br) throws NumberFormatException, IOException {
		int option = Integer.parseInt(br.readLine());
		return option;
	}
	
	
	public void doOption(int option, BufferedReader br) throws IOException{
		
		switch(option) {
		
		case 1:
			System.out.println("----------------------------------------------------");
			System.out.println("                    ADD BILLBOARD");
			System.out.println("----------------------------------------------------");
			readBillboardData(br);
			
			break;

		case 2:
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("                                 BILLBOARDS");
			System.out.println("--------------------------------------------------------------------------------");
			showBillboards();
			
			break;

		case 3:
			showHazardReport();
			break;

		case 4:
			System.out.println("Bye!");
			br.close();
			break;

		default:
			System.out.println("Wrong choice. Try again");
			
	}
		
	}
	
	public void startProgram() throws IOException{
		infrastructureDepartment.importBillboards();
		infrastructureDepartment.saveBillboards();
		
		int exit = 4;
		int option = 0;
		do {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			showMenu();
			option = readOption(br);
			doOption(option, br);
		}while(option != exit);
	}
	
	
	public void readBillboardData(BufferedReader br) throws IOException{
		System.out.println("Please write the billboard data as follows:\n width++height++true/false++brand: ");
		System.out.println("Note:\nIf the billboard is in use, write true.\nIf the billboard is not in use, write false");
		
		String line = br.readLine();
		if (line != null) {
			String[] parts = line.split("\\++");
			double width = Double.parseDouble(parts[0]);
			double height = Double.parseDouble(parts[1]);
			boolean inUse = Boolean.parseBoolean(parts[2]);
			String brand = parts[3];
			infrastructureDepartment.addBillboard(width, height, inUse, brand);
			infrastructureDepartment.exportToFileCSV(width, height, inUse, brand);
			infrastructureDepartment.saveBillboards();
		}
		
		System.out.println("Billboard added!\n");
	}
	
	public void showBillboards(){
		billboards = infrastructureDepartment.getBillBoards();
		System.out.println("W            H            In Use            Brand");
		for(int i=0; i<billboards.size();i++) {
			System.out.println(billboards.get(i).toString());
		}
		
		System.out.println("\nTotal Billboards: "+billboards.size());
	}
	
	public List<Billboard> hazardReport() {
		billboards = infrastructureDepartment.getBillBoards();
		List<Billboard> dangerousBillboards = new ArrayList<Billboard>();;
		
		for(int i=0; i<billboards.size();i++) {
			if(billboards.get(i).calculateArea()>=160) {
				dangerousBillboards.add(billboards.get(i));
			}
		}
		
		return dangerousBillboards;
	}
	
	
	public void showHazardReport() throws IOException {
		infrastructureDepartment.exportHazardReport(hazardReport());
		
		System.out.println("===============================================================================");
		System.out.println("                        DANGEROUS BILLBOARD REPORT");
		System.out.println("===============================================================================");
		System.out.println("The dangerous billboards are: ");
		
		for(int i=0; i<hazardReport().size(); i++) {
			System.out.println((i+1)+". Billboard of "+hazardReport().get(i).getBrand()+", with area equal to "+hazardReport().get(i).calculateArea()+" square centimeters.");
		}
			
	}
	
}

