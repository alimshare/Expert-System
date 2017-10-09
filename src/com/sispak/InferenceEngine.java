package com.sispak;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sispak.entity.Gejala;
import com.sispak.entity.Masalah;

/**
 * Inference Engine 
 * <p>
 * 	Class untuk menangani proses <em>Expert System</em> (Sistem Pakar)
 * </p>
 * @author abdullah.alim
 *
 */
public class InferenceEngine {

	KnowlegeBase knowledgeBase = new KnowlegeBase();
	List<Masalah> list_masalah = new ArrayList<Masalah>();

	List<Gejala> list_yes = new ArrayList<Gejala>();
	List<Gejala> list_no = new ArrayList<Gejala>();

	Masalah masalah = new Masalah();
	Gejala gejala = new Gejala();
	Masalah perkiraan = new Masalah();

	int index_masalah = 0;
	int index_gejala = 0;

	boolean last_masalah = false;
	boolean last_gejala = false;

	public InferenceEngine() {
		list_masalah = knowledgeBase.getMasalah();
		masalah = list_masalah.get(index_masalah);
		gejala = masalah.getGejala().get(index_gejala);
	}

	/**
	 * Proses untuk jawaban <strong>YES</strong>
	 */
	public void yes() {
		list_yes.add(gejala);

		if (ketemu()) {
			perkiraan = masalah;
		}

		index_gejala++;
		nextGejala();
	}

	/**
	 * Proses untuk jawaban <strong>NO</strong>
	 */
	public void no() {
		list_no.add(gejala);
		index_masalah++;
		nextMasalah();
	}

	/**
	 * Proses untuk mendapatkan masalah selanjutnya yang akan di proses
	 */
	public void nextMasalah() {
		if (index_masalah <= list_masalah.size() - 1) {
			masalah = list_masalah.get(index_masalah);
			index_gejala = 0;
			nextGejala();
		} else {
			last_masalah = true;
			last_gejala = true;
		}
	}

	/**
	 * Proses untuk mendapatkan gejala selanjutnya yang akan di proses
	 */
	public void nextGejala() {
		if (index_gejala <= list_masalah.get(index_masalah).getGejala().size() - 1) {
			gejala = masalah.getGejala().get(index_gejala);
			if (isGejalaExist(list_no, gejala)) { // sudah pernah dijawab NO
				index_masalah++;
				nextMasalah(); // skip masalah
			}

			if (isGejalaExist(list_yes, gejala)) { // sudah pernah dijawab YES
				index_gejala++;
				nextGejala(); // skip gejala
			}
		} else {
			last_gejala = true;

			if (ketemu()) {
				perkiraan = masalah;
			}

			index_masalah++;
			nextMasalah();
		}
	}

	/**
	 * Cek apakah solusi ditemukan ? 
	 * @return
	 */
	public boolean ketemu() {
		if (equalsWithoutOrder(list_yes, masalah.getGejala())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get Results of expert system
	 * @return
	 */
	public Masalah getPerkiraan() {
		return perkiraan;
	}

	/**
	 * Get Pertanyaan
	 * @return
	 */
	public String getQuestion() {
		return gejala.getPertanyaan();
	}

	/**
	 * Cek apakah Gejala ada di dalam List Jawaban (YES/NO)
	 * @param list
	 * @param obj
	 * @return
	 */
	public <T> boolean isGejalaExist(List<T> list, T obj) {
		for (T gejala : list) {
			if (gejala.toString().equals(obj.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Cek apakah proses sedang ada di masalah terakhir
	 * @return
	 */
	public boolean isLastMasalah() {
		return last_masalah;
	}

	/**
	 * Cek apakah proses sedang ada di gejala terakhir
	 * @return
	 */
	public boolean isLastGejala() {
		return last_gejala;
	}

	/**
	 * Compare 2 ArrayList Object <br>
	 * answered by Chalkos in Stackoverflow
	 * @since 2015-08-13 00:07:33Z <br>
	 * {@link <a href="https://stackoverflow.com/questions/13501142/java-arraylist-how-can-i-tell-if-two-lists-are-equal-order-not-mattering">source</a>}
	 * @param fst
	 * @param snd
	 * @return
	 */
	public <T> boolean equalsWithoutOrder(List<T> fst, List<T> snd) {
		if (fst != null && snd != null) {
			if (fst.size() == snd.size()) {
				// create copied lists so the original list is not modified
				List<T> cfst = new ArrayList<T>(fst);
				List<T> csnd = new ArrayList<T>(snd);

				Iterator<T> ifst = cfst.iterator();
				boolean foundEqualObject;
				while (ifst.hasNext()) {
					Iterator<T> isnd = csnd.iterator();
					foundEqualObject = false;
					String strcompare = ifst.next().toString();
					while (isnd.hasNext()) {
						// modified the original function with compare 2 object after convert it to String object
						if (strcompare.equals(isnd.next().toString())) { 
							ifst.remove();
							isnd.remove();
							foundEqualObject = true;
							break;
						}
					}

					if (!foundEqualObject) {
						// fail early
						break;
					}
				}
				if (cfst.isEmpty()) { // both temporary lists have the same size
					return true;
				}
			}
		} else if (fst == null && snd == null) {
			return true;
		}
		return false;
	}

}
