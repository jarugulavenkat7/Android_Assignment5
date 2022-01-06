package com.example.assignment5;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RedeemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RedeemFragment extends Fragment {
    public interface RedeemCallBack {
        public void update();
        public void swapToPurchase();
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  //  private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
  //  private Integer mParam1;


    public RedeemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     *
     * @return A new instance of fragment RedeemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RedeemFragment newInstance() {
        RedeemFragment fragment = new RedeemFragment();
       // Bundle args = new Bundle();
       // args.putInt(ARG_PARAM1, param1);

       // fragment.setArguments(args);
        return fragment;
    }
    private RedeemCallBack activity = null;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (RedeemCallBack) context;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
    //        mParam1 = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_redeem, container, false);

        EditText redeemET = v.findViewById(R.id.redeemET);
        GiftCardModel gcm=GiftCardModel.getGiftCardModel();


        Button redeemBTN = v.findViewById(R.id.redeemBTN);
        redeemBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int code=Integer.parseInt(redeemET.getText().toString());
                if(code<=gcm.giftCardsArrayList.size()) {
                    int pos=gcm.redeemCard(code-1);
                    activity.update();
                    if(pos!=-1) {

                        Toast.makeText(getContext(), "Card with code " + pos + " redeemed .Success", Toast.LENGTH_LONG).show();
                    }
                    if(pos==-1){
                        Toast.makeText(getContext(), "Card with code " + code + " not available .Failure", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Please enter valid code", Toast.LENGTH_LONG).show();
                }
            }
        });

/*
        Button swapBTN = v.findViewById(R.id.swapBTN);
        swapBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.swapToPurchase();
            }
        });
*/
        return v;
    }
}