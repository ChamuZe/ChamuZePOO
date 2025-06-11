package br.pucpr.chamuzepoo.controller;
import br.pucpr.chamuzepoo.model.Servico;

import java.io.*;
import java.util.ArrayList;
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
        servicos.add(this.servico);
        salvarLista(servicos);
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}

