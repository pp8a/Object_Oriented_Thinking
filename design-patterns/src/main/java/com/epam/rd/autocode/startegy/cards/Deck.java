package com.epam.rd.autocode.startegy.cards;

import java.util.List;
/*
 * Представляет собой интерфейс для колоды карт, с которой будут производиться операции раздачи карт.
 */
public interface Deck{
    Card dealCard();//Метод, который раздает одну карту из колоды. Возвращает объект, реализующий интерфейс Card.
    List<Card> restCards();//Метод, возвращающий список карт, которые остались в колоде после раздачи.
    int size();//Метод, возвращающий текущий размер колоды (количество оставшихся карт).
}
