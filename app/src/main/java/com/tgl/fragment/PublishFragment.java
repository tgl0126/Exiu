package com.tgl.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgl.beans.PublishBean;
import com.tgl.beans.UserBean;
import com.tgl.exiu.R;
import com.tgl.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

@SuppressWarnings("ALL")
public class PublishFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @SuppressWarnings("FieldCanBeLocal")
    private String mParam1;
    @SuppressWarnings("FieldCanBeLocal")
    private String mParam2;
    @SuppressWarnings("FieldCanBeLocal")
    private View view;

    private Handler mHandler;
    private PublishBean publish;
    private UserBean user;
    //Spinner
    @BindView(R.id.spinner_main)
    Spinner spinner_main;
    @BindView(R.id.spinner_child)
    Spinner spinner_child;
    //发布
    @BindView(R.id.tv_publish_publish)
    TextView tv_publish_publish;
    //标题
    @BindView(R.id.et_title)
    EditText et_title;
    //内容描述
    @BindView(R.id.et_content)
    EditText et_content;
    private String str_child="";
    private String str_main="";
    private String str_problem="";

    public PublishFragment() {
    }

    public static PublishFragment newInstance(String param1, String param2) {
        PublishFragment fragment = new PublishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_publish, container, false);
        ButterKnife.bind(this, view);
        setSpinner();
        itit();
        return view;
    }

    private void itit() {
        //点击发布.
        tv_publish_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(et_title.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "标题不能为空!", Toast.LENGTH_SHORT).show();
                }
                if ("".equals(et_content.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "内容描述不能为空!", Toast.LENGTH_SHORT).show();
                }
                if (!SharedPreferenceUtil.getBooleanInfo(getContext(), "isLogin")) {
                    Toast.makeText(getActivity(), "请登录之后在发布!", Toast.LENGTH_SHORT).show();
                } else {

                    //上传发布信息
                    mHandler = new Handler();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            publish = new PublishBean();//新建发布消息
                            user = BmobUser.getCurrentUser(UserBean.class);//获取当前用户
                            publish.setTitle(et_title.getText().toString().trim());//获取标题
                            publish.setContent(et_content.getText().toString().trim());//获取内容
                            publish.setWhereFrom(user.getAddress().trim());//获取地址
                            publish.setSoluted(false);//获取是否已经被处理
                            publish.setSoluting(false);//获取是否正在被处理
                            publish.setProblemType(str_problem);//获取问题类型
                            publish.setUserBean(user);//表示是该用户发布的信息
//                            publish.setCreateTime(new Date());//设置创建的时间,有利于统计发布了多久了
                            publish.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_SHORT).show();
                                        et_title.setText("");
                                        et_content.setText("");
                                    } else {
                                        Toast.makeText(getActivity(), "发布失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });

    }

    private void setSpinner() {
        ArrayAdapter<CharSequence> adapter_spinner_main = ArrayAdapter.createFromResource(getContext(), R.array.problem, android.R.layout.simple_list_item_1);
        //设置下拉列表的风格
        adapter_spinner_main.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将数据绑定到Spinner视图上
        spinner_main.setAdapter(adapter_spinner_main);

        spinner_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_main = (String) parent.getItemAtPosition(position);
                ArrayAdapter<CharSequence> adpter_spinner_child = null;
                if (str_main.equals("硬件")) {
                    adpter_spinner_child = ArrayAdapter.createFromResource
                            (getContext(), R.array.problem_ying, android.R.layout.simple_spinner_item);
                } else if (str_main.equals("软件")) {
                    adpter_spinner_child = ArrayAdapter.createFromResource
                            (getContext(), R.array.problem_ruan, android.R.layout.simple_list_item_1);
                }
                //设置子类选择的适配器,并增加item选择事件获取item内容
                spinner_child.setAdapter(adpter_spinner_child);
                spinner_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        str_child = (String) parent.getItemAtPosition(position);
                        str_problem = str_main +","+ str_child;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                str_problem="未选择,未选择";
            }
        });

    }
}
