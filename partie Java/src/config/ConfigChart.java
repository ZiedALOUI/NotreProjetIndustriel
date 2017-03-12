/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *  Classe Abstraite pour la configuration des charts
 *
 *  Classe mère manipulée pour la creation d'un chart
 * @author Wouaip
 */
public abstract class ConfigChart {
    private final String title;

    /**
     * 
     * @param title Titre du chart
     */
    public ConfigChart(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
    
}
