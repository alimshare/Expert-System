package com.sispak.io;

import java.util.ArrayList;
import java.util.Scanner;

import com.sispak.InferenceEngine;
import com.sispak.entity.Masalah;

public class MainApp {
	
	InferenceEngine inferenceEngine;
	
	public MainApp() {
		inferenceEngine = new InferenceEngine();
	}
	
	public void proses() {
		
		while (true) {
	
			System.out.println(inferenceEngine.getQuestion());
			
			Scanner input = new Scanner(System.in);
			System.out.print("[YES/NO] : \t"); 
			String jawaban = input.nextLine();
			
			this.prosesJawaban(jawaban);
						
			if (inferenceEngine.isLastMasalah()==true && inferenceEngine.isLastGejala()==true) {
				
				if (inferenceEngine.getPerkiraan().toString().equals(new Masalah().toString())) {					
					System.out.println("==============================================");
					System.out.println("Masalah tidak ditemukan");
					break;
					
				} else {
					Masalah masalah = inferenceEngine.getPerkiraan();
					
					System.out.println("==============================================");
					System.out.println("Terdeteksi : "+masalah.getNama());
					System.out.println("Penyebab : "+masalah.getPenyebab());
					System.out.println("Solusi : "+masalah.getSolusi());
					
					break;					
				}
			}
			
			
		}
		
	}
	
	public void prosesJawaban(String jawaban) {
		if (jawaban.equalsIgnoreCase("yes")) {
			inferenceEngine.yes();
		} else if (jawaban.equalsIgnoreCase("no")) {
			inferenceEngine.no();
		} else {
			
			System.out.println("Harap hanya memberikan jawaban [YES/NO] saja");
			System.out.print("[YES/NO] : \t");
			
			Scanner input = new Scanner(System.in);
			jawaban = input.nextLine();
			this.prosesJawaban(jawaban);
			
		}
	}
	
	public boolean solusi() {
		return false;
	}
	
	public static void main(String[] args) {
		
		System.out.println("===== Selamat Datang di Aplikasi Sistem Pakar =====");
		
		MainApp app = new MainApp();
		app.proses();

		System.out.println("===== Terima Kasih =====");	
		
	}

}
