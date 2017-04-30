package com.tgl.exiu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgl.beans.PublishBean;
import com.tgl.beans.UserBean;
import com.tgl.utils.TimeFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishDetailsActivity extends AppCompatActivity {
    @BindView(R.id.img_user_head)
    ImageView img_user_head;
    @BindView(R.id.tv_userName)
    TextView tv_userName;
    @BindView(R.id.tv_publishDate)
    TextView tv_publishDate;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_publishcontent_wherefrom)
    TextView tv_wherefrom;
    @BindView(R.id.btn_calltel)
    Button btn_calltel;
    @BindView(R.id.toolbar_left)
    Button btn_toolbar_back;
    private UserBean user;
    private PublishBean publish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_details);
        ButterKnife.bind(this);
        init();
        user = (UserBean) getIntent().getSerializableExtra("userInfo");
        publish = (PublishBean) getIntent().getSerializableExtra("publishInfo");

        tv_userName.setText(user.getUsername());
//        img_user_head.setImageBitmap(getbitmap(user.getHead().getFileUrl()));
        tv_publishDate.setText(TimeFormat.getTimeFormat(publish.getCreatedAt()));
        tv_title.setText(publish.getTitle());
        tv_content.setText(publish.getContent());
        tv_wherefrom.setText(user.getAddress());

        btn_calltel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (PublishDetailsActivity.this.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Uri uri = Uri.parse("tel:" + user.getMobilePhoneNumber());
                        Intent intent = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intent);
                    } else {
                    }
                } else {
                    Uri uri = Uri.parse("tel:" + user.getMobilePhoneNumber());
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    startActivity(intent);
                }

            }
        });
    }

    private void init() {
        btn_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
   /* private Bitmap getbitmap(String imageUri) {
        // 显示网络上的图片
        Bitmap bitmap = null;
        try {
            URL myFileUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bitmap = null;
        } catch (IOException e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }*/
}
