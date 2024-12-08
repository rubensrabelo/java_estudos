package br.com.alura.screenmatch.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ChatGPTQuery {
	
	public static String getTranslation(String text) {
		OpenAiService service = new OpenAiService("paste your key here");
		
		CompletionRequest request = CompletionRequest.builder()
				.model("gpt-3.5-turbo-instruct")
				.prompt("traduza para o português o texto: " + text)
				.maxTokens(1000)
				.temperature(0.7)
				.build();
		
		var response = service.createCompletion(request);
		return response.getChoices().get(0).getText();
	}
}
