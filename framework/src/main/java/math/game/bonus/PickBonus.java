package math.game.bonus;

import math.game.enums.BonusType;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PickBonus extends Bonus {

    private boolean finished;
    private int[] values;
    private int[] pickedIndices;
    private int[] pickedValues;

    public PickBonus(int[] values, int size) {
        this(size);
        this.values = values;
    }

    public PickBonus(int size) {
        super(BonusType.PICK_BONUS);
        pickedIndices = new int[size];
        pickedValues = new int[size];
        finished = false;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void addPick(int index, int value) {
        pickedIndices[index] = 1;
        pickedValues[index] = value;
        this. values = IntStream.range(0, values.length)
                .filter(i -> index != i)
                .mapToObj(i -> values[i])
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int countPickedValues() {
        return (int) Arrays.stream(pickedIndices)
                .filter(x -> x == 1)
                .count();
    }

    public int getValuesSize() {
        return values.length;
    }

    public int getValue(int index) {
        return values[index];
    }

    public int[] getPickedValues() {
        return pickedValues;
    }

    public long getWin() {
        return Arrays.stream(values).reduce(Integer::sum).orElse(0);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void incIndex() {
    }
}
