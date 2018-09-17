package me.dodocarlos.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Weather implements Listener{

	@EventHandler
	public void tempo(WeatherChangeEvent e){
		if(e.toWeatherState()){
			e.setCancelled(true);
		}
	}

}
