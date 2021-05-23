package co.edu.ufps.parcial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.parcial.dao.CountryDao;
import co.edu.ufps.parcial.dao.CountryDaoFactory;
import co.edu.ufps.parcial.dao.TeamDao;
import co.edu.ufps.parcial.dao.TeamDaoFactory;
import co.edu.ufps.parcial.modelo.Country;
import co.edu.ufps.parcial.modelo.Team;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeamServlet
 */
@WebServlet({"/team" })
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamDao teamDao;

    /**
     * Default constructor. 
     */
    public TeamServlet() {
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	String type = getServletContext().getInitParameter("type");
		this.teamDao = TeamDaoFactory.getTeamDao(type);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/team/new":
				showNewForm(request, response);
				break;
			case "/team/insert":
				insertarTeam(request, response);
				break;
			case "/team/delete":
				eliminarTeam(request, response);
				break;
			case "/team/edit":
				showEditForm(request, response);
				break;
			case "/team/update":
				actualizarTeam(request, response);
				break;
			default:
				listTeam(request, response);
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
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/team.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarTeam(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("nombre");
		String id = request.getParameter("id");
		String country = request.getParameter("country");

		Team usuario = new Team(id, name, new Country(country));
		teamDao.insert(usuario);

		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Team teamActual = teamDao.select(id);
		String type = getServletContext().getInitParameter("type");
		CountryDao countrydao = CountryDaoFactory.getCountryDao(type);
		List<Country> listCountry = countrydao.selectAll();
				
		request.setAttribute("listCountry", listCountry);
		request.setAttribute("team", teamActual);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/team.jsp");
		dispatcher.forward(request, response);
	}
	
	private void actualizarTeam(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String country = request.getParameter("country");

		Team team = new Team(id, name, new Country(country));
		teamDao.update(team);

		response.sendRedirect("list");
	}

	private void eliminarTeam(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		// TODO Auto-generated method stub
		String id = request.getParameter("id");

		teamDao.delete(id);

		response.sendRedirect("list");
	}
	

	private void listTeam(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		List<Team> listTeams =  teamDao.selectAll(); 
		request.setAttribute("listTeams", listTeams);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teamlist.jsp");
		dispatcher.forward(request, response);
	}

}
