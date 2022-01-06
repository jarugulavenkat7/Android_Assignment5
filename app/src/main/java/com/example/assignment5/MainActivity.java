package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PurchaseCardFragment.PurchaseCallBack, RedeemFragment.RedeemCallBack {
    int numberOfGiftCardsPurchased;
    int numberOfGiftCardsRedeemed;
    double totalPurchasedValue;
    double totalRedeemedValue;

    GiftCardModel gcm = GiftCardModel.getGiftCardModel();

    Boolean swapBoolean=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PurchaseCardFragment pcf=PurchaseCardFragment.newInstance();
        RedeemFragment rf=RedeemFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tran = fm.beginTransaction();
        tran.add(R.id.fragmentContainerView, pcf, "Purchase");
        tran.add(R.id.fragmentContainerView, rf, "Redeem");
        tran.hide(rf);
        //tran.hide(frag2);
        tran.commit();

    }

    @Override
    public void update() {
        TextView purshaseDisplayTV = findViewById(R.id.purshaseDisplayTV);
        TextView redeemDisplayTV = findViewById(R.id.redeemDisplayTV);


        numberOfGiftCardsPurchased=gcm.numberOfGiftCardsPurchased;
        numberOfGiftCardsRedeemed=gcm.numberOfGiftCardsRedeemed;
        totalPurchasedValue=gcm.totalPurchasedValue;
        totalRedeemedValue=gcm.totalRedeemedValue;
        purshaseDisplayTV.setText(numberOfGiftCardsPurchased+" cards purchased worth "+totalPurchasedValue);
        redeemDisplayTV.setText(numberOfGiftCardsRedeemed+" cards redeemed worth "+totalRedeemedValue);
    }

    @Override
    public void swapToPurchase() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tran = fm.beginTransaction();
        Fragment purchase = fm.findFragmentByTag("Purchase");
        Fragment redeem = fm.findFragmentByTag("Redeem");
        tran.hide(redeem);
        tran.show(purchase);
        tran.commit();
    }

    @Override
    public void swapToRedeem() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tran = fm.beginTransaction();
        Fragment purchase = fm.findFragmentByTag("Purchase");
        Fragment redeem = fm.findFragmentByTag("Redeem");
        tran.hide(purchase);
        tran.show(redeem);
        tran.commit();

    }
    public void swap(View v){
        Button swapButton=findViewById(R.id.swapMainBTN);
        if(swapBoolean==false) {
            swapToRedeem();
            swapBoolean=true;
            swapButton.setText("Switch To Purchase");
        }
        else{
            swapToPurchase();
            swapBoolean=false;
            swapButton.setText("Switch To Redeem");
        }


    }
}