/*
 * SimpleGraphView.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */
package ui;

import edu.uci.ics.jung.algorithms.layout.Layout;
import java.awt.Dimension;
import javax.swing.JFrame;


import bootstrapping.NetworkModel;
import configuration.Configuration;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import static edu.uci.ics.jung.samples.AddNodeDemo.EDGE_LENGTH;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;
import temperaturemonitoring.Node;
import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author Dr. Greg M. Bernstein
 */
public class Userinterface {

    private Registry registry = null;
    private static final Logger logger = Logger.getLogger(Userinterface.class.getName());

    public Userinterface() throws InterruptedException {
        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Integer, String> layout;
        layout = new SpringLayout(NetworkModel.g,
                new ConstantTransformer(EDGE_LENGTH));
        layout.setSize(new Dimension(300, 300)); // sets the initial size of the layout space
        // The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
        VisualizationViewer<Integer, String> vv = new VisualizationViewer<>(
                layout);
        vv.setPreferredSize(new Dimension(600, 600)); //Sets the viewing area size


        final PickedState<Integer> pickedState = vv.getPickedVertexState();
        // Attach the listener that will print when the vertices selection changes.
        pickedState.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object subject = e.getItem();
                // The graph uses Integers for vertices.
                if (subject instanceof Integer) {
                    Integer vertex = (Integer) subject;
                    if (pickedState.isPicked(vertex)) {
                        System.out.println("Vertex " + vertex
                                + " is now selected");
                    } else {
                        System.out.println("Vertex " + vertex
                                + " no longer selected");
                    }
                }
            }
        });

        final Relaxer relaxer = vv.getModel().getRelaxer();
        layout.initialize();
        layout.lock(Integer.SIZE, false);
        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
            public Paint transform(Integer i) {
                if (i == NetworkModel.currentAdmin) {
                    return Color.GREEN;
                } else {
                    return Color.YELLOW;
                }

            }
        };
// Set up a new stroke Transformer for the edges
        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<String, Stroke> edgeStrokeTransformer =
                new Transformer<String, Stroke>() {
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        //vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);


        ActionListener prototionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                logger.log(Level.INFO, "Clicked " + ae.getActionCommand());
                if (registry == null) {
                    try {
                        registry = java.rmi.registry.LocateRegistry.getRegistry("localhost");
                    } catch (RemoteException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    TemperatureNode node = (TemperatureNode) registry.lookup("/Process/" + ae.getActionCommand());
                    node.promote();
                } catch (RemoteException | NotBoundException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        };

        JFrame frame;
        frame = new JFrame(Configuration.productName + " (" + Configuration.productVersion + ")");

        vv.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(vv, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

        buttonPanel.add(new JLabel("Promote:"));

        for (int i = 0; i < Configuration.Number_Of_Nodes; i++) {
            JButton promoteButton = new JButton(new Integer(i).toString());
            promoteButton.addActionListener(prototionListener);
            buttonPanel.add(promoteButton);
        }

        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Temperature information.
        JPanel tempPanel = new JPanel();
        tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel temp1 = new JLabel("1: ");
        tempPanel.add(temp1);

        frame.getContentPane().add(tempPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(buttonPanel, BorderLayout.EAST);

        // Final window operations.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Make relaxer less aggressive.
        relaxer.setSleepTime(100);
        
    }
}
