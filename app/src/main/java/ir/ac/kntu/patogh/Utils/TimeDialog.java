package ir.ac.kntu.patogh.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.ac.kntu.patogh.R;

public class TimeDialog extends Dialog {
    @BindView(R.id.time_picker)
    TimePicker timePicker;
    @BindView(R.id.btn_dialog_time)
    Button buttonSubmit;

    public TimeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time_picker);
        ButterKnife.bind(this);
        timePicker.setIs24HourView(false);
    }

    public Button getButtonSubmit() {
        return buttonSubmit;
    }

    public TimePicker getTimePicker() {
        return timePicker;
    }
}
