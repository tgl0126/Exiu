package com.tgl.exiu;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tgl.beans.ExiuSQ;
import com.tgl.beans.UserBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.BmobUser.getCurrentUser;
import static com.tgl.exiu.R.id.chakan;

public class BecomeExiuActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_back)
    Button toolbar_back;
    @BindView(R.id.btn_becomeexiu)
    Button btn_becomeexiu;
    @BindView(R.id.et_sqContent)
    EditText et_sqContent;
    @BindView(chakan)
    Button btn_chakan;
    private AlertDialog.Builder alertDialog;
    private  ExiuSQ exsq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_become_exiu);
        ButterKnife.bind(this);
        alertDialog= new AlertDialog.Builder(BecomeExiuActivity.this);
        init();
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case 0:
                    alertDialog.setMessage("申请已经被处理,恭喜您具有维修人员资格!");
                    break;
                case 1:
                    alertDialog.setMessage("对不起,您不具备相关维修能力,请继续努力!");
                    break;
                case 2:
                    alertDialog.setMessage("申请正在被处理中......");
                    break;
                case 3:
                    alertDialog.setMessage("未查询到相关用户数据");
                    break;
            }
        }
    };
    private void init() {
        //返回
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BecomeExiuActivity.this.finish();
            }
        });
        //查看
        btn_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final UserBean userBean= BmobUser.getCurrentUser(UserBean.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                    BmobQuery<ExiuSQ> query=new BmobQuery();
                    query.addWhereEqualTo("user",getCurrentUser(UserBean.class));
                query.findObjects(new FindListener<ExiuSQ>() {
                    @Override
                    public void done(List<ExiuSQ> list, BmobException e) {
                        ExiuSQ exiuSQ=list.get(0);
                        if (exiuSQ.isSolutioning()){//正在被处理

                            if(exiuSQ.isSolutioned()){//已经被处理了,通过了
                                mHandler.sendEmptyMessage(0);
                                userBean.setExiuStaff(true);
                                userBean.update(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                    }
                                });
                            }
                            else {//已经被处理,没有通过
                                mHandler.sendEmptyMessage(1);
                            }

                        }else {//没有被处理
                            mHandler.sendEmptyMessage(2);
                        }

                    }

                });
                        alertDialog.setTitle("申请详情");
                        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.show();
    }
});



            }
        });
        btn_becomeexiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getCurrentUser(UserBean.class).isExiuStaff()) {
                    if (!"".equals(et_sqContent.getText().toString())) {
                        exsq = new ExiuSQ();
                        exsq.setUser(getCurrentUser(UserBean.class));
                        exsq.setSolutioning(true);//正在处理
                        exsq.setSolutioned(false);//已经被处理
                        exsq.setSqContent(et_sqContent.getText().toString().trim());
//                        BmobQuery<ExiuSQ> query=new BmobQuery();
//                        query.addWhereEqualTo("user",getCurrentUser().getObjectId());
//                        query.findObjects(new FindListener<ExiuSQ>() {
//                            //查找是否存在该用户的申请记录,查找是否有当前user的id
//                            @Override
//                            public void done(List<ExiuSQ> list, BmobException e) {
//                                if (list.size()>0){
//                                    exsq.update(new UpdateListener() {
//                                        @Override
//                                        public void done( BmobException e) {
//                                            Toast.makeText(BecomeExiuActivity.this,"您的申请已经发送,请耐心等待审核!", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }else {
                                    exsq.save(new SaveListener<String>() {
                                        @Override
                                        public void done(String s, BmobException e) {
                                            Toast.makeText(BecomeExiuActivity.this, "您的申请已经发送,请耐心等待审核!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
//                                }
//                            }
//                        });

                    } else {
                        Toast.makeText(BecomeExiuActivity.this, "内容为空,不能申请", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BecomeExiuActivity.this, "您已经是维修人员", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
