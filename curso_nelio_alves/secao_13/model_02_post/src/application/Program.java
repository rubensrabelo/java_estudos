package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.entities.Comment;
import model.entities.Post;

public class Program {

	public static void main(String[] args) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		String moment_str = "21/06/2018 13:05:44";
		
		LocalDateTime moment = LocalDateTime.parse(moment_str, fmt);
		String title = "Traveling to New Zealand";
		String content = "I'm going to visit this wonderful country!";
		int likes = 12;
		
		Post post = new Post(moment, title, content, likes);
		
		String text1 = "Have a nive trip";
		Comment comment1 = new Comment(text1);
		
		post.addComments(comment1);
		
		String text2 = "Wow that's awesome";
		Comment comment2 = new Comment(text2);
		
		post.addComments(comment2);
		
		System.out.println(post);
	}

}
