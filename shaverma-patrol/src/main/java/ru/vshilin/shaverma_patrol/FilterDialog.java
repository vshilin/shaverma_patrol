package ru.vshilin.shaverma_patrol;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by helpdesk on 09.08.2016.
 */

public class FilterDialog extends DialogFragment {

    private OnDoneListener onDoneListener;

    RangeSeekBar<Float> rangeScore;
    RangeSeekBar<Float> rangeTaste;
    RangeSeekBar<Float> rangeFill;
    RangeSeekBar<Float> rangeStruct;
    RangeSeekBar<Float> rangeOrig;
    RangeSeekBar<Float> rangeIntpers;

    public FilterDialog() {
    }

    public static FilterDialog newInstance() {
        Bundle args = new Bundle();
        FilterDialog dialog = new FilterDialog();
        dialog.setArguments(args);
        return dialog;
    }

    private void mDismiss() {
        dismiss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_dialog, container, false);

        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle(0);
        }

        setCancelable(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        rangeScore = (RangeSeekBar<Float>) view.findViewById(R.id.range_score);
        rangeTaste = (RangeSeekBar<Float>) view.findViewById(R.id.range_taste);
        rangeFill = (RangeSeekBar<Float>) view.findViewById(R.id.range_fill);
        rangeStruct = (RangeSeekBar<Float>) view.findViewById(R.id.range_struct);
        rangeOrig = (RangeSeekBar<Float>) view.findViewById(R.id.range_orig);
        rangeIntpers = (RangeSeekBar<Float>) view.findViewById(R.id.range_intpers);

        view.findViewById(R.id.reset_filter).setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        FilterState.INSTANCE.resetFilter();
                                                                        readFilterState();
                                                                    }
                                                                }
        );

        readFilterState();
        return view;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        FilterState.INSTANCE.maxScore = rangeScore.getSelectedMaxValue();
        FilterState.INSTANCE.minScore = rangeScore.getSelectedMinValue();
        FilterState.INSTANCE.maxTaste = rangeTaste.getSelectedMaxValue();
        FilterState.INSTANCE.minTaste = rangeTaste.getSelectedMinValue();
        FilterState.INSTANCE.maxFill = rangeFill.getSelectedMaxValue();
        FilterState.INSTANCE.minFill = rangeFill.getSelectedMinValue();
        FilterState.INSTANCE.maxStruct = rangeStruct.getSelectedMaxValue();
        FilterState.INSTANCE.minStruct = rangeStruct.getSelectedMinValue();
        FilterState.INSTANCE.maxOrig = rangeOrig.getSelectedMaxValue();
        FilterState.INSTANCE.minOrig = rangeOrig.getSelectedMinValue();
        FilterState.INSTANCE.maxIntpers = rangeIntpers.getSelectedMaxValue();
        FilterState.INSTANCE.minIntpers = rangeIntpers.getSelectedMinValue();

        onDoneListener.onDone();
    }

    public interface OnDoneListener {
        void onDone();
    }

    public void setOnDoneListener(OnDoneListener onDoneListener) {
        this.onDoneListener = onDoneListener;
    }

    private void readFilterState() {
        rangeScore.setSelectedMinValue(FilterState.INSTANCE.minScore);
        rangeScore.setSelectedMaxValue(FilterState.INSTANCE.maxScore);

        rangeTaste.setSelectedMinValue(FilterState.INSTANCE.minTaste);
        rangeTaste.setSelectedMaxValue(FilterState.INSTANCE.maxTaste);
        rangeFill.setSelectedMinValue(FilterState.INSTANCE.minFill);
        rangeFill.setSelectedMaxValue(FilterState.INSTANCE.maxFill);
        rangeStruct.setSelectedMinValue(FilterState.INSTANCE.minStruct);
        rangeStruct.setSelectedMaxValue(FilterState.INSTANCE.maxStruct);
        rangeOrig.setSelectedMinValue(FilterState.INSTANCE.minOrig);
        rangeOrig.setSelectedMaxValue(FilterState.INSTANCE.maxOrig);
        rangeIntpers.setSelectedMinValue(FilterState.INSTANCE.minIntpers);
        rangeIntpers.setSelectedMaxValue(FilterState.INSTANCE.maxIntpers);
    }

}

