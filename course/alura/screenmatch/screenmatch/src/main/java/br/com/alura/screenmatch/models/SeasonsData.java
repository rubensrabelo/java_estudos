package br.com.alura.screenmatch.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonsData(
							@JsonAlias("Season") Integer number,
							@JsonAlias("Episodes") List<EpisodeData> episodes
						) {
}
