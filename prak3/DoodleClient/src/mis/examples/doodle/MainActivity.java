package mis.examples.doodle;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.XmlSpringAndroidSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import mis.examples.doodle.model.Poll;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public final static	String APIUrl 	 = "http://doodle-test.com/api1WithoutAccessControl/";
	public final 		String testToken = "bspsi4b7pzrcrvwb";
	
	private SpiceManager spiceManager = new SpiceManager(XmlSpringAndroidSpiceService.class);
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.main);

        initUIComponents();
    }

	private void initUIComponents() {
		// Add listeners for buttons
		Button btnFetch = (Button) findViewById(R.id.btnNewpoll);
				
		btnFetch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//fetchPollInfo(MainActivity.this.testToken);
				// Create a new poll; new activity
				startActivity(new Intent(getApplicationContext(), NewPollActivity.class));
				finish();
			}
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		spiceManager.start(this);
	}

	@Override
	protected void onStop() {
		spiceManager.shouldStop();
		super.onStop();
	}
	
	private void fetchPollInfo(String token) {
        MainActivity.this.setProgressBarIndeterminateVisibility(true);
        
        PollRequest request = new PollRequest(token);
        spiceManager.execute(request, new ListFollowersRequestListener());
        System.out.println("Added new request " + request);
	}
	
	private class ListFollowersRequestListener implements RequestListener<Poll> {
	    @Override
	    public void onRequestFailure(SpiceException e) {
	        Toast.makeText(MainActivity.this,
	            "Error during request: " + e.getLocalizedMessage(), Toast.LENGTH_LONG)
	            .show();
	        MainActivity.this.setProgressBarIndeterminateVisibility(false);
	        System.out.println("Failure!");
	    }
	
	    @Override
	    public void onRequestSuccess(Poll info) {
	        if (info == null) {
	            return;
	        }	        
	        
	        MainActivity.this.setProgressBarIndeterminateVisibility(false);
	    }	
	}
	
}
