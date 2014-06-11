package mis.examples.doodle.net;

import mis.examples.doodle.activities.MainActivity;
import mis.examples.doodle.model.Poll;
import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class PollRequest extends SpringAndroidSpiceRequest<Poll> {

    private String pollId;

    public PollRequest(String pollId) {
	super(Poll.class);
	this.pollId = pollId;
    }

    @Override
    public Poll loadDataFromNetwork() throws Exception {
	Log.d("request", "loading from network");

	return getRestTemplate().getForObject(
		MainActivity.APIUrl + "polls/" + this.pollId, Poll.class);
    }

}
