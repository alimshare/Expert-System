package com.sispak;

import java.util.ArrayList;
import java.util.List;

import com.sispak.dao.GejalaDAO;
import com.sispak.dao.MasalahDAO;
import com.sispak.entity.Gejala;
import com.sispak.entity.Masalah;

public class KnowlegeBase {
	
	MasalahDAO masalahDao 	= new MasalahDAO();
	List<Masalah> masalah = new ArrayList<Masalah>();
	
	public KnowlegeBase() {
		
	}
	
	public List<Masalah> getMasalah(){
		return masalahDao.all();
	}
	
	
	public static void main(String[] args) {
		
		KnowlegeBase obj = new KnowlegeBase();
		List<Masalah> list = obj.getMasalah();
		for (Masalah masalah : list) {
			
			System.out.println(masalah.toString());
			List<Gejala> listGejala = masalah.getGejala();
			for (Gejala gejala : listGejala) {
				System.out.println(gejala.toString());
			}			
			System.out.println("================================");
		}
		
		
	}
	
	
}
