package mis.examples.doodle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import mis.examples.doodle.model.Initiator;
import mis.examples.doodle.model.Poll;
import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class CreatePollRequest extends SpringAndroidSpiceRequest<Poll> {
	
	private Poll poll;
	
	public CreatePollRequest(Poll poll) {
		super(Poll.class);	
		this.poll = poll;
	}

	@Override
	public Poll loadDataFromNetwork() throws Exception {
		Log.d( "request", "sending post request" );		
		
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
			
			ResponseEntity<String> entity = t.postForEntity( MainActivity.APIUrl + "polls", this.poll, String.class);
						
			try {				
				HttpHeaders headers = entity.getHeaders();
				
				// For newly created polls, we need the following information
				String pollId = headers.get("Content-Location").get(0);
				String doodleKey = headers.get("X-DoodleKey").get(0);

				// Update poll information
				this.poll.setId(pollId);
				this.poll.setDoodleKey(doodleKey);
								
			} catch (Exception e) {
				System.out.println(e);
			}			
			
			return this.poll;
			
		} catch (HttpStatusCodeException e) {
			
			System.out.println(e.getStatusText());
		}
		
		return null;
	}

}
