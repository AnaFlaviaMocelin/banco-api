package com.bancoApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContaJaExisteException extends RuntimeException {

  public ContaJaExisteException(String mensagem) {
    super(mensagem);
  }
}
