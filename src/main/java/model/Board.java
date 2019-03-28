package model;

public class Board {

    Node start;
    Node boxes[];
    Player players[];

    public Board() {
        boxes = new Node[100];
        players = new Player[2];

        for (int i = 0; i < 100; i++)
            boxes[i] = new Node(i+1);

        for (int i = 0; i < 2; i++)
            players[i] = new Player();

        connectExtraBoxes();
    }

    /**
     * Mueve un jugador el numero de casillas escificado. Si cae en una serpiente o escalera lo mueve a la casilla especifica
     * @param numBoxes el numero de casillas para avanzar
     * @param numPlayer el numero de jugador, 1, 2, 3, etc.
     */
    public void move(int numBoxes, int numPlayer){

        int currentBox = players[numPlayer-1].getCurrentBox() - 1;
        int nextBox = currentBox + numBoxes;

        if(boxes[nextBox].conecctionExtra != null){

            nextBox = boxes[nextBox].conecctionExtra.numBox - 1;

        }

        players[numPlayer-1].setCurrentBox(nextBox+1);
    }

    /**
     * Conecta una casilla con otra, puede ser una serpiente o una escalera
     * @param start la casilla donde inicia la conexion
     * @param end la casilla en donde termina la conexion
     */
    private void conectBox(int start, int end){
        boxes[start-1].conecctionExtra = boxes[end-1];
    }

    private void connectExtraBoxes(){
        //conectar escaleras
        conectBox(11, 39);
        conectBox(17, 67);
        conectBox(19, 45);
        conectBox(21, 56);
        conectBox(26, 50);
        conectBox(43, 84);
        conectBox(52, 76);
        conectBox(70, 92);
        conectBox(74, 100);

        //conectar serpientes
        conectBox(18, 6);
        conectBox(22, 2);
        conectBox(36, 20);
        conectBox(62,14);
        conectBox(75, 30);
        conectBox(78, 49);
        conectBox(83, 8);
        conectBox(93, 40);
        conectBox(96, 69);
    }

    public int getCurrentBox(int numPlayer){
        return players[numPlayer-1].getCurrentBox();
    }

    public void reset(){
        for (int i = 0; i < 2; i++) {
            players[i].setCurrentBox(0);
        }
    }

    private class Node{
        int numBox;
        Node conecctionExtra;

        public Node(int numBox) {
            this.numBox = numBox;
            conecctionExtra = null;
        }
    }
}
