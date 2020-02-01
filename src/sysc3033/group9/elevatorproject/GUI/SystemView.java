package sysc3033.group9.elevatorproject.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class SystemView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel eventPanel, queuePanel;
	private JTextArea eventPanelText, displayText, elevatorText, schedulerText, floorText, queueText;
	private JScrollPane elevatorScrollPane, displayScrollPane, schedulerScrollPane, floorScrollPane;
	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblQueue;

	public SystemView() {
		eventPanel = new JPanel();
		eventPanelText = new JTextArea();
		elevatorScrollPane = new JScrollPane();
		elevatorText = new JTextArea();
		lblNewLabel = new JLabel("EventFile Log");
		lblNewLabel_1 = new JLabel("DisplayLamp");
		lblNewLabel_2 = new JLabel("FloorSubSystem Log");
		lblNewLabel_3 = new JLabel("Scheduler Log");
		lblNewLabel_4 = new JLabel("ElevatorSubSystem Log");
		displayScrollPane = new JScrollPane();
		schedulerScrollPane = new JScrollPane();
		displayText = new JTextArea();
		schedulerText = new JTextArea();
		floorScrollPane = new JScrollPane();
		floorText = new JTextArea();
		queuePanel = new JPanel();
		queueText = new JTextArea();
		lblQueue = new JLabel("CURRENTLY PROCESSING");
		eventPanelText.setEditable(false);
		displayText.setEditable(false);
		elevatorText.setEditable(false);
		schedulerText.setEditable(false);
		floorText.setEditable(false);
		initialize();
	}

	private void initialize() {
		setTitle("System Logs");
		setBounds(100, 100, 1700, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		eventPanel.setBounds(43, 84, 381, 132);
		getContentPane().add(eventPanel);
		eventPanel.add(eventPanelText);
		elevatorScrollPane.setBounds(1284, 84, 362, 624);
		getContentPane().add(elevatorScrollPane);
		elevatorText.setTabSize(0);
		elevatorScrollPane.setViewportView(elevatorText);
		lblNewLabel.setBounds(184, 59, 79, 14);
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setBounds(194, 359, 150, 14);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setBounds(576, 59, 153, 14);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_3.setBounds(1413, 59, 150, 14);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_4.setBounds(966, 59, 263, 14);
		getContentPane().add(lblNewLabel_4);
		displayScrollPane.setBounds(110, 384, 227, 207);
		getContentPane().add(displayScrollPane);
		displayScrollPane.setViewportView(displayText);
		schedulerScrollPane.setBounds(843, 84, 362, 624);
		getContentPane().add(schedulerScrollPane);
		schedulerScrollPane.setViewportView(schedulerText);
		floorScrollPane.setBounds(467, 78, 320, 630);
		getContentPane().add(floorScrollPane);
		floorScrollPane.setViewportView(floorText);
		queuePanel.setBounds(126, 270, 190, 78);
		getContentPane().add(queuePanel);
		queuePanel.add(queueText);
		queueText.setColumns(10);
		lblQueue.setBounds(160, 245, 180, 14);
		getContentPane().add(lblQueue);
		setVisible(true);
	}

	public JTextArea getEventPanelText() {
		return eventPanelText;
	}

	public JTextArea getDisplayText() {
		return displayText;
	}

	public JTextArea getElevatorText() {
		return elevatorText;
	}

	public JTextArea getFloorText() {
		return floorText;
	}

	public JTextArea getQueueText() {
		return queueText;
	}

	public JTextArea getSchedulerText() {
		return schedulerText;
	}

	public void setText(JTextComponent textCmpt, String msg) {
		String str = textCmpt.getText() + java.time.LocalTime.now().toString() + ": ";
		textCmpt.setText(str + msg);
		repaint();
	}

	public void setQueue(JTextComponent textCmpt, String msg) {
		textCmpt.setText(msg);
		repaint();
	}
}
