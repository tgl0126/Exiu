package com.tgl.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.tgl.exiu.R;

public class PublishFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Spinner spinner_main;
    private Spinner spinner_child;
    private View view;
    private OnFragmentInteractionListener mListener;
//    private ArrayAdapter<String> adpter_spinner_main;
//    private ArrayAdapter<String> adpter_spinner_child;
//    private String[] main = new String[]{"软件", "硬件"};
//    private String[][] child = new String[][]{
//            //软件
//            {"系统重装", "系统还原", "总是蓝屏", "异常错误", "其它"},
//            //硬件
//            {"硬盘损坏", "风扇噪音大", "光驱无法使用"}
//    };

    public PublishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublishFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        //   setSpinner();
        return view;
    }

  /*  private void setSpinner() {
        spinner_main=(Spinner) view.findViewById(R.id.spinner_main);
        spinner_child=(Spinner) view.findViewById(R.id.spinner_child);
        //绑定适配器和值
          adpter_spinner_main=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, main);
        spinner_main.setAdapter(adpter_spinner_main);
        spinner_main.setSelection(0,true);

        adpter_spinner_child=new ArrayAdapter<String>(getContext(),
                android.R.layout.select_dialog_item,child[0]);
        spinner_child.setAdapter(adpter_spinner_child);
        spinner_child.setSelection(1,true);
    }*/

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
     *//**/
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
