package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.savingfoodscanner.R;

/**
 * Created by brunolemgruber on 21/08/17.
 */

public class DueDateCalendarActivity extends Activity implements OnDateSelectedListener {

    private static final DateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    private MaterialCalendarView widget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.due_date_calendar);

        widget = (MaterialCalendarView) findViewById(R.id.calendarView);
        widget.setOnDateChangedListener(this);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        Intent intent = getIntent();
        intent.putExtra("select_date", getSelectedDatesString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }
}
