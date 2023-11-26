package Paint;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage;

public class CanvasDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI() {
		System.out.println("Created GUI on EDT?" + SwingUtilities.isEventDispatchThread());
		JFrame f = new JFrame("Swing Paint Demo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RectPanel rectPanel = new RectPanel();
		f.add(rectPanel, BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);

	}

}

class RectPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

	String shapeString = "";
	Point firstPointer = new Point(0, 0);
	Point secondPointer = new Point(0, 0);
	BufferedImage bufferedImage;
	Color colors = Color.black;
	Float stroke = (float) 5;
    JComboBox<Float> strokeComboBox;

	int width;
	int height;
	int minPointx;
	int minPointy;
	
	public RectPanel() {

		JButton penButton = new JButton("펜");
		JButton eraseButton = new JButton("지우개");

		add(penButton);
		add(eraseButton);


		Dimension d = getPreferredSize();
		bufferedImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		setImageBackground(bufferedImage); // save 할 때 배경이 default로 black이여서 흰색으로

		penButton.addActionListener(this);
		eraseButton.addActionListener(this);

		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void mousePressed(MouseEvent e) {

		// 다시 클릭됐을경우 좌표 초기화
		firstPointer.setLocation(0, 0);
		secondPointer.setLocation(0, 0);

		firstPointer.setLocation(e.getX(), e.getY());

	}

	public void mouseReleased(MouseEvent e) {

		if (shapeString != "펜") {
			secondPointer.setLocation(e.getX(), e.getY());
			updatePaint();
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass().toString().contains("JButton")) {
			shapeString = e.getActionCommand();
		} else if (e.getSource().equals(strokeComboBox)) {
            stroke = (float) strokeComboBox.getSelectedItem();
        }

	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 700);
	}

	public void updatePaint() {

		width = Math.abs(secondPointer.x - firstPointer.x);
		height = Math.abs(secondPointer.y - firstPointer.y);

		minPointx = Math.min(firstPointer.x, secondPointer.x);
		minPointy = Math.min(firstPointer.y, secondPointer.y);

		Graphics2D g = bufferedImage.createGraphics();

		switch (shapeString) {

		case ("펜"):
			g.setColor(colors);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(firstPointer.x, firstPointer.y, secondPointer.x, secondPointer.y);
			break;

		case ("지우개"):
			g.setColor(Color.white);
			g.setStroke(new BasicStroke(stroke));
			g.drawLine(firstPointer.x, firstPointer.y, secondPointer.x, secondPointer.y);
			break;
			
		default:
			break;

		}

		g.dispose();
		
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, null);

	}

	public void setImageBackground(BufferedImage bi) {
		this.bufferedImage = bi;
		Graphics2D g = bufferedImage.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 700);
		g.dispose();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		width = Math.abs(secondPointer.x - firstPointer.x);
		height = Math.abs(secondPointer.y - firstPointer.y);

		minPointx = Math.min(firstPointer.x, secondPointer.x);
		minPointy = Math.min(firstPointer.y, secondPointer.y);
		
		if (shapeString == "펜" | shapeString == "지우개") {
			if (secondPointer.x != 0 && secondPointer.y != 0) {
				firstPointer.x = secondPointer.x;
				firstPointer.y = secondPointer.y;
			}
			secondPointer.setLocation(e.getX(), e.getY());
			updatePaint();
		}	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}