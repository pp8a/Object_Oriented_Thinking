package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new CardDealingStrategy() {
			
			@Override
			public Map<String, List<Card>> dealStacks(Deck deck, int players) {
				Map<String, List<Card>> cardStack = new HashMap<>();
				
//				for (int i = 0; i < players; i++) {
//					List<Card> playerStack = new ArrayList<>();//создаем стопки для каждого игрока
//					for (int j = 0; j < 2; j++) {
//						playerStack.add(deck.dealCard());//раздаем карты игрокам
//					}	
//					cardStack.put("Player " + i, playerStack);
//				}
				
				for (int i = 0; i < 2; i++) {
					for (int playerNum = 1; playerNum <= players; playerNum++) {
						List<Card> playerStack = cardStack.computeIfAbsent("Player " + playerNum, t -> new ArrayList<>());
						playerStack.add(deck.dealCard());
					}
				}
				
				
				List<Card> communityStack = new ArrayList<>();
				for (int i = 0; i < 5; i++) {
					communityStack.add(deck.dealCard());
				}				
				cardStack.put("Community", communityStack);
				
				List<Card> remainingStack = new ArrayList<>();
				while(deck.size() > 0) {
					remainingStack.add(deck.dealCard());
				}
				cardStack.put("Remaining", remainingStack);
				
				System.out.println(cardStack);
				
				return cardStack;
			}
		};
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
    	return new CardDealingStrategy() {
			
			@Override
			public Map<String, List<Card>> dealStacks(Deck deck, int players) {
				Map<String, List<Card>> cardStack = new HashMap<>();
				
				for (int i = 0; i < 5; i++) {
					for (int playerNum = 1; playerNum <= players; playerNum++) {
						List<Card> playerStack = cardStack.computeIfAbsent("Player " + playerNum, t -> new ArrayList<>());
						playerStack.add(deck.dealCard());
					}					
				}
				
				List<Card> remainingStack = new ArrayList<>();
				while(deck.size() > 0) {
					remainingStack.add(deck.dealCard());
				}
				cardStack.put("Remaining", remainingStack);				
				
				return cardStack;
			}
		};
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new CardDealingStrategy() {
			
			@Override
			public Map<String, List<Card>> dealStacks(Deck deck, int players) {
				Map<String, List<Card>> cardStack = new HashMap<>();
				
				for (int i = 0; i < 13; i++) {
					for (int playerNum = 1; playerNum <= players; playerNum++) {
						List<Card> playerStack = cardStack.computeIfAbsent("Player " + playerNum, t -> new ArrayList<>());
						playerStack.add(deck.dealCard());
					}					
				}
				
				return cardStack;
			}
		};
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new CardDealingStrategy() {
			
			@Override
			public Map<String, List<Card>> dealStacks(Deck deck, int players) {
				Map<String, List<Card>> cardStack = new HashMap<>();
				
				for (int i = 0; i < 6; i++) {
					for (int playerNum = 1; playerNum <= players; playerNum++) {
						List<Card> playerStack = cardStack.computeIfAbsent("Player " + playerNum, t -> new ArrayList<>());
						playerStack.add(deck.dealCard());
					}					
				}
				
				List<Card> trumpStack = new ArrayList<>();
				trumpStack.add(deck.dealCard());			
				cardStack.put("Trump card", trumpStack);
				
				List<Card> remainingStack = new ArrayList<>();
				while(deck.size() > 0) {
					remainingStack.add(deck.dealCard());
				}
				cardStack.put("Remaining", remainingStack);	
				
				return cardStack;
			}
		};
    }

}
