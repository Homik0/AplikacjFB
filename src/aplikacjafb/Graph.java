/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjafb;

import static aplikacjafb.AplikacjaFB.wyszukiwanie;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.util.List;
/**
 *
 * @author Grzesiek
 */
public class Graph extends JFrame
{

	/**
	 * 
	 */
	//private static final long serialVersionUID = -2707712944901661771L;

	public Graph(int liczba,String nazwa)
	{
		//super("Hello, World!");
        Baza baza = new Baza();
         baza.polaczZbazaDanych();
         List<String> dane =baza.selectDane(wyszukiwanie,liczba);
        baza.rozlaczZbazaDanych();
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
                    Object v1 = graph.insertVertex(parent, null, nazwa, 600, 350, 100,
					30);
                    for(int i=0;i<liczba;i++){
			if(i<dane.size()){
			Object v = graph.insertVertex(parent, Integer.toString(i), dane.get(i), 600+220*cos(i*2*PI/liczba), 350+220*sin(i*2*PI/liczba),
					100, 30);
                        
			graph.insertEdge(parent, null, null, v1, v);
                }
                    }
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

}