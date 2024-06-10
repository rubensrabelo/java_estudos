package model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Post {
	private LocalDateTime moment;
	private String title;
	private String content;
	private int like;
	
	private List<Comment> comments = new ArrayList<>();
	
	public Post(LocalDateTime moment, String title, String content, int like) {
		this.moment = moment;
		this.title = title;
		this.content = content;
		this.like = like;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addComments(Comment comment) {
		comments.add(comment);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		str.append("Moment = " + getMoment().format(formatter));
		str.append("\nTitle = " + getTitle());
		str.append("\nContent = " + getContent());
		str.append("\nLike = " + getLike());
		
		for(Comment comment : comments) {
			str.append("Text = " + comment);
		}
		
		return str.toString();
	}
}
