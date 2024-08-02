/*
 * TEMPERATURE CONVERSION PROGRAM
 * BY BANELE MDLULI
 * 2024-07-18
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JTextField inputField;
    private JComboBox<String> scaleBox;
    private JLabel temperatureLabel;
    private JLabel cloudLabel;

    public TemperatureConverter() {
        
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

     
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        inputPanel.add(inputField);

        String[] scales = {"Celsius", "Fahrenheit", "Kelvin"};
        scaleBox = new JComboBox<>(scales);
        inputPanel.add(scaleBox);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        inputPanel.add(convertButton);

        add(inputPanel, BorderLayout.NORTH);

      
        cloudLabel = new JLabel();
        cloudLabel.setIcon(new ImageIcon("C:\\Users\\Banele Mdluli\\OneDrive - University of Cape Town\\Documents\\clouds.jpg")); // Update with your cloud image path
        cloudLabel.setLayout(new BorderLayout());

        temperatureLabel = new JLabel("", SwingConstants.CENTER);
        temperatureLabel.setFont(new Font("Arial", Font.BOLD, 20));
        temperatureLabel.setForeground(Color.WHITE); 

        JPanel overlayPanel = new JPanel(new GridBagLayout());
        overlayPanel.setOpaque(false); 
        overlayPanel.add(temperatureLabel);

        cloudLabel.add(overlayPanel, BorderLayout.CENTER);

        add(cloudLabel, BorderLayout.CENTER);

    
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double temp = Double.parseDouble(inputField.getText());
            String scale = (String) scaleBox.getSelectedItem();

            StringBuilder result = new StringBuilder();

            switch (scale.toLowerCase()) {
                case "celsius":
                    result.append("Temperature in Kelvin: ").append(CtoKelvin(temp)).append(" K \n");
                    result.append("Temperature in Fahrenheit: ").append(CtoFahrenheit(temp)).append(" °F \n");
                    break;
                case "fahrenheit":
                    result.append("Temperature in Celsius: ").append(FtoCelsius(temp)).append(" °C \n");
                    result.append("Temperature in Kelvin: ").append(FtoKelvin(temp)).append(" K \n");
                    break;
                case "kelvin":
                    result.append("Temperature in Celsius: ").append(KtoCelsius(temp)).append(" °C \n");
                    result.append("Temperature in Fahrenheit: ").append(KtoFahrenheit(temp)).append(" °F \n");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid scale");
            }

            
            temperatureLabel.setText(result.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }

    public static double FtoKelvin(double value) {
        return roundToTwoDecimals(((value - 32) / 1.8) + 273.15);
    }

    public static double CtoKelvin(double value) {
        return roundToTwoDecimals(value + 273.15);
    }

    public static double KtoCelsius(double value) {
        return roundToTwoDecimals(value - 273.15);
    }

    public static double FtoCelsius(double value) {
        return roundToTwoDecimals((value - 32) / 1.8);
    }

    public static double KtoFahrenheit(double value) {
        return roundToTwoDecimals(((value - 273.15) * 1.8) + 32);
    }

    public static double CtoFahrenheit(double value) {
        return roundToTwoDecimals((value * 1.8) + 32);
    }

    public static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
