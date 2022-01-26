package com.example.mcsemesterproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    List<Room> roomList;
    Activity mAct;
    Context context;

    public MyRecyclerViewAdapter(List<Room> roomList, Activity mAct, Context context) {
        this.roomList = roomList;
        this.mAct = mAct;
        this.context = context;
    }
    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.roomlayout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.data=roomList.get(position);
        holder.roomNumber.setText(holder.data.getRoomNumber());
        holder.roomRating.setText(String.valueOf(holder.data.getRoomRating()));
        holder.roomReviews.setText(holder.data.getRoomReviews());
        holder.roomInfo.setText(holder.data.getRoomInfo());
        holder.roomPrice.setText(holder.data.getRoomPrice());
        holder.roomImage.setImageResource(holder.data.getRoomImageName());
        if(!holder.data.getVacant()){
            holder.btnRoomBook.setText("Vacant");
        }
        Log.d("T111", holder.data.getRoomInfo());
        holder.btnRoomBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.data.getVacant()){
                    int roomNumber=0;
                    String childTableName= null;
                    roomNumber= Integer.parseInt(holder.data.getRoomNumber());
                    if(roomNumber>100 && roomNumber<200){
                        childTableName="basicRooms";
                    }
                    else if(roomNumber>200 && roomNumber<300){
                        childTableName="standardRooms";
                    }
                    else{
                        childTableName="premiumRooms";
                    }
                    DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().child(childTableName);
                    database2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String roomKey;
                            String roomNumber2;
                            for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                                roomKey = childSnapshot.getKey();
                                roomNumber2= snapshot.child(roomKey).child("roomNumber").getValue().toString();
                                if(roomNumber2.equals(holder.data.getRoomNumber())){
                                    HashMap hashMap= new HashMap();
                                    hashMap.put("book",false);
                                    hashMap.put("vacant",true);
                                    database2.child(roomKey).updateChildren(hashMap);
                                    roomList.remove(holder.data);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    Intent intent = new Intent(context, Payment.class);
                    intent.putExtra("roomNumber", holder.data.getRoomNumber());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView roomImage;
        TextView roomNumber;
        TextView roomNumberString;
        TextView roomRating;
        TextView roomReviews;
        TextView roomInfo;
        TextView roomPrice;
        Room data;
        Button btnRoomBook;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roomImage = itemView.findViewById(R.id.roomImage);
            roomNumber = itemView.findViewById(R.id.roomNumber);
            roomRating = itemView.findViewById(R.id.roomRating);
            roomReviews = itemView.findViewById(R.id.roomReviews);
            roomInfo = itemView.findViewById(R.id.roomInfo);
            roomPrice = itemView.findViewById(R.id.roomPrice);
            roomNumberString=itemView.findViewById(R.id.roomNumber23);
            btnRoomBook = itemView.findViewById(R.id.btnRoomBook);

        }

//        public void paymentDetails(){
//            Intent intent= new Intent(MainActivity.this, MainActivity3.class);
//            startActivity(intent);
//        }
    }

}
