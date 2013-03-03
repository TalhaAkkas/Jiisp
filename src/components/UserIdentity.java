package components;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserIdentity {
	public static final String ISLOGGEDIN = "isloggedin";
	private Map<String,String> getUsers(){
		Map<String,String> mp=new HashMap<String, String>();
		mp.put("admin", "admin");
		mp.put("demo", "demo");
		return mp;
	}
	public boolean authenticateUser(HttpServletResponse response, String username, String password){
		Map<String,String> mp = getUsers();
		String passwordForUser = mp.get(username);
		if(passwordForUser != null) {
			if(passwordForUser.equals(password)){
				setCookie(response, UserIdentity.ISLOGGEDIN, "true");
				return true;
			}
		}
		return false;		
	}
	public void logout(HttpServletResponse response){
		killCookie(response, UserIdentity.ISLOGGEDIN, "false");
	}
	
	public boolean isAuthenticate(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();     // request is an instance of type 
        //HttpServletRequest
		
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++){ 
				Cookie c = cookies[i];
				if (c.getName().equals(ISLOGGEDIN)){
					return Boolean.parseBoolean(c.getValue());
				}
			}
		}
		return false;
	}

	
	private void setCookie(HttpServletResponse response, String cookieName, String value){
		Cookie c = new Cookie(cookieName, value);
		c.setMaxAge(24*60*60);
		response.addCookie(c); 
	}
	private void killCookie(HttpServletResponse response, String cookieName, String value){
		Cookie c = new Cookie(cookieName, value);
		c.setMaxAge(0);
		response.addCookie(c); 
	};
}
