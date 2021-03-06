package mis.examples.doodle.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Element
    protected Integer id;

    @Element
    protected String name;

    @Element(required = false)
    protected String userId;

    @ElementList(name = "preferences", inline = false, entry = "option")
    protected ArrayList<Integer> preferences = new ArrayList<Integer>();

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public ArrayList<Integer> getPreferences() {
	return preferences;
    }

    public void setPreferences(ArrayList<Integer> preferences) {
	this.preferences = preferences;
    }

}
