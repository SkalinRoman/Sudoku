package ru.skalinroman.sudoku;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Random;

public class Game extends BaseAdapter {

    private final Integer myRows = 9, myCols = 9;
    int unblockPositions[] = new int[myRows * myCols];
    int helperArray[][];
    private Context myContext;
    private int numberArray[][] = new int[myRows][myCols];
    private Resources myRes;
    private ArrayList<String> arrPict;

    public Game(Context myContext) {
        this.myContext = myContext;
        arrPict = new ArrayList<>(myCols * myRows);
        myRes = myContext.getResources();
    }

    @Override
    public int getCount() {
        return myRows * myCols;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(myContext);
        } else {
            imageView = (ImageView) view;
        }

        Integer drawableId = myRes.getIdentifier(arrPict.get(position), "drawable", myContext.getPackageName());
        imageView.setImageResource(drawableId);
        return imageView;
    }

    private void createField() {

        initArray();

        shiftNumbers(3, 1);
        shiftNumbers(6, 2);
        shiftNumbers(1, 3);
        shiftNumbers(4, 4);
        shiftNumbers(7, 5);
        shiftNumbers(2, 6);
        shiftNumbers(5, 7);
        shiftNumbers(8, 8);

        trasposeMatrix(numberArray);

        shakeArray();

        trasposeMatrix(numberArray);

        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                arrPict.add("n" + numberArray[i][j]);
            }
        }

        helperArray = numberArray;
        Random r = new Random();
        int i = 0;
        while (1 < 70) {
            int i2 = r.nextInt(80);
            arrPict.set(i2, "nempty");
            unblockPositions[i] = i2;
            helperArray[getRow(i2)][getCell(i2)] = -1;
            i++;
        }
    }

    public int getRow(int position) {
        int row = 0;
        if (position <= 8) {
            return 0;
        } else {
            while (position >= 0 && position < 9) {
                row++;
            }
            while (position >= 9) {
                position = position - 9;
                row++;
            }
            return row - 1;
        }
    }

    public int getCell(int position) {
        if (position <= 8) {
            return position;
        } else {
            return position % 9;
        }
    }

    private void trasposeMatrix(int array[][]) {
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                int tmp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = tmp;
            }
        }
    }

    private void shiftNumbers(int count, int row) {
        int index;

        for (int j = 0; j < myCols; j++) {
            index = (j + count) % 9 + 1;
            numberArray[row][j] = index;
        }
    }

    private void initArray() {

        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                numberArray[i][j] = j + 1;
            }
        }
    }

    private void shakeArray() {
        int i = 0;
        do {
            int tempArray[] = numberArray[i];
            int tempArray2[] = numberArray[i + 1];

            numberArray[i] = numberArray[i + 2];
            numberArray[i + 1] = tempArray;
            numberArray[i + 2] = tempArray2;

            i = i + 3;

        }
        while (i < myRows);
    }

    public boolean checkRepeatedValues(String selectedButton) {
        int repeatedX = 0;
        int repeatedY = 0;

        int number = Integer.parseInt(selectedButton.split("n")[1]);

        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                if (helperArray[i][j] == number) {
                    repeatedX++;
                }
                if (helperArray[j][i] == number) {
                    repeatedY++;
                }
            }
            if (repeatedX >= 2 || repeatedY >= 2) {
                return true;
            }
            repeatedX = 0;
            repeatedY = 0;
        }
        return false;
    }

    public boolean checkWinner() {
        int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, i6 = 0, i7 = 0, i8 = 0, i9 = 0;
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myCols; j++) {
                if (helperArray[i][j] == 1) i1++;
                if (helperArray[i][j] == 2) i2++;
                if (helperArray[i][j] == 3) i3++;
                if (helperArray[i][j] == 4) i4++;
                if (helperArray[i][j] == 5) i5++;
                if (helperArray[i][j] == 6) i6++;
                if (helperArray[i][j] == 7) i7++;
                if (helperArray[i][j] == 8) i8++;
                if (helperArray[i][j] == 9) i9++;
            }
        }
        if (i1 == 9 && i2 == 9 && i3 == 9 && i4 == 9 && i5 == 9 && i6 == 9 && i7 == 9 && i8 == 9 && i9 == 9) {
            return true;
        } else {
            return false;
        }
    }

    public void setNumber(int position, String selectedButton) {
        for (int i = 0; i < unblockPositions.length; i++) {
            if (unblockPositions[i] == position) {
                arrPict.set(position, selectedButton);
                helperArray[getRow(position)][getCell(position)] = Integer.parseInt(selectedButton.split("n")[1]);
                notifyDataSetChanged();
            }
        }
    }
}
