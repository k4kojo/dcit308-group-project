import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class PharmacyManagementSystemGUI extends JFrame {
    private PharmacyManagementSystem pharmacySystem;
    private JTable drugsTable;
    private DefaultTableModel drugsTableModel;
    private JTable searchResultsTable;
    private DefaultTableModel searchResultsTableModel;

    public PharmacyManagementSystemGUI() {
        pharmacySystem = new PharmacyManagementSystem();
        initUI();
    }

    private void initUI() {
        setTitle("Pharmacy Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel to hold addDrugPanel and addDrugButtonPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Add Drug Panel
        JPanel addDrugPanel = new JPanel();
        addDrugPanel.setBackground(Color.LIGHT_GRAY);
        addDrugPanel.setLayout(new GridLayout(6, 2, 5, 5));

        JTextField drugNameField = new JTextField();
        JTextField drugCodeField = new JTextField();
        JTextField manDateField = new JTextField();
        JTextField expDateField = new JTextField();
        JTextField supplierNameField = new JTextField();
        JTextField supplierLocationField = new JTextField();

        JLabel drugNameLabel = new JLabel("Drug Name:", JLabel.CENTER);
        JLabel drugCodeLabel = new JLabel("Drug Code:", JLabel.CENTER);
        JLabel manDateLabel = new JLabel("Manufacture Date:", JLabel.CENTER);
        JLabel expDateLabel = new JLabel("Expiry Date:", JLabel.CENTER);
        JLabel supplierNameLabel = new JLabel("Supplier Name:", JLabel.CENTER);
        JLabel supplierLocationLabel = new JLabel("Supplier Location:", JLabel.CENTER);

        addDrugPanel.add(drugNameLabel);
        addDrugPanel.add(drugNameField);
        addDrugPanel.add(drugCodeLabel);
        addDrugPanel.add(drugCodeField);
        addDrugPanel.add(manDateLabel);
        addDrugPanel.add(manDateField);
        addDrugPanel.add(expDateLabel);
        addDrugPanel.add(expDateField);
        addDrugPanel.add(supplierNameLabel);
        addDrugPanel.add(supplierNameField);
        addDrugPanel.add(supplierLocationLabel);
        addDrugPanel.add(supplierLocationField);

        // Add Drug Button Panel
        JPanel addDrugButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addDrugButton = new JButton("Add Drug");
        addDrugButton.setPreferredSize(new Dimension(500, 30));
        addDrugButtonPanel.add(addDrugButton, BorderLayout.CENTER);

        // Add addDrugPanel and addDrugButtonPanel to mainPanel
        mainPanel.add(addDrugPanel, BorderLayout.CENTER);
        mainPanel.add(addDrugButtonPanel, BorderLayout.SOUTH);

        // Create and configure tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Drug", mainPanel);

        // Search Drug Panel
        JPanel searchDrugPanel = new JPanel();
        searchDrugPanel.setLayout(new BorderLayout());

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(200, 30));

        JPanel searchInputPanel = new JPanel(new BorderLayout());
        searchInputPanel.add(searchField, BorderLayout.CENTER);
        searchInputPanel.add(searchButton, BorderLayout.EAST);

        searchDrugPanel.add(searchInputPanel, BorderLayout.NORTH);

        searchResultsTableModel = new DefaultTableModel(new Object[]{"Drug Name", "Drug Code", "Supplier Name", "Supplier Location"}, 0);
        searchResultsTable = new JTable(searchResultsTableModel);
        JScrollPane searchScrollPane = new JScrollPane(searchResultsTable);
        searchDrugPanel.add(searchScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("Search Drug", searchDrugPanel);

        // View All Drugs Panel
        JPanel viewAllDrugsPanel = new JPanel();
        viewAllDrugsPanel.setLayout(new BorderLayout());

        drugsTableModel = new DefaultTableModel(new Object[]{"Drug Name", "Drug Code", "Supplier Name", "Supplier Location"}, 0);
        drugsTable = new JTable(drugsTableModel);

        JScrollPane scrollPane = new JScrollPane(drugsTable);
        viewAllDrugsPanel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("View All Drugs", viewAllDrugsPanel);

        // Purchase History Panel
        JPanel purchaseHistoryPanel = new JPanel();
        purchaseHistoryPanel.setLayout(new BorderLayout());

        JTextField purchaseHistoryField = new JTextField();
        JTextArea purchaseHistoryArea = new JTextArea();
        purchaseHistoryArea.setEditable(false);

        JButton viewPurchaseHistoryButton = new JButton("View Purchase History");
        viewPurchaseHistoryButton.setPreferredSize(new Dimension(200, 30));

        JPanel purchaseHistoryInputPanel = new JPanel(new BorderLayout());
        purchaseHistoryInputPanel.add(purchaseHistoryField, BorderLayout.CENTER);
        purchaseHistoryInputPanel.add(viewPurchaseHistoryButton, BorderLayout.EAST);

        purchaseHistoryPanel.add(purchaseHistoryInputPanel, BorderLayout.NORTH);
        purchaseHistoryPanel.add(new JScrollPane(purchaseHistoryArea), BorderLayout.CENTER);

        tabbedPane.addTab("Purchase History", purchaseHistoryPanel);

        add(tabbedPane);

        // Ensure the JFrame is visible
        setVisible(true);

        // Action Listeners
        addDrugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = drugNameField.getText();
                String code = drugCodeField.getText();
                LocalDate manDate = LocalDate.parse(manDateField.getText());
                LocalDate expDate = LocalDate.parse(expDateField.getText());
                String supplierName = supplierNameField.getText();
                String supplierLocation = supplierLocationField.getText();

                ArrayList<SupplierNode> suppliers = new ArrayList<>();
                suppliers.add(new SupplierNode(supplierName, supplierLocation));

                drugNameField.setText("");
                drugCodeField.setText("");
                manDateField.setText("");
                expDateField.setText("");
                supplierNameField.setText("");
                supplierLocationField.setText("");

                pharmacySystem.addDrug(name, code, manDate, expDate, suppliers);
                JOptionPane.showMessageDialog(null, "Drug added successfully!");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                Drug drug = pharmacySystem.searchDrug(searchText);

                searchResultsTableModel.setRowCount(0); // Clear existing data

                if (drug != null) {
                    for (SupplierNode supplier : drug.getSuppliers()) {
                        searchResultsTableModel.addRow(new Object[]{
                                drug.getName(),
                                drug.getCode(),
                                supplier.getSupplierName(),
                                supplier.getLocation()
                        });
                    }
                } else {
                    searchResultsTableModel.addRow(new Object[]{"Drug not found", "", "", ""});
                }
            }
        });

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == tabbedPane.indexOfTab("View All Drugs")) {
                    ArrayList<Drug> drugs = pharmacySystem.viewAllDrugs();
                    drugsTableModel.setRowCount(0); // Clear existing data

                    for (Drug drug : drugs) {
                        for (SupplierNode supplier : drug.getSuppliers()) {
                            drugsTableModel.addRow(new Object[]{
                                    drug.getName(),
                                    drug.getCode(),
                                    supplier.getSupplierName(),
                                    supplier.getLocation()
                            });
                        }
                    }
                }
            }
        });

        viewPurchaseHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String drugCode = purchaseHistoryField.getText();
                ArrayList<PurchaseRecord> purchaseHistory = pharmacySystem.viewPurchaseHistory(drugCode);
                StringBuilder purchaseHistoryText = new StringBuilder();

                for (PurchaseRecord record : purchaseHistory) {
                    purchaseHistoryText.append("Date: ").append(record.getDate()).append(", Time: ").append(record.getTime())
                            .append(", Buyer: ").append(record.getBuyerName()).append(", Amount: ").append(record.getAmount()).append("\n");
                }

                purchaseHistoryArea.setText(purchaseHistoryText.toString());
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PharmacyManagementSystemGUI ex = new PharmacyManagementSystemGUI();
            ex.setVisible(true);
        });
    }
}
