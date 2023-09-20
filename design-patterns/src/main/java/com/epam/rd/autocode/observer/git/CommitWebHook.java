package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

import com.epam.rd.autocode.observer.git.Event.Type;

public class CommitWebHook implements WebHook{
	private String branch;
	private List<Event> events = new ArrayList<>();

	public CommitWebHook(String branch) {
		super();
		this.branch = branch;
	}

	@Override
	public String branch() {
		// TODO Auto-generated method stub
		return branch;
	}

	@Override
	public Type type() {
		// TODO Auto-generated method stub
		return Event.Type.COMMIT;
	}

	@Override
	public List<Event> caughtEvents() {
		// TODO Auto-generated method stub
		return events;
	}

	@Override
	public void onEvent(Event event) {
		if(event.type() == Event.Type.COMMIT && event.branch().equals(branch)) {
			events.add(event);
		}
	}

}
