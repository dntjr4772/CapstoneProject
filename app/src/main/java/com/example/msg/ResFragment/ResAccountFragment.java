package com.example.msg.ResFragment;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


<<<<<<< HEAD
import com.example.msg.Api.UserApi;
import com.example.msg.DatabaseModel.UserModel;
=======
import com.example.msg.Api.AuthenticationApi;
import com.example.msg.Api.RestaurantApi;
import com.example.msg.DatabaseModel.RestaurantModel;
>>>>>>> 1be2161c28db4cbf4e8d9bd15c9208d16ee8d1ca
import com.example.msg.R;
import com.example.msg.Sale.ResSalesHistoryActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ResAccountFragment extends Fragment {
    private View view;
    private LinearLayout ressaleshistory;

<<<<<<< HEAD
    private String dong="";
    private TextView address;
    private double defaultLongitude = 0;
    private double defaultLatitude = 0;

    private void getAddress(String uid){
        UserApi.getUserById(uid, new UserApi.MyCallback() {
            @Override
            public void onSuccess(UserModel userModel) {
                defaultLatitude=userModel.latitude;
                defaultLongitude=userModel.longitude;
                //Toast.makeText(getActivity(), defaultLatitude+" "+defaultLongitude, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFail(int errorCode, Exception e) {
=======
    private TextView res_name;
    private TextView res_address;

>>>>>>> 1be2161c28db4cbf4e8d9bd15c9208d16ee8d1ca

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_resaccount,container,false);
        ressaleshistory=view.findViewById(R.id.resaccount_linearLayout_saleshistory);

<<<<<<< HEAD

=======
        res_name =view.findViewById(R.id.resaccount_textView_UID);
        res_address=view.findViewById(R.id.resaccount_textView_address);

        RestaurantApi.getUserById(AuthenticationApi.getCurrentUid(), new RestaurantApi.MyCallback() {
            @Override
            public void onSuccess(RestaurantModel restaurantModel) {
                res_name.setText(restaurantModel.res_name);
                res_address.setText(restaurantModel.res_address+" "+restaurantModel.res_address_detail);
            }

            @Override
            public void onFail(int errorCode, Exception e) {

            }
        });
>>>>>>> 1be2161c28db4cbf4e8d9bd15c9208d16ee8d1ca

        ressaleshistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResSalesHistoryActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    public void getLocation(double lat, double lng){
        String addressString = null;
        Geocoder geocoder = new Geocoder(getContext(), Locale.KOREAN);
        Log.d("GOS", lat+" "+lng);
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 10);
            for (int i=0; i<addresses.size(); i++) {
                if(addresses.get(i).getThoroughfare() != null ) {
                    dong = addresses.get(i).getThoroughfare();
                    address.setText(dong);
                }
//                    Log.d("GOS", addresses.get(i).getThoroughfare());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
