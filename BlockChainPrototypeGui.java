package blockchain.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class BlockChainPrototypeGui {
	JFrame oneFrame;
	String addressDoctor = "0x14723a09acff6d2a60dcdf7aa4aff308fddc160c";
	String addressInsurance = "0x4b0897b0513fdc7c541b6d9d7e929c4e5364d2db";
	String addressFDA = "0x583031d1113ad414f02576bd6afabfb302140225";
	String deviceSupplier = "0xdd870fa1b7c4700f2bd7f44238821c26f7392148";
	String addressPatient = "0xca35b7d915458ef540ade6068dfe2f44e8fa733c";
	String foodLocation = "Buffalo, New York"; // for FDA
	String foodExpDate = "4/15/2018";
	int oldWeight;
	int weight = 20;
	int dosage;
	String serialNum = "MOOG001";
	
	public BlockChainPrototypeGui() {
		oneFrame = new JFrame();
		oneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		String[] choices = { "Patient","Doctor/Clinic/Hospital", "Just an M.V.P."};

	    JComboBox<String> dropdown = new JComboBox<String>(choices);
	    JButton jb1 = new JButton("Select an interface");		
	    JFrame x = new JFrame("Patient's Interface");
		JPanel x1 = new JPanel();
		x.setSize(500, 500);
		GridLayout y = new GridLayout(4,2);
		x1.setLayout(y);
		x.add(x1);
		
		JLabel weightLabel = new JLabel("weight:");
		JButton checkWeight = new JButton("Check Weight");
		checkWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == checkWeight) {
					weightLabel.setText("weight:"+ weight);
				}
			}
		});
		
		
		x1.add(checkWeight);
		
		x1.add(weightLabel);
		
		JButton setDoctor = new JButton("Set Doctor");
		JTextField textField = new JTextField("Input new Doctor address", 20);
		setDoctor.addActionListener(new ActionListener() {      //set new doctor address
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == setDoctor) {
					addressDoctor = textField.getText();
				}
			}
		});
		x1.add(setDoctor);
		x1.add(textField);
		JButton setInsurance= new JButton("Set Insurance");
		JTextField InsuranceField = new JTextField("Input new insurance address", 20);
		setInsurance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == setInsurance) {
					addressInsurance = InsuranceField.getText();
				}
			}
		});
		x1.add(setInsurance);
		x1.add(InsuranceField);
		
		JButton setDevice= new JButton("Set manufacturer");
		JTextField ManufacturerField = new JTextField("Input new manufacturer address", 20);
		setDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == setDevice) {
					deviceSupplier = ManufacturerField.getText();
				}
			}
		});
		x1.add(setDevice);
		x1.add(ManufacturerField);
		JFrame doctorFrame = new JFrame();
		JPanel doctorP = new JPanel();
		JButton recordWeight = new JButton("Assume device record weight daily");
		GridLayout y1 = new GridLayout(6,2);
		doctorP.setLayout(y1);
		recordWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == recordWeight) {
					weight += 1;
				}
			}
		});
		JLabel weightLabel1 = new JLabel("weight:");
		JButton checkWeight1 = new JButton("Check Weight");
		checkWeight1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == checkWeight1) {
					weightLabel1.setText("weight:"+ weight);
				}
			}
		});
		JButton setDosage= new JButton("Set Dosage");
		JTextField DosageField = new JTextField("Input new dosage", 20);
		setDosage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == setDosage) {
					try {
						int x = Integer.parseInt(DosageField.getText());
						dosage = x;
					} catch (NumberFormatException a) {
						
					}
					
				}
			}
		});
		JButton getDosage= new JButton("get Dosage");
		JLabel DosageOutput = new JLabel("Dosage is: ");
		getDosage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == getDosage) {
					DosageOutput.setText("Dosage is: " + dosage);
				}
			}
		});
		JButton foodRequest= new JButton("new food request");
		JTextField newRequest = new JTextField("Input new request", 20);

		foodRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == foodRequest) {
					foodLocation = newRequest.getText();
				}
			}
		});
		
		JButton weightEvaluation= new JButton("Treatment validation");
		JLabel passOrFail = new JLabel("Treatment is:");

		weightEvaluation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == weightEvaluation) {
					if (weight >= oldWeight) {
					passOrFail.setText("Treatment is good");
					} else {
						passOrFail.setText("Treatment is bad");

					}
				}
			}
		});
		
		doctorP.add(weightEvaluation);
		doctorP.add(passOrFail);
		doctorP.add(foodRequest);
		doctorP.add(newRequest);
		doctorP.add(setDosage);
		doctorP.add(DosageField);
		doctorP.add(getDosage);
		doctorP.add(DosageOutput);
		
		doctorP.add(recordWeight);
		doctorP.add(checkWeight1);
		doctorP.add(weightLabel1);
		doctorFrame.add(doctorP);
	    doctorFrame.pack();
		panel.add(dropdown);
		panel.add(jb1);
		
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == jb1) {
					if((String)dropdown.getSelectedItem() == "Patient"){
					x.setVisible(true);
					oneFrame.setVisible(false);
					} else {
						oneFrame.setVisible(false);
						doctorFrame.setVisible(true);
					}
				}
			}
		});
		
		
		
		
		// Set the window to be visible as the default to be false
		oneFrame.add(panel);
		oneFrame.pack();
		oneFrame.setVisible(true);		
		
		
	}
	
	
	
	public static void main(String[] args) {
		new CreateGui();
	}
}
 

		
		
