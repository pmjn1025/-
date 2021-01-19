package com.example.recyclerviewtest1_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class RecyclerViewActivity1 extends AppCompatActivity {

    ArrayList<PlaceData> placeArrayList;

    androidx.recyclerview.widget.RecyclerView rv_place;
    FloatingActionButton intent_btn;
    PlaceAdapter placeAdapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview1);


        initViews();

        readData(initShared());

        initPlaceRecyclerView();

        Intent intent = getIntent();

        addPlaceDataToList(intent);

        /*데이터 저장해주기*/
        saveData(initShared());
        test();
        placeAdapter.notifyDataSetChanged();

        // 바코드 찍기 버튼 클릭 이벤트
        intent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        /*홀더를 눌렀을때 이벤트*/
        placeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(PlaceAdapter.CustomViewHolder holder, View view, int position) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 할 수 있는 페이지 열기
                intent.putExtra("이름", placeArrayList.get(position).getName());
                intent.putExtra("날짜", placeArrayList.get(position).getDate());
                intent.putExtra("시간", placeArrayList.get(position).getTime());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(PlaceAdapter.CustomViewHolder holder, View view, int position) {
                //removeData(position);
                placeArrayList.remove(placeArrayList.get(position));
                saveData(initShared());
                readData(initShared());
                test();
                placeAdapter.notifyDataSetChanged();
            }
        });


    }

    public void test(){
        for(PlaceData placeData: placeArrayList){
            System.out.print("데이터 이름 : ");
            System.out.println(placeData.getName().toString());
        }
    }

    /*리스트 데이터 삭제*/
    public void removeData(int position){
        placeArrayList.remove(placeArrayList.get(position));
        saveData(initShared());
        readData(initShared());
        for(PlaceData placeData: placeArrayList){
            System.out.println("데이터 이름 ");
            System.out.println(placeData.getName().toString());
        }
        placeAdapter.notifyDataSetChanged();
    }

    // 뷰들 초기화
    private void initViews(){
        intent_btn = findViewById(R.id.rvfab1);
        rv_place = findViewById(R.id.rv1); // 리사이클러뷰 변수 할당
    }

    // 리사이클러뷰 초기화
    private void initPlaceRecyclerView(){
        placeAdapter = new PlaceAdapter(placeArrayList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_place.setLayoutManager(linearLayoutManager);
        rv_place.setAdapter(placeAdapter);
    }

    // 장소 이름 받아오기
    private String getPlaceName(Intent intent){
        return intent.getStringExtra("이름");
    }

    // 장소 방문 날짜 받아오기
    private String getVisitDate(Intent intent){
        return intent.getStringExtra("날짜");
    }

    // 장소 방문 시간 받아오기
    private String getVisitTime(Intent intent){
        return intent.getStringExtra("시간");
    }

    // 인텐트로 받은 데이터로 새로운 Place 클래스 만들어 리턴
    private PlaceData getPlaceData(Intent intent){
        return new PlaceData(getPlaceName(intent), getVisitDate(intent), getVisitTime(intent));
    }

    // 리사이클러뷰에 데이터 넣기
    private void addPlaceDataToList(Intent intent){
        placeArrayList.add(getPlaceData(intent));
    }

    /*데이터를 읽어오기*/
    public void readData(SharedPreferences sharedPreferences){
        String json = sharedPreferences.getString("arraylist", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<PlaceData>>() {
        }.getType();
        ArrayList<PlaceData> dataList = gson.fromJson(json, type);
        if(placeArrayList == null){
            placeArrayList = new ArrayList<>();
        }
        placeArrayList.clear();
        placeArrayList.addAll(dataList);
    }


    // 쉐어드에 제이슨으로 저장하기
    public void saveData(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(placeArrayList);
        editor.putString("arraylist", json);
        editor.apply();
    }


    // 쉐어드 초기화
    public SharedPreferences initShared(){
        SharedPreferences sharedPreferences = getSharedPreferences("arraylist", MODE_PRIVATE);
        return sharedPreferences;
    }


}



/*//이미지 값 세팅
        int image;
        switch (name){
            case "경복궁" : {
                image = R.drawable.icon_maxcoffee;
                break;
            }
            case "창덕궁" : {
                image = R.drawable.icon_chocopie;
                break;
            }
            case "팀노바" : {
                image = R.drawable.icon_candy;
                break;
            }
            case "인천" : {
                image = R.drawable.icon_jelly;
                break;
            }
            default: image = R.drawable.icon_b2;
        }*/


//setArrayList();
//saveData(initShared());


        /*SharedPreferences sharedPreferences = getSharedPreferences("flag", MODE_PRIVATE);
        //petName이라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        int flag = sharedPreferences.getInt("flag", 0);

        if(adapter==null&& flag!=1){
            System.out.println("1번째만 되야한다.");
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(manager);
            adapter = new ListAdapter1(personArrayList, this);
            rv.setAdapter(adapter);

            *//*저장을 하기위해 editor를 이용하여 값을 저장시켜준다.*//*
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("flag",1); // 이름 저장: key(petName) , value (name값) 를 이용하여 저장하는 형태
            editor.apply();//최종저장

        }else{
            System.out.println("2번째 부터 쭉 되야한다.");
            adapter.notifyDataSetChanged();
        }*/

//Person 객체 생성, 값 세팅
        /*
        Person person = new Person(image,name,age,price);
        person.setName(name);
        person.setAge(age);
        person.setPrice(price);
        person.setImage(image);
        */


//EditText 초기화
       /* name_et.setText("");
        age_et.setText("");
        price_et.setText("");*/

    /*private void setUpRecyclerView(){
        rv_place.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
            {
                helper.onDraw(c,parent, state);
            }
        });
    }*/


/*//ItemTouchHelper 생성
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        //RecyclerView에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(rv_place);
        //Adapter에 데이터 추가*/


/*ListAdapter1 adapter;
    ItemTouchHelper helper;
    FloatingActionButton intent_btn;*/

/*처음으로 페이지에 들어왔을때 arraylist에 값 넣어주기*//*
    public void setArrayList(){
        for(Person person : personArrayList){
            personArrayList.add(person);
        }
    }*/