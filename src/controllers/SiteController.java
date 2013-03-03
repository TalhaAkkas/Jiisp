 package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import components.UserIdentity;

import models.Post;
import models.WebError;

/**
 * Servlet implementation class Controller
 */
@SuppressWarnings("serial")
public class SiteController extends Controller {
	@Override
	protected String getContainer(){
		return "main.jsp";
	}@Override
	protected String getBase(){
		return "site";
	}@Override
	protected String getLayout(){
		return "column2.jsp";
	}@Override
	protected String getBaseurl() {
		return "Site";
	}

	@Override
	protected Map<String, Action> getActions() {
		HashMap<String,Action> actionMap = new HashMap<String,Action>();
		actionMap.put(DEFAULT, new ActionIndex());
		actionMap.put("index", new ActionIndex());
		actionMap.put("contact", new ActionContact());
		actionMap.put("login", new ActionLogin());
		actionMap.put("logout", new ActionLogout());
		actionMap.put("create", new ActionCreate());
		return actionMap;
	}
	@Override
	public void doError(HttpServletRequest request, HttpServletResponse response, WebError error) throws ServletException, IOException {
		request.setAttribute("appname", "Jiisp");
		request.setAttribute("title", "Hello app");
		request.setAttribute("error", error);
		renderPage(request, response, "error.jsp");
	}
	
	class ActionContact implements Action{
		@Override
		public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setAttribute("appname", "Jiisp");
			request.setAttribute("title", "Hello app");
			renderPage(request, response, "contact.jsp");
		}
		@Override
		public boolean beforeAction(HttpServletRequest request,HttpServletResponse response) {
			return true;
		}
	}
	class ActionIndex implements Action{
		@Override
		public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setAttribute("appname", "Jiisp");
			request.setAttribute("title", "Hello app");
			renderPage(request, response, "index.jsp");
		}

		@Override
		public boolean beforeAction(HttpServletRequest request,HttpServletResponse response) {
			return true;
		}
	}
	class ActionLogin implements Action{
		@Override
		public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(request.getParameter("username") != null){
				UserIdentity user = new UserIdentity();
				if(user.authenticateUser(response,request.getParameter("username"), request.getParameter("password"))){
					try {

						response.sendRedirect(response.encodeRedirectURL(getBaseurl()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			request.setAttribute("appname", "Jiisp");
			request.setAttribute("title", "Hello app");
			renderPage(request, response, "login.jsp");
		}

		@Override
		public boolean beforeAction(HttpServletRequest request, HttpServletResponse response) {
			if(new UserIdentity().isAuthenticate(request)){
				try {
					response.sendRedirect(response.encodeRedirectURL(getBaseurl()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
				}
			return true;
		}
	}
	class ActionLogout implements Action{

		@Override
		public boolean beforeAction(HttpServletRequest request, HttpServletResponse response) {
			if(! new UserIdentity().isAuthenticate(request)){
				try {
					response.sendRedirect(response.encodeRedirectURL(getBaseurl()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
				}
			return true;
		}
		
		@Override
		public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(request.getParameter("logout") != null){
				UserIdentity user = new UserIdentity();
				user.logout(response);
				try {
					response.sendRedirect(response.encodeRedirectURL(getBaseurl()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			request.setAttribute("appname", "Jiisp");
			request.setAttribute("title", "Hello app");
			renderPage(request, response, "logout.jsp");
		}
		
	}
	class ActionCreate implements Action{

		@Override
		public boolean beforeAction(HttpServletRequest request, HttpServletResponse response) {
			return new UserIdentity().isAuthenticate(request);
		}

		@Override
		public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
			if(request.getParameter("create") != null){
				Post p = new Post();
				p.setHeader(request.getParameter("header"));
				p.setText(request.getParameter("text"));
				p.savePost();
				
				try {
					response.sendRedirect(response.encodeRedirectURL(getBaseurl()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("appname", "Jiisp");
			request.setAttribute("title", "Hello app");
			renderPage(request, response, "create.jsp");
		}
		
	}
}
