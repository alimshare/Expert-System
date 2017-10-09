package com.sispak.entity;

import java.util.List;

public class Masalah {
	
	private int id;
	private String nama;
	private String penyebab;
	private String solusi;
	
	private List<Gejala> gejala;
	
	public int getId() {
		return id;
	}
	public void setId( int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public List<Gejala> getGejala() {
		return gejala;
	}
	public void setGejala(List<Gejala> gejala) {
		this.gejala = gejala;
	}
	public String getPenyebab() {
		return penyebab;
	}
	public void setPenyebab(String penyebab) {
		this.penyebab = penyebab;
	}
	public String getSolusi() {
		return solusi;
	}
	public void setSolusi(String solusi) {
		this.solusi = solusi;
	}
	@Override
	public String toString() {
		return "Masalah [id=" + id + ", nama=" + nama + ", penyebab=" + penyebab + ", solusi=" + solusi + "]";
	}
	
}
