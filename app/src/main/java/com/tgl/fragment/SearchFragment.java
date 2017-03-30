package com.tgl.fragment;

import android.net.Uri;
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

public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "match_parent";
    private static final String ARG_PARAM2 = "match_parent";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
private ListView listView;
    private View view;
    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        view = inflater.inflate(R.layout.fragment_search, container, false);
        listView = (ListView) view.findViewById(R.id.listview_search);
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), getDada(),
                R.layout.layout_adapter_mesage, new String[]{"img_head", "user_name", "content"},
                new int[]{R.id.img_mesage_user_head, R.id.tv_masage_user_name, R.id.tv_mesage_title_content});
        listView.setAdapter(adapter);
        return view;
    }
    /**
     *对listview进行adapter填充
     * 暂时这样填充，后面改为和首页listView发布的信息一样的就行了。或者改为只能搜索科技知识，不搜索
     * 其它用户发布的信息也可以。
     * made in 17-03-09
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
     /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
