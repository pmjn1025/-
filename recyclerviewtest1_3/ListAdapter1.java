package com.example.recyclerviewtest1_3;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
public class ListAdapter1 extends RecyclerView.Adapter<ListAdapter1.ItemViewHolder>
        implements OnDialogListener {


    ArrayList<Person> item;
    Context context;
    public ListAdapter1(ArrayList<Person> item,Context context){
        this.context = context;
        this.item = item;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater를 이용해서 원하는 레이아웃을 띄워줍니다.

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1,parent,false);
        ItemViewHolder holder= new ItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //ItemViewHolder가 생성되고 넣어야할 코드들을 넣어줍다.
        // 보통 onBind 함수 안에 모두 넣어줍니다.
        holder.onBind(item.get(position));



    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(Person person){
        //item에 Person객체 추가
        item.add(person);
        //추가후 Adapter에 데이터가 변경된것을 알림
        notifyDataSetChanged();
    }
    @Override
    public boolean onItemMove(int from_position, int to_position) {

        //이동할 객체 저장
        Person person = item.get(from_position);
        //이동할 객체 삭제
        item.remove(from_position);
        //이동하고 싶은 position에 추가
        item.add(to_position,person);
        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position,to_position);
        return true;
    }
    @Override
    public void onItemSwipe(int position) {
        item.remove(position);
        notifyItemRemoved(position);
    }
    */
/*//*
/왼쪽 버튼 누르면 수정할 다이얼로그 띄우기
    @Override public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {
        //수정 버튼 클릭시 다이얼로그 생성
        CustomDialog1 dialog = new CustomDialog1(context, position, item.get(position));

        //화면 사이즈 구하기
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //다이얼로그 사이즈 세팅
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();
        wm.copyFrom(dialog.getWindow().getAttributes());
        wm.width = (int) (width * 0.7); wm.height = height/2;

        //다이얼로그 Listener 세팅
        dialog.setDialogListener(this);

        //다이얼로그 띄우기
        dialog.show();
    }*//*


    //오른쪽 버튼 누르면 아이템 삭제
    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        item.remove(position);
        notifyItemRemoved(position);
    }
   */
/* @Override
    public void onFinish(int position, Person person) {
        item.set(position,person);
        notifyItemChanged(position);
    }*//*


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView list_name,list_age,list_price;
        ImageView list_image;

        public ItemViewHolder(View itemView) {
            super(itemView);
            */
/*list_name = itemView.findViewById(R.id.list_name1);
            list_age = itemView.findViewById(R.id.list_age1);
            list_image = itemView.findViewById(R.id.list_image1);
            list_price = itemView.findViewById(R.id.list_price1);*//*

        }

        public void onBind(Person person) {

            list_name.setText((CharSequence) person.getName());
            list_age.setText(String.valueOf(person.getAge()));
            list_image.setImageResource(person.getImage());
            list_price.setText(String.valueOf(person.getPrice()));
        }




    }

}
*/
