import java.awt.*;
import java.awt.event.*;

class GradeCalculator extends Frame implements ActionListener {
    TextField[] subjectFields;
    TextField totalMarksField, averagePercentageField, gradeField;
    Button calculateButton;

    GradeCalculator() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setLayout(null);

        Label titleLabel = new Label("Enter marks obtained in each subject (out of 100)");
        titleLabel.setBounds(50, 50, 300, 20);
        add(titleLabel);

        subjectFields = new TextField[5];
        for (int i = 0; i < 5; i++) {
            Label label = new Label("Subject " + (i + 1) + ": ");
            label.setBounds(50, 80 + i * 30, 70, 20);
            add(label);

            subjectFields[i] = new TextField();
            subjectFields[i].setBounds(130, 80 + i * 30, 50, 20);
            add(subjectFields[i]);
        }

        Label totalMarksLabel = new Label("Total Marks:");
        totalMarksLabel.setBounds(50, 230, 70, 20);
        add(totalMarksLabel);

        totalMarksField = new TextField();
        totalMarksField.setBounds(130, 230, 50, 20);
        totalMarksField.setEditable(false);
        add(totalMarksField);

        Label averagePercentageLabel = new Label("Average Percentage:");
        averagePercentageLabel.setBounds(200, 230, 120, 20);
        add(averagePercentageLabel);

        averagePercentageField = new TextField();
        averagePercentageField.setBounds(330, 230, 50, 20);
        averagePercentageField.setEditable(false);
        add(averagePercentageField);

        Label gradeLabel = new Label("Grade:");
        gradeLabel.setBounds(50, 260, 50, 20);
        add(gradeLabel);

        gradeField = new TextField();
        gradeField.setBounds(130, 260, 50, 20);
        gradeField.setEditable(false);
        add(gradeField);

        calculateButton = new Button("Calculate");
        calculateButton.setBounds(200, 260, 70, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int subjects = 0;
            for (TextField field : subjectFields) {
                try {
                    int marks = Integer.parseInt(field.getText());
                    totalMarks += marks;
                    subjects++;
                } catch (NumberFormatException ex) {
                    // Ignore non-integer input
                }
            }

            if (subjects == 0) {
                totalMarksField.setText("");
                averagePercentageField.setText("");
                gradeField.setText("");
                return;
            }

            double averagePercentage = (double) totalMarks / subjects;
            totalMarksField.setText(String.valueOf(totalMarks));
            averagePercentageField.setText(String.format("%.2f", averagePercentage));

            char grade;
            if (averagePercentage >= 90) {
                grade = 'A';
            } else if (averagePercentage >= 80) {
                grade = 'B';
            } else if (averagePercentage >= 70) {
                grade = 'C';
            } else if (averagePercentage >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            gradeField.setText(String.valueOf(grade));
        }
    }

    public static void main(String[] args) {
        new GradeCalculator();
    }
}
