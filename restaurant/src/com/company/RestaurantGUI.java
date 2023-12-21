package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RestaurantGUI extends JFrame {
    private JButton administratorbutton, waiterButton, receptionistButton, chefButton;
    private Map<String, String> administratorCredentials,waiterCredentials, receptionistCredentials, chefCredentials;
    private Map<String, Double> menuItems;
    private static final String WAITER_FILE = "waiterCredentials.txt";
    private static final String RECEPTIONIST_FILE = "receptionistCredentials.txt";
    private static final String CHEF_FILE = "chefCredentials.txt";
    private static final String ADMINISTRATOR_FILE = "administratorCredentials.txt";
    private Map<String, Double> orderStatus;
    private JTextArea orderStatusTextArea;


    public RestaurantGUI() {
        orderStatusTextArea = new JTextArea("Order Status:\n\n", 10, 30);
        orderStatusTextArea.setEditable(false);
        initializeFrame();
        initializeButtons();
        initializeMaps();
        checkCredentialsFile();
        checkCredentialsFile2();
        checkCredentialsFile3();
        checkCredentialsFile4();
    }

    private void initializeFrame() {
        setTitle("Restaurant Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    private void initializeButtons() {
        waiterButton = createButton("Waiter", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWaiterLogin();
            }
        });

        receptionistButton = createButton("Receptionist", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openReceptionistLogin();
            }
        });

        chefButton = createButton("Chef", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openChefLogin();
            }
        });

        administratorbutton = createButton("administrator", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openadministratorLogin();
            }
        });
        add(administratorbutton);
        add(waiterButton);
        add(receptionistButton);
        add(chefButton);
    }


    private JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        return button;
    }

    private void initializeMaps() {
        waiterCredentials = new HashMap<>();
        receptionistCredentials = new HashMap<>();
        chefCredentials = new HashMap<>();
        administratorCredentials = new HashMap<>();

        menuItems = new HashMap<>();
        menuItems.put("Pizza", 8.99);
        menuItems.put("Burger", 5.49);
        menuItems.put("Salad", 4.99);
    }

    private void checkCredentialsFile() {
        File file = new File(WAITER_FILE);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",");
                    if (parts.length == 2) {
                        waiterCredentials.put(parts[0], parts[1]);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            createFile(file);
        }
    }
    private void checkCredentialsFile2() {
        File file = new File(CHEF_FILE);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",");
                    if (parts.length == 2) {
                        chefCredentials.put(parts[0], parts[1]);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            createFile(file);
        }
    }
    private void checkCredentialsFile3() {
        File file = new File(RECEPTIONIST_FILE);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",");
                    if (parts.length == 2) {
                        receptionistCredentials.put(parts[0], parts[1]);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            createFile(file);
        }
    }
    private void checkCredentialsFile4() {
        File file = new File(ADMINISTRATOR_FILE);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",");
                    if (parts.length == 2) {
                        administratorCredentials.put(parts[0], parts[1]);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            createFile(file);
        }
    }
    private void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWaiterLogin() {
        JFrame waiterFrame = new JFrame("Waiter Login");
        waiterFrame.setSize(300, 200);

        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                if (!waiterCredentials.containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username doesn't exist. Please sign up.");
                } else {
                    String storedPassword = waiterCredentials.get(username);
                    if (storedPassword.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        openWaiterPage();
                        waiterFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect password.");
                    }
                }
            }
        });



        GroupLayout layout = new GroupLayout(waiterFrame.getContentPane());
        waiterFrame.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userLabel)
                                .addComponent(passwordLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userTextField)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(loginButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(userLabel)
                                .addComponent(userTextField)
                                .addComponent(loginButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
        );

        waiterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        waiterFrame.setVisible(true);
    }


    private void openadministratorLogin() {
        JFrame administratorFrame = new JFrame("administrator Login");
        administratorFrame.setSize(300, 200);

        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                if (!administratorCredentials.containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username doesn't exist. Please sign up.");
                } else {
                    String storedPassword = administratorCredentials.get(username);
                    if (storedPassword.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        openadministratorpage();
                        administratorFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect password.");
                    }
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                if (!administratorCredentials.containsKey(username)) {
                    administratorCredentials.put(username, password);
                    saveCredentialsToFile();
                    JOptionPane.showMessageDialog(null, "Sign up successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists. Try a different one.");
                }
            }
        });

        GroupLayout layout = new GroupLayout(administratorFrame.getContentPane());
        administratorFrame.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userLabel)
                                .addComponent(passwordLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userTextField)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(loginButton)
                                .addComponent(signupButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(userLabel)
                                .addComponent(userTextField)
                                .addComponent(loginButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField)
                                .addComponent(signupButton))
        );

        administratorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        administratorFrame.setVisible(true);
    }


    // Existing imports and class declaration remain unchanged

    private void openadministratorpage() {
        JFrame administratorPageFrame = new JFrame("Administrator Page");
        administratorPageFrame.setSize(400, 300);

        JPanel adminPanel = new JPanel(new FlowLayout());

        String[] roles = {"Waiter", "Chef", "Receptionist"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                addRole(selectedRole);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                removeRole(selectedRole);
                JOptionPane.showMessageDialog(administratorPageFrame, "Removed " + selectedRole);
            }
        });

        adminPanel.add(roleComboBox);
        adminPanel.add(addButton);
        adminPanel.add(removeButton);

        administratorPageFrame.add(adminPanel);
        administratorPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        administratorPageFrame.setVisible(true);
    }

    private void addRole(String selectedRole) {
        JFrame addRoleFrame = new JFrame("Add " + selectedRole);
        addRoleFrame.setSize(300, 200);

        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("ID:");

        JTextField nameTextField = new JTextField(15);
        JTextField idTextField = new JTextField(15);

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String id = idTextField.getText();
                saveCredentials(selectedRole, name, id);
                JOptionPane.showMessageDialog(addRoleFrame, "Saved " + selectedRole + ": Name - " + name + ", ID - " + id);
                addRoleFrame.dispose();
            }
        });

        JPanel addRolePanel = new JPanel(new GridLayout(3, 2));
        addRolePanel.add(nameLabel);
        addRolePanel.add(nameTextField);
        addRolePanel.add(idLabel);
        addRolePanel.add(idTextField);
        addRolePanel.add(saveButton);

        addRoleFrame.add(addRolePanel);
        addRoleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addRoleFrame.setVisible(true);
    }

    private void saveCredentials(String role, String name, String id) {
        String filePath = getFilePathByRole(role);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(name + "," + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeRole(String selectedRole) {
        JFrame removeRoleFrame = new JFrame("Remove " + selectedRole);
        removeRoleFrame.setSize(300, 200);

        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("ID:");

        JTextField nameTextField = new JTextField(15);
        JTextField idTextField = new JTextField(15);

        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String id = idTextField.getText();
                removeCredentials(selectedRole, name, id);
                JOptionPane.showMessageDialog(removeRoleFrame, "Removed " + selectedRole + ": Name - " + name + ", ID - " + id);
                removeRoleFrame.dispose();
            }
        });

        JPanel removeRolePanel = new JPanel(new GridLayout(3, 2));
        removeRolePanel.add(nameLabel);
        removeRolePanel.add(nameTextField);
        removeRolePanel.add(idLabel);
        removeRolePanel.add(idTextField);
        removeRolePanel.add(removeButton);

        removeRoleFrame.add(removeRolePanel);
        removeRoleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeRoleFrame.setVisible(true);
    }

    private void removeCredentials(String selectedRole, String name, String id) {
        String filePath = getFilePathByRole(selectedRole);

        try {
            File inputFile = new File(filePath);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(inputFile));

            String lineToRemove = name + "," + id;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains(lineToRemove)) {
                    writer.println(currentLine);
                }
            }
            writer.close();
            reader.close();

            boolean successfulRename = inputFile.renameTo(inputFile);
            if (!successfulRename) {
                System.out.println("Error updating credentials file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private String getFilePathByRole(String role) {
        switch (role) {
            case "Waiter":
                return WAITER_FILE;
            case "Receptionist":
                return RECEPTIONIST_FILE;
            case "Chef":
                return CHEF_FILE;
            default:
                return ""; // Handle appropriately or throw an exception
        }
    }

    private void openReceptionistLogin() {
        JFrame receptionistFrame = new JFrame("Receptionist Login");
        receptionistFrame.setSize(300, 200);

        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                if (!receptionistCredentials.containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username doesn't exist. Please sign up.");
                } else {
                    String storedPassword = receptionistCredentials.get(username);
                    if (storedPassword.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        openReceptionistPage();
                        receptionistFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect password.");
                    }
                }
            }
        });



        GroupLayout layout = new GroupLayout(receptionistFrame.getContentPane());
        receptionistFrame.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userLabel)
                                .addComponent(passwordLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userTextField)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(loginButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(userLabel)
                                .addComponent(userTextField)
                                .addComponent(loginButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
        );

        receptionistFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        receptionistFrame.setVisible(true);
    }

    private void openReceptionistPage() {
        JFrame receptionistPageFrame = new JFrame("Receptionist Page");
        receptionistPageFrame.setSize(400, 300);

        JPanel receptionistPanel = new JPanel(new GridLayout(2, 1));

        Map<Integer, Boolean> tableStatus = initializeTableStatus(); // Initialize table statuses

        JButton checkTableButton = new JButton("Check Table");
        JButton addReservationButton = new JButton("Add Reservation");

        receptionistPanel.add(checkTableButton);
        receptionistPanel.add(addReservationButton);

        checkTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display table information with availability status
                StringBuilder tableInfo = new StringBuilder("Table Information:\n");

                for (int table : tableStatus.keySet()) {
                    tableInfo.append("Table ").append(table).append(" - Max Capacity: ").append(getTableCapacity(table)).append(" - Status: ");
                    tableInfo.append(tableStatus.get(table) ? "Available\n" : "Not Available\n");
                }

                JOptionPane.showMessageDialog(receptionistPageFrame, tableInfo.toString());
            }
        });

        addReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask for max capacity for reservation
                String maxCapacityStr = JOptionPane.showInputDialog(receptionistPageFrame, "Enter Required Max Capacity:");

                if (maxCapacityStr != null && !maxCapacityStr.isEmpty()) {
                    int requiredCapacity = Integer.parseInt(maxCapacityStr);

                    // Simulate finding a suitable table based on max capacity and availability
                    int suitableTable = findSuitableTable(tableStatus, requiredCapacity);

                    if (suitableTable != -1) {
                        tableStatus.put(suitableTable, false); // Mark table as unavailable after reservation
                        JOptionPane.showMessageDialog(receptionistPageFrame, "Reserved Table " + suitableTable + " for " + requiredCapacity + " people.");
                    } else {
                        JOptionPane.showMessageDialog(receptionistPageFrame, "No suitable table available.");
                    }
                } else {
                    JOptionPane.showMessageDialog(receptionistPageFrame, "Invalid input.");
                }
            }
        });

        receptionistPageFrame.add(receptionistPanel);
        receptionistPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        receptionistPageFrame.setVisible(true);
    }

    private Map<Integer, Boolean> initializeTableStatus() {
        Map<Integer, Boolean> tableStatus = new HashMap<>();
        tableStatus.put(1, true);
        tableStatus.put(2, true);
        tableStatus.put(3, true);
        // Add more tables as needed

        return tableStatus;
    }

    private int getTableCapacity(int table) {
        // Simulated table capacities
        Map<Integer, Integer> tableCapacities = new HashMap<>();
        tableCapacities.put(1, 4);
        tableCapacities.put(2, 6);
        tableCapacities.put(3, 10);
        // Add more tables and capacities as needed

        return tableCapacities.getOrDefault(table, 0); // Return table capacity or 0 if not found
    }

    private int findSuitableTable(Map<Integer, Boolean> tableStatus, int requiredCapacity) {
        // Simulated table capacities
        Map<Integer, Integer> tableCapacities = new HashMap<>();
        tableCapacities.put(1, 4);
        tableCapacities.put(2, 6);
        tableCapacities.put(3, 10);
        // Add more tables and capacities as needed

        // Find a suitable available table based on required capacity
        for (int table : tableCapacities.keySet()) {
            if (tableCapacities.get(table) >= requiredCapacity && tableStatus.getOrDefault(table, true)) {
                return table;
            }
        }
        return -1; // Return -1 if no suitable table found or available
    }



    private void openChefLogin() {
        JFrame chefLoginFrame = new JFrame("Chef Login");
        chefLoginFrame.setSize(300, 200);

        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                if (!chefCredentials.containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username doesn't exist. Please sign up.");
                } else {
                    String storedPassword = chefCredentials.get(username);
                    if (storedPassword.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        openChefPage();
                        chefLoginFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect password.");
                    }
                }
            }
        });



        GroupLayout layout = new GroupLayout(chefLoginFrame.getContentPane());
        chefLoginFrame.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userLabel)
                                .addComponent(passwordLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userTextField)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(loginButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(userLabel)
                                .addComponent(userTextField)
                                .addComponent(loginButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
        );

        chefLoginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chefLoginFrame.setVisible(true);
    }

    private void openWaiterPage() {
        JFrame waiterPageFrame = new JFrame("Waiter Page");
        waiterPageFrame.setSize(400, 300);

        DefaultListModel<String> menuListModel = new DefaultListModel<>();
        for (String item : menuItems.keySet()) {
            menuListModel.addElement(item);
        }
        JList<String> menuList = new JList<>(menuListModel);
        JScrollPane menuScrollPane = new JScrollPane(menuList);

        DefaultListModel<String> orderListModel = new DefaultListModel<>();
        JList<String> orderList = new JList<>(orderListModel);
        JScrollPane orderScrollPane = new JScrollPane(orderList);

        JButton addItemButton = new JButton("Add Item");
        JButton placeOrderButton = new JButton("Place Order");
        JButton printBillButton = new JButton("Print Bill");

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = menuList.getSelectedValue();
                if (selectedItem != null) {
                    orderListModel.addElement(selectedItem);

                    // Notify the chef about the new order
                    notifyChef(selectedItem);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an item from the menu.");
                }
            }
        });
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> orderedItems = new ArrayList<>();
                for (int i = 0; i < orderListModel.size(); i++) {
                    orderedItems.add(orderListModel.getElementAt(i));
                }
                double totalPrice = calculateTotalPrice(orderedItems);
                JOptionPane.showMessageDialog(null, "Order placed successfully!\nTotal Price: $" + totalPrice);
                orderListModel.clear();
            }
        });

        printBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> orderedItems = new ArrayList<>();
                for (int i = 0; i < orderListModel.size(); i++) {
                    orderedItems.add(orderListModel.getElementAt(i));
                }
                double totalPrice = calculateTotalPrice(orderedItems);
                printBill(totalPrice);
            }
        });

        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(new JLabel("Menu"), BorderLayout.NORTH);
        menuPanel.add(menuScrollPane, BorderLayout.CENTER);

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.add(new JLabel("Order"), BorderLayout.NORTH);
        orderPanel.add(orderScrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));
        buttonsPanel.add(addItemButton);
        buttonsPanel.add(placeOrderButton);
        buttonsPanel.add(printBillButton);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(menuPanel);
        mainPanel.add(orderPanel);
        mainPanel.add(buttonsPanel);

        waiterPageFrame.add(mainPanel);
        waiterPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        waiterPageFrame.setVisible(true);
    }


    private double calculateTotalPrice(ArrayList<String> items) {
        double total = 0;
        for (String item : items) {
            if (menuItems.containsKey(item)) {
                total += menuItems.get(item);
            }
        }
        return total;
    }


    private void printBill(double totalPrice) {
        JFrame billFrame = new JFrame("Bill");
        billFrame.setSize(300, 200);

        JTextArea billTextArea = new JTextArea("Bill\n\nTotal Price: $" + totalPrice, 10, 20);
        billTextArea.setEditable(false);
        JScrollPane billScrollPane = new JScrollPane(billTextArea);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(billScrollPane, BorderLayout.CENTER);

        billFrame.add(mainPanel);
        billFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billFrame.setVisible(true);
    }

    private void notifyChef(String item) {
        // Simulate notifying the chef about the new order
        String orderNotification = "New Order: " + item;
        orderStatusTextArea.append(orderNotification + "\n");

        // You can further enhance this to send a notification to the chef
        // through a messaging system or other communication mechanism.
    }



    private void openChefPage() {
        JFrame chefPageFrame = new JFrame("Chef Page");
        chefPageFrame.setSize(400, 300);

        JScrollPane orderStatusScrollPane = new JScrollPane(orderStatusTextArea);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JLabel("Chef Page"), BorderLayout.NORTH);
        mainPanel.add(orderStatusScrollPane, BorderLayout.CENTER);

        chefPageFrame.add(mainPanel);
        chefPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chefPageFrame.setVisible(true);
    }



    private String getOrderStatusText() {
        StringBuilder orderStatusText = new StringBuilder("Order Status:\n\n");

        for (Map.Entry<String, Double> entry : orderStatus.entrySet()) {
            orderStatusText.append(entry.getKey()).append(" - $").append(entry.getValue()).append("\n");
        }

        return orderStatusText.toString();
    }

    private void saveCredentialsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ADMINISTRATOR_FILE, true))) {
            for (Map.Entry<String, String> entry : administratorCredentials.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RestaurantGUI());
    }
}