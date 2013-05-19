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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

/**
 *
 * @author Dr. Greg M. Bernstein
 */
public class Userinterface {
    //private final GraphicsConfiguration BorderLayout;
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
                if (i == 0) {
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


        JButton promoteButton1 = new JButton("Promote 1");
        JButton promoteButton2 = new JButton("Promote 2");
        JButton promoteButton3 = new JButton("Promote 3");
        JButton promoteButton4 = new JButton("Promote 4");
        JButton promoteButton5 = new JButton("Promote 5");

        promoteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (ae.getActionCommand().equals("Promote 1")) {
                    System.out.println("Promote 1");
                }
            }
        });

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
