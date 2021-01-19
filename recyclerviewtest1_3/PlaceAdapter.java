package com.example.recyclerviewtest1_3;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.CustomViewHolder> implements ItemTouchHelperListener, OnItemClickListener{

    OnItemClickListener listener;
    ArrayList<PlaceData> placeDataArrayList;
    Context context;



    // 생성자
    public PlaceAdapter(ArrayList<PlaceData> placeDataArrayList, Context context) {
        this.placeDataArrayList = placeDataArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_place_name.setText(placeDataArrayList.get(position).getName());
        holder.tv_visit_date.setText(placeDataArrayList.get(position).getDate());
        holder.tv_visit_time.setText(placeDataArrayList.get(position).getTime());

        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                System.out.println("리스너1");
                if(listener !=null){
                    System.out.println("리스너2");
                    listener.onItemClick(holder,v,position);
                }
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int position = holder.getAdapterPosition();
                System.out.println("포지션값:"+position);
                if(listener !=null){
                    listener.onItemLongClick(holder,v,position);
                }
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != placeDataArrayList ? placeDataArrayList.size() : 0 );
    }

    /*새로 추가한 부분*/
    public  void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onItemClick(CustomViewHolder holder, View view, int position) {
    }


    @Override
    public void onItemLongClick(CustomViewHolder holder, View view, int position) {

    }///---------------------

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        //이동할 객체 저장
        PlaceData placeData = placeDataArrayList.get(from_position);
        //이동할 객체 삭제
        placeDataArrayList.remove(from_position);
        //이동하고 싶은 position에 추가
        placeDataArrayList.add(to_position,placeData);
        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position,to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {

        placeDataArrayList.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {
        //수정 버튼 클릭시 다이얼로그 생성
        CustomDialog1 dialog = new CustomDialog1(context, position, placeDataArrayList.get(position));

        //화면 사이즈 구하기
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //다이얼로그 사이즈 세팅
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();
        wm.copyFrom(dialog.getWindow().getAttributes());
        wm.width = (int) (width * 0.7); wm.height = height/2;

        //다이얼로그 Listener 세팅
        dialog.setDialogListener((OnDialogListener) this);

        //다이얼로그 띄우기
        dialog.show();

    }

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {

    }//--------------------------------


    // item.xml 과 홀더를 연결 시켜 준다.
    public class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView tv_place_name;
        TextView tv_visit_date;
        TextView tv_visit_time;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_place_name = itemView.findViewById(R.id.tv_place_name);
            tv_visit_date = itemView.findViewById(R.id.tv_visit_date);
            tv_visit_time = itemView.findViewById(R.id.tv_visit_time);
        }
    }










}