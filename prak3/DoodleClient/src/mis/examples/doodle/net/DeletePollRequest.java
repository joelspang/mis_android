package mis.examples.doodle.net;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import mis.examples.doodle.activities.MainActivity;
import mis.examples.doodle.model.Poll;
import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class DeletePollRequest extends SpringAndroidSpiceRequest<Poll> {
	
	private Poll poll;
	
	public DeletePollRequest(Poll poll) {
		super(Poll.class);	
		this.poll = poll;
	}

	@Override
	public Poll loadDataFromNetwork() throws Exception {
		Log.d( "request", "sending delete request" );		
		
		try {
			RestTemplate t = getRestTemplate();
			
			// Add some message converters to interpret the response
	        SimpleXmlHttpMessageConverter simpleXmlHttpMessageConverter = new SimpleXmlHttpMessageConverter();
	        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
	        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
	        final List<HttpMessageConverter<?>> listHttpMessageConverters = t.getMessageConverters();

	        listHttpMessageConverters.add(simpleXmlHttpMessageConverter);
	        listHttpMessageConverters.add(formHttpMessageConverter);
	        listHttpMessageConverters.add(stringHttpMessageConverter);
	        t.setMessageConverters(listHttpMessageConverters);			
			
			t.delete( MainActivity.APIUrl + "polls", this.poll, String.class);
			
		} catch (HttpStatusCodeException e) {
			
			System.out.println(e.getStatusText());
		}
		
		return null;
	}

}
