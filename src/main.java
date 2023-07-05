import java.util.*;

public class main {

	static int size = 0; // queue size

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the number of vertices: ");
		int v = sc.nextInt();

		System.out.print("Enter the number of edges: ");
		int e = sc.nextInt();

		int[][] array = new int[v][v];

		for (int i = 0; i < v; i++) {
			array[i][i] = 0;
		}

		for (int i = 0; i < e; i++) {
			while (true) {

				System.out.print("Enter the edges between vertices: ");
				int v1 = sc.nextInt();
				int v2 = sc.nextInt();

				System.out.print("Enter the weight between vertices: ");
				int weight = sc.nextInt();
				System.out.println("---------------------------------");

				if (v1 < 1 || v1 > v || v2 < 1 || v2 > v) {
					System.out.println("Invalid vertices, try again. ");
				} else {
					array[v1 - 1][v2 - 1] = weight;

					break;
				}

			}
		}

		printArray(array, v);

		int source, end;

		while (true) {
			System.out.print("Enter a source to start traversing: ");
			source = sc.nextInt();

			if (source > v || source < 1) {
				System.out.println("Invalid inputs, try again.");
			} else
				break;

		}

		System.out.println("Traversing from node " + source + " ...");

		int[] visited = new int[v];
		int[] distance = new int[v];
		int[] predecessor = new int[v];
		int[] queue = new int[v];

		for (int i = 0; i < v; i++) {
			visited[i] = 0;
			distance[i] = 9999;
			predecessor[i] = 9999;
			queue[i] = 9999;
		}

		visited[source - 1] = 1;
		distance[source - 1] = 0; // weight
		predecessor[source - 1] = 0;

		enqueue(queue, source, v);
		System.out.println("----------------------------------");
		// while queue is not empty
		while (size != 0) {
			// check all adjacent nodes
			int node = peek(queue);
			dequeue(queue, v);
			

			for (int i = 0; i < v; i++) {
				System.out.println("----------------------------------");
				System.out.println(" Current node: " + node);
				System.out.println(" I: " + i);
				System.out.println(" visited: " + visited[i]);
				System.out.println(" distance[node-1]: " + distance[node - 1]);
				System.out.println(" array[node-1][i]: " + array[node - 1][i]);
				System.out.println(" distance[i]: " + distance[i]);

				if (visited[i] != 1 && (distance[i] < distance[i] + array[node - 1][i]) && array[node - 1][i] != 0) {

					
					visited[i] = 1;
					predecessor[i] = node;
					distance[i] = distance[node - 1] + array[node - 1][i];
					enqueue(queue, i + 1, v);

				}

			}

			

		}

		// go through all visited nodes

		int set = 0;

		for (int i = 0; i < v; i++) {
			if (visited[i] == 0) {
				System.out.println("The graph is not connected.");
				set = 1;
				break;
			}

		}
		
		if (set == 0) {
			System.out.println("Shortest path from node " + source + " to every node is: ");
			for (int i = 0; i < v; i++) {
				System.out.println("Distance from " + source + " to " + (i + 1) + ": " + distance[i]);

			}

			for (int i = 0; i < v; i++) {
				System.out.print(predecessor[i] + " ");

			}
		}

	}

	public static int peek(int[] queue) {

		System.out.println(" PEEKING");

		return queue[0];

	}

	// sorts the distances by weights
	public static void enqueue(int[] queue, int source, int v) {

		queue[size] = source;
		size++;

		System.out.println("----------------------------------");
		System.out.println("Current queue: ");
		for (int i = 0; i < v; i++) {
			System.out.print(" " + queue[i] + " ");
		}

		System.out.println(" ENQUEUEING");
		System.out.println("----------------------------------");

	}

	public static void dequeue(int[] queue, int v) {

		queue[0] = 9999;
		size--;

		// move everything to the top

		System.out.println("----------------------------------");
		System.out.println("Current queue: ");
		for (int i = 1; i < v; i++) {
			
			int temp = queue[i-1];
			queue[i-1] = queue[i];
			queue[i] = temp;


		}
		
		for (int i=0;i<v;i++) {
			System.out.print(" " + queue[i] + " ");
		}

		System.out.println(" DEQUEUEING");

		System.out.println("----------------------------------");

	}

	public static void printArray(int[][] array, int v) {

		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println(" ");
		}

	}

}