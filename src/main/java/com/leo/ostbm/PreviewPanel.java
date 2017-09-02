package com.leo.ostbm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PreviewPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static final String A_SAVE_BOXES = "saveBoxes";

	private BufferedImage image;
	private ImageIcon previewImage;
	private JButton saveButton;

	public PreviewPanel(BufferedImage image) {
		this.image = image;
		previewImage = new ImageIcon(image, "image preview");
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		JPanel previewPanel = new JPanel();
		JLabel previewLabel = new JLabel(previewImage);
		previewLabel.setMinimumSize(new Dimension(0, previewImage.getIconHeight()));
		previewPanel.add(previewLabel);
		previewPanel.setMinimumSize(new Dimension(previewImage.getIconWidth(), 0));
		JScrollPane previewScroll = new JScrollPane(previewPanel);
		add(previewScroll, BorderLayout.CENTER);
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveButton.setActionCommand(A_SAVE_BOXES);
		saveButton.setToolTipText("Save this textbox (or these textboxes) as an image");
		add(saveButton, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (A_SAVE_BOXES.equals(e.getActionCommand())) {
			File sel = Main.openFileDialog(true, this, "Save textbox(es) image",
					new FileNameExtensionFilter("PNG files", "png"));
			if (sel == null)
				return;
			try {
				ImageIO.write(image, "png", sel);
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "An exception occured while saving the image:\n" + e1,
						"Couldn't save image!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
