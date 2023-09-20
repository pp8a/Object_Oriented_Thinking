package com.epam.rd.autocode.factory.plot;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
    	/*
    	 * Для реализации метода classicDisneyPlotFactory 
    	 * необходимо создать фабрику сюжетов (PlotFactory), 
    	 * которая будет возвращать сюжеты в стиле классических мультфильмов Disney. 
    	 * Фабрика принимает три персонажа: героя (hero), возлюбленного (beloved) и злодея (villain).
    	 */
    	return new PlotFactory() {
            @Override
            public Plot plot() {
                String heroName = hero.name();
                String belovedName = beloved.name();
                String villainName = villain.name();

                // Создайте описание сюжета Disney на основе имен персонажей.
                String plotDescription = String.format(
                        "%s is great. %s and %s love each other. %s interferes with their happiness but loses in the end.",
                        heroName, heroName, belovedName, villainName
                );

                // Возвращаем описание сюжета как объект Plot.
                return new Plot() {
                    @Override
                    public String toString() {
                        return plotDescription;
                    }
                };
            }
        };
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new PlotFactory() {
			
			@Override
			public Plot plot() {
				String heroName = hero.name();
				String epicName = epicCrisis.name();
				String funnyName = funnyFriend.name();
				
				String plotDescription = String.format(
						"%s feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - %s. "
						+ "%s stands up against it, but with no success at first.But putting self together and help of friends, "
						+ "including spectacular funny "
						+ "%s restore the spirit and %s overcomes the crisis and gains gratitude and respect", 
						heroName, epicName, heroName, funnyName, heroName);
				return new Plot() {

					@Override
					public String toString() {
						// TODO Auto-generated method stub
						return plotDescription;
					}
					
				};
			}
		};
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new PlotFactory() {      	

			@Override
			public Plot plot() {
				StringBuilder plotDescription = new StringBuilder();

	            // Добавляем описание кризиса.
	            plotDescription.append(epicCrisis.name()).append(" threatens the world. But brave ");

	         // Добавляем имена персонажей.
	            for (int i = 0; i < heroes.length; i++) {
	                plotDescription.append(heroes[i].name());
	                if (i < heroes.length - 1) {
	                    plotDescription.append(", brave ");
	                }
	            }	            
	                /*
	                 * Это условие проверяет, является ли текущий персонаж последним в массиве heroes. 
	                 * Если текущий персонаж не является последним, 
	                 * то после его имени будет добавлена строка ", brave " для создания более читаемой фразы с перечислением имен персонажей.
	                 * 
	                 * Когда i равно индексу последнего персонажа (то есть heroes.length - 1), 
	                 * дополнительная строка ", brave " не добавляется, чтобы избежать появления лишних запятых и слов перед последним именем.
	                 */
	            
	            
	         // Добавляем "on guard" и имя злодея.
	            plotDescription.append(" on guard. So, no way that intrigues of ").append(villain.name());
	            plotDescription.append(" overcome the willpower of inflexible heroes");
	            
				return new Plot() {

					@Override
					public String toString() {
						// TODO Auto-generated method stub
						return plotDescription.toString();
					}
					
				};
			}
		};
    }
}
