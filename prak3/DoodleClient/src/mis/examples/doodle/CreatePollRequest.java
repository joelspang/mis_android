package mis.examples.doodle;

import java.util.ArrayList;

import mis.examples.doodle.model.Initiator;
import mis.examples.doodle.model.Option;
import mis.examples.doodle.model.Poll;
import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class CreatePollRequest extends SpringAndroidSpiceRequest<CreatePollResponse> {
	
	public CreatePollRequest() {
		super(CreatePollResponse.class);	
	}

	@Override
	public CreatePollResponse loadDataFromNetwork() throws Exception {
		Log.d( "request", "sending post request" );
		
		Poll p = new Poll();
		p.setDescription("My description");
		p.setTitle("My Title");
		
		Initiator i = new Initiator();
		i.setName("Test Name");
		i.seteMailAddress("no@nopecom");
		p.setInitiator(i);
		
		p.setLocation("A location, Somewhere");
		
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Yes"));
		options.add(new Option("Nope"));
		p.setOptions(options);
		
		System.out.println(p.toString());
		
		return getRestTemplate().postForObject( MainActivity.APIUrl + "polls", p, CreatePollResponse.class );		
	}

}