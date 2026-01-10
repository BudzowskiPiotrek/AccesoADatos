package net.codejava.hibernate;

public class Main {

	public static void main(String[] args) {
		BookManager b = new BookManager();
		b.setup();
		// b.create();
		b.update();
		// b.delete();
		b.read();
		b.exit();
	}
}
