package com.umpay.developutils.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.FileUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.Utils;
import com.umpay.developutils.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressWarnings("SpellCheckingInspection")
public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.install)
    Button install;
    @InjectView(R.id.unInstall)
    Button unInstall;
    private String path;
    private String dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Utils.init(this);
        LogUtils.d("http://www.jianshu.com/p/a58b375b6b98");
        initpath();
        initClick();
    }

    private void initpath() {
        String apkFileName = "QingdaoNfc_120_prod_singned.apk";
        dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/";
        path = dir + apkFileName;
        LogUtils.d("路径为:" + path);
        LogUtils.d(FileUtils.getFileName(path));
    }

    private void initClick() {
        install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("apk包是否存在:" + FileUtils.isFileExists(path));
                File file = new File(dir);
                String[] fileNames = file.list();
                for (String fileName : fileNames) {
                    LogUtils.d("-------------:::" + fileName);
                }
                AppUtils.installApp(MainActivity.this, path);
            }
        });

        unInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.uninstallApp(MainActivity.this, "com.umpay.qingdaonfc", 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == 0) {
////            ToastUtils.showShortToast("卸载程序成功");
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
