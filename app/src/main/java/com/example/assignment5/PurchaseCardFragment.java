package com.example.assignment5;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PurchaseCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PurchaseCardFragment extends Fragment {
    public interface PurchaseCallBack {
        public void update();
        public void swapToRedeem();
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   // private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
   // private double mParam1;


    public PurchaseCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment PurchaseCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PurchaseCardFragment newInstance() {
        PurchaseCardFragment fragment = new PurchaseCardFragment();
        return fragment;
    }

    private PurchaseCallBack activity = null;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (PurchaseCallBack) context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
      //      mParam1 = getArguments().getDouble(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_purchase_card, container, false);
        EditText amountET = v.findViewById(R.id.amountET);

        GiftCardModel gcm=GiftCardModel.getGiftCardModel();
        Button purchaseBTN = v.findViewById(R.id.purchaseBTN);
         int pos=0;
        purchaseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Double amount=Double.parseDouble(amountET.getText().toString());

                   int  pos=gcm.purchaseCard(Math.round(amount * 100.0) / 100.0);
                    Toast.makeText(getContext(),"Gift Card with code "+pos+" purchased with amount "+ amount, Toast.LENGTH_LONG).show();
                }
                catch (NumberFormatException e){
                    Log.d("message",e.getMessage());
                }
                activity.update();

            }
        });
        /**
       Button swapBTN = v.findViewById(R.id.swapBTN);
        swapBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.swapToRedeem();
            }
        });
*/
        return v;


    }
}