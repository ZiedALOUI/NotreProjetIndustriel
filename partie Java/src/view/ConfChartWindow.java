/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import config.ConfigChart;
import config.ConfigPie;
import config.ConfigSerie;
import config.ConfigXY;
import static data.Constants.colors;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 * 
 * 
 * @author Wouaip
 */
public class ConfChartWindow extends javax.swing.JPanel implements ItemListener {
    //Valeur doit correspondre a l'item du combox box!
    final static String GRAPHPANEL = "Graphique";
    final static String PIEPANEL = "Camembert";
    /**
     * indice d'apparition dans le confWindow
     */
    private final int indice;
    private final JPanel parent;
    private final ArrayList<JTextField> divisionNameTextFieldList;
    private final ArrayList<JTextField> serieNameTextFieldList;
    private final ArrayList<JComboBox> colorComboBoxList;
    private final ArrayList<JCheckBox> shapeVisibleCheckBoxList;
    private final ArrayList<JCheckBox> lineVisibleCheckBoxList;

    /**
     * Permet d'avertir le confWindow qu'il ne faut pas prendre en compte cette fenêtre
     */
    private boolean deleted;
    /**
     * Creates new form confChartWindow
     * @param parent Panel père du Panel de chart
     * @param indice indice d'apparation dans le confWindow
     */
    public ConfChartWindow(JPanel parent, int indice) {
        initComponents();
        this.deleted = false;
        this.parent = parent;
        divisionNameTextFieldList = new ArrayList<>();
        serieNameTextFieldList = new ArrayList<>();
        colorComboBoxList = new ArrayList<>();
        shapeVisibleCheckBoxList = new ArrayList<>();
        lineVisibleCheckBoxList = new ArrayList<>();
        this.indice = indice;

        initMyComponents();
    }

    public boolean isDeleted() 
    {
        return deleted;
    }
    
    
    
    /**
     * 
     * @return un chart correspondant au chart configuré dans la fenêtre
     */
    public ConfigChart getChart()
    {
         //Si on a affaire à un camembert
        if(((String)this.typeComboBox.getSelectedItem()).equals(PIEPANEL))
        {
            //Si le nb de division est bien saisie et que le titre est renseigné
            if(this.nbDivisionsTextField.getText().matches("^[+-]?\\d+$") && !this.titleTextField.getText().isEmpty())
            {
               int nbDivisions = Integer.valueOf(this.nbDivisionsTextField.getText());
               if(nbDivisions > 0 ) 
               {
                   if(!divisionNameTextFieldList.isEmpty())
                   {
                       if(divisionNameTextFieldsWellFilled())
                       {
                            ArrayList<String> datasetName = new ArrayList<>();
                            for(JTextField tmpTF : divisionNameTextFieldList)
                            {
                                datasetName.add(tmpTF.getText().trim());
                            }
                           return  new ConfigPie(titleTextField.getText().trim(), legendCheckBox.isSelected(), nbDivisions, datasetName);
                        }

                    }
                }
            }
        }
         else  if(((String)this.typeComboBox.getSelectedItem()).equals(GRAPHPANEL))
        {
            //Si le nb de série est bien saisie et que le titre est renseigné 
            if(this.nbSeriesTextField.getText().matches("^[+-]?\\d+$") && !this.titleTextField1.getText().isEmpty() &&
                  //  et si logarithmique, la base est un chiffre
                    ((this.logCheckBox.isSelected() && this.baseTextField.getText().matches("^[+-]?\\d+$") ) || (!this.logCheckBox.isSelected()))  )
            {
               int nbSeries = Integer.valueOf(this.nbSeriesTextField.getText());
               if(nbSeries > 0 ) 
               {
                   if(!serieNameTextFieldList.isEmpty())
                   {
                       if(serieNameTextFieldsWellFilled())
                       {
                           
                           
                            ArrayList<ConfigSerie> listSerie = new ArrayList<>();
                            for(int i = 0 ; i < nbSeries; i++)
                            {
                                String titre = serieNameTextFieldList.get(i).getText().trim();
                                Color color = (Color) colorComboBoxList.get(i).getSelectedItem();
                                boolean shape = shapeVisibleCheckBoxList.get(i).isSelected();
                                boolean line = lineVisibleCheckBoxList.get(i).isSelected();
                                listSerie.add(new ConfigSerie(titre, color, shape, line));
                            }
                            //Si c'est pas logarithmique
                            ConfigXY config;
                            if(!this.logCheckBox.isSelected() )
                            {
                               config = new ConfigXY(titleTextField1.getText().trim(),xLabelTextField.getText().trim(), yLabelTextField.getText().trim(),listSerie);
                            } else
                            {
                                config = new ConfigXY(titleTextField1.getText().trim(),xLabelTextField.getText().trim(), yLabelTextField.getText().trim(),listSerie,!this.logCheckBox.isSelected(), Integer.valueOf(this.baseTextField.getText())  );
                            }
                            return config;
                        }

                    }
                }
            }
        }
       
        return null;
    }

