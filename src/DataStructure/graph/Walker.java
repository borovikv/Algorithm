package DataStructure.graph;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by vborovic on 4/19/17.
 */
public class Walker {
    class Vertex {

        boolean wasVisited;
    }
    private Vertex[] vertexList;
    private int[][] adjMat;

    // Обход в глубину
    public void dfs() {
        Stack<Integer> theStack = new Stack<>();
        // Алгоритм начинает с вершины 0
        vertexList[0].wasVisited = true;  // Пометка
        displayVertex(0); // Вывод
        theStack.push(0); // Занесение в стек

        // Пока стек не опустеет
        while (!theStack.isEmpty()) {

            // Получение непосещенной вершины, смежной к текущей
            int v = getAdjUnvisitedVertex(theStack.peek());
            if (v == -1) {
                // Если такой вершины нет, элемент извлекается из стека
                theStack.pop();
            }
            else {
                // Если вершина найдена
                vertexList[v].wasVisited = true;  // Пометка
                displayVertex(v);  // Вывод
                theStack.push(v); // Занесение в стек
            }
        } // Стек пуст, работа закончена


        clear();
    }

    // Метод возвращает непосещенную вершину, смежную по отношению к v
    private int getAdjUnvisitedVertex(int v) {
        for(int j=0; j < vertexList.length; j++)
            if(adjMat[v][j]==1 && !vertexList[j].wasVisited)
                return j;               // Возвращает первую найденную вершину
        return -1;                    // Таких вершин нет
    }

    private void clear() {
        // Сброс флагов
        for (Vertex aVertexList : vertexList) {
            aVertexList.wasVisited = false;
        }
    }

    private void displayVertex(int v) {
        System.out.println(v);
    }


    // Обход в ширину
    public void bfs() {
        // Алгоритм начинает с вершины 0
        Queue<Integer> theQueue = new LinkedList<>();
        vertexList[0].wasVisited = true; // Пометка
        displayVertex(0); // Вывод
        theQueue.add(0); // Вставка в конец очереди

        int v2;
        while (!theQueue.isEmpty()) { // Пока очередь не опустеет

            int v1 = theQueue.remove(); // Извлечение вершины в начале очереди
            // Пока остаются непосещенные соседи
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                // Получение вершины
                vertexList[v2].wasVisited = true;  // Пометка
                displayVertex(v2);
                theQueue.add(v2);
            }
        }
    }
}
