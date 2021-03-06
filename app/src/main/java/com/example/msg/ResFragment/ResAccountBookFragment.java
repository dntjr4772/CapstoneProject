package com.example.msg.ResFragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.msg.Api.RestaurantApi;
import com.example.msg.Api.StatisticsApi;
import com.example.msg.DatabaseModel.RestaurantModel;
import com.example.msg.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.StackedBarModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ResAccountBookFragment extends Fragment {
    private View view;
    private TextView date;
    private TextView title;
    private TextView yesterday;
    private TextView today;
    private TextView yesterdayCost;
    private TextView todayCost;
    private TextView monthCost;
    private TextView totalCost;
    private String formatDate;
    private String todays;
    private String yester;
    private String todayDateYear;
    private String todayDateMonth ;
    private String todayDateDay ;
    private String yesterDateYear ;
    private String yesterDateMonth;
    private String yesterDateDay ;
    private String yesterDateYear1;
    private String yesterDateMonth1;
    private String yesterDateDay1 ;
    private String yesterDateYear2;
    private String yesterDateMonth2;
    private String yesterDateDay2;
    private String yesterDateYear3;
    private String yesterDateMonth3;
    private String yesterDateDay3 ;
    private String yesterDateYear4;
    private String yesterDateMonth4;
    private String yesterDateDay4;
    private String yesterDateYear5;
    private String yesterDateMonth5;
    private String yesterDateDay5 ;
    private String yesterDateYear6;
    private String yesterDateMonth6;
    private String yesterDateDay6;
    private  String yesterDateYear7;
    private String yesterDateMonth7;
    private String yesterDateDay7;

    private int total1, total2, total3, total4;

    private int total5, total6, total7, total8, total9, total10, total11;
    private int man, woman;
    private int age10, age20, age30, age40;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_resaccountbook,container,false);

        date=(TextView)view.findViewById(R.id.resaccountbook_textView_date);
        title=(TextView)view.findViewById(R.id.resaccountbook_textView_title);
        yesterday=(TextView)view.findViewById(R.id.resaccountbook_textView_yesterdayDate);
        today=(TextView) view.findViewById(R.id.resaccountbook_textView_todayDate);
        yesterdayCost=(TextView) view.findViewById(R.id.resaccountbook_textView_yesterdayCost);
        todayCost=(TextView)view.findViewById(R.id.resaccountbook_textView_todayCost);
        monthCost=(TextView)view.findViewById(R.id.resaccountbook_textView_monthCost);
        totalCost=(TextView)view.findViewById(R.id.resaccountbook_textView_totalCost);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        RestaurantApi.getUserById(uid, new RestaurantApi.MyCallback() {
            @Override
            public void onSuccess(RestaurantModel restaurantModel) {
                title.setText(restaurantModel.res_name);
                long now = System.currentTimeMillis();
                Date dates = new Date(now);
                SimpleDateFormat sdfNow1 = new SimpleDateFormat("yyyy-MM-dd E"+"??????");
                SimpleDateFormat todayss = new SimpleDateFormat("MM/dd");
                SimpleDateFormat yesters = new SimpleDateFormat("MM/dd");



                SimpleDateFormat todayYear = new SimpleDateFormat("yy");
                SimpleDateFormat todayMonth = new SimpleDateFormat("MM");
                SimpleDateFormat todayDay = new SimpleDateFormat("dd");

                Calendar cal=Calendar.getInstance();
                Calendar cal1=Calendar.getInstance();
                Calendar cal2=Calendar.getInstance();
                Calendar cal3=Calendar.getInstance();
                Calendar cal4=Calendar.getInstance();
                Calendar cal5=Calendar.getInstance();
                Calendar cal6=Calendar.getInstance();
                Calendar cal7=Calendar.getInstance();

                cal.add(cal.DATE,-1);
                cal1.add(cal.DATE,-2);
                cal2.add(cal.DATE,-3);
                cal3.add(cal.DATE,-4);
                cal4.add(cal.DATE,-5);
                cal5.add(cal.DATE,-6);
                cal6.add(cal.DATE,-7);
                cal7.add(cal.DATE,-8);

                formatDate = sdfNow1.format(dates);
                todays=todayss.format(dates);
                yester=yesters.format(cal.getTime());

                todayDateYear = todayYear.format(dates);
                todayDateMonth = todayMonth.format(dates);
                todayDateDay = todayDay.format(dates);

                yesterDateYear = todayYear.format(cal.getTime());
                yesterDateMonth = todayMonth.format(cal.getTime());
                yesterDateDay = todayDay.format(cal.getTime());

                yesterDateYear1 = todayYear.format(cal1.getTime());
                yesterDateMonth1 = todayMonth.format(cal1.getTime());
                yesterDateDay1 = todayDay.format(cal1.getTime());

                yesterDateYear2 = todayYear.format(cal2.getTime());
                yesterDateMonth2 = todayMonth.format(cal2.getTime());
                yesterDateDay2 = todayDay.format(cal2.getTime());

                yesterDateYear3 = todayYear.format(cal3.getTime());
                yesterDateMonth3 = todayMonth.format(cal3.getTime());
                yesterDateDay3 = todayDay.format(cal3.getTime());

                yesterDateYear4 = todayYear.format(cal4.getTime());
                yesterDateMonth4 = todayMonth.format(cal4.getTime());
                yesterDateDay4 = todayDay.format(cal4.getTime());

                yesterDateYear5 = todayYear.format(cal5.getTime());
                yesterDateMonth5 = todayMonth.format(cal5.getTime());
                yesterDateDay5 = todayDay.format(cal5.getTime());

                yesterDateYear6 = todayYear.format(cal6.getTime());
                yesterDateMonth6 = todayMonth.format(cal6.getTime());
                yesterDateDay6 = todayDay.format(cal6.getTime());

                yesterDateYear7 = todayYear.format(cal7.getTime());
                yesterDateMonth7 = todayMonth.format(cal7.getTime());
                yesterDateDay7 = todayDay.format(cal7.getTime());




                date.setText(formatDate);
                yesterday.setText(yester);
                today.setText(todays);

                SpannableString content = new SpannableString(date.getText().toString());


// ???????????? TextView ??? String ??? ????????? ????????? ?????? ?????? TextView.getText().toString() ??????

                content.setSpan(new UnderlineSpan(), 0, content.length(),0);
                content.setSpan(new StyleSpan(Typeface.ITALIC), 0, content.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                date.setText(content);

                BarChart mBarChart = (BarChart) view.findViewById(R.id.barchart);

//        mBarChart.addBar(new BarModel(2.3f, 0xFF123456));
                mBarChart.startAnimation();

                ////
                final PieChart mPieChart = (PieChart) view.findViewById(R.id.piechart);



                StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear, 10), Integer.parseInt(yesterDateMonth, 10), Integer.parseInt(yesterDateDay, 10), new StatisticsApi.MyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Integer> sum) {
                        total1=0;
                        for (int i=0; i<sum.size(); i++){
                            total1+=sum.get(i);
                        }
                        Log.d("??????1",Integer.toString(total1)+" "+sum);
                        yesterdayCost.setText(total1+"???");
                    }

                    @Override
                    public void onFail(int errorCode, Exception e) {

                    }
                });

                StatisticsApi.getTodayCost(uid, 1, Integer.parseInt(todayDateYear, 10), Integer.parseInt(todayDateMonth, 10), Integer.parseInt(todayDateDay, 10), new StatisticsApi.MyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Integer> sum) {
                        total2=0;
                        for (int i=0; i<sum.size(); i++){
                            total2+=sum.get(i);
                        }
                        Log.d("??????2",Integer.toString(total2)+" "+sum);
                        todayCost.setText(total2+"???");
                    }

                    @Override
                    public void onFail(int errorCode, Exception e) {

                    }
                });

                StatisticsApi.getMonthCost(uid, 1, Integer.parseInt(todayDateYear, 10), Integer.parseInt(todayDateMonth, 10), new StatisticsApi.MyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Integer> sum) {
                        total3=0;
                        for (int i=0; i<sum.size(); i++){
                            total3+=sum.get(i);
                        }
                        Log.d("??????3",Integer.toString(total3)+" "+sum);

                        monthCost.setText(total3+"???");
                    }

                    @Override
                    public void onFail(int errorCode, Exception e) {

                    }
                });

                StatisticsApi.getTotalCost(uid, 1, new StatisticsApi.MyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Integer> sum) {
                        total4=0;
                        for (int i=0; i<sum.size(); i++){
                            total4+=sum.get(i);
                        }
                        Log.d("??????4",Integer.toString(total4)+" "+sum);

                        totalCost.setText(total4+"???");
                    }

                    @Override
                    public void onFail(int errorCode, Exception e) {

                    }
                });

                StatisticsApi.getAge(new StatisticsApi.MyCallback1() {
                    @Override
                    public void onSuccess(ArrayList<Integer> sum1, ArrayList<Integer> sum2, ArrayList<Integer> sum3, ArrayList<Integer> sum4) {
                        age10=0;
                        age20=0;
                        age30=0;
                        age40=0;
                        for (int i=0; i<sum1.size(); i++){
                            age10+=sum1.get(i);
                        }
                        for (int i=0; i<sum2.size(); i++){
                            age20+=sum2.get(i);
                        }
                        for (int i=0; i<sum3.size(); i++){
                            age30+=sum3.get(i);
                        }
                        for (int i=0; i<sum4.size(); i++){
                            age40+=sum4.get(i);
                        }
                        ///
                        StackedBarChart mStackedBarChart = (StackedBarChart) view.findViewById(R.id.stackedbarchart);

                        StackedBarModel s1 = new StackedBarModel("10???");

                        s1.addBar(new BarModel(age10, 0xFF63CBB0));

                        StackedBarModel s2 = new StackedBarModel("20???");
                        s2.addBar(new BarModel(age20, 0xFF63CBB0));

                        StackedBarModel s3 = new StackedBarModel("30???");

                        s3.addBar(new BarModel(age30, 0xFF63CBB0));

                        StackedBarModel s4 = new StackedBarModel("40??? ??????");
                        s4.addBar(new BarModel(age40, 0xFF63CBB0));

                        mStackedBarChart.addBar(s1);
                        mStackedBarChart.addBar(s2);
                        mStackedBarChart.addBar(s3);
                        mStackedBarChart.addBar(s4);

                        mStackedBarChart.startAnimation();

                    }

                    @Override
                    public void onFail(int errorCode, Exception e) {

                    }
                });
