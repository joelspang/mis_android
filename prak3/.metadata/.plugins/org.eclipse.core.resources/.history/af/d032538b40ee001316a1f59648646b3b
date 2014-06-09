package mis.examples.doodle;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.XmlSpringAndroidSpiceService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class NewPollActivity extends Activity {
	
	SpiceManager spiceManager = new SpiceManager(XmlSpringAndroidSpiceService.class);	

	private String input_title 			= "";
	private String input_location 		= "";
	private String input_description 	= "";
	private String input_name 			= "";
	private String input_email 			= "";
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	    setContentView(R.layout.newpoll);
	    
	    // Check if we have any info from the previous activity
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
	    // Jump back to the main activity
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		finish();
	}
	
	private void initGUIComponents() {
		// Add a listener for the Next button
		Button btnNext = (Button) findViewById(R.id.btnNext);
		
		btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Go to the next screen and pass on the input
				Intent i = new Intent(getApplicationContext(), EnterOptionsActivity.class);
				
				TextView txtTitle = (TextView) findViewById(R.id.txtNpTitle);
				TextView txtLocation = (TextView) findViewById(R.id.txtNpLocation);
				TextView txtDescription = (TextView) findViewById(R.id.txtNpDescription);
				TextView txtName = (TextView) findViewById(R.id.txtNpName);
				TextView txtEmail = (TextView) findViewById(R.id.txtNpEmail);

				i.putExtra("input_title", txtTitle.getText().toString());	
				i.putExtra("input_location", txtLocation.getText().toString());	
				i.putExtra("input_description", txtDescription.getText().toString());		
				i.putExtra("input_name", txtName.getText().toString());		
				i.putExtra("input_email", txtEmail.getText().toString());
				
				startActivity(i);
				finish();
			}
		});

		((TextView) findViewById(R.id.txtNpTitle)).setText(this.input_title);
		((TextView) findViewById(R.id.txtNpLocation)).setText(this.input_location);
		((TextView) findViewById(R.id.txtNpDescription)).setText(this.input_description);
		((TextView) findViewById(R.id.txtNpName)).setText(this.input_name);
		((TextView) findViewById(R.id.txtNpEmail)).setText(this.input_email);
	}
	
}
