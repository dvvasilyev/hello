package com.dolvesa.hello;

import org.jgrapht.*;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.rmi.server.ExportException;
import java.util.*;

/**
 * Description is absent
 */
public class HelloJGraphT {

  private HelloJGraphT() {
  } // ensure non-instantiability.

  /**
   * The starting point for the demo.
   *
   * @param args ignored.
   * @throws URISyntaxException if invalid URI is constructed.
   * @throws ExportException    if graph cannot be exported.
   */
  public static void main(String[] args)
      throws URISyntaxException,
             ExportException {
    Graph<String, DefaultEdge> stringGraph = createStringGraph();

    // note undirected edges are printed as: {<v1>,<v2>}
    System.out.println("-- toString output");
    System.out.println(stringGraph.toString());
    System.out.println();

    // create a graph based on URI objects
    Graph<URI, DefaultEdge> hrefGraph = createHrefGraph();

    // find the vertex corresponding to www.jgrapht.org
    URI start = hrefGraph
        .vertexSet().stream().filter(uri -> uri.getHost().equals("www.jgrapht.org")).findAny()
        .get();

    // perform a graph traversal starting from that vertex
    System.out.println("-- traverseHrefGraph output");
    traverseHrefGraph(hrefGraph, start);
    System.out.println();

    System.out.println("-- renderHrefGraph output");
    renderHrefGraph(hrefGraph);
    System.out.println();

    shortestPath();
  }

  /**
   * Creates a toy directed graph based on URI objects that represents link structure.
   *
   * @return a graph based on URI objects.
   */
  private static Graph<URI, DefaultEdge> createHrefGraph()
      throws URISyntaxException {

    Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

    URI google = new URI("http://www.google.com");
    URI wikipedia = new URI("http://www.wikipedia.org");
    URI jgrapht = new URI("http://www.jgrapht.org");

    // add the vertices
    g.addVertex(google);
    g.addVertex(wikipedia);
    g.addVertex(jgrapht);

    // add edges to create linking structure
    g.addEdge(jgrapht, wikipedia);
    g.addEdge(google, jgrapht);
    g.addEdge(google, wikipedia);
    g.addEdge(wikipedia, google);

    return g;
  }

  /**
   * Traverse a graph in depth-first order and print the vertices.
   *
   * @param hrefGraph a graph based on URI objects
   * @param start     the vertex where the traversal should start
   */
  private static void traverseHrefGraph(Graph<URI, DefaultEdge> hrefGraph, URI start) {
    Iterator<URI> iterator = new DepthFirstIterator<>(hrefGraph, start);
    while (iterator.hasNext()) {
      URI uri = iterator.next();
      System.out.println(uri);
    }
  }

  /**
   * Render a graph in DOT format.
   *
   * @param hrefGraph a graph based on URI objects
   */
  private static void renderHrefGraph(Graph<URI, DefaultEdge> hrefGraph)
      throws ExportException {

    // use helper classes to define how vertices should be rendered,
    // adhering to the DOT language restrictions
//          ComponentNameProvider<URI> vertexIdProvider = new ComponentNameProvider<URI>()
//          {
//              public String getName(URI uri)
//              {
//                  return uri.getHost().replace('.', '_');
//              }
//          };
//          ComponentNameProvider<URI> vertexLabelProvider = new ComponentNameProvider<URI>()
//          {
//              public String getName(URI uri)
//              {
//                  return uri.toString();
//              }
//          };
//          GraphExporter<URI, DefaultEdge> exporter =
//              new DOTExporter<>(vertexIdProvider, vertexLabelProvider, null);
//          Writer writer = new StringWriter();
//          exporter.exportGraph(hrefGraph, writer);
//          System.out.println(writer.toString());
  }

  /**
   * Create a toy graph based on String objects.
   *
   * @return a graph based on String objects.
   */
  private static Graph<String, DefaultEdge> createStringGraph() {
    Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

    String v1 = "v1";
    String v2 = "v2";
    String v3 = "v3";
    String v4 = "v4";

    // add the vertices
    g.addVertex(v1);
    g.addVertex(v2);
    g.addVertex(v3);
    g.addVertex(v4);

    // add edges to create a circuit
    g.addEdge(v1, v2);
    g.addEdge(v2, v3);
    g.addEdge(v3, v4);
    g.addEdge(v4, v1);

    return g;
  }


  private static void shortestPath() {
    // constructs a directed graph with the specified vertices and edges
    Graph<String, DefaultEdge> directedGraph =
        new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
    directedGraph.addVertex("a");
    directedGraph.addVertex("b");
    directedGraph.addVertex("c");
    directedGraph.addVertex("d");
    directedGraph.addVertex("e");
    directedGraph.addVertex("f");
    directedGraph.addVertex("g");
    directedGraph.addVertex("h");
    directedGraph.addVertex("i");
    directedGraph.addEdge("a", "b");
    directedGraph.addEdge("b", "d");
    directedGraph.addEdge("b", "i");
    directedGraph.addEdge("d", "c");
    directedGraph.addEdge("c", "a");
    directedGraph.addEdge("e", "d");
    directedGraph.addEdge("e", "f");
    directedGraph.addEdge("f", "g");
    directedGraph.addEdge("g", "e");
    directedGraph.addEdge("h", "e");
    directedGraph.addEdge("i", "h");

    // computes all the strongly connected components of the directed graph
    StrongConnectivityAlgorithm<String, DefaultEdge> scAlg =
        new KosarajuStrongConnectivityInspector<>(directedGraph);
    List<Graph<String, DefaultEdge>> stronglyConnectedSubgraphs =
        scAlg.getStronglyConnectedComponents();

    // prints the strongly connected components
    System.out.println("Strongly connected components:");
    for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {
      System.out.println(stronglyConnectedSubgraphs.get(i));
    }
    System.out.println();

    // Prints the shortest path from vertex i to vertex c. This certainly
    // exists for our particular directed graph.
    System.out.println("Shortest path from i to c:");
    DijkstraShortestPath<String, DefaultEdge> dijkstraAlg =
        new DijkstraShortestPath<>(directedGraph);
    ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> iPaths = dijkstraAlg.getPaths("i");
    System.out.println(iPaths.getPath("c") + "\n");

    // Prints the shortest path from vertex c to vertex i. This path does
    // NOT exist for our particular directed graph. Hence the path is
    // empty and the result must be null.
    System.out.println("Shortest path from c to i:");
    ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> cPaths = dijkstraAlg.getPaths("c");
    System.out.println(cPaths.getPath("i"));

    AllDirectedPaths<String, DefaultEdge> allDirectedPaths = new AllDirectedPaths<>(directedGraph);
    List<GraphPath<String, DefaultEdge>> paths = allDirectedPaths.getAllPaths("b", "b", false, 15);

    GraphPath<String, DefaultEdge> graphPath;
    String vertex;

    for (GraphPath<String, DefaultEdge> path : paths) {
      graphPath = path;
      for (int j = 0; j < graphPath.getVertexList().size(); j++) {
        System.out.println(graphPath.getVertexList().get(j));
      }
      System.out.println("------------------------");

    }
  }

}
