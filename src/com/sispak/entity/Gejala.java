package com.sispak.entity;

public class Gejala {
	
	private int id;
	private String nama;
	private String pertanyaan;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}	
	public String getPertanyaan() {
		return pertanyaan;
	}
	public void setPertanyaan(String pertanyaan) {
		this.pertanyaan = pertanyaan;
	}
	@Override
	public String toString() {
		return "Gejala [id=" + id + ", nama=" + nama + ", pertanyaan=" + pertanyaan + "]";
	}
	
}
