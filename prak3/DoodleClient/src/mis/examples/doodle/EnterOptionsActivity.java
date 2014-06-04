package mis.examples.doodle;

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
				/* Start a new POST request */
				CreatePollRequest request = new CreatePollRequest();
		        spiceManager.execute(request, new CreatePollListener());
		        System.out.println("Added new request " + request);
			}
		});
	}
	
	private class CreatePollListener implements RequestListener<CreatePollResponse> {
	    @Override
	    public void onRequestFailure(SpiceException e) {
	        Toast.makeText(EnterOptionsActivity.this,
	            "Error during request: " + e.getLocalizedMessage(), Toast.LENGTH_LONG)
	            .show();
	        EnterOptionsActivity.this.setProgressBarIndeterminateVisibility(false);
	        System.out.println("Failure!");
	    }
	
	    @Override
	    public void onRequestSuccess(CreatePollResponse response) {
	        if (response == null) {
	            return;
	        }	        
	        
	        EnterOptionsActivity.this.setProgressBarIndeterminateVisibility(false);
	    }	
	}
	
}
