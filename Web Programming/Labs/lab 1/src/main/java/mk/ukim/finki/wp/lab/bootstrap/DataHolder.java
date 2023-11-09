package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authorList = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init(){
        authorList.add(new Author(1, "William", "Shakespeare",
                "William Shakespeare was a renowned English poet, playwright, and actor born in 1564 in Stratford-upon-Avon. His birthday is most commonly celebrated on 23 April, which is also believed to be the date he died in 1616."));
        authorList.add(new Author(2, "William", "Faulkner ", "William Cuthbert Faulkner (September 25, 1897 – July 6, 1962) was an American writer known for his novels and short stories set in the fictional Yoknapatawpha County, based on Lafayette County, Mississippi, where Faulkner spent most of his life."));
        authorList.add(new Author(3, "Henry", "James", "Henry James OM (15 April 1843 – 28 February 1916) was an American-British author. He is regarded as a key transitional figure between literary realism and literary modernism, and is considered by many to be among the greatest novelists in the English language."));
        authorList.add(new Author(4, "Jane", "Austen", "Jane Austen (16 December 1775 – 18 July 1817) was an English novelist known primarily for her six novels, which implicitly interpret, critique, and comment upon the British landed gentry at the end of the 18th century. Austen's plots often explore the dependence of women on marriage for the pursuit of favourable social standing and economic security."));
        authorList.add(new Author(5, "Jordan", "Peterson", "Jordan Bernt Peterson (born 12 June 1962) is a Canadian psychologist, author, and media commentator. Often described as conservative, he began to receive widespread attention in the late 2010s for his views on cultural and political issues. Peterson has described himself as a classic British liberal and a traditionalist."));

        books.add(new Book("978-0345816023", "12 Rules for Life", "Self-help book", 2018));
        books.add(new Book("978-0743477123", "Hamlet", "Tragedy/Drama", 1601));
        books.add(new Book("978-0679732242", "The Sound and the Fury", "Novel/Modern Literature", 1929));
        books.add(new Book("978-1612930992", "The Turn of the Screw", "Horror/Ghost", 1898));
        books.add(new Book("978-0141439518", "Pride and Prejudice", "Novel/Fiction", 1813));
    }
}
