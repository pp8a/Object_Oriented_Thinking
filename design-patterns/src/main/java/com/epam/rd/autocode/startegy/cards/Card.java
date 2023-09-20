package com.epam.rd.autocode.startegy.cards;

import com.epam.rd.autocode.Named;
/*
 * базовый интерфейс для карт в карточной игре.
 */
public interface Card extends Named {
	String name();//Он возвращает имя или идентификатор карты.
}
