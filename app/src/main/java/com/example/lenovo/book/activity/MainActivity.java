package com.example.lenovo.book.activity;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.book.AppProfile;
import com.example.lenovo.book.R;
import com.example.lenovo.book.fragment.CollectionList;
import com.example.lenovo.book.fragment.CollectionStatics;
import com.example.lenovo.book.orm.OrmHelper;
import com.example.lenovo.book.utils.image.NetWorkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    public static final int REQUEST_CODE = 1;
    private OrmHelper helper;

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    @BindView(R.id.radio_group)
    RadioGroup mradioGroup;

    @BindView(R.id.rb_collecion)
    RadioButton mRadioCollection;

    @BindView(R.id.rb_statistics)
    RadioButton mRadioStatistics;

    private Fragment mCollectionListFg;
    private Fragment mCollectionStaticFg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    private void initData(){
        helper = OrmHelper.getInstance(this);

        if(!NetWorkUtils.isNetworkConnected()) {
            Toast.makeText(AppProfile.getContext(), "请检查您的网络连接是否正常" ,Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        mradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,  int i) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (i) {
                    case R.id.rb_collecion:
                        if (mCollectionListFg == null){
                            mCollectionListFg = new CollectionList();
                        }
                        transaction.replace(R.id.main_fl , mCollectionListFg);
                        break;
                    case R.id.rb_scan:
                        if (ContextCompat.checkSelfPermission(MainActivity.this , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this , Manifest.permission.CAMERA)){
                                Toast.makeText(MainActivity.this , "我们需要摄像权限", Toast.LENGTH_SHORT).show();
                                ActivityCompat.requestPermissions(MainActivity.this ,
                                        new String[]{Manifest.permission.CAMERA} ,
                                        REQUEST_CAMERA_PERM);
                            }else {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CAMERA},
                                        REQUEST_CAMERA_PERM);
                            }
                        }else {
                            //TODO
                        }
                        break;
                    case R.id.rb_statistics:
                        if (mCollectionStaticFg == null) {
                            mCollectionStaticFg = new CollectionStatics();
                        }
                        transaction.replace(R.id.main_fl, mCollectionStaticFg);
                }
                setTabState();//设置对按钮状态的切换
                transaction.commit();
            }
        });
        //进行fragment与activity之间的切换
        mCollectionListFg = new CollectionList();
        mCollectionStaticFg = new CollectionStatics();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.main_fl , mCollectionListFg).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRadioCollection.setChecked(true);
    }
    private void setTabState() {
        setHomeState();
        setLocationState();
    }

    private void setHomeState() {
        if(mRadioCollection.isChecked()) {
            mRadioCollection.setTextColor(ContextCompat.getColor(this , R.color.basic_green));
        }else {
            mRadioCollection.setTextColor(ContextCompat.getColor(this , R.color.basic_black));
        }
    }

    private void setLocationState() {
        if(mRadioStatistics.isChecked()) {
            mRadioStatistics.setTextColor(ContextCompat.getColor(this , R.color.basic_green));
        }else {
            mRadioStatistics.setTextColor(ContextCompat.getColor(this , R.color.basic_black));
        }
    }
}
