public class Board {

    Node start;
    Node boxes[];
    Player players[];

    public Board(int numPlayers) {
        boxes = new Node[60];
        players = new Player[numPlayers];

        for (int i = 0; i < 60; i++)
            boxes[i] = new Node(i+1);

        for (int i = 0; i < numPlayers; i++)
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
    private void connectBox(int start, int end){
        boxes[start-1].conecctionExtra = boxes[end-1];
    }

    private void connectExtraBoxes(){
        //conectar escaleras
        connectBox(12, 33);
        connectBox(16, 37);
        connectBox(26, 47);
        connectBox(31, 52);
        connectBox(40,59);

        //conectar serpientes
        connectBox(13, 10);
        connectBox(14, 5);
        connectBox(21, 18);
        connectBox(44, 35);
        connectBox(55, 48);
        connectBox(57, 42);
    }

    public int getCurrentBox(int numPlayer){
        return players[numPlayer-1].getCurrentBox();
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
