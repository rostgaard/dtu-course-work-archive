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

public class NetworkGraph {
    
	private Graph<String, String> g;

    public NetworkGraph(ArrayList<Node> stations, ArrayList<Edge> edges) {
    	//create graph
    	g = new UndirectedSparseMultigraph<String, String>();

        // Add vertices
        for(int i = 0; i<stations.size(); i++){
        	g.addVertex(stations.get(i).toString());
        }
        
        //add edges
        for(int i =0; i<edges.size(); i++){
        	g.addEdge(Integer.toString(i), edges.get(i).getN1().toString(), edges.get(i).getN2().toString());
        }
    }
    
    public VisualizationViewer<String, String> getPanel(int width, int height) {
        //TODO: spring layout
    	Layout<String, String> layout = new CircleLayout<String, String>(g);
        layout.setSize(new Dimension(width, height));
        
        VisualizationViewer<String,String> vv =  new VisualizationViewer<String,String>(layout);
        vv.setPreferredSize(new Dimension(width, height)); //Sets the viewing area size

        Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
            public Paint transform(String i) {
            	return Color.WHITE;
            }
        };    
        Transformer<String,Shape> vertexSize = new Transformer<String,Shape>(){
            public Shape transform(String i){
                Ellipse2D circle = new Ellipse2D.Double(-40, -13, 80, 26);
                return circle;
            }
        };
        
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
        vv.getRenderContext().setVertexShapeTransformer(vertexSize);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        DefaultModalGraphMouse<Object, Object> gm = new DefaultModalGraphMouse<Object, Object>();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm); 
        
        vv.addKeyListener(gm.getModeKeyListener());
        vv.setBorder(new LineBorder(Color.BLACK));
               
		return vv; 
   }   
}