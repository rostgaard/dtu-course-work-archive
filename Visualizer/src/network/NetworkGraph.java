package network;

import domain.Edge;
import domain.Node;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import org.apache.commons.collections15.Transformer;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

/**
 * 
 * Draws the railway network graph, using the framework JUNG.
 * Has ability to update colors on the nodes.
 *
 */

public class NetworkGraph {
    
	private static Graph<String, String> g;
	private static VisualizationViewer<String,String> vv;
	private static int width = 450;
	
	/**
	 * Constructor
	 * @param stations - nodes 
	 * @param tracks - edges
	 */
    public NetworkGraph(ArrayList<Node> stations, ArrayList<Edge> tracks) {
    	// create graph
    	g = new UndirectedSparseMultigraph<String, String>();

        // Add vertices
        for(int i = 0; i<stations.size(); i++){
        	g.addVertex(stations.get(i).toString());
        }
        
        // Add edges
        for(int i =0; i<tracks.size(); i++){
        	g.addEdge(Integer.toString(i), tracks.get(i).getN1().toString(), tracks.get(i).getN2().toString());
        }
    }
          
    /**
     * Create the graphical network panel	
     * @param height
     * @return
     */
    public VisualizationViewer<String, String> getPanel(int height) {
    	//graph layout algorithm (circle layout)
    	Layout<String, String> layout = new CircleLayout<String, String>(g);
        layout.setSize(new Dimension(width, height));
        
        vv =  new VisualizationViewer<String,String>(layout);
        vv.setPreferredSize(new Dimension(width, height)); //Sets the viewing area size

        //color of vertices - set all to white
        Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
            public Paint transform(String i) {
            	return Color.WHITE;
            }
        };    
        
        //size if vertices
        Transformer<String,Shape> vertexSize = new Transformer<String,Shape>(){
            public Shape transform(String i){
                Ellipse2D circle = new Ellipse2D.Double(-40, -13, 80, 26);
                return circle;
            }
        };
        
        //color of the edges - set all to black
        Transformer<String, Paint> edgePaint = new Transformer<String, Paint>() {
            public Paint transform(String s) {
                return Color.BLACK;
            }
        };
        
        //graphical settings for the network 
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
        vv.getRenderContext().setVertexShapeTransformer(vertexSize);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        //Mouse and keyboard listener
        DefaultModalGraphMouse<Object, Object> gm = new DefaultModalGraphMouse<Object, Object>();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm); 
        vv.addKeyListener(gm.getModeKeyListener());
        vv.setBorder(new LineBorder(Color.BLACK));
               
		return vv; 
   }   
    
    /**
     * Updates the color of the nodes on the graph.
     * @param nodes - nodes to be colored
     */
    public static void updateNodeColor(final ArrayList<Node> nodes){
	
    	Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
            public Paint transform(String nodeId) {
            	//paint for given nodes
            	for(Node n : nodes){
            		if(n.toString().equals(nodeId)){
            			return n.getColor();
            		}
            	}
            	//for other nodes - paint it white
            	return Color.white;
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.updateUI();
    }
    
    /**
     * Updates the color for the edges on the graph.
     * @param edges - edges to be colored
     */
    public static void updateEdgeColor(final ArrayList<Edge> edges){
    	Transformer<String, Paint> edgePaint = new Transformer<String, Paint>() {
            public Paint transform(String s) {
            	//paint given edges
            	for(int i = 0; i<edges.size(); i++){
            		Edge edge = edges.get(i);
            		//one of those two edges must exists!
            		String t1 = g.findEdge(edge.getN1().toString(), edge.getN2().toString());
            		String t2 = g.findEdge(edge.getN2().toString(), edge.getN1().toString());
            		if(s.equals(t1) || s.equals(t2)){
            			return edge.getColor();
            		}
            	}
            	//for other edges - paint it black
            	return Color.BLACK;
            }
        };
    	
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        vv.updateUI();
    }
    
    /**
     * Clears the colors of the graph.
     * @param edges - edges to be colored
     */
    public static void clearGraph(){
    	Transformer<String, Paint> edgePaint = new Transformer<String, Paint>() {
            public Paint transform(String s) {
            	return Color.BLACK;
            }
        };
        Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
            public Paint transform(String nodeId) {
            	return Color.white;
            }
        };

        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.updateUI();
    }
}