package com.epam.rd.autocode.startegy.cards;

import java.util.List;
import java.util.Map;
/*
 *  Представляет собой интерфейс для стратегии раздачи карт, которая определяет, как карты будут раздаваться в игре.
 */
public interface CardDealingStrategy {
    Map<String, List<Card>> dealStacks(Deck deck, int players);
    /*
     *  Метод, который реализует логику раздачи карт в соответствии с выбранной стратегией. 
     *  Принимает на вход колоду карт (deck) и количество игроков (players). 
     *  Возвращает карты, разделенные на именованные стопки (ключи - имена стопок, значения - списки карт в каждой стопке).
     */
}
