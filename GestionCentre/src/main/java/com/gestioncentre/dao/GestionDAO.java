package com.gestioncentre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gestioncentre.model.Rubriques;

import com.gestioncentre.controller.*;
import com.gestioncentre.database.*;

public class GestionDAO {
	
	//Pour l'ajout
    public static int registerRubriques(Rubriques rubriques) throws ClassNotFoundException {
    	
    	//IL FAUT CREER UNE TABLE RUBRIQUES
    	
        String INSERT_USERS_SQL = "INSERT INTO rubriques" +
            "  (nom, type_de_centre, indirect_direct, inco_non_inco, fixe_variable) VALUES "+
            " (?, ?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DatabaseConnection.getConnection();

       
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, rubriques.getNom());
            preparedStatement.setBoolean(2, rubriques.isType_de_Centre());
            preparedStatement.setBoolean(3, rubriques.isCharges_D_I());
            preparedStatement.setBoolean(4, rubriques.isCharges_Inco_Corp());
            preparedStatement.setBoolean(5, rubriques.isFixe_Variable());
            

            System.out.println(preparedStatement);
            
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            
        	GestionDAO gestionDAO = new GestionDAO();
        	gestionDAO.printSQLException(e);
            
        }
        return result;
    }
    
    //Pour la modification
    public static int updateRubriques(Rubriques rubriques) throws ClassNotFoundException {
    	
    	//IL FAUT CREER UNE TABLE RUBRIQUES
    	int result = 0;
    	String sql = "UPDATE rubriques SET nom = ?, type_de_centre = ?, indirect_direct = ?, inco_non_inco = ?, fixe_variable = ? WHERE id = ?;";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	
            preparedStatement.setString(1, rubriques.getNom());
            preparedStatement.setBoolean(2, rubriques.isType_de_Centre());
            preparedStatement.setBoolean(3, rubriques.isCharges_D_I());
            preparedStatement.setBoolean(4, rubriques.isCharges_Inco_Corp());
            preparedStatement.setBoolean(5, rubriques.isFixe_Variable());
            preparedStatement.setInt(6, rubriques.getId());
            preparedStatement.executeUpdate();
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
        	GestionDAO gestionDAO = new GestionDAO();
        	gestionDAO.printSQLException(e);
            
        }
        return result;
    }
    
    
    
    //Pour la suppression
    public static int deleteRubriques(Rubriques rubriques) throws ClassNotFoundException {
    	
    	//IL FAUT CREER UNE TABLE RUBRIQUES
    	int result = 0;
    	String sql = "DELETE FROM rubriques WHERE id = ?;\r\n"
    			+ "";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        
            preparedStatement.setInt(1, rubriques.getId());
            preparedStatement.executeUpdate();
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
        	GestionDAO gestionDAO = new GestionDAO();
        	gestionDAO.printSQLException(e);
            
        }
        return result;
    }
    
    //Pour l'affichage 
    
    public static int afficherRubriquesG(Rubriques rubriques) throws ClassNotFoundException {
    	
    	int result = 0;
    	
    	//Ajouter la requête SQL
    	String sql = "SELECT * FROM rubriques"
    			+ "WHERE(Fixe_Variable = 'fixe' OR 'fixe' IS NULL)"
    			+ "AND (Fixe_Variable ='variable' OR 'variable' IS NULL)"
    			+ "AND (Inco_Corp = 'inco' OR 'inco' IS NULL)"
    			+ "AND (Inco_Corp = 'corp' OR 'corp' IS NULL)";
    			
    			
    	
    	try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    		preparedStatement.setInt(1,  rubriques.getId());
    		preparedStatement.setString(2,  rubriques.getNom());
    		preparedStatement.setBoolean(3,  rubriques.isCharges_D_I());
    		preparedStatement.setBoolean(4,  rubriques.isCharges_Inco_Corp());
    		preparedStatement.setBoolean(5,  rubriques.isFixe_Variable());
    		preparedStatement.setBoolean(6,  rubriques.isType_de_Centre());
    		preparedStatement.executeUpdate();
    		result = preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		GestionDAO gestionDAO = new GestionDAO();
    		gestionDAO.printSQLException(e);
    	}
    	
    	return result;
    }
    public static int afficherRubriquesChargesFixes(Rubriques rubriques) throws ClassNotFoundException {
    	
    	int result = 0;
    	
    	//Ajouter la requête SQL
    	String sql = "SELECT * FROM rubriques WHERE Fixe_Variable = 1;\r\n"
    			+ "";
    	
    	try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    		
    		preparedStatement.setBoolean(1,  rubriques.isFixe_Variable());
    		preparedStatement.executeUpdate();
    		result = preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		GestionDAO gestionDAO = new GestionDAO();
    		gestionDAO.printSQLException(e);
    	}
    	
    	return result;
    }
    
   
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
