package models;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import config.main;

@IdName("idpost")
public class Post extends Model{
	private String header;
	private String text;

	public boolean savePost(){
		if(!Base.hasConnection())
			Base.open("com.mysql.jdbc.Driver", String.format("%s/%s", main.DBURL, main.DBNAME), main.DBUSERNAME, main.DBUSERPASSWORD);
		set("header", header);
		set("text", text);
		System.out.println("save post");
		return saveIt();
	}

	public String getHeader() {
		if(header != null)
			header = (String) get("header");
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
		set("header", header);
	}
	public String getText() {
		if(text != null)
			text = (String) get("text");
		return text;
	}
	public void setText(String text) {
		this.text = text;
		set("text", text);
	}
}
