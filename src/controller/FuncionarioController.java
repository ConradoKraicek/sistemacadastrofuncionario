package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import view.FuncionarioView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FuncionarioController {
    private FuncionarioView view;
    private FuncionarioDAO dao;
    
    public FuncionarioController(FuncionarioView view) {
        this.view = view;
        this.dao = new FuncionarioDAO();
        carregarFuncionarios();
        
    }
    
    public void carregarFuncionarios() {
        List<Funcionario> funcionarios = dao.listarFuncionarios();
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
        model.setRowCount(0); 
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Funcionario f : funcionarios) {
            model.addRow(new Object[]{
                f.getId(),
                f.getNome(),
                sdf.format(f.getDataAdmissao()),
                String.format("R$ %.2f", f.getSalario()),
                f.isStatus() ? "Ativo" : "Inativo"
            });
        }
    }
    
    public boolean adicionarFuncionario(String nome, String dataStr, String salarioStr, boolean status) {
        if (nome.isEmpty() || dataStr.isEmpty() || salarioStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos");
            return false;
        }
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data = sdf.parse(dataStr);
            double salario = Double.parseDouble(salarioStr);
            
            Funcionario f = new Funcionario();
            f.setNome(nome);
            f.setDataAdmissao(data);
            f.setSalario(salario);
            f.setStatus(status);
            
            boolean sucesso = dao.adicionarFuncionario(f);
            if (sucesso) {
                carregarFuncionarios();
                JOptionPane.showMessageDialog(view, "Funcionário adicionado com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao adicionar funcionário");
                return false;
            }
        } catch (ParseException | NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Formato inválido nos campos");
            return false;
        }
    }
}