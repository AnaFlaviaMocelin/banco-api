package com.bancoApi.lancamentos;

import com.bancoApi.controller.ContaController;
import com.bancoApi.dto.LancamentoDTO;
import com.bancoApi.dto.SaldoDTO;
import com.bancoApi.model.Conta;
import com.bancoApi.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContaController.class)
public class ContaControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ContaService contaService;

  @InjectMocks
  private ContaController contaController;

  private Conta conta;

  @BeforeEach
  public void setUp() {
    conta = new Conta();
    conta.setId(1L);
    conta.setNumeroConta("123456");
    conta.setSaldo(new BigDecimal("5000.0"));
  }

  @Test
  void testCriarConta() throws Exception {
    Conta novaConta = new Conta();
    novaConta.setNumeroConta("654321");
    novaConta.setSaldo(new BigDecimal("3000.0"));

    when(contaService.criarConta(novaConta.getNumeroConta(), novaConta.getSaldo())).thenReturn(conta);

    mockMvc.perform(post("/api/contas")
    .contentType("application/json")
    .content("{\"numeroConta\": \"" + novaConta.getNumeroConta() + "\", \"saldoInicial\": " + novaConta.getSaldo() + "}"))
    .andExpect(status().isCreated())
    .andExpect(jsonPath("$.numeroConta").value(conta.getNumeroConta()))
    .andExpect(jsonPath("$.saldo").value(conta.getSaldo()));
  }

  @Test
  void testConsultarSaldoPorId() throws Exception {
    SaldoDTO saldoDTO = new SaldoDTO(conta.getSaldo());

    when(contaService.consultarSaldoPorId(conta.getId())).thenReturn(saldoDTO);

    mockMvc.perform(get("/api/contas/id/{contaId}/saldo", conta.getId()))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.saldo").value(conta.getSaldo()));
  }

  @Test
  void testRealizarLancamentos() throws Exception {
    LancamentoDTO lancamentoDTO = new LancamentoDTO();
    lancamentoDTO.setContaId(conta.getId());
    lancamentoDTO.setTipo("DEBITO");
    lancamentoDTO.setValor(new BigDecimal("200.0"));

    Conta contaAtualizada = new Conta();
    contaAtualizada.setId(conta.getId());
    contaAtualizada.setNumeroConta(conta.getNumeroConta());
    contaAtualizada.setSaldo(conta.getSaldo().subtract(lancamentoDTO.getValor()));

    when(contaService.realizarLancamentos(anyList())).thenReturn(contaAtualizada);

    mockMvc.perform(post("/api/contas/lancamentos")
    .contentType(MediaType.APPLICATION_JSON)
    .content("[{\"contaId\": " + lancamentoDTO.getContaId() + ", \"valor\": " + lancamentoDTO.getValor() + ", \"tipo\": \"" + lancamentoDTO.getTipo() + "\"}]")
    )
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.id").value(contaAtualizada.getId()))
    .andExpect(jsonPath("$.numeroConta").value(contaAtualizada.getNumeroConta()))
    .andExpect(jsonPath("$.saldo").value(contaAtualizada.getSaldo()));
  }
}
