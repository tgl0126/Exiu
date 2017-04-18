package com.tgl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tgl.exiu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MesageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam2;


    public MesageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MesageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MesageFragment newInstance(String param1, String param2) {
        MesageFragment fragment = new MesageFragment();
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
            String mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mesage, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview_mesage);
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), getDada(),
                R.layout.layout_adapter_mesage, new String[]{"img_head", "user_name", "content"},
                new int[]{R.id.img_mesage_user_head, R.id.tv_masage_user_name, R.id.tv_mesage_title_content});
        listView.setAdapter(adapter);
        return view;
    }
    /**
     *对listview进行adapter填充
     * */
    public List<Map<String, Object>> getDada() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map01 = new HashMap<String, Object>();
        map01.put("img_head", R.drawable.cry);
        map01.put("user_name", "zhangsan");
        map01.put("content", "我发布过的我发布过的我发布过的我发布过的我发布过的");
        list.add(map01);

        Map<String, Object> map02 = new HashMap<String, Object>();
        map02.put("img_head", R.drawable.cry);
        map02.put("user_name", "张三");
        map02.put("content", "我发布过的我发布过的我发布过的");
        list.add(map02);

        Map<String, Object> map03 = new HashMap<String, Object>();
        map03.put("img_head", R.drawable.cry);
        map03.put("user_name", "张三");
        map03.put("content", "我发布过的");
        list.add(map03);

        Map<String, Object> map04 = new HashMap<String, Object>();
        map04.put("img_head", R.drawable.cry);
        map04.put("user_name", "张三");
        map04.put("content", "我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的");
        list.add(map04);

        Map<String, Object> map05 = new HashMap<String, Object>();
        map05.put("img_head", R.drawable.cry);
        map05.put("user_name", "张三");
        map05.put("content", "我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的我发布过的");
        list.add(map05);
        return list;
    }
}
