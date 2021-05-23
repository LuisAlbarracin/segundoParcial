package co.edu.ufps.parcial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import co.edu.ufps.parcial.dao.CountryDao;
import co.edu.ufps.parcial.dao.CountryDaoFactory;
import co.edu.ufps.parcial.dao.CyclistDao;
import co.edu.ufps.parcial.dao.CyclistDaoFactory;
import co.edu.ufps.parcial.dao.TeamDao;
import co.edu.ufps.parcial.dao.TeamDaoFactory;
import co.edu.ufps.parcial.modelo.Country;
import co.edu.ufps.parcial.modelo.Cyclist;
import co.edu.ufps.parcial.modelo.Team;
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
@WebServlet({"/cyclist" })
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
		// TODO Auto-generated method stub
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
			case "/cyclist/new":
				System.out.println("New Cyclist");
				showNewForm(request, response);
				break;
			case "/cyclist/insert":
				insertarCyclist(request, response);
				break;
			case "/cyclist/delete":
				eliminarCyclist(request, response);
				break;
			case "/cyclist/edit":
				showEditForm(request, response);
				break;
			case "/cyclist/update":
				actualizarCyclist(request, response);
				break;
			default:
				listCyclist(request, response);
				break;

			}
		} catch (SQLException | ParseException e) {
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
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cyclist.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarCyclist(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ParseException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date birthday = formatter.parse(request.getParameter("birthday"));
		String country = request.getParameter("country");
		String team = request.getParameter("team");

		Cyclist usuario = new Cyclist(name, email, birthday, new Country(country),new Team(team));
		cyclistDao.insert(usuario);

		response.sendRedirect("/cyclist/list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Cyclist cyclistActual = cyclistDao.select(id);
		
		String type = getServletContext().getInitParameter("type");
		TeamDao teamdao = TeamDaoFactory.getTeamDao(type);
		List<Team> listTeam = teamdao.selectAll();
		CountryDao countrydao = CountryDaoFactory.getCountryDao(type);
		List<Country> listCountry = countrydao.selectAll();
				
		request.setAttribute("listCountry", listCountry);
				
		request.setAttribute("listTeam", listTeam);
		request.setAttribute("cyclist", cyclistActual);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cyclist.jsp");
		dispatcher.forward(request, response);
	}
	
	private void actualizarCyclist(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException, ParseException{
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date birthday = formatter.parse(request.getParameter("birthday"));
		String country = request.getParameter("country");
		String team = request.getParameter("team");

		Cyclist cyclist = new Cyclist(id, name, email, birthday, new Country(country), new Team(team));
		cyclistDao.update(cyclist);

		response.sendRedirect("/cyclist/list");
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
		request.setAttribute("listCyclists", listCyclist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cyclistlist.jsp");
		dispatcher.forward(request, response);
	}

}