    public int getIndice() {
        return indice;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        graphPanel = new javax.swing.JPanel();
        leftPanelPie = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        titleTextField1 = new javax.swing.JTextField();
        logCheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        nbSeriesTextField = new javax.swing.JTextField();
        baseTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        xLabelTextField = new javax.swing.JTextField();
        yLabelTextField = new javax.swing.JTextField();
        seriesPanel = new javax.swing.JPanel();
        piePanel = new javax.swing.JPanel();
        divisionNamePanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        legendCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        nbDivisionsTextField = new javax.swing.JTextField();
        contentPanel = new javax.swing.JPanel();
        comboBoxPanel = new javax.swing.JPanel();
        indiceLabel = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        lessButton = new javax.swing.JButton();

        jLabel5.setText("Titre :");

        logCheckBox.setText("Logarithmique");

        jLabel6.setText("Nombre de séries :");

        nbSeriesTextField.setToolTipText("Appuyez sur entrée");
        nbSeriesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbSeriesTextFieldActionPerformed(evt);
            }
        });

        baseTextField.setText("Base");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, logCheckBox, org.jdesktop.beansbinding.ELProperty.create("${selected}"), baseTextField, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        jLabel1.setText("Label en x :");

        jLabel2.setText("Label en y :");

        javax.swing.GroupLayout leftPanelPieLayout = new javax.swing.GroupLayout(leftPanelPie);
        leftPanelPie.setLayout(leftPanelPieLayout);
        leftPanelPieLayout.setHorizontalGroup(
            leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelPieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(titleTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(leftPanelPieLayout.createSequentialGroup()
                                .addComponent(logCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(baseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftPanelPieLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nbSeriesTextField))
                            .addGroup(leftPanelPieLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(xLabelTextField))))
                    .addGroup(leftPanelPieLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yLabelTextField)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftPanelPieLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );
        leftPanelPieLayout.setVerticalGroup(
            leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelPieLayout.createSequentialGroup()
                .addComponent(titleTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(xLabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(yLabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logCheckBox)
                    .addComponent(baseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nbSeriesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(leftPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftPanelPieLayout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel5)
                    .addContainerGap(151, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout seriesPanelLayout = new javax.swing.GroupLayout(seriesPanel);
        seriesPanel.setLayout(seriesPanelLayout);
        seriesPanelLayout.setHorizontalGroup(
            seriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        seriesPanelLayout.setVerticalGroup(
            seriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(graphPanelLayout.createSequentialGroup()
                .addComponent(leftPanelPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seriesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanelPie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(seriesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout divisionNamePanelLayout = new javax.swing.GroupLayout(divisionNamePanel);
        divisionNamePanel.setLayout(divisionNamePanelLayout);
        divisionNamePanelLayout.setHorizontalGroup(
            divisionNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );
        divisionNamePanelLayout.setVerticalGroup(
            divisionNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setText("Titre :");

        legendCheckBox.setSelected(true);
        legendCheckBox.setText("Légende");

        jLabel4.setText("Nombre de divisions :");

        nbDivisionsTextField.setToolTipText("Appuyez sur entrée");
        nbDivisionsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbDivisionsTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(legendCheckBox)
                .addContainerGap(94, Short.MAX_VALUE))
            .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nbDivisionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(leftPanelLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(legendCheckBox)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftPanelLayout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(52, 52, 52)
                    .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(nbDivisionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout piePanelLayout = new javax.swing.GroupLayout(piePanel);
        piePanel.setLayout(piePanelLayout);
        piePanelLayout.setHorizontalGroup(
            piePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(piePanelLayout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(divisionNamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        piePanelLayout.setVerticalGroup(
            piePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(divisionNamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        indiceLabel.setText("jLabel3");

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Graphique", "Camembert" }));

        lessButton.setText("-");
        lessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout comboBoxPanelLayout = new javax.swing.GroupLayout(comboBoxPanel);
        comboBoxPanel.setLayout(comboBoxPanelLayout);
        comboBoxPanelLayout.setHorizontalGroup(
            comboBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comboBoxPanelLayout.createSequentialGroup()
                .addComponent(indiceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(lessButton)
                .addContainerGap())
            .addGroup(comboBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(comboBoxPanelLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        comboBoxPanelLayout.setVerticalGroup(
            comboBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comboBoxPanelLayout.createSequentialGroup()
                .addGroup(comboBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(indiceLabel)
                    .addComponent(lessButton))
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(comboBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(comboBoxPanelLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(comboBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void nbDivisionsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbDivisionsTextFieldActionPerformed
        if(this.nbDivisionsTextField.getText().matches("^[+-]?\\d+$") && !this.titleTextField.getText().isEmpty())
        {
            int nbDivisions = Integer.valueOf(this.nbDivisionsTextField.getText());
            if(nbDivisions > 0 ) 
            {
                if(!divisionNameTextFieldList.isEmpty())
                {
                   divisionNamePanel.removeAll();
                   divisionNameTextFieldList.removeAll(divisionNameTextFieldList);
                }
                
                for(int i =0; i < nbDivisions; i++)
                {
                    divisionNamePanel.setLayout(new BoxLayout(divisionNamePanel, BoxLayout.Y_AXIS));
                    JPanel tmpPanel = new JPanel();
                    tmpPanel.setLayout(new BoxLayout(tmpPanel,BoxLayout.X_AXIS));
                    JLabel nameLabel = new JLabel("Nom de la division "+ (i+1) + " : ");
                    tmpPanel.add(nameLabel);
                    nameLabel.setAlignmentX(LEFT_ALIGNMENT);
                    
                    JTextField tmpTF = new JTextField("");
                    divisionNameTextFieldList.add(tmpTF);
                    tmpPanel.add(tmpTF );
                    divisionNamePanel.add(tmpPanel); 
                }
               
                this.revalidate();
            } else
            {
                JOptionPane.showMessageDialog(this,"Il faut au moins une division.","Attention",JOptionPane.WARNING_MESSAGE);

            }
        }
        else JOptionPane.showMessageDialog(this,"Information manquante ou mal saise.","Attention",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_nbDivisionsTextFieldActionPerformed

    private void lessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessButtonActionPerformed
        this.setVisible(false);
        this.revalidate();
        deleted = true;
        parent.remove(this);
      
        this.setVisible(false);

        
    }//GEN-LAST:event_lessButtonActionPerformed

    private void nbSeriesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbSeriesTextFieldActionPerformed
        if(this.nbSeriesTextField.getText().matches("^[+-]?\\d+$") && !this.titleTextField1.getText().isEmpty())
        {
            int nbSeries = Integer.valueOf(this.nbSeriesTextField.getText());
            if(nbSeries > 0 ) 
            {
                if(!serieNameTextFieldList.isEmpty())
                {
                   seriesPanel.removeAll();
                   serieNameTextFieldList.removeAll(serieNameTextFieldList);
                }
                
                for(int i =0; i < nbSeries; i++)
                {
                    seriesPanel.setLayout(new BoxLayout(seriesPanel, BoxLayout.Y_AXIS));
                    
                    JPanel tmpPanel = new JPanel();
                    
                    
                    tmpPanel.setLayout(new BoxLayout(tmpPanel,BoxLayout.X_AXIS));
                    tmpPanel.setAlignmentX(LEFT_ALIGNMENT);
                    //Nom de série
                    JLabel nameLabel = new JLabel("Nom de la serie "+ (i+1) + " : ");
                    nameLabel.setAlignmentX(LEFT_ALIGNMENT);
                    tmpPanel.add(nameLabel);
                    
                    //Entrée du nom de série
                    JTextField tmpTF = new JTextField("");
                    serieNameTextFieldList.add(tmpTF);
                    tmpPanel.add(tmpTF );
                    
                    
                    //Color ComboBox
                   final JComboBox comboBox = new JComboBox(colors);
                    comboBox.setEditable(true);
                    comboBox.setEditor(new ColorComboBoxEditor(Color.RED));
                    tmpPanel.add(comboBox );
                    this.colorComboBoxList.add(comboBox);
                    
                    //CheckBox points
                    JCheckBox shapeVisible = new JCheckBox("Points visibles", true);
                    tmpPanel.add(shapeVisible );
                    this.shapeVisibleCheckBoxList.add(shapeVisible);
                    //CheckBox lignes
                    JCheckBox ligneVisible = new JCheckBox("Lignes visible", true);
                    tmpPanel.add(ligneVisible );
                    this.lineVisibleCheckBoxList.add(ligneVisible);
                    
                    
                    
                    seriesPanel.add(tmpPanel); 
                }
               
                this.revalidate();
            } else
            {
                JOptionPane.showMessageDialog(this,"Il faut au moins une série.","Attention",JOptionPane.WARNING_MESSAGE);

            }
        }
        else JOptionPane.showMessageDialog(this,"Information manquante ou mal saise.","Attention",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_nbSeriesTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField baseTextField;
    private javax.swing.JPanel comboBoxPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel divisionNamePanel;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JLabel indiceLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel leftPanelPie;
    private javax.swing.JCheckBox legendCheckBox;
    private javax.swing.JButton lessButton;
    private javax.swing.JCheckBox logCheckBox;
    private javax.swing.JTextField nbDivisionsTextField;
    private javax.swing.JTextField nbSeriesTextField;
    private javax.swing.JPanel piePanel;
    private javax.swing.JPanel seriesPanel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JTextField titleTextField1;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JTextField xLabelTextField;
    private javax.swing.JTextField yLabelTextField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, (String)evt.getItem());
        
    
    }

    private void initMyComponents() {
        typeComboBox.setEditable(false);
        typeComboBox.addItemListener(this);
        
        contentPanel.setLayout(new CardLayout());
        
        //Create the panel that contains the "cards".
        contentPanel.add(graphPanel, GRAPHPANEL);
        contentPanel.add(piePanel, PIEPANEL);
        
        
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(1));
        this.indiceLabel.setText(Integer.toString(this.indice));
  
    }

    /**
     * 
     * @return true if all the name of the division are filled
     * 
     */
    private boolean divisionNameTextFieldsWellFilled() {
        boolean wellFilled = true;
        for(int i =0; i < divisionNameTextFieldList.size(); i++)
        {
            if(divisionNameTextFieldList.get(i).getText().trim().isEmpty())
            {
                wellFilled = false;
            }
        }
        
        return wellFilled;
    }

    private boolean serieNameTextFieldsWellFilled() {
        boolean wellFilled = true;
        for(int i =0; i < serieNameTextFieldList.size(); i++)
        {
            if(serieNameTextFieldList.get(i).getText().trim().isEmpty())
            {
                wellFilled = false;
            }
        }
        
        return wellFilled;
    }
}
