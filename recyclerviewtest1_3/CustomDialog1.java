package com.example.recyclerviewtest1_3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CustomDialog1 extends Dialog {

    private OnDialogListener listener;
    private Context context;
    private Button mod_bt;
    private EditText mod_name, mod_age, mod_price;
    private String name;
    //private int image;
    private String date;
    private String time;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };


    public CustomDialog1(Context context, final int position, PlaceData placeData){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.customdialog1);
        name = placeData.getName();
        date = placeData.getDate();
        time = placeData.getTime();
        //image = placeData.getImage();



        //이름, 나이 EditText에 값 채우기
        mod_name = findViewById(R.id.mod_name1);
        mod_name.setText((CharSequence) name);
        mod_age = findViewById(R.id.mod_age1);
        mod_age.setText((CharSequence) date);
        mod_price = findViewById(R.id.mod_price1);
        mod_price.setText((CharSequence) time);
        mod_bt = findViewById(R.id.mod_bt1);




        mod_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    //EditText의 수정된 값 가져오기
                    String name = mod_name.getText().toString();
                    String age = mod_age.getText().toString();
                    String price = mod_price.getText().toString();
                    PlaceData PlaceData = new PlaceData(name, age, price);
                    //Listener를 통해서 placeData객체 전달
                    listener.onFinish(position, PlaceData);
                    //다이얼로그 종료
                    dismiss();
                }
            }
        });

        EditText et_Date = (EditText) findViewById(R.id.mod_age1);
        et_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CustomDialog1.super.getContext(), myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        EditText et_time = (EditText) findViewById(R.id.mod_price1);
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CustomDialog1.super.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        et_time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_date = (EditText) findViewById(R.id.mod_age1);
        et_date.setText(sdf.format(myCalendar.getTime()));
    }




    public void setDialogListener(OnDialogListener listener){
        this.listener = listener;
    }


}
