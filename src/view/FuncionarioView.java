package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.FuncionarioController;

public class FuncionarioView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    
    public FuncionarioView() {
        FuncionarioController controller = new FuncionarioController(this);
        setTitle("Cadastro de Funcionários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        String[] columns = {"ID", "Nome", "Data Admissão", "Salário", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField nomeField = new JTextField();
        JTextField dataField = new JTextField();
        JTextField salarioField = new JTextField();
        JCheckBox statusCheck = new JCheckBox("Ativo", true);
        
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Data Admissão (YYYY-MM-DD):"));
        formPanel.add(dataField);
        formPanel.add(new JLabel("Salário:"));
        formPanel.add(salarioField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusCheck);
        
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText();
            String data = dataField.getText();
            String salario = salarioField.getText();
            boolean status = statusCheck.isSelected();
        
            controller.adicionarFuncionario(nome, data, salario, status);
        }
        });
        
        formPanel.add(addButton);
        
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
    }
    
    public void init() {
    FuncionarioController controller = new FuncionarioController(this);
    controller.carregarFuncionarios();
    }
    
    public JTable getTable() {
    if (table == null) {
        table = new JTable(); 
        
    }
    return table;
}
}