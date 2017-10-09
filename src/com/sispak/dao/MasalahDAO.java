package com.sispak.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sispak.entity.Gejala;
import com.sispak.entity.Masalah;
import com.sispak.util.Koneksi;

public class MasalahDAO {

	GejalaDAO gejalaDao 	= new GejalaDAO();
	
	public List<Masalah> all (){
		String SQL = "SELECT * FROM view_jumlah_gejala ORDER BY jumlah_gejala desc ";
		return mapping_objek(SQL);		
	}
	
	public List<Masalah> all_possible(List<Gejala> list_no){ // mengambil semua masalah yang mungkin saja
		
		String str_id_gejala = "";
		for (Gejala gejala2 : list_no) {
			str_id_gejala += "," + gejala2.getId();  
		}
		str_id_gejala = (str_id_gejala.length()>0) ? str_id_gejala.substring(1) : "";
		
		if (str_id_gejala.equals("")) {
			return all();
		} else {			
			String SQL = "select masalah.* from mapping_gejala INNER JOIN masalah ON masalah.id=mapping_gejala.id_masalah "
					+ "where id_masalah not in ( " + 
					"	select distinct id_masalah from mapping_gejala where id_gejala in ("+ str_id_gejala +") " + 
					")";
			return mapping_objek(SQL);			
		}
	}
	
	private List<Masalah> mapping_objek(String SQL) {
		Koneksi objKoneksi = new Koneksi();
		Connection conn = objKoneksi.openConnection();
		
		List<Masalah> listMasalah = new ArrayList<>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			
			while (rs.next()) {
				Masalah masalah = new Masalah();
				masalah.setId(rs.getInt("id"));
				masalah.setNama(rs.getString("nama").toString());
				masalah.setPenyebab(rs.getString("penyebab").toString());
				masalah.setSolusi(rs.getString("solusi").toString());
				
				List<Gejala> listGejala = new ArrayList<Gejala>();
				listGejala = gejalaDao.getGejalaByMasalah(rs.getInt("id"));
				masalah.setGejala(listGejala);
				
				listMasalah.add(masalah);
			}
			
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listMasalah;
	}
	
}
