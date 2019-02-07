package com.dms.loucademia.application.util;

/**
 * Classe utilitária.
 * 
 * @author Carlos Tosin
 *
 *         <br>
 *         Escrita e documentada por Diorgenes Morais
 */
public class StringUtils {

	/**
	 * Verifica se o parâmetro informado é vázio.
	 * 
	 * @param value uma string
	 * @return {@code true} se a string informada for {@code null} ou que só
	 *         contenha espaços.
	 */
	public static boolean isEmpty(final String value) {
		return value == null || value.trim().length() == 0;
	}

	/**
	 * Formata um número em uma string de tamanho definido no parâmetro.
	 * 
	 * <pre>
	 * Exemplo:
	 * value: 100
	 * finalSize: 5
	 * Retorna: 00100
	 * </pre>
	 * 
	 * @param value     número que será formatado
	 * @param finalSize define o tamanho da string, contando com o {@code value}
	 *                  informado.
	 * @return um número na forma de string com uma quantidade de zeros a esquerda.
	 */
	public static String leftZeroes(final int value, int finalSize) {
		return String.format("%0" + finalSize + "d", value);
	}
}
