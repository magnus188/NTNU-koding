package blackjack.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Card {
	public static final char[] validSuits = { 'S', 'H', 'D', 'C' };
	private final char suit;
	private final int face;

	public Card() {
		this.suit = 'X';
		this.face = 0;
	}

	public Card(char suit, int face) {
		checkIfValidFace(face);
		checkIfValidSuit(suit);
		this.suit = suit;
		this.face = face;
	}

	private boolean checkIfValidFace(int face) {
		if (0 < face && face < 14) {
			return true;
		}
		throw new IllegalArgumentException("Invalid face");
	}

	private boolean checkIfValidSuit(char suit) {
		for (char s : validSuits) {
			if (s == suit) {
				return true;
			}
		}
		throw new IllegalArgumentException("Invalid suit");
	}

	public char getSuit() {
		return suit;
	}

	public int getFace() {
		return face;
	}

	public ImageView assignImage() throws IOException {
		String imageName = "./cardImages/";
		if (1 < this.getFace() && this.getFace() < 11) {
			imageName += this + ".png";
		} else if (this.getFace() == 1) {
			imageName += "A" + this.getSuit() + ".png";
		} else if (this.getFace() == 11) {
			imageName += "J" + this.getSuit() + ".png";
		} else if (this.getFace() == 12) {
			imageName += "Q" + this.getSuit() + ".png";
		} else if (this.getFace() == 13) {
			imageName += "K" + this.getSuit() + ".png";
		} else
			imageName += "red_back.png";
		try {
			Image image;
			BufferedImage bufferedImage = ImageIO.read(new File(imageName));
			image = convertToImage(bufferedImage);
			ImageView imageView = new ImageView(image);
			return imageView;
		} catch (IOException e) {
			throw new IOException("Failed to assign picture");
		}
	}

	// Her har jeg måtte klippe sammen kode jeg har funnet på Stackoverflow
	private static Image convertToImage(BufferedImage image) {
		WritableImage wr = null;
		if (image != null) {
			wr = new WritableImage(image.getWidth(), image.getHeight());
			PixelWriter pw = wr.getPixelWriter();
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					pw.setArgb(x, y, image.getRGB(x, y));
				}
			}
		}
		return new ImageView(wr).getImage();
	}
	
	@Override
	public String toString() {
		return ("" + face + suit);
	}
}