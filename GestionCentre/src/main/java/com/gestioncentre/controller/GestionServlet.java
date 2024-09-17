package com.gestioncentre.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gestioncentre.dao.GestionDAO;
import com.gestioncentre.model.Rubriques;

/**
 * Servlet implementation class GestionServlet
 */
@WebServlet("/GestionServlet")
public class GestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		String nom = request.getParameter("nom");
		boolean Type_De_Centre = request.getParameter("typeDeCentre") != null;
		boolean Charges_D_I = request.getParameter("chargesDI") != null;
		boolean Charges_Inco_Corp = request.getParameter("chargesIncoCorp") != null;
		boolean Charges_Fixe_Variable = request.getParameter("chargesFixeVariable") != null;
		
		Rubriques rubriques = new Rubriques();
		rubriques.setCharges_D_I(Charges_D_I);
		rubriques.setCharges_Inco_Corp(Charges_Inco_Corp);
		rubriques.setFixe_Variable(Charges_Fixe_Variable);
		rubriques.setNom(nom);
		rubriques.setType_de_Centre(Type_De_Centre);
		
		try {
			if ("ajouter".equals(action)) {
			GestionDAO.registerRubriques(rubriques);
			} else if ("modifier".equals(action)) {
			GestionDAO.updateRubriques(rubriques);
			} else if ("supprimer".equals(action)) {
			GestionDAO.deleteRubriques(rubriques);
			} else if ("afficherRubriques".equals(action)) {
			GestionDAO.afficherRubriques(rubriques);
			} else if ("afficherRubriquesChargesFixes".equals(action)) {
			GestionDAO.afficherRubriquesChargesFixes(rubriques);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/affichageOK.jsp");
		dispatcher.forward(request, response);	
	}

}
