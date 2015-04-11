/*
 * The user interface.
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
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;
import temperaturemonitoring.TemperatureNode;
/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Userinterface {

    private Registry registry = null;
    private static final Logger logger = Logger.getLogger(Userinterface.class.getName());
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);


    public Userinterface() throws InterruptedException {

        // Setup the registry.
        if (registry == null) {
            try {
                registry = java.rmi.registry.LocateRegistry.getRegistry("localhost");
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }

        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Integer, String> layout;
        layout = new SpringLayout(NetworkModel.g,
                new ConstantTransformer(EDGE_LENGTH));
        layout.setSize(new Dimension(300, 300)); // sets the initial size of the layout space
        // The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
        VisualizationViewer<Integer, String> vv = new VisualizationViewer<>(
                layout);
        vv.setPreferredSize(new Dimension(600, 600)); //Sets the viewing area size

        //  For some reason, this does not work :-\
        /*final PickedState<Integer> pickedState = vv.getPickedVertexState();
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

         });*/

        final Relaxer relaxer = vv.getModel().getRelaxer();
        layout.initialize();
        layout.lock(Integer.SIZE, false);

        // Paints the nodes according the whether they are the admin or just regular.
        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
            @Override
            public Paint transform(Integer i) {
                if (i == NetworkModel.currentAdmin) {
                    return Color.GREEN;
                } else {
                    return Color.YELLOW;
                }

            }
        };

        // Set up a new stroke Transformer for the edges, in order to make them dashed.
        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);

        Transformer<String, Stroke> edgeStrokeTransformer =
                new Transformer<String, Stroke>() {
            @Override
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };

        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);

        //  Adds node name (ID) to the center as a label.
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        //vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);


        ActionListener prototionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                logger.log(Level.INFO, "Clicked " + ae.getActionCommand());
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
        JPanel topLabelPanel = new JPanel();
        JPanel dummyPanel = new JPanel();
        dummyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel topLabel = new JLabel("Current network");
        topLabel.setFont(new Font("Serif", Font.BOLD, 16));
        topLabelPanel.add(topLabel);
        topLabelPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(topLabelPanel, BorderLayout.NORTH);
        frame.getContentPane().add(dummyPanel, BorderLayout.EAST);
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
        final TemperaturePanel tempPanel = new TemperaturePanel(1);
        tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.getContentPane().add(tempPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(buttonPanel, BorderLayout.EAST);

        // Final window operations.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Make relaxer less aggressive.
        relaxer.setSleepTime(100);

        // Periodically polls the admin for new measurements.
        Thread pollingService;
        pollingService = new Thread() {
            @Override
            public void run() {
                TemperatureNode node;
                try {
                    logger.log(Level.FINEST, "Looking up node " + NetworkModel.currentAdmin + " for new average.");
            
                    node = (TemperatureNode) registry.lookup("/Process/" + NetworkModel.currentAdmin);
                    double newAverage = Double.NaN;
                    try {
                    newAverage = node.latestAverage();
                        logger.log(Level.FINEST, "Got new average:" + newAverage);
                    } catch (RemoteException ex) {
                        logger.log(Level.WARNING, "Could not contact admin!");
                    }


                    tempPanel.pushMeasurement(newAverage);

                } catch (RemoteException | NotBoundException ex) {
                    logger.log(Level.SEVERE, "Could not bind to admin", ex);
                }
            }
        };

        scheduler.scheduleAtFixedRate(pollingService, 3, 1, TimeUnit.SECONDS);
        
    }
}

/* Private class implementing a temperature panel.*/
class TemperaturePanel extends JPanel {

    // private Double latestMeasurement = Double.NaN;
    private ArrayList<Double> measurements = new ArrayList();
    private ArrayList<JLabel> labels = new ArrayList<>();
    private int maxsize = 0;

    public TemperaturePanel(int numLatestMeasurements) {
        for (int i = 0; i < numLatestMeasurements; i++) {
            this.maxsize = numLatestMeasurements;
            this.measurements.add(i, Double.NaN);
            JLabel label = new JLabel(new Integer(i + 1).toString() + ": " + this.measurements.get(i));
            this.add(label);
            this.labels.add(label); // Keep the reference
        }
    }

    public void pushMeasurement(Double m) {
        this.measurements.add(0, m);
        this.repaint();
    }

    public static double roundTwoDecimals(double num) {
        double result = num * 100;
        result = Math.round(result);
        result = result / 100;
        return result;
    }

    @Override
    public void repaint() {
        if (this.measurements != null) {
            if (this.measurements.get(0).isNaN()) {
                this.labels.get(0).setText("Latest measurement: pending...");
            } else {
                this.labels.get(0).setText("Latest measurement: " + roundTwoDecimals(this.measurements.get(0)));
            }
        }

        super.repaint();

    }
}
