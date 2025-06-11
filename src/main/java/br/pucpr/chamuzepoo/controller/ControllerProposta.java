package br.pucpr.chamuzepoo.controller;

import br.pucpr.chamuzepoo.model.Proposta;

import java.io.*;
import java.util.ArrayList;

public class ControllerProposta {
    private final String CAMINHO_ARQUIVO = "propostas.dat";

    public void cadastrarProposta(Proposta proposta) {
        ArrayList<Proposta> propostas = lerPropostas();

        propostas.add(proposta);
        salvarPropostas(propostas);
    }

    public ArrayList<Proposta> lerPropostas() {
        ArrayList<Proposta> propostas = new ArrayList<>();
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if(arquivo.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
                propostas = (ArrayList<Proposta>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler propostas: " + e.getMessage());
        }
        return propostas;
    }

    private void salvarPropostas(ArrayList<Proposta> propostas) {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if(!arquivo.exists()) {
                arquivo.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(propostas);
            oos.close();
        } catch (Exception e) {
            System.err.println("Erro ao salvar propostas: " + e.getMessage());
        }
    }
}