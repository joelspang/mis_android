package mis.examples.doodle;

import java.util.ArrayList;

import mis.examples.doodle.model.Initiator;
import mis.examples.doodle.model.Poll;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.XmlSpringAndroidSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EnterOptionsActivity extends Activity {
	
	SpiceManager spiceManager = new SpiceManager(XmlSpringAndroidSpiceService.class);
	
	private String input_title 			= "";
	private String input_location 		= "";
	private String input_description 	= "";
	private String input_name 			= "";
	private String input_email 			= "";
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	    setContentView(R.layout.enteroptions);
	    
	    // Fetch extra information
		this.input_title 		= (String) getIntent().getSerializableExtra("input_title");
		this.input_location 	= (String) getIntent().getSerializableExtra("input_location");
		this.input_description 	= (String) getIntent().getSerializableExtra("input_description");
		this.input_name 		= (String) getIntent().getSerializableExtra("input_name");
		this.input_email 		= (String) getIntent().getSerializableExtra("input_email");
		
		initGUIComponents();
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
	
	@Override
	public void onBackPressed() {
	    // Jump back to the "New Poll"-activity
		Intent i = new Intent(getApplicationContext(), NewPollActivity.class);

		i.putExtra("input_title", input_title);	
		i.putExtra("input_location", input_location);	
		i.putExtra("input_description", input_description);		
		i.putExtra("input_name", input_name);		
		i.putExtra("input_email", input_email);
		
		startActivity(i);
		finish();
	}
	
	private void initGUIComponents() {
		// Back button
		Button btnBack = (Button) findViewById(R.id.btnBack); 
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EnterOptionsActivity.this.onBackPressed();
			}
		});

		// Finish button
		Button btnFinish = (Button) findViewById(R.id.btnFinish); 
		btnFinish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Send a POST request to the server to save the newly created poll
				
				/* Prepare the poll */
				Poll poll = new Poll();
				poll.setDescription(EnterOptionsActivity.this.input_description);
				poll.setTitle(EnterOptionsActivity.this.input_title);
				
				Initiator i = new Initiator();
				i.setName(EnterOptionsActivity.this.input_name);
				i.seteMailAddress(EnterOptionsActivity.this.input_email);
				poll.setInitiator(i);
								
				ArrayList<String> o = new ArrayList<String>();
				TextView choices = (TextView) findViewById(R.id.txtChoices);
				String txt = choices.getText().toString();
				for (String s : txt.split("\n")) {
					o.add(s);
				}
				poll.setOptions(o);
				
				/* Start a new POST request */				
				CreatePollRequest request = new CreatePollRequest(poll);				
		        spiceManager.execute(request, new CreatePollListener());
			}
		});
	}
	
	private class CreatePollListener implements RequestListener<Poll> {
	    @Override
	    public void onRequestFailure(SpiceException e) {
	        Toast.makeText(EnterOptionsActivity.this,
	            "Error during request: " + e.getLocalizedMessage(), Toast.LENGTH_LONG)
	            .show();
	        EnterOptionsActivity.this.setProgressBarIndeterminateVisibility(false);
	    }
	
	    @Override
	    public void onRequestSuccess(Poll responsePoll) {
	        if (responsePoll == null) {
	            return;
	        }
	        
	        EnterOptionsActivity.this.setProgressBarIndeterminateVisibility(false);
	        
	        // Add the new poll to the main activity's list
			Intent i = new Intent(getApplicationContext(), MainActivity.class);

			i.putExtra("newpoll", responsePoll);
			
			startActivity(i);
			finish();	        
	    }	
	}
	
}
