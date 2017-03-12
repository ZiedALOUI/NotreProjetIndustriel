/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import config.ConfigSerie;
import config.ConfigChart;
import config.ConfigPie;
import config.ConfigXY;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.LogFormat;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import view.Observateur;

/**
 * Classe representant un chart
 * 
 * @author Wouaip
 */
public class Chart  implements Observer
{
    /**
     * Si la mise a jour de ses valeurs est pausée
     */
    private boolean paused;
    /**
     * Le chart 
     */
    private JFreeChart chart;
    /**
     * Type du chart
     */
    private static data.Constants.TYPE_OF_CHARTS type;
    
    /**
     * Pere de chart
     */
    Observateur observateur;
    /**
     * Constructeur capable de produire un camembert ou un chart en fonction du type
     * @param observateur l'observateur
     * @param config configuration du chart a produire (contient le type)
     */
    public Chart(Observateur observateur, ConfigChart config) {
        paused = false;
        this.observateur = observateur;
            //Test si c'est une conf d'un graph
            if(config instanceof ConfigXY)
            {
                initXYGraph((ConfigXY) config);
               
         
        } else if ( config instanceof ConfigPie)
        {
            
            initPieChart((ConfigPie) config);
           
        }
        
    }
    
    
    /**
     * Change l'etat du chart 
     */
    public void changeState()
    {
        paused = !paused;
    }   
    
    /**
     * Implémente la mise a jour du chart
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        if(!paused)
        {
        /*    if(this instanceof JPieChart)
            {
                
            }
            else {
                
            }*/
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JFreeChart getChart() {
        return chart;
    }

    private void generateRandomValue(XYSeries serie) {
        Random rand = new Random();
        for(int i = 0; i < 10; i++)
        {
            serie.add(i, rand.nextInt(100000));
        }
    }
    
    private void generateRandomValueForPie(String name,DefaultPieDataset dataset)
    {
        Random random = new Random();
        int value = random.nextInt(1000);
        //System.out.println( name + " "+ value);
        dataset.setValue(name, value);

    }

   
    public void autoRegister() 
    /* Specification : identifie les dataTab auxquelles ils doit s'abonner et s'y abonne
    *
     */
    {
        //Pour les pie
        if(Chart.type == Constants.TYPE_OF_CHARTS.Pie)
        {
            PiePlot plot =  (PiePlot) chart.getPlot();
            DefaultPieDataset dataset = (DefaultPieDataset) plot.getDataset();
            
            for(int i = 0; i < dataset.getItemCount(); i++)
            {
                this.observateur.getDataTab((String) dataset.getKey(i)).addObserver(this);
            }

           
        } else if (Chart.type == Constants.TYPE_OF_CHARTS.Pie)
        {
            XYPlot plot = chart.getXYPlot();
            DefaultXYDataset dataset = (DefaultXYDataset) plot.getDataset();
            
            for(int y = 0 ; y < dataset.getSeriesCount(); y++)
            {
                
                this.observateur.getDataTab((String) dataset.getSeriesKey(y)).addObserver(this);
                
            }
                
        }
            //pour chacune serie : observateur.dataTab(label).register
    }

    /**
     * Initialise le Chart comme un graph
     */
    private void initXYGraph(ConfigXY config) 
    {
         type = data.Constants.TYPE_OF_CHARTS.XYGraph;

            ArrayList<ConfigSerie> listSerie = config.getListSerie();
            XYSeriesCollection dataset = new XYSeriesCollection(); 
            XYSeries serie;

            //-----Générateur de valeur
            for(int i = 0; i < listSerie.size(); i++)
            {
               serie  =  new XYSeries(listSerie.get(i).getName());
               dataset.addSeries(serie);
               generateRandomValue(serie);//A commenter
            }
            //--------

            chart = ChartFactory.createXYLineChart(
            config.getTitle(),      // chart title
            config.getxLabel(),                      // x axis label
            config.getyLabel(),                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
            );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);


    //        final StandardLegend legend = (StandardLegend) chart.getLegend();
    //      legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for(int i =0; i < listSerie.size(); i++)
        {
            ConfigSerie serieConf = listSerie.get(i);
            renderer.setSeriesLinesVisible(i,serieConf.isLinesVisible());
            renderer.setSeriesShapesVisible(i, serieConf.isShapesVisible());
            renderer.setSeriesPaint(i, serieConf.getColor());

        }
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.



        if(config.isLogarithmic())
        {
            LogAxis yAxis = new LogAxis(config.getyLabel()); 
            yAxis.setBase( config.getBase());
            yAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
            LogFormat format = new LogFormat(yAxis.getBase(), Integer.toString(config.getBase())+"^", "", true);
            yAxis.setNumberFormatOverride(format);

            plot.setRangeAxis(yAxis); //!!! Pas sûr !
        }
            

    }

    private void initPieChart(ConfigPie config) {
         type = data.Constants.TYPE_OF_CHARTS.Pie;
            

        this.chart = ChartFactory.createPieChart(config.getTitle(), new DefaultPieDataset(), ((ConfigPie) config).isLegend(), false, false);

        PiePlot plot =  (PiePlot) chart.getPlot();
        DefaultPieDataset dataset = (DefaultPieDataset) plot.getDataset();

        for(int i = 0; i < ((ConfigPie) config).getNbDataSet(); i++)
        {
            dataset.insertValue(dataset.getItemCount() ,((ConfigPie) config).getDataSetName().get(i), 0);
        }

        for(int i =0; i < dataset.getItemCount(); i++)
        {
            generateRandomValueForPie(((ConfigPie) config).getDataSetName().get(i), dataset);
        }
    }
    
    
    
}
