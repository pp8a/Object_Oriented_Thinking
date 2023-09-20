package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitRepository implements Repository{
	/*
	 * имитация системы контроля версий Git.
	 */
	private Map<String, List<WebHook>> webHooks = new HashMap<>();
    private Map<String, List<Commit>> branches = new HashMap<>();   

	@Override
	public void addWebHook(WebHook webHook) {//Метод получает объект веб-хука webHook
		/*
		 * метод для добавления веб-хука к конкретной ветке. 
		 */
		String branchName = webHook.branch();// извлекает имя ветки, к которой хук будет привязан		
        webHooks.computeIfAbsent(branchName, k -> new ArrayList<>()).add(webHook);//добавляет переданный веб-хук в этот список.
		// для создания списка веб-хуков для этой ветки, если он еще не существует.
	}

	@Override
	public Commit commit(String branch, String author, String[] changes) {
		/*
		 *  метод для создания нового коммита в указанной ветке. 
		 *  Метод получает имя ветки, автора коммита и список изменений. 
		 */
		List<Commit> branchCommits = branches.computeIfAbsent(branch, k -> new ArrayList<>());
		//Затем он извлекает список коммитов для указанной ветки, используя метод computeIfAbsent
	    Commit newCommit = new Commit(author, changes);//Создается новый коммит с переданным автором и изменениями, 
	    branchCommits.add(newCommit);//и этот коммит добавляется в список коммитов для ветки.

	    List<Event> events = new ArrayList<>();
	    //Затем создается список событий events, в котором создается одно событие типа COMMIT. 
	    events.add(new Event(Event.Type.COMMIT, branch, List.of(newCommit))); // Используйте List.of() для создания списка	    
	    //Это событие содержит информацию о созданном коммите и ветке, к которой он относится.
	    
	    List<WebHook> webHookList = webHooks.get(branch);
	    if (webHookList != null) {
	        for (WebHook webHook : webHookList) {
	        	//Далее извлекается список веб-хуков для данной ветки, и если он не пустой,
	        	//то для каждого веб-хука вызывается метод onEvent, передавая ему созданное событие COMMIT.
	            webHook.onEvent(new Event(Event.Type.COMMIT, branch, List.of(newCommit))); // Используйте List.of() для создания списка
	        }
	    }

	    return newCommit;
	}

	@Override
	public void merge(String sourceBranch, String targetBranch) {
		// метод для слияния коммитов из одной ветки (sourceBranch) в другую (targetBranch). 
		List<Commit> sourceCommits = branches.get(sourceBranch);
	    if (sourceCommits == null || sourceCommits.isEmpty()) {
	    	//Метод начинает с проверки, есть ли что сливать в исходной ветке. 
	    	//Если список коммитов пуст или ветка не существует, метод завершает выполнение.
	        return; // Нечего сливать
	    }

	    List<Commit> targetCommits = branches.computeIfAbsent(targetBranch, k -> new ArrayList<>());
	    /*
	     * Затем он извлекает список коммитов для целевой ветки и создает новый список newCommits, 
	     * в котором будут храниться только те коммиты из исходной ветки, которых нет в целевой.
	     */

	    // Фильтруем коммиты, оставляем только те, которых нет в целевой ветке
	    List<Commit> newCommits = new ArrayList<>();
	    for (Commit sourceCommit : sourceCommits) {
	        boolean existsInTarget = false;
	        for (Commit targetCommit : targetCommits) {
	            if (sourceCommit.equals(targetCommit)) {
	                existsInTarget = true;
	                break;
	            }
	        }
	        if (!existsInTarget) {
	            newCommits.add(sourceCommit);
	        }
	    }

	    targetCommits.addAll(newCommits);

	    List<Event> events = new ArrayList<>();
	    if (!newCommits.isEmpty()) {
	    	//Далее метод добавляет коммиты из newCommits в целевую ветку и создает список событий events
	        events.add(new Event(Event.Type.MERGE, targetBranch, newCommits));
	        // Если есть новые коммиты для слияния, то создается одно событие типа MERGE, содержащее информацию о добавленных коммитах.
	    }
	    List<WebHook> targetWebHooks = webHooks.get(targetBranch);
	    if (targetWebHooks != null) {
	        for (WebHook webHook : targetWebHooks) {//Затем метод извлекает список веб-хуков для целевой ветки и, 
	            if (!newCommits.isEmpty()) {//если он не пуст, для каждого веб-хука вызывает метод onEvent,
	                webHook.onEvent(new Event(Event.Type.MERGE, targetBranch, newCommits));// передавая ему событие MERGE с информацией о новых коммитах.
	            }
	        }
	    }
	}
}
