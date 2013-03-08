package controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WebError;

/**
 * Servlet implementation class Controller
 */
@SuppressWarnings("serial")
public abstract class Controller extends HttpServlet {
	protected static final String DEFAULT = "default";
	private static final String INDEX = "/index.jsp";
	private static final String PROTECTEDDIR = "/WEB-INF/protected";
	private static final String VIEWSDIR = "/views";
	private static final String LAYOUTSDIR = "/layouts";

	public abstract void doError(HttpServletRequest request, HttpServletResponse response, WebError webEror) throws ServletException, IOException;
	protected abstract String getContainer() ;
	protected abstract String getBase();
	protected abstract String getLayout();
	protected abstract String getBaseurl();
	protected abstract Map<String,Action> getActions();

	
	public void renderPage(HttpServletRequest request, HttpServletResponse response, String View) throws ServletException, IOException{
		renderPage(request, response, View, getLayout());
	}

	public void renderPage(HttpServletRequest request, HttpServletResponse response, String View, String Layout) throws ServletException, IOException{
		 String forward = String.format("%s%s%s/%s", PROTECTEDDIR, VIEWSDIR, LAYOUTSDIR, getContainer());
		 String viewPath = String.format("../%s/%s", getBase(), View);
		 
		 RequestDispatcher view = request.getRequestDispatcher(INDEX);
		 request.setAttribute("layout", Layout);
		 request.setAttribute("content", viewPath);
		 request.setAttribute("forward", forward);
		 
		 view.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doMove(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doMove(request, response);
	}
	protected void doMove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Action> actions = getActions();
	    if (request.getParameter("action") != null){
	    	if(actions.containsKey(request.getParameter("action"))){
	    		if (actions.get(request.getParameter("action")).beforeAction(request, response))
	    			actions.get(request.getParameter("action")).doAction(request, response);
	    	}else{
	    		doError(request, response, new WebError(404, "aradığınız sayfaya ulaşılamıyor lütfen daha sonra tekrar deneyin"));
	    	}
	    }else{
	    	if(actions.containsKey(DEFAULT)){
	    		if (actions.get(DEFAULT).beforeAction(request, response))
	    			actions.get("default").doAction(request, response);
	    	}else{
	    		doError(request, response, new WebError(404, "aradığınız sayfaya ulaşılamıyor lütfen daha sonra tekrar deneyin"));
	    	}
	    }
	}
	
	
	protected interface Action{
		boolean beforeAction(HttpServletRequest request, HttpServletResponse response);
		void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	}



}
