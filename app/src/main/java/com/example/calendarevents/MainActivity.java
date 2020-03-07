package com.example.calendarevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton addButton;
    private TextView goal, event, reminder, cancel,TitleEvent;
    private BottomSheetDialog bottomSheetDialog, setreminders;
    private TextInputEditText setevent;
    private MaterialButton save;
    private MaterialDatePicker materialDatePicker;
    private String DateToday;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private recyclerAdapter adapter;
    public static eventsDatabases eventsDatabases;
    private List<Events> newList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (FloatingActionButton) findViewById(R.id.addButton);

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select Date");
        Long today = MaterialDatePicker.todayInUtcMilliseconds();
        builder.setSelection(today);
        materialDatePicker = builder.build();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),materialDatePicker.toString());
            }
        });
        showBottomSheet();
        setTitleOfEvents();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Toast.makeText(MainActivity.this,materialDatePicker.getHeaderText(),Toast.LENGTH_LONG).show();
                DateToday = materialDatePicker.getHeaderText();
                bottomSheetDialog.show();
            }
        });


        eventsDatabases = Room.databaseBuilder(getApplicationContext(),eventsDatabases.class,"Mydb").allowMainThreadQueries().build();
        recyclerView = findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        List<Events> list = MainActivity.eventsDatabases.dao().getEvents();

        for (Events evs :list){
            newList.add(new Events(0,evs.EventType,evs.eventDate,evs.eventName));

        }
        adapter = new recyclerAdapter(newList);
        recyclerView.setAdapter(adapter);


    }



    private void showBottomSheet() {
        View v = LayoutInflater.from(this).inflate(R.layout.bottom_layout,null);
        goal = v.findViewById(R.id.goal);
        event = v.findViewById(R.id.event);
        reminder = v.findViewById(R.id.reminder);
        cancel = v.findViewById(R.id.cancel);
        bottomSheetDialog= new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(v);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        goal.setOnClickListener(this);
        event.setOnClickListener(this);
        reminder.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    private void setTitleOfEvents() {
        View view11 = LayoutInflater.from(this).inflate(R.layout.set_event,null);
        TitleEvent = view11.findViewById(R.id.eventType);
        setevent = view11.findViewById(R.id.eventTitle);
        save = view11.findViewById(R.id.Save);
        setreminders = new BottomSheetDialog(this);
        setreminders.setContentView(view11);
        setreminders.setCanceledOnTouchOutside(false);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String type = TitleEvent.getText().toString();
                String desc = setevent.getText().toString();

                Events events = new Events();
                events.setEventDate(DateToday);
                events.setEventName(desc);
                events.setEventType(type);
                events.setEventID(0);
                MainActivity.eventsDatabases.dao().addEvents(events);
                Toast.makeText(MainActivity.this,"Reminder added",Toast.LENGTH_LONG).show();
                setreminders.cancel();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.event:
                setreminders.show();
                TitleEvent.setText(event.getText().toString());
                bottomSheetDialog.cancel();
                break;
            case R.id.reminder:
                setreminders.show();
                TitleEvent.setText(reminder.getText().toString());
                bottomSheetDialog.cancel();
                break;
            case R.id.goal:
                setreminders.show();
                TitleEvent.setText(goal.getText().toString());
                bottomSheetDialog.cancel();
                break;
            case R.id.cancel:
                bottomSheetDialog.cancel();
                break;

        }
    }
}
