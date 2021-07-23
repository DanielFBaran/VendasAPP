package com.vendas.api.utils;
 
public class ExceptionUtil extends Exception {
 
   	private static final long serialVersionUID = 1L;
 
   	private String mensagem;
   	
   	public ExceptionUtil(String mensagem) {
         	this.mensagem = mensagem;
   	}
   	
   	public ExceptionUtil(String format, Object... args) {
         	
         	this.mensagem = format;
         	
         	for (Object object : args) {
                	
                	if (object != null)
                       	mensagem = mensagem.replaceFirst("\\{\\}", object.toString());
                	
         	}
         	
   	}
 
   	public String getMensagem() {
         	return mensagem;
   	}

   	public void setMensagem(String mensagem) {
         	this.mensagem = mensagem;
   	}
   	
}
