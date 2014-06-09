package mis.examples.doodle.net;

import mis.examples.doodle.activities.MainActivity;
import mis.examples.doodle.model.Poll;
import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class PollRequest extends SpringAndroidSpiceRequest<Poll> {

	private String token;
	
	public PollRequest(String token) {
		super(Poll.class);
		this.token = token;		
	}

	@Override
	public Poll loadDataFromNetwork() throws Exception {
		Log.d( "request", "loading from network" );		
        return getRestTemplate().getForObject( MainActivity.APIUrl + "polls/" + this.token, Poll.class );
	}

}
