package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.javalite.activejdbc.Model;

import components.DatabaseAction;
import components.DatabaseManager;

import config.main;

public class Post {
	private int id;
	private String header;
	private String text;
	private boolean isNewPost;
	public Post( String header, String text){
		this.header = header;
		this.text = text;
		this.isNewPost = true;
	}
	private Post(int id, String header, String text){
		this.id = id;
		this.header = header;
		this.text = text;
		this.isNewPost = false;
	}
	public void savePost() {
		if(isNewPost){
			String str = String.format("insert into  post (header, text) VALUES ('%s', '%s');", header,text);
			DatabaseManager.execute(str);
		}else{
			String str = String.format("update post set header = '%s' , text = '%s' where idpost = '%d';", header, text, id);
			DatabaseManager.execute(str);			
		}  
	}
	
	public static List<Post> findAll() {
		LinkedList<Post> list = new LinkedList<Post>();
		DatabaseManager.executeQuery(new SelectPost(list), "select * from post");
		return list;
	}
	public static Post findById(int id) {
		LinkedList<Post> list = new LinkedList<Post>();
		DatabaseManager.executeQuery(new SelectPost(list), String.format("select * from post where idpost = %d", id));
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public void delete() {
		if(!isNewPost)
			DatabaseManager.execute(String.format("delete from post  where idpost = %d;", id));
	}
	public static class SelectPost implements DatabaseAction{
		List<Post> list;
		public SelectPost(List<Post> list){
			this.list = list;
		}
		@Override
		public void doAction(ResultSet rs) throws SQLException {
			System.out.printf("%d %s %s \n", rs.getInt(1),rs.getString(2),rs.getString(3));
			list.add(new Post(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public boolean isNewPost() {
		return isNewPost;
	}

}