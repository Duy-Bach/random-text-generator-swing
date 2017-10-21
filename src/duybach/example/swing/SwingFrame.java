package duybach.example.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import duybach.example.MarkovBuilder;

public class SwingFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea builderTextArea, generatedTextArea;
	private JScrollPane builderTextWrapper, generatedTextWrapper;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingFrame frame = new SwingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwingFrame() {
		setResizable(false);
		setTitle("Text generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		builderTextArea = new JTextArea(2, 5);
		builderTextArea.setLineWrap(true);
		builderTextArea.setWrapStyleWord(true);
		builderTextArea.setBorder(new EmptyBorder(5, 5, 5, 5));

		builderTextWrapper = new JScrollPane(builderTextArea);
		builderTextWrapper.setBounds(12, 30, 430, 100);
		contentPane.add(builderTextWrapper);
		
		generatedTextArea = new JTextArea(2, 5);
		generatedTextArea.setLineWrap(true);
		generatedTextArea.setWrapStyleWord(true);
		generatedTextArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		generatedTextArea.setEditable(false);
		generatedTextArea.setText("Please insert builder string first...");

		
		generatedTextWrapper = new JScrollPane(generatedTextArea);
		generatedTextWrapper.setBounds(12, 170, 430, 100);
		contentPane.add(generatedTextWrapper);
		
		JLabel lblBuilderText = new JLabel("Builder text:");
		lblBuilderText.setBounds(12, 10, 120, 15);
		contentPane.add(lblBuilderText);
		
		JLabel lblGeneratedText = new JLabel("Auto-generated text: ");
		lblGeneratedText.setBounds(12, 150, 200, 15);
		contentPane.add(lblGeneratedText);
		
		button = new JButton();
		button.setBounds(150, 280, 150, 30);
		button.setText("START");
		button.setBackground(new Color(165, 155, 165));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] input;
				input = builderTextArea.getText().split(" ");
				generateText(input);
			}
		});
		
		contentPane.add(button);
	}
	
	private void generateText(String[] input) {
		MarkovBuilder builder = new MarkovBuilder(input);
		generatedTextArea.setText(builder.generate());
	}
}
