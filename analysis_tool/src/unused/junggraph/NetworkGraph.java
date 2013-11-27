package unused.junggraph;

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
import java.util.List;

import javax.swing.border.LineBorder;

/**
 * 
 * Draws the network graph, using the framework JUNG.
 * Has ability to update colors on the nodes and edges.
 *
 */

public class NetworkGraph {

	private static Graph<String, String> g;
	private static VisualizationViewer<String,String> vv;

	//painting of vertices (white) and edges (black)
	private static Transformer<String,Paint> whiteVertexPaint = new Transformer<String,Paint>() {
		public Paint transform(String i) {
			return Color.WHITE;
		}
	};    
	private static Transformer<String, Paint> blackEdgePaint = new Transformer<String, Paint>() {
		public Paint transform(String s) {
			return Color.BLACK;
		}
	};

	/**
	 * Constructor
	 * @param graph - graph containing nodes and edges
	 */
	public NetworkGraph(unused.junggraph.Graph graph) {
		// create graph - undirected graph
		g = new UndirectedSparseMultigraph<String, String>();

		// Add vertices
		List<Node> nodes = graph.getNodes();
		for(Node node : nodes){
			g.addVertex(node.toString());
		}

		// Add edges
		List<Edge> edges = graph.getEdges();
		for(Edge edge : edges){
			g.addEdge(Integer.toString(edges.indexOf(edge)), edge.getSource().toString(), edge.getTarget().toString());
		}
	}

	/**
	 * Creates the graphical network panel
	 * @param width - width of the panel in pixels
	 * @param height - height of the panel in pixels
	 * @return - returns the network panel
	 */
	public VisualizationViewer<String, String> createPanel(int width, int height) {
		//graph layout algorithm (circle layout)
		Layout<String, String> layout = new CircleLayout<String, String>(g);
		layout.setSize(new Dimension(width, height));

		vv =  new VisualizationViewer<String,String>(layout);
		vv.setPreferredSize(new Dimension(width, height)); //Sets the viewing area size

		//set size of vertices
		Transformer<String,Shape> vertexSize = new Transformer<String,Shape>(){
			public Shape transform(String i){
				Ellipse2D circle = new Ellipse2D.Double(-40, -13, 80, 26);
				return circle;
			}
		};

		//graphical settings of the network 
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
		vv.getRenderContext().setVertexShapeTransformer(vertexSize);
		vv.getRenderContext().setVertexFillPaintTransformer(whiteVertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		vv.getRenderContext().setEdgeDrawPaintTransformer(blackEdgePaint);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		//Mouse and keyboard listener
		DefaultModalGraphMouse<Object, Object> gm = new DefaultModalGraphMouse<Object, Object>();
		gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		vv.setGraphMouse(gm); 
		vv.addKeyListener(gm.getModeKeyListener());
		vv.setBorder(new LineBorder(Color.BLACK));

		return vv; 
	}   

//	/**
//	 * Updates the color of the nodes on the graph.
//	 * @param nodes - nodes to be colored
//	 */
//	public static void updateNodeColor(final ArrayList<Node> nodes){
//		Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
//			public Paint transform(String nodeName) {
//				//paint for given nodes
//				for(Node node : nodes){
//					if(nodeName.equals(node.toString())){
//						return node.getColor();
//					}
//				}
//				//for other nodes - paint it white
//				return Color.WHITE;
//			}
//		};
//		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
//		vv.updateUI();
//	}

//	/**
//	 * Updates the color for the edges on the graph.
//	 * @param edges - edges to be colored
//	 */
//	public static void updateEdgeColor(final ArrayList<Edge> edges){
//		Transformer<String, Paint> edgePaint = new Transformer<String, Paint>() {
//			public Paint transform(String edgeName) {
//				//paint given edges
//				for(Edge edge : edges){
//					//one of those two edges must exists!
//					String edge1 = g.findEdge(edge.getSource().toString(), edge.getTarget().toString());
//					String edge2 = g.findEdge(edge.getTarget().toString(), edge.getSource().toString());
//					if(edgeName.equals(edge1) || edgeName.equals(edge2)){
//						return edge.getColor();
//					}
//				}
//				//for other edges - paint it black
//				return Color.BLACK;
//			}
//		};
//
//		vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
//		vv.updateUI();
//	}

//	/**
//	 * Resets the colors of the graph (nodes and edges).
//	 */
//	public static void resetGraph(){
//		vv.getRenderContext().setEdgeDrawPaintTransformer(blackEdgePaint);
//		vv.getRenderContext().setVertexFillPaintTransformer(whiteVertexPaint);
//		vv.updateUI();
//	}
}
