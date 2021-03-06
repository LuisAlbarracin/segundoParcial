package co.edu.ufps.parcial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.ufps.parcial.dao.CyclistDao;
import co.edu.ufps.parcial.dao.CyclistDaoFactory;
import co.edu.ufps.parcial.modelo.Cyclist;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CyclistServlet
 */
@WebServlet({ "/CyclistServlet", "/" })
public class CyclistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CyclistDao cyclistDao;

    /**
     * Default constructor. 
     */
    public CyclistServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		String type = getServletContext().getInitParameter("type");
		this.cyclistDao = CyclistDaoFactory.getCyclistDao(type);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarCyclist(request, response);
				break;
			case "/delete":
				eliminarCyclist(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				actualizarCyclist(request, response);
				break;
			default:
				listCyclist(request, response);
				break;

			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cyclist.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarCyclist(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String textoFecha = request.getParameter("birthday");
		System.out.println(textoFecha);
		SimpleDateFormat ffecha = new SimpleDateFormat("yyyy-mm-dd");
		Date birthday = null;
		
		
		try {
			birthday = ffecha.parse(textoFecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String country = request.getParameter("country");
		String team = request.getParameter("team");

		Cyclist cyclist = new Cyclist(name, email, birthday, country, team);
		cyclistDao.insert(cyclist);

		response.sendRedirect("list");
	
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Cyclist cyclistActual = cyclistDao.select(id);
		
		request.setAttribute("cyclist", cyclistActual);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cyclist.jsp");
		dispatcher.forward(request, response);
	}
	
	private void actualizarCyclist(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String textoFecha = request.getParameter("birthday");
		SimpleDateFormat ffecha = new SimpleDateFormat("yyyy-mm-dd");
		Date birthday = null;
		try {
			birthday = ffecha.parse(textoFecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String country = request.getParameter("country");
		String team = request.getParameter("team");

		Cyclist cyclist = new Cyclist(id, name, email, birthday, country, team);
		cyclistDao.update(cyclist);

		response.sendRedirect("list");
	}

	private void eliminarCyclist(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		cyclistDao.delete(id);

		response.sendRedirect("list");
	}
	

	private void listCyclist(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		List<Cyclist> listCyclist =  cyclistDao.selectAll(); 
		request.setAttribute("listCyclist", listCyclist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cyclistlist.jsp");
		dispatcher.forward(request, response);
	}
	

}
