/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.ArrayList;

/**
 * Configuration d'un Graph logarithmique ou non
 *
 * Contient tous les informations pour la creation d'un graphe logarithmique ou non
 * @author Wouaip
 */
public class ConfigXY extends ConfigChart
{

    private final int nbSeries;
    private final ArrayList<ConfigSerie> listSerie;
    private final  boolean logarithmic;
    private final int base;
    private final String xLabel;
    private final String yLabel;
    
    /**
     *    Constructeur pour un graph Non Logarithmic
     * @param title Titre
     * @param xLabel Nom du label en abscisse 
     * @param yLabel Nom du label en ordonnée
     * @param listSerie Liste des configuration des séries 
     */
    public ConfigXY(String title, String xLabel,String yLabel, ArrayList<ConfigSerie> listSerie) {
        super(title);
        this.listSerie = listSerie;
        this.nbSeries = listSerie.size();
        this.base = -1;
        this.logarithmic = false;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        
    }
    
    /**
     *     Constructeur pour un graph Logarithmique (ou non)
     * @param title titre
     * @param xLabel nom du label en abscisse
     * @param yLabel nom du label en ordonnée
     * @param listSerie Liste des configuration des séries
     * @param logarithmic Permet de choisir si le graph a une echelle logarithmique
     * @param base Base de l'échelle logarithmique 
     */
    public ConfigXY(String title,  String xLabel,String yLabel,  ArrayList<ConfigSerie> listSerie, boolean logarithmic, int base) {
        super(title);
        this.listSerie = listSerie;
        this.nbSeries = listSerie.size();
        this.base = base;
        this.logarithmic = logarithmic;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        
    }

    public int getNbSeries() {
        return nbSeries;
    }

    public ArrayList<ConfigSerie> getListSerie() {
        return listSerie;
    }

    public boolean isLogarithmic() {
        return logarithmic;
    }

    public int getBase() {
        return base;
    }

    public String getxLabel() {
        return xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }
    
    
}
