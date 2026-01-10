package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {
	protected SessionFactory sessionFactory;
	private Session session;

	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected void exit() {
		session.close();
		sessionFactory.close();
	}

	protected void create() {
		Book book = new Book();
		book.setTitle("Effective Java5");
		book.setAuthor("Joshua Bloch");
		book.setPrice(32.59f);

		session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);

		session.getTransaction().commit();
	}

	protected void read() {
		session = sessionFactory.openSession();

		long bookId = 1;
		Book book = session.get(Book.class, bookId);

		System.out.println("Title: " + book.getTitle());
		System.out.println("Author: " + book.getAuthor());
		System.out.println("Price: " + book.getPrice());
	}

	protected void update() {
		 Book book = new Book();
		 book.setId(1);
		 book.setTitle("Ultimate Java Programming");
		 book.setAuthor("Nam Ha Minh");
		 book.setPrice(19.99f);

		 session = sessionFactory.openSession();
		 session.beginTransaction();

		 session.update(book);

		 session.getTransaction().commit();
	}

	protected void delete() {
		 Book book = new Book();
		 book.setId(4);

		 session = sessionFactory.openSession();
		 session.beginTransaction();

		 session.delete(book);

		 session.getTransaction().commit();

	}

	public static void main(String[] args) {
		// code to run the program
	}

}
