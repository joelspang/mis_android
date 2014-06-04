package mis.examples.doodle.model;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Poll {
	
	@Element
	protected String title;

    @Element
    protected String description;
    
    @Element
    protected String location;
    
    @ElementList(name = "participants", inline = false, required = false)
    private List< Participant > participants;	
    
    @Element
    protected Initiator initiator;
    
    @Element
    protected int levels = 2;
    
    @Element
    protected String state = "OPEN";
    
    @Element
    protected String language = "en";

    @ElementList(name = "options", inline = false)
    protected List< Option > options;
    
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public List<Participant> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Initiator getInitiator() {
		return initiator;
	}
	public void setInitiator(Initiator initiator) {
		this.initiator = initiator;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}		
	
}
