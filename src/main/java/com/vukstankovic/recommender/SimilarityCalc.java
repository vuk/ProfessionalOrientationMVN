package com.vukstankovic.recommender;

import com.vukstankovic.professionalorientation.classes.User;

public class SimilarityCalc {
	
	public double average(User u){
		return (u.getPoljoprivreda()+
				u.getSumarstvo()+
				u.getGeodezija()+
				u.getMasinstvo()+
				u.getElektrotehnika()+
				u.getHemija()+
				u.getTekstil()+
				u.getSaobracaj()+
				u.getTrgovinaugostiteljstvo()+
				u.getEkonomijapravo()+
				u.getHidrometeorologija()+
				u.getKultura()+
				u.getZdravstvo()+
				u.getUsluge())/14;
	}
	
	public double numerator(User x, User y){
		double avgx = average(x);
		double avgy = average(y);
		
		double numerator = 
				(x.getPoljoprivreda()-avgx)*(y.getPoljoprivreda() - avgy)+
				(x.getSumarstvo()-avgx)*(y.getSumarstvo() - avgy)+
				(x.getGeodezija()-avgx)*(y.getGeodezija() - avgy)+
				(x.getMasinstvo()-avgx)*(y.getMasinstvo() - avgy)+
				(x.getElektrotehnika()-avgx)*(y.getElektrotehnika() - avgy)+
				(x.getHemija()-avgx)*(y.getHemija() - avgy)+
				(x.getTekstil()-avgx)*(y.getTekstil() - avgy)+
				(x.getSaobracaj()-avgx)*(y.getSaobracaj() - avgy)+
				(x.getTrgovinaugostiteljstvo()-avgx)*(y.getTrgovinaugostiteljstvo() - avgy)+
				(x.getEkonomijapravo()-avgx)*(y.getEkonomijapravo() - avgy)+
				(x.getHidrometeorologija()-avgx)*(y.getHidrometeorologija() - avgy)+
				(x.getKultura()-avgx)*(y.getKultura() - avgy)+
				(x.getZdravstvo()-avgx)*(y.getZdravstvo() - avgy)+
				(x.getUsluge()-avgx)*(y.getUsluge() - avgy);
		
		return numerator;
	}
	
	public double denominator(User x, User y){
		double avgx = average(x);
		double avgy = average(y);
		
		double denominator = 0;
		
		denominator = 
				(Math.pow(x.getPoljoprivreda() - avgx, 2)+
				Math.pow(x.getSumarstvo() - avgx, 2)+
				Math.pow(x.getGeodezija() - avgx, 2)+
				Math.pow(x.getMasinstvo() - avgx, 2)+
				Math.pow(x.getElektrotehnika() - avgx, 2)+
				Math.pow(x.getHemija() - avgx, 2)+
				Math.pow(x.getTekstil() - avgx, 2)+
				Math.pow(x.getSaobracaj() - avgx, 2)+
				Math.pow(x.getTrgovinaugostiteljstvo() - avgx, 2)+
				Math.pow(x.getEkonomijapravo() - avgx, 2)+
				Math.pow(x.getHidrometeorologija() - avgx, 2)+
				Math.pow(x.getKultura() - avgx, 2)+
				Math.pow(x.getZdravstvo() - avgx, 2))*
				(Math.pow(y.getPoljoprivreda() - avgy, 2)+
				Math.pow(y.getSumarstvo() - avgy, 2)+
				Math.pow(y.getGeodezija() - avgy, 2)+
				Math.pow(y.getMasinstvo() - avgy, 2)+
				Math.pow(y.getElektrotehnika() - avgy, 2)+
				Math.pow(y.getHemija() - avgy, 2)+
				Math.pow(y.getTekstil() - avgy, 2)+
				Math.pow(y.getSaobracaj() - avgy, 2)+
				Math.pow(y.getTrgovinaugostiteljstvo() - avgy, 2)+
				Math.pow(y.getEkonomijapravo() - avgy, 2)+
				Math.pow(y.getHidrometeorologija() - avgy, 2)+
				Math.pow(y.getKultura() - avgy, 2)+
				Math.pow(y.getZdravstvo() - avgy, 2));
		
		return Math.sqrt(denominator);
	}
	
	public double similarity(User x, User y){
		return numerator(x, y)/denominator(x, y);
	}

}
