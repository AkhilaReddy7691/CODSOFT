import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student 
{
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) 
	{
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() 
	{
        return name;
    }

    public String getRollNumber() 
	{
        return rollNumber;
    }

    public String getGrade() 
	{
        return grade;
    }

    public void setName(String name) 
	{
        this.name = name;
    }

    public void setRollNumber(String rollNumber) 
	{
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) 
	{
        this.grade = grade;
    }

    @Override
    public String toString() 
	{
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagementSystem 
{
    private List<Student> students;

    public StudentManagementSystem() 
	{
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) 
	{
        students.add(student);
    }

    public void removeStudent(String rollNumber) 
	{
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) 
	{
        for (Student student : students) 
		{
            if (student.getRollNumber().equals(rollNumber)) 
			{
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() 
	{
        return students;
    }
}

public class StudentManagementGUI 
{
    private StudentManagementSystem system;

    public StudentManagementGUI() 
	{
        this.system = new StudentManagementSystem();
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Student Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        JButton addButton = new JButton("Add Student");
        frame.add(addButton, gbc);

        gbc.gridx = 1;
        JButton removeButton = new JButton("Remove Student");
        frame.add(removeButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        JButton searchButton = new JButton("Search Student");
        frame.add(searchButton, gbc);

        gbc.gridx = 1;
        JButton displayButton = new JButton("Display All Students");
        frame.add(displayButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        JButton saveButton = new JButton("Save to File");
        frame.add(saveButton, gbc);

        gbc.gridx = 1;
        JButton loadButton = new JButton("Load from File");
        frame.add(loadButton, gbc);

        addButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                removeStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                searchStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                displayAllStudents();
            }
        });

        saveButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                saveToFile();
            }
        });

        loadButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                loadFromFile();
            }
        });

        frame.setVisible(true);
    }

    private void addStudent() 
	{
        JTextField nameField = new JTextField(20);
        JTextField rollField = new JTextField(20);
        JTextField gradeField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Roll Number:"));
        panel.add(rollField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
		{
            String name = nameField.getText().trim();
            String rollNumber = rollField.getText().trim();
            String grade = gradeField.getText().trim();
            if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) 
			{
                system.addStudent(new Student(name, rollNumber, grade));
                JOptionPane.showMessageDialog(null, "Student added successfully!");
            } 
			else 
			{
                JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeStudent() 
	{
        String rollNumber = JOptionPane.showInputDialog("Enter Roll Number of the student to remove:");
        if (rollNumber != null) 
		{
            system.removeStudent(rollNumber.trim());
            JOptionPane.showMessageDialog(null, "Student removed successfully!");
        }
    }

    private void searchStudent() 
	{
        String rollNumber = JOptionPane.showInputDialog("Enter Roll Number of the student to search:");
        if (rollNumber != null) 
		{
            Student student = system.searchStudent(rollNumber.trim());
            if (student != null) 
			{
                JOptionPane.showMessageDialog(null, "Student found: " + student);
            } 
			else 
			{
                JOptionPane.showMessageDialog(null, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayAllStudents() 
	{
        List<Student> students = system.getAllStudents();
        StringBuilder sb = new StringBuilder();
        for (Student student : students) 
		{
            sb.append(student).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToFile() 
	{
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt"))) 
		{
            for (Student student : system.getAllStudents()) 
			{
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
            JOptionPane.showMessageDialog(null, "Data saved to file successfully!");
        } 
		catch (IOException e) 
		{
            JOptionPane.showMessageDialog(null, "Error saving to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFromFile() 
	{
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) 
		{
            String line;
            system = new StudentManagementSystem();
            while ((line = reader.readLine()) != null) 
			{
                String[] parts = line.split(",");
                if (parts.length == 3) 
				{
                    system.addStudent(new Student(parts[0], parts[1], parts[2]));
                }
            }
            JOptionPane.showMessageDialog(null, "Data loaded from file successfully!");
        } 
		catch (IOException e) 
		{
            JOptionPane.showMessageDialog(null, "Error loading from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) 
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            @Override
            public void run() 
			{
                new StudentManagementGUI();
            }
        });
    }
}


