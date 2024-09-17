package com.gestioncentre.model;

public class Rubriques {
	private int id;
	private String Nom;
	private boolean Type_de_Centre;
	private boolean Charges_D_I;
	private boolean Charges_Inco_Corp;
	private boolean Fixe_Variable;
	
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public boolean isType_de_Centre() {
		return Type_de_Centre;
	}
	public boolean isFixe_Variable() {
		return Fixe_Variable;
	}

	public void setFixe_Variable(boolean fixe_Variable) {
		Fixe_Variable = fixe_Variable;
	}

	public void setType_de_Centre(boolean type_de_Centre) {
		Type_de_Centre = type_de_Centre;
	}
	public boolean isCharges_D_I() {
		return Charges_D_I;
	}
	public void setCharges_D_I(boolean charges_D_I) {
		Charges_D_I = charges_D_I;
	}
	public boolean isCharges_Inco_Corp() {
		return Charges_Inco_Corp;
	}
	public void setCharges_Inco_Corp(boolean charges_Inco_Corp) {
		Charges_Inco_Corp = charges_Inco_Corp;
	}
	
	
}
