package com.example.assignment5;

import android.widget.Toast;

import java.util.ArrayList;

public class GiftCardModel {
    int numberOfGiftCardsPurchased;
    int numberOfGiftCardsRedeemed;
    double totalPurchasedValue;
    double totalRedeemedValue;
    ArrayList<GiftCard> giftCardsArrayList;
    private static GiftCardModel giftCardModel=null;

    public static GiftCardModel getGiftCardModel(){
        if(giftCardModel==null){
            giftCardModel=new GiftCardModel();
        }
        return giftCardModel;
    }

    private GiftCardModel(){
        giftCardsArrayList=new ArrayList<>();
    }


    public int purchaseCard(double amount){
        giftCardsArrayList.add(new GiftCard(amount,false));
      numberOfGiftCardsPurchased+=1;
      totalPurchasedValue+=amount;
return giftCardsArrayList.size();

    }

    public int redeemCard( int code){
GiftCard giftCard=giftCardsArrayList.get(code);
if(giftCard.redeemed==false){
numberOfGiftCardsRedeemed++;
totalRedeemedValue=totalRedeemedValue+giftCard.amount;
giftCardsArrayList.get(code).redeemed=true;
return code+1;

}

return -1;
    }

    public static class GiftCard{
        public double amount;
        public Boolean redeemed;
        public GiftCard(double amount,Boolean redeemed){
            this.amount=amount;
            this.redeemed=redeemed;
        }
    }



}
