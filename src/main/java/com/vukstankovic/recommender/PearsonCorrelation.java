package com.vukstankovic.recommender;

import com.vukstankovic.professionalorientation.classes.User;

public class PearsonCorrelation implements RecommenderInterface{

	@Override
	public double calculateSimilarity(User u1, User u2) {
		double numerator = sumXY(u1, u2) - (sumPrefs(u1)*sumPrefs(u2)/14);
		double denominator = Math.sqrt((sumPrefsSquare(u1) - Math.pow(sumPrefs(u1), 2)/14) * (sumPrefsSquare(u2) - Math.pow(sumPrefs(u2), 2)/14));
		
		if(denominator == 0) return 0;
		return numerator/denominator;
	}

	@Override
	public double sumPrefs(User u) {
		return u.getPoljoprivreda()+
				u.getSumarstvo()+
				u.getGeodezija()+
				u.getMasinstvo()+
				u.getElektrotehnika()+
				u.getHemija()+
				u.getTekstil()+
				u.getSaobracaj()+
				u.getTrgovinaugostiteljstvo()+
				u.getEkonomijapravo()+
				u.getKultura()+
				u.getZdravstvo()+
				u.getUsluge();
	}

	@Override
	public double sumPrefsSquare(User u) {
		return Math.pow(u.getPoljoprivreda(), 2)+
				Math.pow(u.getSumarstvo(), 2)+
				Math.pow(u.getGeodezija(), 2)+
				Math.pow(u.getMasinstvo(), 2)+
				Math.pow(u.getElektrotehnika(), 2)+
				Math.pow(u.getHemija(), 2)+
				Math.pow(u.getTekstil(), 2)+
				Math.pow(u.getSaobracaj(), 2)+
				Math.pow(u.getTrgovinaugostiteljstvo(), 2)+
				Math.pow(u.getEkonomijapravo(), 2)+
				Math.pow(u.getKultura(), 2)+
				Math.pow(u.getZdravstvo(), 2)+
				Math.pow(u.getUsluge(), 2);
	}
	
	public double sumXY(User x, User y){
		return x.getPoljoprivreda()*y.getPoljoprivreda()+
				x.getSumarstvo()*y.getSumarstvo()+
				x.getGeodezija()*y.getGeodezija()+
				x.getMasinstvo()*y.getMasinstvo()+
				x.getElektrotehnika()*y.getElektrotehnika()+
				x.getHemija()*y.getHemija()+
				x.getTekstil()*y.getTekstil()+
				x.getSaobracaj()*y.getSaobracaj()+
				x.getTrgovinaugostiteljstvo()*y.getTrgovinaugostiteljstvo()+
				x.getEkonomijapravo()*y.getEkonomijapravo()+
				x.getKultura()*y.getKultura()+
				x.getZdravstvo()*y.getZdravstvo()+
				x.getUsluge()*y.getUsluge();
	}
	
	

}
