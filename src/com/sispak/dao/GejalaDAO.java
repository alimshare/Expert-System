package com.sispak.dao;

import com.sispak.entity.Gejala;
import com.sispak.util.Koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GejalaDAO {
		
	public List<Gejala> getGejalaByMasalah(int id){

		String SQL = "SELECT T1.* "
				+ "FROM gejala T1 INNER JOIN mapping_gejala T2 ON T2.id_gejala = T1.id "
				+ "WHERE id_masalah ="+id+" ORDER BY T2.id ";
		
		Koneksi objKoneksi = new Koneksi();
		Connection conn = objKoneksi.openConnection();
		
		List<Gejala> listGejala = new ArrayList<>();
		
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {
				Gejala gejala = new Gejala();
				gejala.setId(rs.getInt("id"));
				gejala.setNama(rs.getString("nama").toString());
				gejala.setPertanyaan(rs.getString("pertanyaan").toString());
				
				listGejala.add(gejala);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listGejala;
	}
	
}
