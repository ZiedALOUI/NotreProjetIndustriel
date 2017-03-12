/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.ArrayList;

/**
 *
 * Classe de configuration d'un camembert
 * 
 * Contient toutes les informations nécessaire à la creations d'un chart Camembert
 * 
 * @author Wouaip  
 */
public class ConfigPie extends ConfigChart{
    /**
     * Permet de choisir d'afficher une légende
     */
    private final boolean legend;
    /**
     * Le nombre de 'quartiers' ou 'divisions' qui composent le camembert
     */
    private final int nbDataSet;
    /*
    * La liste des noms des 'divisions' 
    */
    private final ArrayList<String> dataSetName;

    /**
     * 
     * @param title Titre du chart
     * @param legend Afficher la legende
     * @param nbDataSet Nombre de divisions
     * @param datasetName List des noms de divisions
     */
    public ConfigPie(String title, boolean legend, int nbDataSet, ArrayList<String> datasetName) {
        super(title);
        this.legend = legend;
        this.nbDataSet = nbDataSet;
        this.dataSetName = datasetName;
    }

    public boolean isLegend() {
        return legend;
    }

    public int getNbDataSet() {
        return nbDataSet;
    }

    public ArrayList<String> getDataSetName() {
        return dataSetName;
    }
    
    
    
}
