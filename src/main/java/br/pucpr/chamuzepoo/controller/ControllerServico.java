package br.pucpr.chamuzepoo.controller;
import br.pucpr.chamuzepoo.model.Servico;
import br.pucpr.chamuzepoo.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class ControllerServico {
    private static final String CAMINHO_ARQUIVO = "servicos.dat";
    private Servico servico;
    public ControllerServico(Servico servico) {
        this.servico = servico;
    }

    public static void salvarLista(ArrayList<Servico> servico) {
        try {
            File arq = new File(CAMINHO_ARQUIVO);
            if (!(arq.exists())) {
                arq.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq));
            oos.writeObject(servico);
            oos.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }
    public static ArrayList<Servico> lerLista() {
        ArrayList<Servico> servicos = new ArrayList<>();
        try  {
            File arq = new File(CAMINHO_ARQUIVO);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq));
                servicos = (ArrayList<Servico>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return servicos;
    }

    // Adiciona uma nova pessoa à lista e regrava o arquivo
    public void solicitarServico() {
        ArrayList<Servico> servicos = lerLista();
        servico.setId(gerarId(servicos));
        servicos.add(this.servico);
        salvarLista(servicos);
    }

    public boolean verificarIdExiste(UUID idGerado, ArrayList<Servico> servicos){
        for(Servico servico: servicos){
            if ((servico.getId()).equals(idGerado)) {
                return true;
            }
        }
        return false;
    }

    public UUID gerarId(ArrayList<Servico> servicos){
        UUID id = UUID.randomUUID();
        while(servico.getId() != null && verificarIdExiste(id, servicos)){
            id = UUID.randomUUID();
        }
        return id;
    }

    public static void excluirServico(String id) {
        ArrayList<Servico> servicos = lerLista();
        Iterator<Servico> it = servicos.iterator();

        while(it.hasNext()) {
            Servico servico = it.next();
            if(((servico.getId()).toString()).equals(id)){
                it.remove();
                salvarLista(servicos);
            }
        }

    }

    public static boolean editarServicos(String id, String descricao, String titulo, br.pucpr.chamuzejava.file.Categoria categoria, br.pucpr.chamuzejava.file.LocalServico localServico, String preco){
        ArrayList<Servico> servicos = lerLista();
        Iterator<Servico> it = servicos.iterator();

        while(it.hasNext()) {
            Servico servico = it.next();
            if(((servico.getId()).toString()).equals(id)){

                if (descricao == null && titulo == null && categoria == null && localServico == null && preco == null) {
                    return false;
                }
                if(descricao != null){
                    servico.setDescricao(descricao);
                }
                if (titulo != null){
                    ;
                    servico.setTitulo(titulo);
                }
                if (categoria != null) {
                    servico.setCategoria(categoria);
                }
                if (localServico != null) {
                    servico.setLocalServico(localServico);
                }
                if (preco != null) {
                    double precoDouble = Double.parseDouble(preco);
                    servico.setPreco(precoDouble);
                }

                salvarLista(servicos);
                return true;
            }

        }
        return false;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}

