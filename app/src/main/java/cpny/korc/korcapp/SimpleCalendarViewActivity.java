package cpny.korc.korcapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;
import cpny.korc.korcapp.adapters.SimpleCalendarGridCellAdapterButtons;


public class SimpleCalendarViewActivity extends AppCompatActivity implements OnClickListener  {
    private static final String tag = "SimpleCalendarViewActivity";

    private TextView currentMonth;
    private ImageView prevMonth;
    private ImageView nextMonth;
    private GridView calendarView;
    private SimpleCalendarGridCellAdapterButtons adapter;
    private int month, year;
    private final DateFormat dateFormatter = new DateFormat();
    private Calendar _calendar;
    private static final String dateTemplate = "MMMM yyyy";

    Calendar calendarPick = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendarPick.set(Calendar.YEAR, year);
            calendarPick.set(Calendar.MONTH, monthOfYear);
            calendarPick.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateSelectedDate();
        }
    };

    private void updateSelectedDate() {

        month= calendarPick.get(Calendar.MONTH);
        year= calendarPick.get(Calendar.YEAR);

        if(month==11)
        {
            month=0;
        }else{
            month++;
        }
        setGridCellAdapterToDate(month, year);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calendar_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);
        Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);

        prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);

        currentMonth = (TextView) this.findViewById(R.id.currentMonth);
        currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));

        nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) this.findViewById(R.id.calendar);
        adapter = new SimpleCalendarGridCellAdapterButtons(getApplicationContext(), R.id.calendar_day_gridcell_buttons, month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);

        setCustomItemClick(adapter);

    }

    private void setCustomItemClick(SimpleCalendarGridCellAdapterButtons adapter) {
        adapter.setCustomItemClick(new SimpleCalendarGridCellAdapterButtons.SimpleCalendarGridCellAdapterListener() {
            @Override
            public void onCustomItemClick(String title) {
                String date_month_year = title;
                Toast.makeText(getApplicationContext(),date_month_year,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     * @param month
     * @param year
     */
    private void setGridCellAdapterToDate(int month, int year)
    {
        adapter = new SimpleCalendarGridCellAdapterButtons(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
        setCustomItemClick(adapter);
    }

    @Override
    public void onClick(View v)
    {
        if (v == prevMonth)
        {
            if (month <= 1)
            {
                month = 12;
                year--;
            }
            else
            {
                month--;
            }
            Log.d(tag, "Setting Prev Month: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(month, year);
        }
        else if (v == nextMonth)
        {
            if (month > 11)
            {
                month = 1;
                year++;
            }
            else
            {
                month++;
            }
            Log.d(tag, "Setting Next Month: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(month, year);
        }
//        else if (v==selectedDayMonthYear)
//        {
//            new DatePickerDialog(SimpleCalendarViewActivity.this, date, calendarPick
//                    .get(Calendar.YEAR), calendarPick.get(Calendar.MONTH),
//                    calendarPick.get(Calendar.DAY_OF_MONTH)).show();
//        }

    }

    @Override
    public void onDestroy()
    {
        Log.d(tag, "Destroying View ...");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showSettingsActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void showSettingsActivity() {
        Intent myIntent = new Intent(SimpleCalendarViewActivity.this,
                KorcSettingsActivity.class);
        startActivity(myIntent);
    }

}
