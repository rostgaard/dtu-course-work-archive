/*
 * Copyright (c) 2003, the JUNG Project and the Regents of the University of
 * California All rights reserved.
 * 
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.PolarPoint;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.util.Animator;
import syntaxtree.AbstractSyntaxTree;

/**
 * Demonsrates TreeLayout and RadialTreeLayout.
 *
 * @author Tom Nelson
 *
 */
@SuppressWarnings("serial")
public class TreeUI extends JApplet {

    AbstractSyntaxTree tree;
    /**
     * the visual component and renderer for the graph
     */
    VisualizationViewer<String, Integer> vv;
    VisualizationServer.Paintable rings;
    String root;
    TreeLayout<String, Integer> treeLayout;
    RadialTreeLayout<String, Integer> radialLayout;

    public TreeUI() {

        // create a simple graph for the demo
        tree = new AbstractSyntaxTree();

        createTree();
        System.out.println(tree.getTrees().toArray().length);

        treeLayout = new TreeLayout<String, Integer>(tree);
        radialLayout = new RadialTreeLayout<String, Integer>(tree);
        radialLayout.setSize(new Dimension(600, 600));
        vv = new VisualizationViewer<String, Integer>(treeLayout, new Dimension(600, 600));
        vv.setBackground(Color.white);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        // add a listener for ToolTips
        vv.setVertexToolTipTransformer(new ToStringLabeller());
        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));

        Container content = getContentPane();
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);

        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

        vv.setGraphMouse(graphMouse);

        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.addItemListener(graphMouse.getModeListener());
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);

        final ScalingControl scaler = new CrossoverScalingControl();

        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1 / 1.1f, vv.getCenter());
            }
        });

        JToggleButton radial = new JToggleButton("Radial");
        radial.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    LayoutTransition<String, Integer> lt =
                            new LayoutTransition<String, Integer>(vv, treeLayout, radialLayout);
                    Animator animator = new Animator(lt);
                    animator.start();
                    vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
                    vv.addPreRenderPaintable(rings);
                } else {
                    LayoutTransition<String, Integer> lt =
                            new LayoutTransition<String, Integer>(vv, radialLayout, treeLayout);
                    Animator animator = new Animator(lt);
                    animator.start();
                    vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
                    vv.removePreRenderPaintable(rings);
                }
                vv.repaint();
            }
        });

        JPanel scaleGrid = new JPanel(new GridLayout(1, 0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(scaleGrid);
        controls.add(modeBox);

        content.add(controls, BorderLayout.SOUTH);
    }

    /**
     *
     */
    private void createTree() {
        // Program: y:=x; z:=1; while y>0 do z:=z*y; y:=y-1 od; y:=0
        tree.addVertex("Program");
        tree.addChild(1,"Program","y:=x");
        tree.addChild(2,"y:=x", "z:=1");

    }

    /**
     * a driver for this demo
     */
    public static void main(String[] args) {
        System.out.println("HELOO");


        JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content.add(new TreeUI());
        frame.pack();
        frame.setVisible(true);
    }
}