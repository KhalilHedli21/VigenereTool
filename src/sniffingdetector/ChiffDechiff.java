package sniffingdetector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChiffDechiff {

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String message, String key) {
        StringBuilder cipherText = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                int charIndex = alphabet.indexOf(c);
                int keyIndex = alphabet.indexOf(key.charAt(j++ % key.length()));
                int cipherIndex = (charIndex + keyIndex) % 52;
                cipherText.append(alphabet.charAt(cipherIndex));
            } else {
                cipherText.append(c);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        int j = 0;
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                int charIndex = alphabet.indexOf(c);
                int keyIndex = alphabet.indexOf(key.charAt(j++ % key.length()));
                int plainIndex = (charIndex - keyIndex + 52) % 52;
                plainText.append(alphabet.charAt(plainIndex));
            } else {
                plainText.append(c);
            }
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Encryption and Decryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel messageLabel = new JLabel("Message:");
        JTextField messageField = new JTextField(30);

        JLabel keyLabel = new JLabel("Key:");
        JTextField keyField = new JTextField(30);

        JLabel resultLabel = new JLabel("Result:");
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");

        // Adding components to the panel with GridBagConstraints
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.gridx = 0; gbc.gridy = 0; panel.add(messageLabel, gbc);
        gbc.gridx = 1; panel.add(messageField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(keyLabel, gbc);
        gbc.gridx = 1; panel.add(keyField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(resultLabel, gbc);
        gbc.gridx = 1; panel.add(scrollPane, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(encryptButton, gbc);
        gbc.gridx = 1; panel.add(decryptButton, gbc);

        frame.add(panel, BorderLayout.CENTER);

        // Action listeners for buttons
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                String key = keyField.getText();
                if (!key.isEmpty()) {
                    String cipherText = encrypt(message, key);
                    resultArea.setText(cipherText);
                } else {
                    JOptionPane.showMessageDialog(frame, "Key cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cipherText = messageField.getText();
                String key = keyField.getText();
                if (!key.isEmpty()) {
                    String plainText = decrypt(cipherText, key);
                    resultArea.setText(plainText);
                } else {
                    JOptionPane.showMessageDialog(frame, "Key cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
