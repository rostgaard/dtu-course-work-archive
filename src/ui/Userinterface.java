/*
 * SimpleGraphView.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */
package ui;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Dimension;
import javax.swing.JFrame;


import bootstrapping.NetworkModel;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEvent;
import edu.uci.ics.jung.graph.event.GraphEventListener;
import static edu.uci.ics.jung.samples.AddNodeDemo.EDGE_LENGTH;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

/**
 *
 * @author Dr. Greg M. Bernstein
 */
public class Userinterface {
    public Userinterface() throws InterruptedException {
        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Integer, String> layout;
        layout = new SpringLayout(NetworkModel.g,
                new ConstantTransformer(EDGE_LENGTH));
        layout.setSize(new Dimension(300, 300)); // sets the initial size of the layout space
        // The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
        BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(350, 350)); //Sets the viewing area size
        Relaxer relaxer = vv.getModel().getRelaxer();

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
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);



        JFrame frame = new JFrame("Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);

        //relaxer.resume();
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
        //relaxer.pause();
    }
}
