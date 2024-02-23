package com.example.demo;

import java.io.FileInputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

	@RestController
	public class Controller {

	    @PostMapping("/translate")
	    public Response translateText(@RequestBody Request request) {
	        try {
	            Translate translateWithCredentials = TranslateOptions.newBuilder()
	                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("path/to/your/service-account-key.json")))
	                    .build()
	                    .getService();

	            Translation translation = translateWithCredentials.translate(request.getText(),
	                    Translate.TranslateOption.sourceLanguage("en"),
	                    Translate.TranslateOption.targetLanguage("fr"));

	            return new Response(translation.getTranslatedText());
	        } catch (Exception e) {
	            return new Response("Translation error: " + e.getMessage());
	        }
	    }
	}
