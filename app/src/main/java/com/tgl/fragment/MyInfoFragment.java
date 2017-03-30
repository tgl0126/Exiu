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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;
    private View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyInfoFragment newInstance(String param1, String param2) {
        MyInfoFragment fragment = new MyInfoFragment();
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
        /**
         * adapter 实现对listview 的初始化
         *
         * */
        view = inflater.inflate(R.layout.fragment_myinfo, container, false);
        listView = (ListView) view.findViewById(R.id.listview_myinfo);
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), getDada(),
                R.layout.layout_adapter_myinfo_listview, new String[]{"img_icon", "context", "icon_go"},
                new int[]{R.id.img_myinfo_item_icon, R.id.tv_myinfo_item_content, R.id.btn_myinfo_item_icon_go});
        listView.setAdapter(adapter);
        return view;
    }


    /**
     *对listview进行adapter填充
     * */

    public List<Map<String, Object>> getDada() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map01 = new HashMap<String, Object>();
        map01.put("img_icon", R.drawable.home);
        map01.put("context", "我发布过的");
        map01.put("icon_go", R.drawable.go);
        list.add(map01);

        Map<String, Object> map02 = new HashMap<String, Object>();
        map02.put("img_icon", R.drawable.mesage);
        map02.put("context", "我维修过的");
        map02.put("icon_go", R.drawable.go);
        list.add(map02);



        Map<String, Object> map03 = new HashMap<String, Object>();
        map03.put("img_icon", R.drawable.mesage);
        map03.put("context", "我赞过的");
        map03.put("icon_go", R.drawable.go);
        list.add(map03);

        Map<String, Object> map04 = new HashMap<String, Object>();
        map04.put("img_icon", R.drawable.mesage);
        map04.put("context", "建议与反馈");
        map04.put("icon_go", R.drawable.go);
        list.add(map04);

        Map<String, Object> map05 = new HashMap<String, Object>();
        map05.put("img_icon", R.drawable.mesage);
        map05.put("context", "设置");
        map05.put("icon_go", R.drawable.go);
        list.add(map05);

        Map<String, Object> map06 = new HashMap<String, Object>();
        map06.put("img_icon", R.drawable.mesage);
        map06.put("context", "我要成为维修人员");
        map06.put("icon_go", R.drawable.go);
        list.add(map06);
        return list;
    }
    // TODO: Rename method, update argument and hook method into UI event
  /*  public void onButtonPressed(Uri uri) {
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
    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
