package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Personne {
    private int id;
    private String nom, prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Personne> findAll() throws ClassNotFoundException, SQLException {
        Connection connect = DBConnection.getConnection();

        String SQLPrep = "SELECT * FROM personne;";
        PreparedStatement prep = connect.prepareStatement(SQLPrep);
        prep.execute();
        ResultSet rs = prep.getResultSet();
        ArrayList<Personne> personnes = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Personne p = new Personne(nom, prenom);
            p.setId(id);
            personnes.add(p);
        }
        return personnes;
    }

    public static Personne findById(int id) throws ClassNotFoundException, SQLException {
        Connection connect = DBConnection.getConnection();

        String SQLPrep = "SELECT nom, prenom FROM personne WHERE id = ?;";
        PreparedStatement prep = connect.prepareStatement(SQLPrep);
        prep.setInt(1, id);
        prep.execute();
        ResultSet rs = prep.getResultSet();
        Personne personne = null;
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            personne = new Personne(nom, prenom);
            personne.setId(id);
        }
        return personne;
    }

    public String toString() {
        return "-> (" + id + ") " + nom + ", " + prenom;
    }
}
