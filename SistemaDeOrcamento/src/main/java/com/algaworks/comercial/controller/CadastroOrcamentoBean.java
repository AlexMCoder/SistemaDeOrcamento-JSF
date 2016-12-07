package com.algaworks.comercial.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.comercial.model.Orcamento;
import com.algaworks.comercial.model.OrcamentoItem;
import com.algaworks.comercial.service.GestaoOrcamentos;

@Named
@ViewScoped
public class CadastroOrcamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	//injetando o serviço de gestão do orçamento
	@Inject
	private GestaoOrcamentos gestaoOrcamentos;
	
	//Instanciando a classe Orçamento, essa é a nossa entidade
	private Orcamento orcamento = new Orcamento();
	
	private OrcamentoItem item;
	
	
	/*
	 * 
	 * Quando clicar no botão de Novo item, iremos instanciar um tipo de OrcamentoItem
	 * com isso o item adicionado no método adicionarItem já não vai estar nulo, na hora que clicar em adicionar item
	 */
	public void novoItem() {
		item = new OrcamentoItem();
	}
	
	
	/*
	 *Adicionando um item no pedido.
	 *Acessamos o orcamento da classe Orcamento, pegando o getItens que é uma lista de OrcamentoItem
	 *Nesse caso não precisamos instanciar ela aqui, pois ela já está sendo instanciada dentro de Orcamento
	 *e em seguida adicionamos um novo item, salvando dentro de orcamento
	 */
	public void adicionarItem() {
		orcamento.getItens().add(item);
		item.setOrcamento(orcamento);
	}
	
	/*
	 *Método de salvar o orçamento 
	 */
	public void salvar() {
		gestaoOrcamentos.salvar(orcamento);
		
		//quando instanciamos o orçamento, ele limpa o formulário após carregar.
		orcamento = new Orcamento();
		
		//mensagem caso o orçamento seja gravado corretamente.
		FacesMessage msg = new FacesMessage("Orçamento salvo com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public Orcamento getOrcamento() {
		return orcamento;
	}

	public OrcamentoItem getItem() {
		return item;
	}
	
}