/////////////////////////////////////////////////////////////////

                StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear1, 10), Integer.parseInt(yesterDateMonth1, 10), Integer.parseInt(yesterDateDay1, 10), new StatisticsApi.MyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Integer> sum) {
                        ////
                        ValueLineChart mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.cubiclinechart);

                        final ValueLineSeries series = new ValueLineSeries();
                        series.setColor(0xFF56B7F1);
                        series.addPoint(new ValueLinePoint("", 0));

                        StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear2, 10), Integer.parseInt(yesterDateMonth2, 10), Integer.parseInt(yesterDateDay2, 10), new StatisticsApi.MyCallback() {
                            @Override
                            public void onSuccess(ArrayList<Integer> sum) {
                                StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear3, 10), Integer.parseInt(yesterDateMonth3, 10), Integer.parseInt(yesterDateDay3, 10), new StatisticsApi.MyCallback() {
                                    @Override
                                    public void onSuccess(ArrayList<Integer> sum) {
                                        StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear4, 10), Integer.parseInt(yesterDateMonth4, 10), Integer.parseInt(yesterDateDay4, 10), new StatisticsApi.MyCallback() {
                                            @Override
                                            public void onSuccess(ArrayList<Integer> sum) {
                                                StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear5, 10), Integer.parseInt(yesterDateMonth5, 10), Integer.parseInt(yesterDateDay5, 10), new StatisticsApi.MyCallback() {
                                                    @Override
                                                    public void onSuccess(ArrayList<Integer> sum) {
                                                        StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear6, 10), Integer.parseInt(yesterDateMonth6, 10), Integer.parseInt(yesterDateDay6, 10), new StatisticsApi.MyCallback() {
                                                            @Override
                                                            public void onSuccess(ArrayList<Integer> sum) {
                                                                StatisticsApi.getYesterdayCost(uid, 1, Integer.parseInt(yesterDateYear7, 10), Integer.parseInt(yesterDateMonth7, 10), Integer.parseInt(yesterDateDay7, 10), new StatisticsApi.MyCallback() {
                                                                    @Override
                                                                    public void onSuccess(ArrayList<Integer> sum) {
                                                                        total11=0;
                                                                        for (int i=0; i<sum.size(); i++){
                                                                            total11+=sum.get(i);
                                                                        }
                                                                        Log.d("??????1",Integer.toString(total11)+" "+sum);
                                                                   }

                                                                    @Override
                                                                    public void onFail(int errorCode, Exception e) {

                                                                    }
                                                                });

                                                                total10=0;
                                                                for (int i=0; i<sum.size(); i++){
                                                                    total10+=sum.get(i);
                                                                }
                                                                Log.d("??????1",Integer.toString(total10)+" "+sum);

                                                            }

                                                            @Override
                                                            public void onFail(int errorCode, Exception e) {

                                                            }
                                                        });
                                                        total9=0;
                                                        for (int i=0; i<sum.size(); i++){
                                                            total9+=sum.get(i);
                                                        }
                                                        Log.d("??????1",Integer.toString(total9)+" "+sum);

                                                    }

                                                    @Override
                                                    public void onFail(int errorCode, Exception e) {

                                                    }
                                                });
                                                total8=0;
                                                for (int i=0; i<sum.size(); i++){
                                                    total8+=sum.get(i);
                                                }
                                                Log.d("??????1",Integer.toString(total8)+" "+sum);

                                            }

                                            @Override
                                            public void onFail(int errorCode, Exception e) {

                                            }
                                        });
                                        total7=0;
                                        for (int i=0; i<sum.size(); i++){
                                            total7+=sum.get(i);
                                        }
                                        Log.d("??????1",Integer.toString(total7)+" "+sum);

                                    }

                                    @Override
                                    public void onFail(int errorCode, Exception e) {

                                    }
                                });
                                total6=0;
                                for (int i=0; i<sum.size(); i++){
                                    total6+=sum.get(i);
                                }
                                Log.d("??????1",Integer.toString(total6)+" "+sum);

                            }

                            @Override
                            public void onFail(int errorCode, Exception e) {

                            }
                        });
                        total5=0;
                        for (int i=0; i<sum.size(); i++){
                            total5+=sum.get(i);
                        }
                        Log.d("??????1",Integer.toString(total5)+" "+sum);
                        series.addPoint(new ValueLinePoint(yesterDateMonth7+"/"+yesterDateDay7, (float)total11));
                        series.addPoint(new ValueLinePoint(yesterDateMonth6+"/"+yesterDateDay6, (float)total10));
                        series.addPoint(new ValueLinePoint(yesterDateMonth5+"/"+yesterDateDay5, (float)total9));
                        series.addPoint(new ValueLinePoint(yesterDateMonth4+"/"+yesterDateDay4, (float)total8));
                        series.addPoint(new ValueLinePoint(yesterDateMonth3+"/"+yesterDateDay3, (float)total7));
                        series.addPoint(new ValueLinePoint(yesterDateMonth2+"/"+yesterDateDay2, (float)total6));
                        series.addPoint(new ValueLinePoint(yesterDateMonth1+"/"+yesterDateDay1, (float)total5));
                        series.addPoint(new ValueLinePoint(yesterDateMonth+"/"+yesterDateDay, (float)total1));
                        series.addPoint(new ValueLinePoint(todayDateMonth+"/"+todayDateDay, (float)total2));
                        series.addPoint(new ValueLinePoint("", 0));

                        mCubicValueLineChart.addSeries(series);
                        mCubicValueLineChart.startAnimation();
                    }

                    @Override
                    public void onFail(int errorCode, Exception e) {

                    }
                });



                StatisticsApi.getMen(new StatisticsApi.MyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Integer> sum) {
                        man=0;
                        for (int i=0; i<sum.size(); i++){
                            man+=sum.get(i);
                        }
                        woman=0;
                        for (int i=0; i<sum.size(); i++){
                            woman+=sum.get(i);
                        }
                        mPieChart.addPieSlice(new PieModel("??????", man, Color.parseColor("#FE6DA8")));
                        mPieChart.addPieSlice(new PieModel("??????", woman, Color.parseColor("#56B7F1")));

                        mPieChart.startAnimation();
                    }

                    @Override
                    public void onFail(int errorCode, Exception e) {

                    }
                });

            }

            @Override
            public void onFail(int errorCode, Exception e) {

            }
        });


        ////


        return view;




    }


}
