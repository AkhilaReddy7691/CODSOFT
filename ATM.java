import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


class BankAccount 
{
    private int balance;

    public BankAccount(int initialBalance) 
	{
        this.balance = initialBalance;
    }

    public int getBalance() 
	{
        return balance;
    }

    public void setBalance(int balance) 
	{
        this.balance = balance;
    }
}



public class ATM 
{
    private BankAccount account;

    public ATM(BankAccount account) 
	{
        this.account = account;
        createUI();
    }

    private void createUI() 
	{
        JFrame frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JLabel balanceLabel = new JLabel("Current Balance: $" + account.getBalance());
        frame.add(balanceLabel);

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        frame.add(withdrawButton);
        frame.add(depositButton);
        frame.add(checkBalanceButton);
        frame.add(exitButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                try {
                    int amount = Integer.parseInt(amountStr);
                    withdraw(amount);
                    balanceLabel.setText("Current Balance: $" + account.getBalance());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        depositButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                try 
				{
                    int amount = Integer.parseInt(amountStr);
                    deposit(amount);
                    balanceLabel.setText("Current Balance: $" + account.getBalance());
                } 
				catch (NumberFormatException ex) 
				{
                    JOptionPane.showMessageDialog(frame, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                JOptionPane.showMessageDialog(frame, "Current Balance: $" + account.getBalance());
            }
        });

        exitButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void withdraw(int amount) 
	{
        if (amount > account.getBalance()) 
		{
            JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
		else if (amount <= 0) 
		{
            JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
		else 
		{
            account.setBalance(account.getBalance() - amount);
            JOptionPane.showMessageDialog(null, "$" + amount + " withdrawn successfully.");
        }
    }

    private void deposit(int amount) 
	{
        if (amount <= 0) 
		{
            JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
		else 
		{
            account.setBalance(account.getBalance() + amount);
            JOptionPane.showMessageDialog(null, "$" + amount + " deposited successfully.");
        }
    }

    public static void main(String[] args) 
	{
        BankAccount account = new BankAccount(100000);
        new ATM(account);
    }
}
