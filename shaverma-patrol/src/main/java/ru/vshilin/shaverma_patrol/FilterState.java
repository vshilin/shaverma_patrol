package ru.vshilin.shaverma_patrol;

/**
 * Created by helpdesk on 09.08.2016.
 */
public class FilterState {
    public static final FilterState INSTANCE = new FilterState();

    public float minScore;
    public float maxScore;

    public float minTaste;
    public float maxTaste;
    public float minFill;
    public float maxFill;
    public float minStruct;
    public float maxStruct;
    public float minOrig;
    public float maxOrig;
    public float minIntpers;
    public float maxIntpers;

    private boolean enabled = false;

    public FilterState() {
        resetFilter();
    }

    public boolean isDisabled() {
        return minScore == 0.0f && maxScore == 5.0f &&
                minTaste == 0.0f && maxTaste == 5.0f &&
                minFill == 0.0f && maxFill == 5.0f &&
                minStruct == 0.0f && maxStruct == 5.0f &&
                minOrig == 0.0f && maxOrig == 5.0f &&
                minIntpers == 0.0f && maxIntpers == 5.0f;
    }

    public void resetFilter() {
        minScore = 0.0f;
        maxScore = 5.0f;
        minTaste = 0.0f;
        maxTaste = 5.0f;
        minFill = 0.0f;
        maxFill = 5.0f;
        minStruct = 0.0f;
        maxStruct = 5.0f;
        minOrig = 0.0f;
        maxOrig = 5.0f;
        minIntpers = 0.0f;
        maxIntpers = 5.0f;
    }

}
