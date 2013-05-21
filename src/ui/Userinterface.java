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
import javax.swing.JPanel;
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
        vv.setPreferredSize(new Dimension(350, 350)); //Sets the viewing area size


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
        //vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
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
        JButton promoteButton1 = new JButton("0");
        promoteButton1.addActionListener(prototionListener);
        JButton promoteButton2 = new JButton("1");
        promoteButton2.addActionListener(prototionListener);
        JButton promoteButton3 = new JButton("2");
        promoteButton3.addActionListener(prototionListener);
        JButton promoteButton4 = new JButton("3");
        promoteButton4.addActionListener(prototionListener);
        JButton promoteButton5 = new JButton("4");
        promoteButton5.addActionListener(prototionListener);

        

        JFrame frame;
        frame = new JFrame("02220 Demo UI");

        frame.getContentPane().add(vv, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

        buttonPanel.add(promoteButton1);
        buttonPanel.add(promoteButton2);
        buttonPanel.add(promoteButton3);
        buttonPanel.add(promoteButton4);
        buttonPanel.add(promoteButton5);

        frame.getContentPane().add(buttonPanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        relaxer.setSleepTime(200);


        
/*                Thread.sleep(200);
         NetworkModel.addNode(1);
        Thread.sleep(200);
        NetworkModel.addNode(2);
        Thread.sleep(200);
        NetworkModel.addNode(3);
        Thread.sleep(200);
        NetworkModel.addNode(4);

        Thread.sleep(200);
        NetworkModel.connect(1, 2);
        
        Thread.sleep(200);
        NetworkModel.connect(2, 3);
        Thread.sleep(200);
        NetworkModel.connect(2, 4);
        Thread.sleep(200);
        NetworkModel.connect(4, 5);


         */

        
    }
}
