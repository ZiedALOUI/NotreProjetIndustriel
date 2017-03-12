/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.Color;

/**
 * Info de configuration pour une Serie d'un graph
 * 
 * Permet la creation d'une configuration de serie qui compose un ConfigXY
 * 
 * @author Wouaip
 */
public class ConfigSerie {
    /**
     * Nom de série (=identifiant)
     */
    private final String name;
    /**
     * Couleur d'affichage
     */
    private final Color color;
    /**
     * Forme des points visibles ?
     */
    private final boolean shapesVisible;
    /**
     * Relié les points par des lignes ?
     */
    private final boolean linesVisible;

    /**
     * 
     * @param name Identifiant de la série
     * @param color couleur d'affichage
     * @param seriesShapesVisible   Forme des points visibles ?
     * @param linesVisible Relié les points par des lignes ?
     */
    public ConfigSerie(String name, Color color, boolean seriesShapesVisible, boolean linesVisible) {
        this.name = name;
        this.color = color;
        this.shapesVisible = seriesShapesVisible;
        this.linesVisible = linesVisible;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public boolean isShapesVisible() {
        return shapesVisible;
    }

    public boolean isLinesVisible() {
        return linesVisible;
    }
    
    
    
}
