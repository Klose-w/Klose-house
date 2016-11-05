package me.itangqi.buildingblocks.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.ui.activity.OtherActivity.Com_Activity;
import me.itangqi.buildingblocks.ui.activity.OtherActivity.Lose_Get;
import me.itangqi.buildingblocks.ui.activity.OtherActivity.Map_Activity;
import me.itangqi.buildingblocks.ui.activity.OtherActivity.School_Activity;
import me.itangqi.buildingblocks.ui.activity.OtherActivity.Schoolplace;
import me.itangqi.buildingblocks.ui.activity.OtherActivity.Shop_Activity;
import me.itangqi.buildingblocks.ui.activity.OtherActivity.Tea_Activity;

/**
 * Created by wade on 2016/4/5.
 */
public class ElseFragment extends Fragment  {
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    private ViewFlipper viewFlipper;
    GestureDetector detector;
    ImageButton Ima1;
    ImageButton Ima2;
    ImageButton Ima3;
    ImageButton Ima4;
    ImageButton Ima5;
    ImageButton Ima6;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_else, container, false);
        Ima1=(ImageButton)view.findViewById(R.id.imageButton);
        Ima2=(ImageButton)view.findViewById(R.id.imageButton2);
        Ima3=(ImageButton)view.findViewById(R.id.imageButton3);
        Ima4=(ImageButton)view.findViewById(R.id.imageButton4);
        Ima5=(ImageButton)view.findViewById(R.id.imageButton5);
        Ima6=(ImageButton)view.findViewById(R.id.imageButton6);
        Ima1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuanzhe(0);
            }
        });
        Ima2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuanzhe(1);
            }
        });
        Ima3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuanzhe(2);
            }
        });
        Ima4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuanzhe(3);
            }
        });
        Ima5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuanzhe(4);
            }
        });
        Ima6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuanzhe(5);
            }
        });
        Ima1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuanzhe(0);
            }
        });
        viewFlipper=(ViewFlipper)view.findViewById(R.id.viewflipper);
        viewFlipper.addView(getImageView(R.drawable.xinong6));
        viewFlipper.addView(getImageView(R.drawable.xinong2));
        viewFlipper.addView(getImageView(R.drawable.xinong1));
        viewFlipper.addView(getImageView(R.drawable.xinong4));
        viewFlipper.addView(getImageView(R.drawable.xinong3));
        viewFlipper.addView(getImageView(R.drawable.xinong5));
        onFling();
        return view;
    }
    private View getImageView(int id){
        ImageView imgView = new ImageView(getActivity());
        imgView.setImageResource(id);
        return imgView;
    }

        public void xuanzhe(int i) {

            Intent intent = new Intent();

            if(i==0) {
                intent.setClass(getActivity(), Shop_Activity.class);
                startActivity(intent);
            }
            else if(i==1) {
                intent.setClass(getActivity(), Tea_Activity.class);
                startActivity(intent);
            }
            else if(i==2) {
                intent.setClass(getActivity(), Com_Activity.class);
                startActivity(intent);
            }
            else if(i==3) {
                intent.setClass(getActivity(),Map_Activity.class);
                Toast.makeText(getActivity(), "正在完善中", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            else if(i==4) {
                intent.setClass(getActivity(), Lose_Get.class);
                Toast.makeText(getActivity(), "正在完善中", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            else if(i==5) {
                intent.setClass(getActivity(), School_Activity.class);
                startActivity(intent);
            }

        }
    class tupianListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }


    public boolean onFling() {

        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        viewFlipper.startFlipping();

        return false;
    }



}




