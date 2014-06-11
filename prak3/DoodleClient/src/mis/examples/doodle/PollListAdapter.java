package mis.examples.doodle;

import java.util.ArrayList;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.XmlSpringAndroidSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import mis.examples.doodle.activities.MainActivity;
import mis.examples.doodle.model.Poll;
import mis.examples.doodle.net.DeletePollRequest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PollListAdapter extends BaseAdapter {

    private ArrayList<Poll> items;
    private LayoutInflater inflater;
    private MainActivity activity;

    public PollListAdapter(Activity activity, ArrayList<Poll> items) {
	this.items = items;
	this.inflater = (LayoutInflater) activity
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	this.activity = (MainActivity) activity;
    }

    @Override
    public int getCount() {
	return items.size();
    }

    @Override
    public Object getItem(int position) {
	return items.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    @Override
    public View getView(int position, View vi, ViewGroup parent) {
	if (vi == null) {
	    vi = inflater.inflate(R.layout.list_row, parent, false);
	}

	Poll currentPoll = items.get(position);

	TextView text = (TextView) vi.findViewById(R.id.title);
	text.setText(currentPoll.getTitle());

	ImageButton btnOpenWeb = (ImageButton) vi.findViewById(R.id.btnBrowser);
	btnOpenWeb.setOnClickListener(new OpenBrowserOnClickListener(activity,
		currentPoll));

	ImageButton btnDelete = (ImageButton) vi.findViewById(R.id.btnDelete);
	btnDelete.setOnClickListener(new DeleteItemOnClickListener(activity,
		currentPoll));

	return vi;
    }

    private class DeleteItemOnClickListener implements OnClickListener {

	Poll poll;
	MainActivity mainAct;

	public DeleteItemOnClickListener(MainActivity main, Poll poll) {
	    super();
	    this.poll = poll;
	    this.mainAct = main;
	}

	@Override
	public void onClick(View v) {
	    mainAct.deletePoll(poll);
	}
    }

    private class OpenBrowserOnClickListener implements OnClickListener {

	Poll poll;
	MainActivity mainAct;

	public OpenBrowserOnClickListener(MainActivity main, Poll poll) {
	    super();
	    this.poll = poll;
	    this.mainAct = main;
	}

	@Override
	public void onClick(View v) {
	    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
		    Uri.parse("http://www.doodle-test.com/" + poll.getId()));

	    mainAct.startActivity(browserIntent);
	}

    }

}
