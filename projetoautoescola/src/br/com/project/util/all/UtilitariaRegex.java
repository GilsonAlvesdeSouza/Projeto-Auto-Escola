package br.com.project.util.all;

public class UtilitariaRegex {

	public String retiraAcentos(String string) {
		String aux = new String(string);
		aux = aux.replaceAll("[èéêëÈÉÊË]", "e");
		aux = aux.replaceAll("[ûùüúÛÚÙÜ]", "u");
		aux = aux.replaceAll("[ïîíìÏÎÍÌ]", "i");
		aux = aux.replaceAll("[àáâäãÀÁÂÃÄ]", "a");
		aux = aux.replaceAll("[óòõöÓÒÕÖ]", "o");
		aux = aux.replaceAll("[çÇ]", "c");
		return aux;
	}

}
