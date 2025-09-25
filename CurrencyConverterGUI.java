import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterGUI extends JFrame {

    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField, resultField;
    private JButton convertButton;

    // Conversion rates with respect to USD (example values, update as needed)
    private static final double USD = 1.0;
    private static final double EUR = 0.94;
    private static final double INR = 83.12;
    private static final double GBP = 0.82;
    private static final double JPY = 149.50;
    private static final double AUD = 1.54;
    private static final double CAD = 1.36;

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();

        JLabel fromLabel = new JLabel("From Currency:");
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY", "AUD", "CAD"});

        JLabel toLabel = new JLabel("To Currency:");
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY", "AUD", "CAD"});

        JLabel resultLabel = new JLabel("Converted Amount:");
        resultField = new JTextField();
        resultField.setEditable(false);

        convertButton = new JButton("Convert");

        add(amountLabel);
        add(amountField);
        add(fromLabel);
        add(fromCurrency);
        add(toLabel);
        add(toCurrency);
        add(resultLabel);
        add(resultField);
        add(new JLabel()); // empty space
        add(convertButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String from = (String) fromCurrency.getSelectedItem();
                    String to = (String) toCurrency.getSelectedItem();

                    double result = convert(from, to, amount);

                    resultField.setText(String.format("%.2f", result));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number!");
                }
            }
        });
    }

    private double getRate(String currency) {
        switch (currency) {
            case "USD": return USD;
            case "EUR": return EUR;
            case "INR": return INR;
            case "GBP": return GBP;
            case "JPY": return JPY;
            case "AUD": return AUD;
            case "CAD": return CAD;
            default: return 1.0;
        }
    }

    private double convert(String from, String to, double amount) {
        double fromRate = getRate(from);
        double toRate = getRate(to);

        // Convert first to USD, then to target currency
        double inUSD = amount / fromRate;
        return inUSD * toRate;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverterGUI().setVisible(true);
        });
    }
}
