package com.edu.cnu.poker;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
@Data
public class Evaluator {
    public String evaluate(List<Card> cardList) {
        Map<Suit, Integer> suitMap = new HashMap<Suit, Integer>();
        Map<Integer, Integer> rankMap = new HashMap<Integer, Integer>();

        for (Card card : cardList) {
            if (suitMap.containsKey(card.getSuit())) {
                Integer count = suitMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                suitMap.put(card.getSuit(), count);
            } else {
                suitMap.put(card.getSuit(), new Integer(1));
            }
        }

        for (Card card : cardList) {
            if (rankMap.containsKey(card.getRank())) {
                Integer count = rankMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                rankMap.put(card.getRank(), count);
            } else {
                rankMap.put(card.getRank(), new Integer(1));
            }
        }

        for (Suit key : suitMap.keySet()) {
            if (suitMap.get(key) == 5) {
                return "FLUSH";
            }
        }

        if (rankMap.size() == cardList.size()) {
            return "HIGH CARD";
        }

        if(rankMap.size()+1 == cardList.size() ){
            for(int value:rankMap.values()){
                if(value==2){
                    return "ONE PAIR";
                }
            }
        }

        if(rankMap.size()+2 == cardList.size() ){
            int count=0;
            for(int value:rankMap.values()){
                if(value==2){
                    count++;
                    if(count==2){
                        return "TWO PAIR";
                    }
                }
                if(value==3){
                    return "TRIPLE";
                }
            }
        }

        if(rankMap.size()+3==cardList.size()){
            boolean pair=false;
            boolean triple=false;
            for(int value:rankMap.values()){
                if(value==2){
                    pair=true;
                }
                if(value==3){
                    triple=true;
                }
                if(value==4){
                    return "FOUR CARD";
                }
            }
            if(pair&&triple){
                return "FULL HOUSE";
            }
        }
        return "NOTHING";
    }
}
