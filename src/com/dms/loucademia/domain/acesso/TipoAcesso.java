package com.dms.loucademia.domain.acesso;

public enum TipoAcesso {

	ENTRADA {
		@Override
		public String obterMensagem() {
			return "Entrada registrada!";
		}
	},
	SAIDA {
		@Override
		public String obterMensagem() {
			return "Saída registrada!";
		}
	};

	public abstract String obterMensagem();
}
