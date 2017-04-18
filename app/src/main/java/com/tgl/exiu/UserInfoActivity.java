package com.tgl.exiu;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgl.beans.UserBean;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class UserInfoActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 2;
    private static final int CUT_PHOTO = 3;
    private static final String PHOTO_FILE_NAME = "head_photo.jpg";
    private File tempFile;
    private UserBean user;
    //    private String[] arrSex = getResources().getStringArray(R.array.sexdata);
    private String userName;
    private String qianming;
    private String address;
    private boolean sex;

    @BindView(R.id.img_user_head)
    ImageView img_user_head;//头像
    @BindView(R.id.et_UserName)
    EditText et_UserName;//用户名
    @BindView(R.id.et_qianming)
    EditText et_qianming;//个性签名
    @BindView(R.id.et_address)
    EditText et_address;//地址
    @BindView(R.id.spinner_sex)
    Spinner spinner_sex;//性别
    @BindView(R.id.btn_save)
    Button btn_save;//保存

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        //给页面赋值
        user = BmobUser.getCurrentUser(UserBean.class);
//        BmobQuery bmobQuery = new BmobQuery();
       /* bmobQuery.findObjects(new FindListener<UserBean>() {
            @Override
            public void done(List<UserBean> object, BmobException e) {
                if(e==null){
                    for (UserBean user : object) {
                        BmobFile bmobfile = user.getHead();
                        if(bmobfile!= null){
                            //调用bmobfile.download方法
                                bmobfile.download(new File(Environment.getExternalStorageState() +"img_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg"), new DownloadFileListener() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (null==e){

                                        img_user_head.setImageBitmap();
                                    }
                                    else {

                                    }
                                }

                                @Override
                                public void onProgress(Integer integer, long l) {

                                }
                            });
                        }
                    }
                }else{
                    Toast.makeText(UserInfoActivity.this, "头像获取失败!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        userName = user.getUsername();
        qianming = user.getPersonalSign();
        address = user.getAddress();
//        userHead=user.getHead();
        sex = user.isSex();
        et_UserName.setText(userName);
        et_qianming.setText(qianming);
        et_address.setText(address);

        //填充Spinner性别
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrSex);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//绑定 Adapter到控件
//        spinner_sex .setAdapter(adapter);
        //点击头像事件
        img_user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopuWindow();
            }
        });
//上传更改用户头像    ,暂时未实现
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(et_UserName.getText().toString());
                user.setPersonalSign(et_qianming.getText().toString());
                user.setAddress(et_address.getText().toString());
//                user.setSex(false);
//                更新信息
                user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (null == e) {
                            Toast.makeText(UserInfoActivity.this, "修改成功!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserInfoActivity.this, "修改失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //弹出选择头像框,有相册,拍照
    private void showPopuWindow() {
        //自定义布局
        View popupView = UserInfoActivity.this.getLayoutInflater().inflate(R.layout.layout_popuwindow, null);
        PopupWindow window = new PopupWindow(popupView, LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        //设置动画
        window.setAnimationStyle(R.style.popup_window_anim);
        //设置背景颜色
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAB2BD")));
        //设置可以获取焦点
        window.setFocusable(true);
        //设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        //更新popupwindow的状态
        window.update();
        //以下拉的方式显示，并且可以设置显示的位置
        window.showAtLocation(popupView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        TextView tv_fromPZ = (TextView) popupView.findViewById(R.id.tv_fromPZ);
        TextView tv_fromXC = (TextView) popupView.findViewById(R.id.tv_fromXC);
        //拍照获取照片
        tv_fromPZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 激活相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory() + "/" +
                            PHOTO_FILE_NAME);
                    // 从文件中创建uri
                    Uri uri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
            }
        });
        //从相册获取照片
        tv_fromXC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 激活系统图库，选择一张图片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
            }
        });
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            img_user_head.setImageDrawable(drawable);
//            BmobFile bmobFile=new BmobFile(tempFile);
//            Toast.makeText(this, "tupian---"+tempFile.getPath()+tempFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//            upLoad(bmobFile);
        }
    }

    /**
     * 裁剪图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, CUT_PHOTO);
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                try {
                    crop(uri);//如果裁剪了,则跳UT_PHOTO处理
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }

            }

        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory() + "/" +
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));//如果裁剪了,则跳转到CUT_PHOTO处理
            } else {
                Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == CUT_PHOTO) {
            // 从剪切图片返回的数据
            if (data != null) {
                setPicToView(data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
        /**
         * 自定义图片名，获取照片的file
         */
  /*  private File createImgFile() {
        //创建文件
        String fileName = "img_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";//确定文件名
        File dir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = Environment.getExternalStorageDirectory();
        } else {
            dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
         tempFile = new File(dir, fileName);
        try {
            if (tempFile.exists()) {
                tempFile.delete();
            }
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件路径
        return tempFile;
    }*/

    private String uriToPath(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();

        String img_path = actualimagecursor.getString(actual_image_column_index);
        return img_path;
    }

    private void upLoad(BmobFile bmobFile) {
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (null == e) {
                    Toast.makeText(UserInfoActivity.this, "上传成功!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserInfoActivity.this, "上传失败头像!" + e.getErrorCode() + " : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
