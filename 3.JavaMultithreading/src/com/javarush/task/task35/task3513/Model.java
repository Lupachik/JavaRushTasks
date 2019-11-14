package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score = 0; // текущий счет.
    protected int maxTile = 0; // максимальный вес плитки на игровом поле.
    private Stack<Tile[][]> previousStates = new Stack<>(); // стек предыдущего состояния поля
    private Stack<Integer> previousScores = new Stack<>();  // стек предыдущего результата
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }
    // метод возвращает список свободных клеток
    private List<Tile> getEmptyTiles(){
        List<Tile> listTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                if (gameTiles[i][j].isEmpty()) listTiles.add(gameTiles[i][j]);
            }
        }
        return listTiles;
    }
    // метод смотрит какие плитки пустуют и меняет вес одной из них, выбранной случайным образом, на 2 или 4 (на 9 двоек должна приходиться 1 четверка).
    // Для вычисления веса новой плитки используй выражение (Math.random() < 0.9 ? 2 : 4).
    private void addTile(){
        if(!getEmptyTiles().isEmpty()){
            int i = (int)(Math.random() * getEmptyTiles().size()); // получаем случайный индекс списка.
            getEmptyTiles().get(i).value = (Math.random() < 0.9 ? 2 : 4); // присваиваем вес полученной клетке.
        }
    }
    // Метод должен заполнять массив gameTiles новыми плитками и менять значение двух из них с помощью двух вызовов метода addTile.
    protected void resetGameTiles(){
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
    // метод сжатия, чтобы все пустые плитки были справа, т.е. ряд {4, 2, 0, 4} становится рядом {4, 2, 4, 0}
    // Изменим метод compressTiles, чтобы он возвращал true в случае, если он вносил изменения во входящий массив, иначе - false.
    private boolean compressTiles(Tile[] tiles){
        boolean flag = false;
        Tile[]copyTiles = tiles.clone(); // копия массива
        List<Tile> listX = new ArrayList<>(); // создаем список для записи не пустых элементов
        // в циксле заполняем список
        for (int i = 0; i < tiles.length; i++) {
            if(!tiles[i].isEmpty())listX.add(tiles[i]);
        }
        //  создаем пустой цикл, обнуляем все значения
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
        }
        // записывем в массив элементы списка
        for(int i = 0; i < listX.size(); i++){
            tiles[i] = listX.get(i);
        }
        for (int i = 0; i< tiles.length; i++){
            if(copyTiles[i].value != tiles[i].value) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    // Слияние плиток одного номинала, т.е. ряд {4, 4, 2, 0} становится рядом {8, 2, 0, 0}.
    // Изменим метод mergeTiles, чтобы он возвращал true в случае, если он вносил изменения во входящий массив, иначе - false.
    private boolean mergeTiles(Tile[] tiles){
        boolean flag = false;
        Tile[]copyTiles = tiles.clone(); // копия массива
        for (int i=0; i < tiles.length-1; i++){
            if(tiles[i].value == tiles[i+1].value){
                int valueT = tiles[i].value * 2;
                score += valueT;
                if(valueT > maxTile) maxTile = valueT;
                tiles[i].value = valueT;
                tiles[i+1] = new Tile();
            }
        }
        compressTiles(tiles); // сжимаем плитки после слияния.
        for (int i = 0; i< tiles.length; i++){
            if(copyTiles[i].value != tiles[i].value) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    // метод left, который будет для каждой строки массива gameTiles вызывать методы compressTiles и mergeTiles
    // и добавлять одну плитку с помощью метода addTile в том случае, если это необходимо.
    protected void left(){
        if (isSaveNeeded) saveState(gameTiles);
        boolean flag = false;
        for(int i = 0; i < FIELD_WIDTH; i++){
           if(compressTiles(gameTiles[i]) || mergeTiles(gameTiles[i])) flag = true;
        }
        if (flag) addTile();
        isSaveNeeded = true;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    // метод поворота массива на 90 градусов на право, 0 столбец оригинала, становится 0 строкой после поворота
    private void turn(){
        int M = gameTiles.length;
        int N = gameTiles[0].length;
        Tile[][] turnTiles = new Tile[N][M];
        for (int r = 0; r < M; r++){
            for (int c = 0; c < N; c++){
                turnTiles[c][M-1-r] = gameTiles[r][c];
            }
        }
        gameTiles = turnTiles;
    }
    // чтобы сместить вниз мы вызываем метод поворота 1 раз,применяем методы и поворачиваем еще 3 раза.
    protected void down(){
        saveState(gameTiles);
        turn();
        left();
        turn();
        turn();
        turn();
    }
    // чтобы сместить вправо мы вызываем метод поворота 2 раз,применяем методы и поворачиваем еще 2 раза.
    protected void right(){
        saveState(gameTiles);
        turn();
        turn();
        left();
        turn();
        turn();
    }
    // чтобы сместить вверх мы вызываем метод поворота 3 раз,применяем методы и поворачиваем еще 1 раза.
    protected void up(){
        saveState(gameTiles);
        turn();
        turn();
        turn();
        left();
        turn();
    }
    //метод canMove возвращающий true в случае, если в текущей позиции возможно сделать ход так,
    // чтобы состояние игрового поля изменилось.
    // проверяем есть ли свободные клетки.
    public boolean canMove(){
        boolean flag = false;
        for (int i = 0; i < gameTiles.length-1; i++){
            for (int j = 0; j < gameTiles[0].length-1; j++){
                if(gameTiles[i][j].value == gameTiles[i+1][j].value || gameTiles[i][j].value == gameTiles[i][j+1].value){
                    flag = true;
                }
            }
        }
        return (flag || getEmptyTiles().size() > 0);
    }
    // метод сохранения
    private void saveState(Tile[][] tiles){
        int M = tiles.length;
        int N = tiles[0].length;
        Tile[][]saveTiles = new Tile[M][N];
        for (int r=0; r < M; r++){
            for (int c = 0; c < N; c++){
                saveTiles[r][c] = new Tile(tiles[r][c].value);
            }
        }
        //tiles = Arrays.copyOf(gameTiles,M);
        previousStates.push(saveTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }
    // метод восстановления
    public void rollback(){
        if (!previousStates.isEmpty() & !previousScores.isEmpty()) {
            if (!previousStates.isEmpty()) gameTiles = (Tile[][]) previousStates.pop();
            if (!previousScores.isEmpty())score = (int)previousScores.pop();
        }
    }
    // запуск случайным образом
    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }
    //метод определения веса всех ячеек
    private int sumTile(Tile[][] tiles){
        int sum = 0;
        for (int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                sum+=tiles[i][j].value;
            }
        }
        return sum;
    }

    // будет возвращать true, в случае, если вес плиток в массиве gameTiles отличается от веса плиток в верхнем
    // массиве стека previousStates. Обрати внимание на то, что мы не должны удалять из стека верхний элемент,
    // используй метод peek.
    private boolean hasBoardChanged(){
        return sumTile(gameTiles)!=sumTile((Tile[][])previousStates.peek());
    }
    //метод описывающий эффективность
    private MoveEfficiency getMoveEfficiency(Move move){
        move.move();
        int numEf = getEmptyTiles().size();
        int scoreEf = score;
        if(!hasBoardChanged()) return new MoveEfficiency(-1, 0, move);
        rollback();
        return new MoveEfficiency(numEf, scoreEf, move);
    }
    // метод автоматического оптимального заполнения
    public void autoMove(){
        //Создадим локальную PriorityQueue с параметром Collections.reverseOrder()
        // (для того, чтобы вверху очереди всегда был максимальный элемент) и размером равным четырем.
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        //Заполним PriorityQueue четырьмя объектами типа MoveEfficiency (по одному на каждый вариант хода).
        // через лямбду, т.к. в классе один метод
        //P.S. В качестве факультативного задания можешь почитать про указатели на методы и попробовать передать
        // аргумент в метод getMoveEfficiency используя оператор "::".
        // Для метода left должно получиться getMoveEfficiency(this::left).
        queue.offer(getMoveEfficiency(() -> left()));
        queue.offer(getMoveEfficiency(() -> right()));
        queue.offer(getMoveEfficiency(()-> up()));
        queue.offer(getMoveEfficiency(() -> down()));

        queue.peek().getMove().move(); // выбор оптимально движения
    }
}
