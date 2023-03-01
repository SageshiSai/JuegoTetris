package Tetris;

import java.util.Random;
import java.lang.Math;


public class formas {
    //Enumerados de Tetrominos. (Estados posibles de poligonos);
    enum Tetrominoes { NoShape, ZShape, SShape, LineShape, 
                TShape, SquareShape, LShape, MirroredLShape };

    private Tetrominoes pieceShape;
    private int coords[][];
    private int[][][] coordsTable;


    public formas() {

        coords = new int[4][2];
        setShape(Tetrominoes.NoShape);

    }
    //Las formas de los poligonos
    public void setShape(Tetrominoes shape) {

        coordsTable = new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }
        pieceShape = shape;

    }

    private void setX(int index, int x) { coords[index][0] = x; }
    private void setY(int index, int y) { coords[index][1] = y; }
    public int x(int index) { return coords[index][0]; }
    public int y(int index) { return coords[index][1]; }
    public Tetrominoes getShape()  { return pieceShape; }
//Decide de que manera/lado vendra la ficha que salga (Arriba, abajo, izquierda, derecha VISTA);
    public void setRandomShape()
    {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominoes[] values = Tetrominoes.values(); 
        setShape(values[x]);
    }
    //cordenada X
    public int minX()
    {
      int m = coords[0][0];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][0]);
      }
      return m;
    }

 //Cordenada Y
    public int minY() 
    {
      int m = coords[0][1];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][1]);
      }
      return m;
    }
    //Rotacion izquierda
    public formas rotateLeft() 
    {
        if (pieceShape == Tetrominoes.SquareShape)
            return this;

        formas result = new formas();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
    //Rotacion Derecha
    public formas rotateRight()
    {
        if (pieceShape == Tetrominoes.SquareShape)
            return this;

        formas result = new formas();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}
