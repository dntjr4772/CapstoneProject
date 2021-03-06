package com.example.msg.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.msg.Api.RestaurantApi;
import com.example.msg.DatabaseModel.RestaurantModel;
import com.example.msg.DatabaseModel.RestaurantProductModel;
import com.example.msg.R;
import com.example.msg.Sale.SaleActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ResProductsAdapter extends RecyclerView.Adapter<ResProductsAdapter.ProductsViewHolder> {

    private ArrayList<RestaurantProductModel> arrayList;
    private Context context;

    public ResProductsAdapter(ArrayList<RestaurantProductModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.resproduct_item,parent,false);
        ProductsViewHolder holder=new ProductsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductsViewHolder holder, int position) {

        RestaurantApi.getUserById(arrayList.get(position).res_id, new RestaurantApi.MyCallback() {
            @Override
            public void onSuccess(RestaurantModel restaurantModel) {
                holder.name.setText(restaurantModel.res_name);
                holder.grade.setRating(restaurantModel.res_rating);
                if(restaurantModel.res_name.contains("(?????????)")) {
                    holder.background.setBackgroundColor(Color.YELLOW);
                }
            }

            @Override
            public void onFail(int errorCode, Exception e) {

            }
        });

        Glide.with(holder.itemView)
                .load(arrayList.get(position).p_imageURL)
                .into(holder.image);
        holder.title.setText(arrayList.get(position).title);

        holder.cost.setText(arrayList.get(position).cost + "???");
        holder.stock.setText("?????? : "+arrayList.get(position).stock+"???");
        RestaurantProductModel item = arrayList.get(position);

        String addressString = null;
        Geocoder geocoder = new Geocoder(context, Locale.KOREAN);
                Log.d("GOS", item.latitude+"Tlqkf "+item.longitude);
        try {
            List<Address> addresses = geocoder.getFromLocation(item.latitude, item.longitude, 10);
            for (int i=0; i<addresses.size(); i++) {
                if(addresses.get(i).getThoroughfare() != null ) {
                    holder.dong.setText(addresses.get(i).getThoroughfare());
                }
                    Log.d("GOS", "diqkf"+addresses.get(i).getThoroughfare());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }


    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView cost;
        TextView name;
        RatingBar grade;
        TextView dong;
        TextView stock;
        LinearLayout background;
        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image=itemView.findViewById(R.id.resproduct_item_imageView_image);
            this.title=itemView.findViewById(R.id.resproduct_item_textView_title);
            this.cost=itemView.findViewById(R.id.resproduct_item_textView_cost);
            this.grade=itemView.findViewById(R.id.resproduct_item_ratingBar_grade);
            this.name=itemView.findViewById(R.id.resproduct_item_textView_name);
            this.dong=itemView.findViewById(R.id.resproduct_item_textView_dong);
            this.stock=itemView.findViewById(R.id.resproduct_item_textView_stock);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        RestaurantProductModel item = arrayList.get(pos);
                        //???????????? ?????? ??????.
                        Intent intent = new Intent(v.getContext(), SaleActivity.class);
                        intent.putExtra("Model", item);
                        v.getContext().startActivity(intent);


                    }
                }
            });
            //????????? ????????????????????? ???????????? ???????????? ?????? ????????? ??? ??????.
        }

    }
}
