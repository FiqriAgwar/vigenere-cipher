import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JCheckBox;

public class GUI implements ActionListener{
	
	private String plaintext = "";
	private String key = "";
	private String ciphertext = "";
	
	private final JFileChooser openFileChooser;
	private final JFileChooser saveFileChooser;
	
	JFrame frame;
	private JTextField keyField;
	private JTextField fileTextField;
	private JTextField keyField_1;
	private JTextField textField_1;
	private JTextField keyField_1_1;
	private JTextField textField_3;
	private JTextField keyField_2;
	private JTextField textField_2;
	private JTextField textCofactor;
	private JTextField textField_4;
	private JTextField textShift;
	private JTextField keyField_6;
	private JTextField textField_5;
	private JTextField keyField_7;
	private JTextField textField_6;
	private JTextField keyField_8;
	private JTextField keyTranspose;
	private JTextField textField_8;
	
	public GUI() {
		openFileChooser = new JFileChooser();
		saveFileChooser = new JFileChooser();
		
		openFileChooser.setCurrentDirectory(new File("C:\\temp"));
		saveFileChooser.setCurrentDirectory(new File("C:\\temp"));
		
		frame = new JFrame();
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("Vigenere Cipher - 13517107");
		
		JTabbedPane tabbedPane = new JTabbedPane();
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel fullVigenere = new JPanel();
		tabbedPane.addTab("Full Vigenere", null, fullVigenere, null);
		fullVigenere.setLayout(null);
		
		JLabel lblYourKey_1 = new JLabel("Your Key : ");
		lblYourKey_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourKey_1.setBounds(10, 120, 82, 14);
		fullVigenere.add(lblYourKey_1);
		
		JLabel lblCipheredText_1 = new JLabel("Ciphered Text : ");
		lblCipheredText_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText_1.setBounds(10, 189, 120, 14);
		fullVigenere.add(lblCipheredText_1);
		
		JTextPane outputLabel_1 = new JTextPane();
		outputLabel_1.setBounds(10, 204, 555, 74);
		fullVigenere.add(outputLabel_1);
		
		keyField_1 = new JTextField();
		keyField_1.setColumns(10);
		keyField_1.setBounds(102, 117, 463, 20);
		fullVigenere.add(keyField_1);
		
		JCheckBox chckbxSeparate_1 = new JCheckBox("Separate by 5");
		chckbxSeparate_1.setBounds(430, 155, 135, 23);
		fullVigenere.add(chckbxSeparate_1);
		
		JTextPane plaintextPane_1 = new JTextPane();
		plaintextPane_1.setBounds(102, 38, 463, 68);
		fullVigenere.add(plaintextPane_1);
		
		JButton btnEncryptText_1 = new JButton("Encrypt Text");
		btnEncryptText_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_1.getText();
				key = keyField_1.getText();
				
				FullVigenere vigenere = new FullVigenere(plaintext, key);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_1.setText(ciphertext);
			}
		});
		btnEncryptText_1.setBounds(102, 155, 140, 23);
		fullVigenere.add(btnEncryptText_1);
		
		JButton btnDecryptText_1 = new JButton("Decrypt Text");
		btnDecryptText_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					plaintext = plaintextPane_1.getText();
					key = keyField_1.getText();
					
					FullVigenere vigenere = new FullVigenere(plaintext, key);
					ciphertext = vigenere.decrypt();
					
					if(chckbxSeparate_1.isSelected()) {
						String temp = "";
						for(int i=0;i<ciphertext.length();i++) {
							if(i % 5 == 4) {
								temp += ciphertext.charAt(i) + " ";
							}
							else {
								temp += ciphertext.charAt(i);
							}
						}
						
						ciphertext = temp;
					}
					
					outputLabel_1.setText(ciphertext);
			}
		});
		btnDecryptText_1.setBounds(252, 155, 140, 23);
		fullVigenere.add(btnDecryptText_1);
		
		JButton btnDownloadAsFile_1 = new JButton("Download as File");
		btnDownloadAsFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile_1.setBounds(241, 289, 120, 23);
		fullVigenere.add(btnDownloadAsFile_1);
		
		JLabel lblYourFile_1 = new JLabel("Your File : ");
		lblYourFile_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile_1.setBounds(10, 11, 82, 14);
		fullVigenere.add(lblYourFile_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(102, 11, 351, 20);
		fullVigenere.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Your Text : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 38, 82, 14);
		fullVigenere.add(lblNewLabel_1);
		
		JButton browseFileButton_1 = new JButton("Browse...");
		browseFileButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						textField_1.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane_1.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					textField_1.setText("No file chosen!");
				}
			}
		});
		browseFileButton_1.setBounds(463, 11, 104, 20);
		fullVigenere.add(browseFileButton_1);
		
		JPanel autoKey = new JPanel();
		tabbedPane.addTab("Autokey Vigenere", null, autoKey, null);
		autoKey.setLayout(null);
		
		JLabel lblYourKey_1_1 = new JLabel("Your Key : ");
		lblYourKey_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourKey_1_1.setBounds(10, 120, 82, 14);
		autoKey.add(lblYourKey_1_1);
		
		JLabel lblCipheredText_1_1 = new JLabel("Ciphered Text : ");
		lblCipheredText_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText_1_1.setBounds(10, 189, 120, 14);
		autoKey.add(lblCipheredText_1_1);
		
		JTextPane outputLabel_1_1 = new JTextPane();
		outputLabel_1_1.setBounds(10, 204, 555, 74);
		autoKey.add(outputLabel_1_1);
		
		keyField_1_1 = new JTextField();
		keyField_1_1.setColumns(10);
		keyField_1_1.setBounds(102, 117, 463, 20);
		autoKey.add(keyField_1_1);
		
		JCheckBox chckbxSeparate_1_1 = new JCheckBox("Separate by 5");
		chckbxSeparate_1_1.setBounds(430, 155, 135, 23);
		autoKey.add(chckbxSeparate_1_1);
		
		JTextPane plaintextPane_1_1 = new JTextPane();
		plaintextPane_1_1.setBounds(102, 38, 463, 68);
		autoKey.add(plaintextPane_1_1);
		
		JButton btnEncryptText_1_1 = new JButton("Encrypt Text");
		btnEncryptText_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_1_1.getText();
				key = keyField_1_1.getText();
				
				AutoKeyVigenere vigenere = new AutoKeyVigenere(plaintext, key);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate_1_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_1_1.setText(ciphertext);
			}
		});
		btnEncryptText_1_1.setBounds(102, 155, 140, 23);
		autoKey.add(btnEncryptText_1_1);
		
		JButton btnDecryptText_1_1 = new JButton("Decrypt Text");
		btnDecryptText_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_1_1.getText();
				key = keyField_1_1.getText();
				
				AutoKeyVigenere vigenere = new AutoKeyVigenere(plaintext, key);
				ciphertext = vigenere.decrypt();
				
				if(chckbxSeparate_1_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_1_1.setText(ciphertext);
			}
		});
		btnDecryptText_1_1.setBounds(252, 155, 140, 23);
		autoKey.add(btnDecryptText_1_1);
		
		JLabel lblYourFile_1_1 = new JLabel("Your File : ");
		lblYourFile_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile_1_1.setBounds(10, 11, 82, 14);
		autoKey.add(lblYourFile_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(102, 11, 351, 20);
		autoKey.add(textField_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Your Text : ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(10, 38, 82, 14);
		autoKey.add(lblNewLabel_1_1);
		
		JButton browseFileButton_1_1 = new JButton("Browse...");
		browseFileButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						textField_3.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane_1_1.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					textField_3.setText("No file chosen!");
				}
			}
		});
		browseFileButton_1_1.setBounds(463, 11, 104, 20);
		autoKey.add(browseFileButton_1_1);
		
		JButton btnDownloadAsFile_1_1 = new JButton("Download as File");
		btnDownloadAsFile_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile_1_1.setBounds(241, 289, 120, 23);
		autoKey.add(btnDownloadAsFile_1_1);
		
		JPanel extended = new JPanel();
		tabbedPane.addTab("Extended Vigenere", null, extended, null);
		extended.setLayout(null);
		
		JLabel lblYourKey_1_1_1 = new JLabel("Your Key : ");
		lblYourKey_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourKey_1_1_1.setBounds(10, 120, 82, 14);
		extended.add(lblYourKey_1_1_1);
		
		JLabel lblCipheredText_1_1_1 = new JLabel("Ciphered Text : ");
		lblCipheredText_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText_1_1_1.setBounds(10, 189, 120, 14);
		extended.add(lblCipheredText_1_1_1);
		
		JTextPane outputLabel_1_1_1 = new JTextPane();
		outputLabel_1_1_1.setBounds(10, 204, 555, 74);
		extended.add(outputLabel_1_1_1);
		
		keyField_2 = new JTextField();
		keyField_2.setColumns(10);
		keyField_2.setBounds(102, 117, 463, 20);
		extended.add(keyField_2);
		
		JCheckBox chckbxSeparate_1_1_1 = new JCheckBox("Separate by 5");
		chckbxSeparate_1_1_1.setBounds(430, 155, 135, 23);
		extended.add(chckbxSeparate_1_1_1);
		
		JTextPane plaintextPane_1_1_1 = new JTextPane();
		plaintextPane_1_1_1.setBounds(102, 38, 463, 68);
		extended.add(plaintextPane_1_1_1);
		
		JButton btnEncryptText_1_1_1 = new JButton("Encrypt Text");
		btnEncryptText_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_1_1_1.getText();
				key = keyField_2.getText();
				
				AutoKeyVigenere vigenere = new AutoKeyVigenere(plaintext, key);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate_1_1_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_1_1_1.setText(ciphertext);
			}
		});
		btnEncryptText_1_1_1.setBounds(102, 155, 140, 23);
		extended.add(btnEncryptText_1_1_1);
		
		JButton btnDecryptText_1_1_1 = new JButton("Decrypt Text");
		btnDecryptText_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_1_1_1.getText();
				key = keyField_2.getText();
				
				AutoKeyVigenere vigenere = new AutoKeyVigenere(plaintext, key);
				ciphertext = vigenere.decrypt();
				
				if(chckbxSeparate_1_1_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_1_1_1.setText(ciphertext);
			}
		});
		btnDecryptText_1_1_1.setBounds(252, 155, 140, 23);
		extended.add(btnDecryptText_1_1_1);
		
		JLabel lblYourFile_1_1_1 = new JLabel("Your File : ");
		lblYourFile_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile_1_1_1.setBounds(10, 11, 82, 14);
		extended.add(lblYourFile_1_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(102, 11, 351, 20);
		extended.add(textField_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Your Text : ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(10, 38, 82, 14);
		extended.add(lblNewLabel_1_1_1);
		
		JButton browseFileButton_1_1_1 = new JButton("Browse...");
		browseFileButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						keyField_2.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane_1_1_1.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					keyField_2.setText("No file chosen!");
				}
			}
		});
		browseFileButton_1_1_1.setBounds(463, 11, 104, 20);
		extended.add(browseFileButton_1_1_1);
		
		JButton btnDownloadAsFile_1_1_1 = new JButton("Download as File");
		btnDownloadAsFile_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile_1_1_1.setBounds(241, 289, 120, 23);
		extended.add(btnDownloadAsFile_1_1_1);
		
		JPanel standard = new JPanel();
		tabbedPane.addTab("Standard Vigenere", null, standard, null);
		standard.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Text : ");
		lblNewLabel.setBounds(10, 38, 82, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		standard.add(lblNewLabel);
		
		JLabel lblYourKey = new JLabel("Your Key : ");
		lblYourKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourKey.setBounds(10, 120, 82, 14);
		standard.add(lblYourKey);
		
		JLabel lblCipheredText = new JLabel("Ciphered Text : ");
		lblCipheredText.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText.setBounds(10, 189, 120, 14);
		standard.add(lblCipheredText);
		
		JTextPane outputLabel = new JTextPane();
		outputLabel.setBounds(10, 204, 555, 74);
		standard.add(outputLabel);
		
		JTextPane plaintextPane = new JTextPane();
		plaintextPane.setBounds(102, 38, 463, 68);
		standard.add(plaintextPane);
		
		keyField = new JTextField();
		keyField.setBounds(102, 117, 463, 20);
		standard.add(keyField);
		keyField.setColumns(10);
		
		JCheckBox chckbxSeparate = new JCheckBox("Separate by 5");
		chckbxSeparate.setBounds(430, 155, 135, 23);
		standard.add(chckbxSeparate);
		
		JButton btnEncryptText = new JButton("Encrypt Text");
		btnEncryptText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plaintext = plaintextPane.getText();
				key = keyField.getText();
				
				Vigenere vigenere = new Vigenere(plaintext, key);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel.setText(ciphertext);
			}
		});
		btnEncryptText.setBounds(102, 155, 140, 23);
		standard.add(btnEncryptText);
		
		JButton btnDecryptText = new JButton("Decrypt Text");
		btnDecryptText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane.getText();
				key = keyField.getText();
				
				Vigenere vigenere = new Vigenere(plaintext, key);
				ciphertext = vigenere.decrypt();
				
				if(chckbxSeparate.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel.setText(ciphertext);
			}
		});
		btnDecryptText.setBounds(252, 155, 140, 23);
		standard.add(btnDecryptText);
		
		JLabel lblYourFile = new JLabel("Your File : ");
		lblYourFile.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile.setBounds(10, 11, 82, 14);
		standard.add(lblYourFile);
		
		fileTextField = new JTextField();
		fileTextField.setColumns(10);
		fileTextField.setBounds(102, 11, 351, 20);
		standard.add(fileTextField);
		
		JButton browseFileButton = new JButton("Browse...");
		browseFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						fileTextField.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					fileTextField.setText("No file chosen!");
				}
			}
		});
		browseFileButton.setBounds(463, 11, 104, 20);
		standard.add(browseFileButton);
		
		JButton btnDownloadAsFile = new JButton("Download as File");
		btnDownloadAsFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile.setBounds(241, 289, 120, 23);
		standard.add(btnDownloadAsFile);
		
		JPanel affineCipher = new JPanel();
		tabbedPane.addTab("Affine Cipher", null, affineCipher, null);
		affineCipher.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Your Text : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(8, 37, 82, 14);
		affineCipher.add(lblNewLabel_2);
		
		JLabel lblYourCofactor_2 = new JLabel("Your Cofactor : ");
		lblYourCofactor_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCofactor_2.setBounds(8, 119, 82, 14);
		affineCipher.add(lblYourCofactor_2);
		
		JLabel lblCipheredText_2 = new JLabel("Ciphered Text : ");
		lblCipheredText_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText_2.setBounds(8, 188, 120, 14);
		affineCipher.add(lblCipheredText_2);
		
		JTextPane outputLabel_2 = new JTextPane();
		outputLabel_2.setBounds(8, 203, 555, 74);
		affineCipher.add(outputLabel_2);
		
		JTextPane plaintextPane_2 = new JTextPane();
		plaintextPane_2.setBounds(100, 37, 463, 68);
		affineCipher.add(plaintextPane_2);
		
		textCofactor = new JTextField();
		textCofactor.setColumns(10);
		textCofactor.setBounds(100, 116, 166, 20);
		affineCipher.add(textCofactor);
		
		textShift = new JTextField();
		textShift.setColumns(10);
		textShift.setBounds(397, 113, 166, 20);
		affineCipher.add(textShift);
		
		JCheckBox chckbxSeparate_2 = new JCheckBox("Separate by 5");
		chckbxSeparate_2.setBounds(428, 154, 135, 23);
		affineCipher.add(chckbxSeparate_2);
		
		JButton btnEncryptText_2 = new JButton("Encrypt Text");
		btnEncryptText_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_2.getText();
				int m = Integer.parseInt(textCofactor.getText());
				int b = Integer.parseInt(textShift.getText());
				
				Affine vigenere = new Affine(plaintext, m, b);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate_2.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_2.setText(ciphertext);
			}
		});
		btnEncryptText_2.setBounds(100, 154, 140, 23);
		affineCipher.add(btnEncryptText_2);
		
		JButton btnDecryptText_2 = new JButton("Decrypt Text");
		btnDecryptText_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_2.getText();
				int m = Integer.parseInt(textCofactor.getText());
				int b = Integer.parseInt(textShift.getText());
				
				Affine vigenere = new Affine(plaintext, m, b);
				ciphertext = vigenere.decrypt();
				
				if(chckbxSeparate_2.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_2.setText(ciphertext);
			}
		});
		btnDecryptText_2.setBounds(250, 154, 140, 23);
		affineCipher.add(btnDecryptText_2);
		
		JLabel lblYourFile_2 = new JLabel("Your File : ");
		lblYourFile_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile_2.setBounds(8, 10, 82, 14);
		affineCipher.add(lblYourFile_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(100, 10, 351, 20);
		affineCipher.add(textField_4);
		
		JButton browseFileButton_2 = new JButton("Browse...");
		browseFileButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						textField_4.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane_2.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					textField_4.setText("No file chosen!");
				}
			}
		});
		browseFileButton_2.setBounds(461, 10, 104, 20);
		affineCipher.add(browseFileButton_2);
		
		JButton btnDownloadAsFile_2 = new JButton("Download as File");
		btnDownloadAsFile_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile_2.setBounds(239, 288, 120, 23);
		affineCipher.add(btnDownloadAsFile_2);
		
		JLabel lblYourCofactor_2_1 = new JLabel("Your Shifting : ");
		lblYourCofactor_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCofactor_2_1.setBounds(305, 116, 82, 14);
		affineCipher.add(lblYourCofactor_2_1);
		
		JPanel superEn = new JPanel();
		tabbedPane.addTab("Super Encryption", null, superEn, null);
		superEn.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Your Text : ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setBounds(10, 38, 82, 14);
		superEn.add(lblNewLabel_2_1);
		
		JLabel lblYourCofactor_2_2 = new JLabel("Your Key : ");
		lblYourCofactor_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCofactor_2_2.setBounds(10, 120, 82, 14);
		superEn.add(lblYourCofactor_2_2);
		
		JLabel lblCipheredText_2_1 = new JLabel("Ciphered Text : ");
		lblCipheredText_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText_2_1.setBounds(10, 189, 120, 14);
		superEn.add(lblCipheredText_2_1);
		
		JTextPane outputLabel_2_1 = new JTextPane();
		outputLabel_2_1.setBounds(10, 204, 555, 74);
		superEn.add(outputLabel_2_1);
		
		JTextPane plaintextPane_2_1 = new JTextPane();
		plaintextPane_2_1.setBounds(102, 38, 463, 68);
		superEn.add(plaintextPane_2_1);
		
		keyField_8 = new JTextField();
		keyField_8.setColumns(10);
		keyField_8.setBounds(102, 117, 166, 20);
		superEn.add(keyField_8);
		
		keyTranspose = new JTextField();
		keyTranspose.setColumns(10);
		keyTranspose.setBounds(399, 114, 166, 20);
		superEn.add(keyTranspose);
		
		JCheckBox chckbxSeparate_2_1 = new JCheckBox("Separate by 5");
		chckbxSeparate_2_1.setBounds(430, 155, 135, 23);
		superEn.add(chckbxSeparate_2_1);
		
		JButton btnEncryptText_2_1 = new JButton("Encrypt Text");
		btnEncryptText_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_2_1.getText();
				key = keyField_8.getText();
				int lengthTranspose = Integer.parseInt(keyTranspose.getText());
				
				SuperEncryption vigenere = new SuperEncryption(plaintext, key, lengthTranspose);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate_2_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_2_1.setText(ciphertext);
			}
		});
		btnEncryptText_2_1.setBounds(102, 155, 140, 23);
		superEn.add(btnEncryptText_2_1);
		
		JButton btnDecryptText_2_1 = new JButton("Decrypt Text");
		btnDecryptText_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_2_1.getText();
				key = keyField_8.getText();
				int lengthTranspose = Integer.parseInt(keyTranspose.getText());
				
				SuperEncryption vigenere = new SuperEncryption(plaintext, key, lengthTranspose);
				ciphertext = vigenere.decrypt();
				
				if(chckbxSeparate_2_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_2_1.setText(ciphertext);
			}
		});
		btnDecryptText_2_1.setBounds(252, 155, 140, 23);
		superEn.add(btnDecryptText_2_1);
		
		JLabel lblYourFile_2_1 = new JLabel("Your File : ");
		lblYourFile_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile_2_1.setBounds(10, 11, 82, 14);
		superEn.add(lblYourFile_2_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(102, 11, 351, 20);
		superEn.add(textField_8);
		
		JButton browseFileButton_2_1 = new JButton("Browse...");
		browseFileButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						textField_8.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane_2_1.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					textField_8.setText("No file chosen!");
				}
			}
		});
		browseFileButton_2_1.setBounds(463, 11, 104, 20);
		superEn.add(browseFileButton_2_1);
		
		JButton btnDownloadAsFile_2_1 = new JButton("Download as File");
		btnDownloadAsFile_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile_2_1.setBounds(241, 289, 120, 23);
		superEn.add(btnDownloadAsFile_2_1);
		
		JLabel lblYourCofactor_2_1_1 = new JLabel("Transpose Length : ");
		lblYourCofactor_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCofactor_2_1_1.setBounds(278, 117, 111, 14);
		superEn.add(lblYourCofactor_2_1_1);
		
		JPanel playfairCipher = new JPanel();
		tabbedPane.addTab("Playfair Cipher", null, playfairCipher, null);
		playfairCipher.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Your Text : ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(10, 38, 82, 14);
		playfairCipher.add(lblNewLabel_3);
		
		JLabel lblYourKey_2 = new JLabel("Your Key : ");
		lblYourKey_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourKey_2.setBounds(10, 120, 82, 14);
		playfairCipher.add(lblYourKey_2);
		
		JLabel lblCipheredText_3 = new JLabel("Ciphered Text : ");
		lblCipheredText_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText_3.setBounds(10, 189, 120, 14);
		playfairCipher.add(lblCipheredText_3);
		
		JTextPane plaintextPane_3 = new JTextPane();
		plaintextPane_3.setBounds(102, 38, 463, 68);
		playfairCipher.add(plaintextPane_3);
		
		keyField_6 = new JTextField();
		keyField_6.setColumns(10);
		keyField_6.setBounds(102, 117, 463, 20);
		playfairCipher.add(keyField_6);
		
		JTextPane outputLabel_3 = new JTextPane();
		outputLabel_3.setBounds(10, 204, 555, 74);
		playfairCipher.add(outputLabel_3);
		
		JCheckBox chckbxSeparate_3 = new JCheckBox("Separate by 5");
		chckbxSeparate_3.setBounds(430, 155, 135, 23);
		playfairCipher.add(chckbxSeparate_3);
		
		JButton btnEncryptText_3 = new JButton("Encrypt Text");
		btnEncryptText_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_3.getText();
				key = keyField_6.getText();
				
				Playfair vigenere = new Playfair(plaintext, key);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate_3.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_3.setText(ciphertext);
			}
		});
		btnEncryptText_3.setBounds(102, 155, 140, 23);
		playfairCipher.add(btnEncryptText_3);
		
		JButton btnDecryptText_3 = new JButton("Decrypt Text");
		btnDecryptText_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_3.getText();
				key = keyField_6.getText();
				
				Playfair vigenere = new Playfair(plaintext, key);
				ciphertext = vigenere.decrypt();
				
				if(chckbxSeparate_3.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_3.setText(ciphertext);
			}
		});
		btnDecryptText_3.setBounds(252, 155, 140, 23);
		playfairCipher.add(btnDecryptText_3);
		
		JLabel lblYourFile_3 = new JLabel("Your File : ");
		lblYourFile_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile_3.setBounds(10, 11, 82, 14);
		playfairCipher.add(lblYourFile_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(102, 11, 351, 20);
		playfairCipher.add(textField_5);
		
		JButton browseFileButton_3 = new JButton("Browse...");
		browseFileButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						textField_5.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane_3.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					textField_5.setText("No file chosen!");
				}
			}
		});
		browseFileButton_3.setBounds(463, 11, 104, 20);
		playfairCipher.add(browseFileButton_3);
		
		JButton btnDownloadAsFile_3 = new JButton("Download as File");
		btnDownloadAsFile_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile_3.setBounds(241, 289, 120, 23);
		playfairCipher.add(btnDownloadAsFile_3);
		
		JPanel hill = new JPanel();
		tabbedPane.addTab("Hill Cipher", null, hill, null);
		hill.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("Your Text : ");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setBounds(10, 38, 82, 14);
		hill.add(lblNewLabel_3_1);
		
		JLabel lblYourKey_2_1 = new JLabel("Your Key : ");
		lblYourKey_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourKey_2_1.setBounds(10, 120, 82, 14);
		hill.add(lblYourKey_2_1);
		
		JLabel lblCipheredText_3_1 = new JLabel("Ciphered Text : ");
		lblCipheredText_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipheredText_3_1.setBounds(10, 189, 120, 14);
		hill.add(lblCipheredText_3_1);
		
		JTextPane plaintextPane_3_1 = new JTextPane();
		plaintextPane_3_1.setBounds(102, 38, 463, 68);
		hill.add(plaintextPane_3_1);
		
		keyField_7 = new JTextField();
		keyField_7.setColumns(10);
		keyField_7.setBounds(102, 117, 463, 20);
		hill.add(keyField_7);
		
		JTextPane outputLabel_3_1 = new JTextPane();
		outputLabel_3_1.setBounds(10, 204, 555, 74);
		hill.add(outputLabel_3_1);
		
		JCheckBox chckbxSeparate_3_1 = new JCheckBox("Separate by 5");
		chckbxSeparate_3_1.setBounds(430, 155, 135, 23);
		hill.add(chckbxSeparate_3_1);
		
		JButton btnEncryptText_3_1 = new JButton("Encrypt Text");
		btnEncryptText_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_3_1.getText();
				key = keyField_7.getText();
				
				Hill vigenere = new Hill(plaintext, key);
				ciphertext = vigenere.encrypt();
				
				if(chckbxSeparate_3_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_3_1.setText(ciphertext);
			}
		});
		btnEncryptText_3_1.setBounds(102, 155, 140, 23);
		hill.add(btnEncryptText_3_1);
		
		JButton btnDecryptText_3_1 = new JButton("Decrypt Text");
		btnDecryptText_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaintext = plaintextPane_3_1.getText();
				key = keyField_7.getText();
				
				Hill vigenere = new Hill(plaintext, key);
				ciphertext = vigenere.decrypt();
				
				if(chckbxSeparate_3_1.isSelected()) {
					String temp = "";
					for(int i=0;i<ciphertext.length();i++) {
						if(i % 5 == 4) {
							temp += ciphertext.charAt(i) + " ";
						}
						else {
							temp += ciphertext.charAt(i);
						}
					}
					
					ciphertext = temp;
				}
				
				outputLabel_3_1.setText(ciphertext);
			}
		});
		btnDecryptText_3_1.setBounds(252, 155, 140, 23);
		hill.add(btnDecryptText_3_1);
		
		JLabel lblYourFile_3_1 = new JLabel("Your File : ");
		lblYourFile_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourFile_3_1.setBounds(10, 11, 82, 14);
		hill.add(lblYourFile_3_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(102, 11, 351, 20);
		hill.add(textField_6);
		
		JButton browseFileButton_3_1 = new JButton("Browse...");
		browseFileButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader (new FileReader(openFileChooser.getSelectedFile()));
						List<String> data = new ArrayList<String>();
						String line;
						while((line = br.readLine()) != null) {
							data.add(line);
						}
						
						br.close();
						plaintext = "";
						data.forEach(datum -> plaintext += datum);
						
						textField_6.setText(openFileChooser.getSelectedFile().getName());
						plaintextPane_3_1.setText(plaintext);
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
				else {
					textField_6.setText("No file chosen!");
				}
			}
		});
		browseFileButton_3_1.setBounds(463, 11, 104, 20);
		hill.add(browseFileButton_3_1);
		
		JButton btnDownloadAsFile_3_1 = new JButton("Download as File");
		btnDownloadAsFile_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = saveFileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile());
						fw.write(ciphertext.toString());
						fw.close();
					}
					catch(IOException exception) {
						System.out.println(exception);
					}
				}
			}
		});
		btnDownloadAsFile_3_1.setBounds(241, 289, 120, 23);
		hill.add(btnDownloadAsFile_3_1);
		
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
