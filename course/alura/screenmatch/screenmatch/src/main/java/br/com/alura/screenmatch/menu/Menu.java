package br.com.alura.screenmatch.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.models.SeriesData;
import br.com.alura.screenmatch.services.ConsumeApi;
import br.com.alura.screenmatch.services.DataConverter;

public class Menu {

	private final String ADDRESS = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=";
	
	private Scanner input = new Scanner(System.in);
	private ConsumeApi consume = new ConsumeApi();
	private DataConverter converter = new DataConverter();

	private List<SeriesData> seriesData = new ArrayList<>();

	public void displayMenu() {
		var option = -1;
		
		while(option != 0) {
			var menu = """
					1 - Search series
					2 - Search episodes
					3 - List searched series
					
					0 - Quit
					""";
			
			System.out.println(menu);
			option = input.nextInt();
			input.nextLine();
			
			switch (option) {
            case 1:
                searchSerieWeb();
                break;
            case 2:
                searchEpisodeBySerie();
                break;
            case 3:
                listSeriesSearched();
                break;
            case 0:
                System.out.println("Quint...");
                break;
            default:
                System.out.println("Invlaid option");
        }
		}
	}

	private void searchSerieWeb() {
		SeriesData datas = getDataSerie();
		seriesData.add(datas);
		System.out.println(datas);
	}

	private SeriesData getDataSerie() {
		System.out.println("Insert series name searched");
		var nameSerie = input.nextLine();
		var json = consume.getData(ADDRESS + nameSerie.replace(" ", "+") + API_KEY);
		SeriesData datas = converter.getData(json, SeriesData.class);
		return datas;
	}

	private void searchEpisodeBySerie() {
		
	}

	private void listSeriesSearched() {
		// TODO Auto-generated method stub
		
	}


}
